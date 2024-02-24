package services.impl;

import models.Contract;
import models.Customer;
import models.Product;
import models.Subscription;
import models.contacts.Contact;
import repositories.*;
import repositories.impl.*;
import services.TelecomService;

import java.util.List;
import java.util.Optional;

public class TelecomServiceImpl implements TelecomService {
    private CustomerRepository customerRepository = new CustomerJpaRepository();;
    private ContractRepository contractRepository = new ContractJpaRepository();;
    private SubscriptionRepository subscriptionRepository = new SubscriptionJpaRepository();

    private ContactRepository contactRepository = new ContactJpaRepository();

    private ProductRepository  productRepository = new ProductJpaRepository();

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.create(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public boolean deleteCustomerById(int id) {
        return customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<List<Customer>> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void createContract(Contract contract) {
        contractRepository.create(contract);
    }

    @Override
    public boolean updateContract(Contract contract) {
        return contractRepository.update(contract);
    }

    @Override
    public boolean deleteContractById(int id) {
        return contractRepository.deleteById(id);
    }

    @Override
    public Optional<Contract> findContractById(int id) {
        return contractRepository.findById(id);
    }

    @Override
    public Optional<List<Contract>> findAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void createSubscription(Subscription subscription) {
        subscriptionRepository.create(subscription);
    }

    @Override
    public boolean updateSubscription(Subscription subscription) {
        return subscriptionRepository.update(subscription);
    }

    @Override
    public boolean deleteSubscriptionById(int id) {
        return subscriptionRepository.deleteById(id);
    }

    @Override
    public Optional<Subscription> findSubscriptionById(int id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public Optional<List<Subscription>> findAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void createContact(Contact contact) {
        contactRepository.create(contact);
    }

    public Optional<List<Product>> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<List<Product>> findProductsCheaperThanX(int price) {
        return productRepository.findCheaperThanX(price);
    }

    @Override
    public Optional<List<Product>> findProductsExpiringInXDays(int days) {
        return productRepository.findExpiringInXDays(days);
    }

    @Override
    public Optional<List<Subscription>> findSubscribersForProduct(Product product) {
        return subscriptionRepository.findSubscribersForProduct(product);
    }
}
