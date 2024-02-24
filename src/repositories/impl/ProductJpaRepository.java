package repositories.impl;

import entities.CustomerData;
import entities.ProductData;
import exceptions.CustomerException;
import exceptions.ProductException;
import jakarta.persistence.NoResultException;
import mappers.CustomerMapper;
import mappers.ProductMapper;
import models.Customer;
import models.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repositories.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductJpaRepository implements ProductRepository {

    private final ProductMapper mapper = new ProductMapper();
    private final SessionFactory sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();

    @Override
    public void create(Product product) {
        try(Session session = sessionFactory.openSession()) {
            ProductData productData = mapper.toProductData(product);
            Transaction trx = session.beginTransaction();
            session.merge(productData);
            trx.commit();
        }
        catch (HibernateException e) {
            throw new ProductException(e);
        }
    }

    @Override
    public boolean update(Product product) {
        try (Session session = sessionFactory.openSession()) {
            ProductData productData = mapper.toProductData(product);
            Transaction trx = session.beginTransaction();
            session.merge(productData);
            trx.commit();
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ProductException(e);
        }

        return true;
    }

    @Override
    public boolean deleteById(int id) {
        try(Session session = sessionFactory.openSession()) {
            ProductData productData = session.createNamedQuery("ProductData.findById", ProductData.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if(Objects.nonNull(productData)) {
                session.remove(productData);
                return true;
            }
            return false;
        }
        catch(NoResultException nre) {
            return false;
        }
        catch(HibernateException e) {
            throw new ProductException(e);
        }
    }

    @Override
    public Optional<Product> findById(int id) {
        Optional<Product> product;
        try(Session session = sessionFactory.openSession()) {
            ProductData productData = session.createNamedQuery("ProductData.findById", ProductData.class)
                    .setParameter("id", id)
                    .getSingleResult();
            product = Optional.ofNullable(mapper.fromProductData(productData));
        }
        catch (NoResultException nre) {
            return Optional.empty();
        }
        catch(HibernateException e) {
            throw new ProductException(e);
        }
        return product;
    }

    @Override
    public Optional<List<Product>> findAll() {
        Optional<List<Product>> productList;
        try(Session session = sessionFactory.openSession()) {
            List<ProductData> resultList = session.createNamedQuery("ProductData.findAll", ProductData.class)
                    .getResultList();
            productList = Optional.ofNullable(mapper.fromProductData(resultList));
        }
        catch(HibernateException e) {
            throw new ProductException(e);
        }
        return productList;
    }
}
