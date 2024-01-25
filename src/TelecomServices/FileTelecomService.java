package TelecomServices;

import Entities.Contract;
import Entities.Customer;
import Entities.Subscription;

import java.util.List;

public class FileTelecomService implements TelecomService {

    @Override
    public void createCustomer() {

    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer findCustomerById(long id) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }

    @Override
    public void createContract() {

    }

    @Override
    public boolean updateContract(Contract contract) {
        return false;
    }

    @Override
    public boolean deleteContract(Contract contract) {
        return false;
    }

    @Override
    public Contract findContractById(long id) {
        return null;
    }

    @Override
    public List<Contract> findAllContracts() {
        return null;
    }

    @Override
    public void createSubscription() {

    }

    @Override
    public boolean updateSubscription(Subscription subscription) {
        return false;
    }

    @Override
    public boolean deleteSubscription(Subscription subscription) {
        return false;
    }

    @Override
    public Subscription findSubscriptionById(long id) {
        return null;
    }

    @Override
    public List<Subscription> findAllSubscriptions() {
        return null;
    }
}

