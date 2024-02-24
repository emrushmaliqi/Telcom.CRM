package services;

import models.Contract;
import models.Customer;
import models.Product;
import models.Subscription;
import models.contacts.Contact;

import java.util.List;
import java.util.Optional;

public interface TelecomService {
    void createCustomer(Customer customer);
    boolean updateCustomer(Customer customer);

    boolean deleteCustomerById(int id);

    Optional<Customer> findCustomerById(int id);

    Optional<List<Customer>> findAllCustomers();

    void createContract(Contract contract);
    boolean updateContract(Contract contract);

    boolean deleteContractById(int id);

    Optional<Contract> findContractById(int id);

    Optional<List<Contract>> findAllContracts();

    void createSubscription(Subscription subscription);
    boolean updateSubscription(Subscription subscription);
    boolean deleteSubscriptionById(int id);

    Optional<Subscription> findSubscriptionById(int id);

    Optional<List<Subscription>> findAllSubscriptions();


    void createContact(Contact contact);

    Optional<List<Product>> findAllProducts();
}
