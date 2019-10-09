package com.bookstore.dao;

import com.bookstore.entity.Customer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

    @Override
    public Customer create(Customer customer) {
        customer.setRegisterDate(new Date());
        return super.create(customer);
    }

    @Override
    public Customer get(Object id) {
        return super.find(Customer.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Customer.class, id);
    }

    @Override
    public List<Customer> listAll() {
        return super.findWithNamedQuery("Customer.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Customer.countAll");
    }

    public Customer findByEmail(String email){
        List<Customer> resultList = super.findWithNamedQuery("Customer.findByEmail", "email", email);
        if(!resultList.isEmpty()){
            return resultList.get(0);
        }
        return null;
    }

    public Customer checkLogin(String email, String password){
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("pass", password);

        List<Customer> result = super.findWithNamedQuery("Customer.checkLogin", params);
        if(!result.isEmpty()){
            return result.get(0);
        }

        return null;
    }
}
