package com.bookstore.dao;

import com.bookstore.entity.Category;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CategoryDAOTest extends BaseDAOTest {

    private static CategoryDAO categoryDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        BaseDAOTest.setUpBeforeClass();
        categoryDAO = new CategoryDAO(entityManager);
    }

    @Test
    public void testCreateCategory(){
        Category newCategory = new Category("Java");
        Category category = categoryDAO.create(newCategory);

        assertTrue(category != null && category.getCategoryId() > 0);
    }

    @Test
    public void testUpdateCategory(){
        Category category = new Category("Java Core");
        category.setCategoryId(1);

        Category categoryUpdated = categoryDAO.update(category);

        assertEquals(category.getName(), categoryUpdated.getName());
    }

    @Test
    public void testGetCategory(){
        Integer categoryId = 13;
        Category cat = categoryDAO.get(categoryId);

        assertNotNull(cat);
    }

    @Test
    public void testDeleteCategory(){
        Integer categoryId = 11;
        categoryDAO.delete(categoryId);

        Category category = categoryDAO.get(categoryId);
        assertNull(category);
    }

    @Test
    public void testListAll(){
        List<Category> listOfCategories = categoryDAO.listAll();
        assertTrue(listOfCategories.size() > 0);
    }

    @Test
    public void testCountAll(){
        long totalCategories = categoryDAO.count();

        assertEquals(2, totalCategories);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        BaseDAOTest.tearDownAfterClass();
    }
}
