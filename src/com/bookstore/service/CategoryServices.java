package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServices {
    private CategoryDAO categoryDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        categoryDAO = new CategoryDAO();
    }

    public void listCategories(String message) throws ServletException, IOException {
        List<Category> listOfCategories = categoryDAO.listAll();

        request.setAttribute("listCategory", listOfCategories);
        if(message != null) {
            request.setAttribute("message", message);
        }
        String listPage = "category_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

        requestDispatcher.forward(request, response);
    }

    public void listCategories() throws ServletException, IOException {
        listCategories(null);
    }
    
    public void createCategory() throws ServletException, IOException {
        String name = request.getParameter("name");
        Category existingCategory = categoryDAO.findByName(name);

        if(existingCategory != null){
            String message = "Could not create category! A category with the name: " + name + " already exists.";
            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        }else{
            Category newCategory = new Category(name);
            categoryDAO.create(newCategory);
            String message = "New category created successfully";
            listCategories(message);
        }
    }


    public void editCategory() throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.get(categoryId);

        String destPage = "category_form.jsp";

        if (category != null) {
            request.setAttribute("category", category);
        } else {
            String message = "Could not find category with ID: " + categoryId;
            request.setAttribute("message", message);
            destPage = "message.jsp";
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request, response);

    }

    public void updateCategory() throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String categoryName = request.getParameter("name");
        
        Category categoryById = categoryDAO.get(categoryId);

        Category categoryByName = categoryDAO.findByName(categoryName);

        if(categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()){
            String message = "Could not update category! Category with name: " + categoryName + " already exists!";
            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
            requestDispatcher.forward(request, response);
        }else{
            categoryById.setName(categoryName);
            categoryDAO.update(categoryById);

            String message = "Category has been updated successfully!";
            listCategories(message);
        }
    }

    public void deleteCategory() throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        String message = "";
        BookDAO bookDAO = new BookDAO();
        long numOfBooks = bookDAO.countByCategory(categoryId);

        Category category = categoryDAO.get(categoryId);

        if (category == null) {
            message = "Could not find category with ID " + categoryId
                    + ", or it might have been deleted";
            request.setAttribute("message", message);
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }else if(numOfBooks>0){
            message = "Could not delete category with Id: %d" + categoryId + ", because it contains some books!";
            message = String.format(message, numOfBooks);
        } else {
            categoryDAO.delete(categoryId);
            message = "Category has been deleted successfully!";
        }
        listCategories(message);
    }
}
