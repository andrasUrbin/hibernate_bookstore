package com.bookstore.entity;
import com.bookstore.entity.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {
    public static void main(String[] args) {
        Users user1 = new Users();
        user1.setEmail("1234@gmail.com");
        user1.setFullName("Test case v2");
        user1.setPassword("hello");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("A user object was created and persisted");
    }
}
