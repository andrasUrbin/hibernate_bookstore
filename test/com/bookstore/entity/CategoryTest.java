package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {
	public static void main(String[] args) {
        
		Category newCategory = new Category("Core java");
		
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(newCategory);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("A category object was created and persisted");
    }
}
