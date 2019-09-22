package com.bookstore.service;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServices {
    private EntityManager entityManager;
    private CategoryDAO categoryDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.entityManager = entityManager;
        categoryDAO = new CategoryDAO(entityManager);
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
}
