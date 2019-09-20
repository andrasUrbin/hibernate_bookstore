package com.bookstore.dao;

import com.bookstore.entity.Users;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Users create(Users user){
        return super.create(user);
    }

    @Override
    public Users update(Users user) {
        return super.update(user);
    }

    @Override
    public Users get(Object userId) {
        return super.find(Users.class, userId);
    }

    @Override
    public void delete(Object id) {
        super.delete(Users.class, id);
    }

    @Override
    public List<Users> listAll() {
        return super.findWithNamedQuery("Users.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Users.countAll");
    }
}
