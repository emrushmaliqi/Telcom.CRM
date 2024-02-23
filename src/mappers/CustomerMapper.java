package mappers;

import entities.ContactData;
import entities.ContractData;
import entities.CustomerData;
import models.Contract;
import models.Customer;
import models.contacts.Contact;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerMapper {

    private static ContactMapper contactMapper = new ContactMapper();
    private static ContractMapper contractMapper = new ContractMapper();

    public CustomerData toCustomerData(Customer customer) {
        ContactData contactData = contactMapper.toContactData(customer.getContact());
        List<ContractData> contractDataList = contractMapper.toContractData(customer.getContracts());

        return new CustomerData(customer.getId(), customer.getType(), customer.getCreatedDate(), customer.getState(),contactData,  contractDataList);
    }

    public List<CustomerData> toCustomerData(List<Customer> customerList) {
        return customerList.stream().map(this::toCustomerData).collect(Collectors.toList());
    }

    public Customer fromCustomerData(CustomerData cd) {
        Contact contact = contactMapper.fromContactData(cd.getContact());
        List<Contract> contractList = contractMapper.fromContractData(cd.getContracts());
        return new Customer(cd.getId(), cd.getType(), cd.getCreatedDate(), cd.getState(), contact, contractList);
    }

    public List<Customer> fromCustomerData(List<CustomerData> customerDataList) {
        return customerDataList.stream().map(this::fromCustomerData).collect(Collectors.toList());
    }
}
