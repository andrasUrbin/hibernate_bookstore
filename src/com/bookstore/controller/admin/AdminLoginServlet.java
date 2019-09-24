package com.bookstore.controller.admin;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServices userServices = new UserServices(entityManager, request, response);
        userServices.login();
    }
}
