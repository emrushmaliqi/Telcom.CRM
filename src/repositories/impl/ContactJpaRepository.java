package repositories.impl;

import entities.*;
import entities.ContactData;
import entities.ContactData;
import entities.ContactData;
import exceptions.ContactException;
import exceptions.ContactException;
import exceptions.ContactException;
import exceptions.ContactException;
import jakarta.persistence.NoResultException;
import mappers.ContactMapper;
import models.contacts.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.ContactRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContactJpaRepository implements ContactRepository {

    private ContactMapper mapper = new ContactMapper();

    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
    @Override
    public void create(Contact contact) {
        try(Session session = sessionFactory.openSession()) {
            ContactData contactData = mapper.toContactData(contact);
            Transaction trx = session.beginTransaction();
            session.persist(contactData);
            trx.commit();
        }
        catch (HibernateException e) {
            throw new ContactException(e);
        }
    }

    @Override
    public boolean update(Contact contact) {
        try (Session session = sessionFactory.openSession()) {
            ContactData contactData = mapper.toContactData(contact);
            Transaction trx = session.beginTransaction();
            session.merge(contactData);
            trx.commit();
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ContactException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
            ContactData contactData = session.createNamedQuery("ContactData.findById", ContactData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if(Objects.nonNull(contactData)) {
                Transaction trx = session.getTransaction();;
                session.remove(contactData);
                trx.commit();
                return true;
            }
            return false;
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ContactException(e);
        }
    }

    @Override
    public Optional<Contact> findById(int id) {
        Optional<Contact> contact;
        try(Session session = sessionFactory.openSession()) {
            ContactData contactData = session.createNamedQuery("ContactData.findById", ContactData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            contact = Optional.ofNullable(mapper.fromContactData(contactData));
        }
        catch (NoResultException nre) {
            return Optional.empty();
        }
        catch(HibernateException e) {
            throw new ContactException(e);
        }
        return contact;
    }

    @Override
    public Optional<List<Contact>> findAll() {
        Optional<List<Contact>> contactList;
        try(Session session = sessionFactory.openSession()) {
            List<ContactData> resultList = session.createNamedQuery("ContactData.findAll", ContactData.class)
                    .getResultList();
            contactList = Optional.ofNullable(mapper.fromContactData(resultList));
        }
        catch(HibernateException e) {
            throw new ContactException(e);
        }
        return contactList;
    }
}
