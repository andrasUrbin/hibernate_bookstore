package com.bookstore.service;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.bookstore.service.CommonUtility.forwardToPage;
import static com.bookstore.service.CommonUtility.showMessageBackend;

public class CustomerServices {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private CustomerDAO customerDAO;

    public CustomerServices(HttpServletRequest request, HttpServletResponse response){
        super();
        this.request = request;
        this.response = response;
        this.customerDAO = new CustomerDAO();
    }

    public void listCustomers(String message) throws ServletException, IOException {
        List<Customer> listCustomer = customerDAO.listAll();
        if(message != null){
            request.setAttribute("message", message);
        }

        request.setAttribute("listCustomer", listCustomer);

        String listPage = "customer_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }

    public void listCustomers() throws ServletException, IOException {
        listCustomers(null);
    }

    public void createCustomer() throws ServletException, IOException {
        String email = request.getParameter("email");
        Customer customerExists = customerDAO.findByEmail(email);
        String message = "";

        if(customerExists != null){
            message = "Could not create new customer! Email: " + email + " is already in use!";
            listCustomers(message);
        }else{
            Customer newCustomer = new Customer();
            updateCustomerFields(newCustomer);
            customerDAO.create(newCustomer);

            message = "Customer created successfully!";
            listCustomers(message);
        }
    }

    public void editCustomer() throws ServletException, IOException {
        Integer customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.get(customerId);

        if (customer == null) {
            String message = "Could not find customer with ID " + customerId;
            showMessageBackend(message, request, response);
        } else {
            request.setAttribute("customer", customer);
            forwardToPage("customer_form.jsp", request, response);
        }
    }

    public void updateCustomer() throws ServletException, IOException {
        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        String email = request.getParameter("email");

        Customer existsCustomer = customerDAO.findByEmail(email);
        String message = "";

        if(existsCustomer != null && existsCustomer.getCustomerId() != customerId){
            message = "Could not update the customer with the ID: " + customerId + ", because an already existing customer has the same email!";
        } else{
            Customer customerById = customerDAO.get(customerId);

            updateCustomerFields(customerById);
            customerDAO.update(customerById);

            message = "Customer updated successfully!";
        }
        listCustomers(message);
    }

    public void deleteCustomer() throws ServletException, IOException {
        Integer customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.get(customerId);

        if (customer != null) {
            customerDAO.delete(customerId);

            String message = "The customer has been deleted successfully.";
            listCustomers(message);
        } else {
            String message = "Could not find customer with ID " + customerId + ", "
                    + "or it has been deleted by another admin";
            showMessageBackend(message, request, response);
        }
    }

    public void registerCustomer() throws ServletException, IOException {
        String email = request.getParameter("email");
        Customer customerExists = customerDAO.findByEmail(email);
        String message = "";

        if(customerExists != null){
            message = "Could not register new customer! Email: " + email + " is already in use!";
        }else{
            Customer newCustomer = new Customer();
            updateCustomerFields(newCustomer);
            customerDAO.create(newCustomer);

            message = "You have registered successfully!<br>"
            + "<a href='login'>Click here to login!</a>";
        }

        String editPage = "frontend/message.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
        request.setAttribute("message", message);
        dispatcher.forward(request, response);
    }

    private void updateCustomerFields(Customer customer){
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipCode");
        String country = request.getParameter("country");

        customer.setEmail(email);
        customer.setFullname(fullName);
        customer.setPassword(password);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setZipcode(zipCode);
        customer.setCountry(country);

    }
}
