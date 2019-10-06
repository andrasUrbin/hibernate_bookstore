package com.bookstore.service;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookServices {
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BookServices(HttpServletRequest request, HttpServletResponse response) {
        super();
        this.request = request;
        this.response = response;
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
    }

    public void listBooks(String message) throws ServletException, IOException {
        List<Book> listOfBooks = bookDAO.listAll();
        request.setAttribute("listBooks", listOfBooks);

        if(message != null){
            request.setAttribute("message", message);
        }

        String listPage = "book_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }

    public void listBooks() throws ServletException, IOException {
        List<Book> listOfBooks = bookDAO.listAll();
        request.setAttribute("listBooks", listOfBooks);

        String listPage = "book_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }

    public void showNewBookForm() throws ServletException, IOException {
        List<Category> listCategory = categoryDAO.listAll();
        request.setAttribute("listCategory", listCategory);

        String newPage = "book_form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
        requestDispatcher.forward(request, response);
    }

    public void createBook() throws ServletException, IOException {
        Book newBook = new Book();
        readBookFields(newBook);

        Book existingBook = bookDAO.findByTitle(newBook.getTitle());
        if(existingBook != null){
            String message = "Could not create new book, because the title: " + newBook.getTitle() + " already exists.";
            listBooks(message);
            return;
        }

        Book createdBook = bookDAO.create(newBook);

        if(createdBook.getBookId() > 0){
            String message = "A new book was created successfully!";
            listBooks(message);
        }
    }

    public void editBook() throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.get(bookId);
        String destPage = "book_form.jsp";

        if (book != null) {
            List<Category> listCategory = categoryDAO.listAll();

            request.setAttribute("book", book);
            request.setAttribute("listCategory", listCategory);

        } else {
            destPage = "message.jsp";
            String message = "Could not find book with ID " + bookId;
            request.setAttribute("message", message);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request, response);

    }

    public void readBookFields(Book book) throws ServletException, IOException {
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String isbn = request.getParameter("isbn");
        float price = Float.parseFloat(request.getParameter("price"));
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date publishDate = null;

        try{
            publishDate = dateFormat.parse(request.getParameter("publishDate"));
        }catch(ParseException ex) {
            ex.printStackTrace();
            throw new ServletException("Error parsing publish date data! MM/dd/yyyy format required!");
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setPublishDate(publishDate);
        book.setPrice(price);

        Category bookCategory = categoryDAO.get(categoryId);
        book.setCategory(bookCategory);

        Part part = request.getPart("bookImage");
        if(part != null && part.getSize() > 0){
            long size = part.getSize();
            byte[] imageBytes = new byte[(int) size];

            InputStream inputStream = part.getInputStream();
            inputStream.read(imageBytes);
            inputStream.close();

            book.setImage(imageBytes);
        }
    }

    public void updateBook() throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Book existingBook = bookDAO.get(bookId);
        String title = request.getParameter("title");
        Book bookByTitle = bookDAO.findByTitle(title);

        /*if(!existingBook.equals(bookByTitle)){
            String message = "Could not update the book, because another book has the same title!";
            listBooks(message);
            return;
        }*/

        readBookFields(existingBook);
        bookDAO.update(existingBook);

        String message = "The book has been updated successfully!";

        listBooks(message);
    }

    public void deleteBook() throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("id"));
        Book book =  bookDAO.get(bookId);

        if (book == null) {
            String message = "Could not find book with ID " + bookId
                    + ", or it might have been deleted";
            request.setAttribute("message", message);
            request.getRequestDispatcher("message.jsp").forward(request, response);

        } else {
            String message = "The book has been deleted successfully.";
            bookDAO.delete(bookId);
            listBooks(message);
        }
    }

    public void listBooksByCategory() throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.get(categoryId);

        if (category == null) {
            String message = "Sorry, the category ID " + categoryId + " is not available.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("frontend/message.jsp").forward(request, response);

            return;
        }

        List<Book> listBooks = bookDAO.listByCategory(categoryId);

        request.setAttribute("listBooks", listBooks);
        request.setAttribute("category", category);

        String listPage = "frontend/books_list_by_category.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);
    }

    public void viewBookDetail() throws ServletException, IOException {
        Integer bookId = Integer.parseInt(request.getParameter("id"));
        Book book = bookDAO.get(bookId);

        String destPage = "frontend/book_detail.jsp";

        if (book != null) {
            request.setAttribute("book", book);

        } else {
            destPage = "frontend/message.jsp";
            String message = "Sorry, the book with ID " + bookId + " is not available.";
            request.setAttribute("message", message);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
        requestDispatcher.forward(request, response);
    }

    public void search() throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Book> result = null;

        if(keyword.equals("")){
            result = bookDAO.listAll();
        } else {
            result = bookDAO.search(keyword);
        }

        request.setAttribute("keyword", keyword);
        request.setAttribute("result", result);

        String resultPage = "frontend/search_result.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
        requestDispatcher.forward(request, response);
    }
}
