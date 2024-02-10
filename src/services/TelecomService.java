package services;

import models.Contract;
import models.Customer;
import models.Subscription;

import java.util.List;

public interface TelecomService {
    void createCustomer();
    boolean updateCustomer(Customer customer);

    boolean deleteCustomerById(int id);

    Customer findCustomerById(int id);

    List<Customer> findAllCustomers();

    void createContract();
    boolean updateContract(Contract contract);

    boolean deleteContractById(int id);

    Contract findContractById(int id);

    List<Contract> findAllContracts();

    void createSubscription();
    boolean updateSubscription(Subscription subscription);
    boolean deleteSubscriptionById(int id);

    Subscription findSubscriptionById(int id);

    List<Subscription> findAllSubscriptions();
}
