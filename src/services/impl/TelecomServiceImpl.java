package services.impl;

import models.Contract;
import models.Customer;
import models.Subscription;
import repositories.ContractRepository;
import repositories.CustomerRepository;
import repositories.SubscriptionRepository;
import repositories.factories.RepositoryFactory;
import services.TelecomService;

import java.util.List;
import java.util.Optional;

public class TelecomServiceImpl implements TelecomService {
    private CustomerRepository customerRepository;
    private ContractRepository contractRepository;
    private SubscriptionRepository subscriptionRepository;

    public TelecomServiceImpl() {
        customerRepository = RepositoryFactory.getRepository(CustomerRepository.class);
        contractRepository = RepositoryFactory.getRepository(ContractRepository.class);
        subscriptionRepository = RepositoryFactory.getRepository(SubscriptionRepository.class);
    }

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
}
