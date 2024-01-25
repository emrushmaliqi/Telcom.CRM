package TelecomServices;

import Entities.Contract;
import Entities.Customer;
import Entities.Subscription;

import java.util.List;

public interface TelecomService {
    void createCustomer();
    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(Customer customer);

    Customer findCustomerById(long id);

    List<Customer> findAllCustomers();

    void createContract();
    boolean updateContract(Contract contract);

    boolean deleteContract(Contract contract);

    Contract findContractById(long id);

    List<Contract> findAllContracts();

    void createSubscription();
    boolean updateSubscription(Subscription subscription);
    boolean deleteSubscription(Subscription subscription);

    Subscription findSubscriptionById(long id);

    List<Subscription> findAllSubscriptions();
}
