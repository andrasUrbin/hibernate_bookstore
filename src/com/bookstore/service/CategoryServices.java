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

    public void listCategories() throws ServletException, IOException {
        List<Category> listOfCategories = categoryDAO.listAll();

        request.setAttribute("listCategory", listOfCategories);

        String listPage = "category_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

        requestDispatcher.forward(request, response);
    }
}
