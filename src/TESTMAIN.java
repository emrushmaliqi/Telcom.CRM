import entities.*;
import enums.*;
import exceptions.ContractException;
import models.Contract;
import models.Customer;
import models.Product;
import models.Subscription;
import models.contacts.BusinessContact;
import models.contacts.Contact;
import models.contacts.IndividualContact;
import models.services.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositories.impl.HibernateUtil;
import services.TelecomService;
import services.impl.TelecomServiceImpl;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class TESTMAIN {
    private static TelecomService telecomService = new TelecomServiceImpl();
    public static void main(String[] args) {
//        Date currDate = Date.from(new Date().toInstant().plusSeconds(60*60*24*365*10));
//        Customer customer = new Customer(12345, CustomerType.BUSINESS, currDate, State.ACTIVE, new BusinessContact(123456, ContactType.CU, currDate, State.ACTIVE, "JKNFAJNSFJNAFN"), new ArrayList<>());
//        telecomService.createCustomer(customer);

        try(Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
//            ContactData contactData = new ContactData(123456, ContactType.CU, new Date(), State.ACTIVE, "JKNFAJNSFJNAFN", null, null, null);


            ContactData contactData = new ContactData(21414, ContactType.CU, new Date(), State.ACTIVE, "fksnajfnafajns", null, null, null);
            ContactData contactData2 = new ContactData(214364, ContactType.SU, new Date(), State.INACTIVE, "fksnasfi9ijns", null, null, null);
            ContactData contactData3 = new ContactData(1124414, ContactType.SU, new Date(), State.ACTIVE, null, "fjsanjfnsa", "ajfnsakjfna", new Date());
            ContactData contactData4 = new ContactData(11999414, ContactType.SU, new Date(), State.ACTIVE, null, "fjsanjfnsa", "ajfnsakjfna", new Date());
            ContactData contactData5 = new ContactData(114, ContactType.CO, new Date(), State.DEACTIVE, null, "fjsanjfnsa", "ajfnsakjfna", new Date());
            ContactData contactData6 = new ContactData(10, ContactType.CO, new Date(), State.ACTIVE, "COMPASFMASF", null, null, null);
            CustomerData customer = new CustomerData(124425, CustomerType.BUSINESS, Date.from(new Date().toInstant().plusSeconds(60*60*24*365*10)), State.INACTIVE, contactData, null);
            ContractData c1 = new ContractData(1, ContractType.POSTPAID, new Date(), State.ACTIVE, null, customer, contactData5);
            ContractData c2 = new ContractData(2, ContractType.PREPAID, new Date(), State.ACTIVE, null, customer, contactData6);

            List<OptionalServiceType > serviceTypes1 = new ArrayList<>(Arrays.asList(OptionalServiceType.DATA, OptionalServiceType.SMS));
            List<OptionalServiceType > serviceTypes2 = new ArrayList<>(Collections.singletonList(OptionalServiceType.DATA));
            SubscriptionData s1 = new SubscriptionData(1, "JKNFAJNSFJNAFN", new Date(), State.ACTIVE, c1, new ArrayList<>(), serviceTypes1, contactData2);
            SubscriptionData s2 = new SubscriptionData(2, "fsa241SFJNAFN", new Date(), State.ACTIVE, c1, new ArrayList<>(), serviceTypes2, contactData3);
            SubscriptionData s3 = new SubscriptionData(24, "JKNooNSFJNAFN", new Date(), State.ACTIVE, c2, new ArrayList<>(), serviceTypes2, contactData4);

            ProductData p1 = new ProductData(1, "JKNFAJNSFJNAFN", 42.5, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), null);
            ProductData p2 = new ProductData(3, "JKNFAJNSFJNAFN", 42.5, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), null);
            ProductData p3 = new ProductData(4, "JKNFAJNSFJNAFN", 42.5, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), null);
            ProductData p4 = new ProductData(5, "JKNFAJNSFJNAFN", 42.5, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), null);

            ServiceData se1 = new ServiceData(1, -1, -1, -1, new Date(), State.ACTIVE, null);
            ServiceData se2 = new ServiceData(2, 24, -1, -1, new Date(), State.ACTIVE, null);
            ServiceData se3 = new ServiceData(3, -1, 523, -1, new Date(), State.ACTIVE, null);
            ServiceData se4 = new ServiceData(4, -1, -1, -1, new Date(), State.ACTIVE, null);
            ServiceData se5 = new ServiceData(5, -1, -1, 2414, new Date(), State.ACTIVE, null);

            p1.getServices().addAll(Arrays.asList(se1, se2, se5));
            p2.getServices().addAll(Arrays.asList(se3, se5));
            p4.getServices().addAll(Arrays.asList(se4, se2, se5, se3));

            s1.getProducts().add(p1);
            s1.getProducts().add(p2);
            s2.getProducts().add(p3);
            s2.getProducts().add(p4);
            s3.getProducts().add(p1);
            s3.getProducts().add(p3);

            session.persist(contactData);
            session.persist(contactData2);
            session.persist(contactData3);
            session.persist(contactData4);
            session.persist(contactData5);
            session.persist(contactData6);
            session.persist(customer);
            session.persist(c1);
            session.persist(c2);
            session.persist(s1);
            session.persist(s2);
            session.persist(s3);
            session.persist(p1);
            session.persist(p2);
            session.persist(p3);
            session.persist(p4);
            session.persist(se1);
            session.persist(se2);
            session.persist(se3);
            session.persist(se4);
            session.persist(se5);
            Transaction trx = session.beginTransaction();



                trx.commit();

//            session.persist(contactData);
        }
        catch(HibernateException e) {
            throw new ContractException(e);
        }
    }
}
