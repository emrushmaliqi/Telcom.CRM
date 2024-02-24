package repositories.impl;

import entities.ContractData;
import entities.SubscriptionData;
import exceptions.ContractException;
import exceptions.SubscriptionException;
import jakarta.persistence.NoResultException;
import mappers.SubscriptionMapper;
import models.Subscription;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.SubscriptionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionJpaRepository implements SubscriptionRepository {
    private SubscriptionMapper mapper = new SubscriptionMapper();

    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();


    @Override
    public void create(Subscription subscription) {
        try(Session session = sessionFactory.openSession()) {
            SubscriptionData subscriptionData = mapper.toSubscriptionData(subscription);
            Transaction trx = session.beginTransaction();
            session.merge(subscriptionData);
            trx.commit();
        }
        catch (HibernateException e) {
            throw new SubscriptionException(e);
        }
    }

    @Override
    public boolean update(Subscription subscription) {
        try (Session session = sessionFactory.openSession()) {
            SubscriptionData subscriptionData = mapper.toSubscriptionData(subscription);
            Transaction trx = session.beginTransaction();
            session.merge(subscriptionData);
            trx.commit();
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new SubscriptionException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
    		SubscriptionData subscriptionData = session.createNamedQuery("SubscriptionData.findById", SubscriptionData.class)
    				.setParameter("id", id)
    				.getSingleResult();
    		if(Objects.nonNull(subscriptionData)) {
                Transaction trx = session.beginTransaction();
                session.remove(subscriptionData);
                trx.commit();
                return true;
            }
            return false;
    	}
    	catch(NoResultException nre) {
    		return false;
    	}
    	catch(HibernateException e) {
    		throw new SubscriptionException(e);
    	}
    }

    @Override
    public Optional<Subscription> findById(int id) {
        Optional<Subscription> subscription;
        try(Session session = sessionFactory.openSession()) {
            SubscriptionData subscriptionData = session.createNamedQuery("SubscriptionData.findById", SubscriptionData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            subscription = Optional.ofNullable(mapper.fromSubscriptionData(subscriptionData));
        }
        catch(NoResultException nre) {
            return Optional.empty();
        }
        catch(HibernateException e) {
            throw new SubscriptionException(e);
        }

        return subscription;
    }

    @Override
    public Optional<List<Subscription>> findAll() {
        Optional<List<Subscription>> subscriptionList;
        try(Session session = sessionFactory.openSession()) {
            List<SubscriptionData> resultList = session.createNamedQuery("SubscriptionData.findAll", SubscriptionData.class)
                    .getResultList();
            subscriptionList = Optional.ofNullable(mapper.fromSubscriptionData(resultList));
        }
        catch(HibernateException e) {
            throw new SubscriptionException(e);
        }
        return subscriptionList;
    }
}
