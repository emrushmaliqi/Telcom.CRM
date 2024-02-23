package repositories.impl;

import entities.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum HibernateUtil {
    INSTANCE;

    private SessionFactory sessionFactory;
    private HibernateUtil() {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(CustomerData.class)
                .addAnnotatedClass(ContractData.class)
                .addAnnotatedClass(SubscriptionData.class)
                .addAnnotatedClass(ProductData.class)
                .addAnnotatedClass(ServiceData.class)
                .addAnnotatedClass(ContactData.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
