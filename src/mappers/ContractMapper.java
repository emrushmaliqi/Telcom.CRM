package mappers;

import entities.ContactData;
import entities.ContractData;
import entities.CustomerData;
import entities.SubscriptionData;
import models.Contract;
import models.Customer;
import models.Subscription;
import models.contacts.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContractMapper {
    private ContactMapper contactMapper;
    private SubscriptionMapper subscriptionMapper;

    private CustomerMapper customerMapper;

    public ContractMapper() {
        contactMapper = new ContactMapper();
        subscriptionMapper = new SubscriptionMapper();
        customerMapper = new CustomerMapper();
    }

    public ContractData toContractData(Contract contract) {
        ContactData contactData = contactMapper.toContactData(contract.getContact());
        List<SubscriptionData> suDataList = subscriptionMapper.toSubscriptionData(contract.getSubscriptions());
        CustomerData customerData = customerMapper.toCustomerData(contract.getCustomer());

        return new ContractData(contract.getId(), contract.getType(), contract.getCreatedDate(), contract.getState(), suDataList, customerData, contactData);
    }

    public List<ContractData> toContractData(List<Contract> contractList) {
        return contractList.stream().map(this::toContractData).collect(Collectors.toList());
    }

    public Contract fromContractData(ContractData cd) {
        Contact contact = contactMapper.fromContactData(cd.getContact());
        List<Subscription> subscriptionList = subscriptionMapper.fromSubscriptionData(cd.getSubscriptions());
        Customer customer = customerMapper.fromCustomerData(cd.getCustomer());

        return new Contract(cd.getId(), cd.getType(), cd.getCreatedDate(), cd.getState(), subscriptionList, customer, contact);
    }

    public List<Contract> fromContractData(List<ContractData> contractDataList) {
        return contractDataList.stream().map(this::fromContractData).collect(Collectors.toList());
    }
}
