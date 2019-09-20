package com.bookstore.service;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserServices {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private UserDAO userDAO;

	public UserServices(){
	    entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	    entityManager = entityManagerFactory.createEntityManager();
	    userDAO = new UserDAO(entityManager);
    }

    public void listUser(){
	    List<Users> listUsers = userDAO.listAll();
    }
}
