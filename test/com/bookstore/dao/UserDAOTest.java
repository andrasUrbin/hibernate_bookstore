package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

import java.util.List;

public class UserDAOTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass(){
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("123");
		user1.setFullName("123");
		user1.setPassword("123");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
        user1 = userDAO.create(user1);

	}

	@Test
	public void testGetUsersFound(){
		Integer userId = 21;
		Users user = userDAO.get(userId);
		assertNotNull(user);
	}

	@Test
	public void testDeleteUsers(){
		Integer userId = 20;
		userDAO.delete(userId);

		Users user = userDAO.get(userId);
		assertNull(user);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistingUsers(){
		Integer userId = 5;
		userDAO.delete(userId);
	}

	@Test
	public void testListAll(){
		List<Users> listUsers = userDAO.listAll();
		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testCountAll(){
		long allUsers = userDAO.count();
		assertEquals(6, allUsers);
	}

	@AfterClass
	public static void tearDownClass() {
		entityManager.close();
		entityManagerFactory.close();
	}
}