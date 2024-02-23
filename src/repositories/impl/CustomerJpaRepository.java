package repositories.impl;

import entities.ContractData;
import entities.CustomerData;
import jakarta.persistence.NoResultException;
import models.Customer;
import exceptions.CustomerException;
import mappers.CustomerMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerJpaRepository implements CustomerRepository {
    private CustomerMapper mapper = new CustomerMapper();

    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();

    @Override
    public void create(Customer customer) {
        try(Session session = sessionFactory.openSession()) {
            CustomerData customerData = mapper.toCustomerData(customer);
            Transaction trx = session.beginTransaction();
            session.persist(customerData);
            trx.commit();
        }
        catch (HibernateException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public boolean update(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            CustomerData customerData = mapper.toCustomerData(customer);
            Transaction trx = session.beginTransaction();
            session.merge(customerData);
            trx.commit();
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new CustomerException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
            CustomerData customerData = session.createNamedQuery("CustomerData.findById", CustomerData.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if(Objects.nonNull(customerData)) {
                Transaction trx = session.getTransaction();;
                session.remove(customerData);
                trx.commit();
                return true;
            }
            return false;
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        Optional<Customer> customer;
        try(Session session = sessionFactory.openSession()) {
            CustomerData customerData = session.createNamedQuery("CustomerData.findById", CustomerData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            customer = Optional.ofNullable(mapper.fromCustomerData(customerData));
        }
        catch (NoResultException nre) {
            return Optional.empty();
        }
        catch(HibernateException e) {
           throw new CustomerException(e);
        }
        return customer;
    }

    @Override
    public Optional<List<Customer>> findAll() {
        Optional<List<Customer>> customerList;
        try(Session session = sessionFactory.openSession()) {
            List<CustomerData> resultList = session.createNamedQuery("CustomerData.findAll", CustomerData.class)
                    .getResultList();
            customerList = Optional.ofNullable(mapper.fromCustomerData(resultList));
        }
        catch(HibernateException e) {
            throw new CustomerException(e);
        }
        return customerList;
    }
}
