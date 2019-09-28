package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookDAOTest extends BaseDAOTest{
	private static BookDAO bookDAO;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDAO = new BookDAO(entityManager);
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();

		Category category = new Category("Java v2.0");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations and autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/cemo/eclipse-workspace/BookStoreWebsite/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		newBook.setImage(imageBytes);

		Book createdBook = bookDAO.create(newBook);

		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testGetBook(){
		int bookId = 2;
		Book book = bookDAO.get(bookId);

		assertNotNull(book);
	}

	@Test
	public void testListAllNotEmpty(){
		List<Book> listOfBooks = bookDAO.listAll();

		assertFalse(listOfBooks.isEmpty());
	}

	@Test
	public void findByTitleNotExists(){
		String title = "Sajt";
		Book book = bookDAO.findByTitle(title);

		assertNull(book);
	}

	@Test
	public void findByTitleExists(){
		String title = "Effective Java (2nd Edition)";
		Book book = bookDAO.findByTitle(title);

		assertNotNull(book);
	}

	@Test
	public void testCountAllBooks(){
		long totalNumOfBooks = bookDAO.count();
		
		assertEquals(1, totalNumOfBooks);
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
