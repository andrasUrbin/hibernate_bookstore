package com.bookstore.dao;

import com.bookstore.entity.Book;

import java.util.Date;
import java.util.List;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

    public BookDAO() {
    }
    
    @Override
	public Book create(Book book) {
    	book.setLastUpdateTime(new Date());
    	return super.create(book);
	}

	@Override
    public Book update(Book book){
        book.setLastUpdateTime(new Date());
        return super.update(book);
    }

	@Override
    public Book get(Object id) {
        return super.find(Book.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Book.class, id);
    }

    @Override
    public List<Book> listAll() {
        return super.findWithNamedQuery("Book.findAll");
    }

    public Book findByTitle(String title){
        List<Book> results = super.findWithNamedQuery("Book.findByTitle", "title", title);

        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }

    public List<Book> listByCategory(int categoryId){
        return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
    }

    public List<Book> listNewBooks(){
        return super.findWithNamedQuery("Book.listNew", 0, 4);
    }

    public List<Book> search(String keyword){
        return super.findWithNamedQuery("Book.search", "keyword", keyword);
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Book.countAll");
    }

    public long countByCategory(int categoryId) {
        return 1l;
    }
}
