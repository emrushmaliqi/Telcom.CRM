package repositories.impl;

import entities.ServiceData;
import exceptions.ServiceException;
import jakarta.persistence.NoResultException;
import mappers.ServiceMapper;
import models.services.Service;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.ServiceRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceJpaRepository implements ServiceRepository {

    private ServiceMapper mapper = new ServiceMapper();
    private SessionFactory sessionFactory;

    public ServiceJpaRepository() {
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ServiceData.class).buildSessionFactory();
        }
        catch (HibernateException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public void create(Service service) {
        try(Session session = sessionFactory.openSession()) {
            ServiceData serviceData = mapper.toServiceData(service);
            Transaction trx = session.beginTransaction();
            session.persist(serviceData);
            trx.commit();
        }
        catch (HibernateException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Service service) {
        try (Session session = sessionFactory.openSession()) {
            ServiceData serviceData = mapper.toServiceData(service);
            Transaction trx = session.beginTransaction();
            session.merge(serviceData);
            trx.commit();
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ServiceException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
            ServiceData serviceData = session.createNamedQuery("ServiceData.findById", ServiceData.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if(Objects.nonNull(serviceData)) {
                Transaction trx = session.getTransaction();;
                session.remove(serviceData);
                trx.commit();
                return true;
            }
            return false;
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Service> findById(int id) {
        Optional<Service> service;
        try(Session session = sessionFactory.openSession()) {
            ServiceData serviceData = session.createNamedQuery("ServiceData.findById", ServiceData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            service = Optional.ofNullable(mapper.fromServiceData(serviceData));
        }
        catch (NoResultException nre) {
            return Optional.empty();
        }
        catch(HibernateException e) {
            throw new ServiceException(e);
        }
        return service;
    }

    @Override
    public Optional<List<Service>> findAll() {
        Optional<List<Service>> serviceList;
        try(Session session = sessionFactory.openSession()) {
            List<ServiceData> resultList = session.createNamedQuery("ServiceData.findAll", ServiceData.class)
                    .getResultList();
            serviceList = Optional.ofNullable(mapper.fromServiceData(resultList));
        }
        catch(HibernateException e) {
            throw new ServiceException(e);
        }
        return serviceList;
    }
}
