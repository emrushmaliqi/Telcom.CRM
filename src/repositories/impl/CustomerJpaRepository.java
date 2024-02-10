package repositories.impl;

import models.Customer;
import exceptions.CustomerException;
import mappers.CustomerMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repositories.CustomerRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerJpaRepository implements CustomerRepository {

    private CustomerMapper mapper = new CustomerMapper();
    private SessionFactory sessionFactory;
    public CustomerJpaRepository() {
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass().buildSessionFactory();
        }
        catch (HibernateException e) {
            throw new CustomerException(e);
        }
    }


    @Override
    public void create() {

    }

    @Override
    public boolean update(Customer type) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }
}
