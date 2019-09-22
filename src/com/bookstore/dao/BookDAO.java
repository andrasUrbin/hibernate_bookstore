package com.bookstore.dao;

import com.bookstore.entity.Book;

import javax.persistence.EntityManager;
import java.util.List;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

    public BookDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Book get(Object id) {
        return null;
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Book> listAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    public long countByCategory(int categoryId) {
        return 1l;
    }
}
