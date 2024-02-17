package repositories.impl;

import entities.ContractData;
import exceptions.ContractException;
import jakarta.persistence.NoResultException;
import mappers.ContactMapper;
import mappers.ContractMapper;
import models.Contract;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.ContractRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractJpaRepository implements ContractRepository {
    private ContractMapper mapper = new ContractMapper();

    private SessionFactory sessionFactory;

    public ContractJpaRepository() {
        try {
            Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ContractData.class).buildSessionFactory();
        }
        catch (HibernateException e) {
            throw new ContractException(e);
        }
    }
    @Override
    public void create(Contract type) {
        try(Session session = sessionFactory.openSession()) {
            ContractData contractData = mapper.toContractData(type);
            Transaction trx = session.beginTransaction();
            session.persist(contractData);
            trx.commit();
        }
        catch(HibernateException e) {
            throw new ContractException(e);
        }
    }

    @Override
    public boolean update(Contract type) {
        try(Session session = sessionFactory.openSession()) {
            ContractData contractData = mapper.toContractData(type);
            Transaction trx = session.beginTransaction();
            session.merge(contractData);
            trx.commit();
        }
        catch (NoResultException nre) {
            return false;
        }
        catch (HibernateException e) {
            throw new ContractException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
            ContractData contractData = session.createNamedQuery("ContractData.findById", ContractData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if(Objects.nonNull(contractData)) {
                Transaction trx = session.getTransaction();
                session.remove(contractData);
                trx.commit();
                return true;
            }
            return false;
        }
        catch (NoResultException e) {
            return false;
        }
        catch (HibernateException e) {
            throw new ContractException(e);
        }
    }

    @Override
    public Optional<Contract> findById(int id) {
        Optional<Contract> contract;
        try(Session session = sessionFactory.openSession()) {
            ContractData contractData = session.createNamedQuery("ContractData.findById", ContractData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            contract = Optional.ofNullable(mapper.fromContractData(contractData));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
        catch (HibernateException e) {
            throw new ContractException(e);
        }
        return contract;
    }

    @Override
    public Optional<List<Contract>> findAll() {
        Optional<List<Contract>> contractList;
        try(Session session = sessionFactory.openSession()) {
            List<ContractData> resultList = session.createNamedQuery("ContractData.findAll", ContractData.class)
                    .getResultList();
            contractList = Optional.ofNullable(mapper.fromContractData(resultList));
        }
        catch (HibernateException e) {
            throw new ContractException(e);
        }
        return contractList;
    }
}
