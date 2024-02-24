package mappers;

import entities.ContactData;
import entities.ContractData;
import entities.CustomerData;
import entities.SubscriptionData;
import models.Contract;
import models.Customer;
import models.Subscription;
import models.contacts.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContractMapper {
    private static ContactMapper contactMapper = new ContactMapper();
    private static SubscriptionMapper subscriptionMapper = new SubscriptionMapper();
    private static CustomerMapper customerMapper = new CustomerMapper();

    public ContractData toContractData(Contract contract) {
        ContactData contactData = contactMapper.toContactData(contract.getContact());
        List<SubscriptionData> suDataList = subscriptionMapper.toSubscriptionData(contract.getSubscriptions());
        CustomerData customerData = customerMapper.toCustomerData(contract.getCustomer());

        return new ContractData(contract.getId(), contract.getType(), contract.getCreatedDate(), contract.getState(), suDataList, customerData, contactData);
    }

    public List<ContractData> toContractData(List<Contract> contractList) {
        return contractList.stream().map(this::toContractData).collect(Collectors.toList());
    }

    public Contract fromContractData(ContractData cd, boolean includeData) {
        Contact contact = contactMapper.fromContactData(cd.getContact());
        List<Subscription> subscriptionList = includeData ?  subscriptionMapper.fromSubscriptionData(cd.getSubscriptions()) : new ArrayList<>();
        Customer customer = customerMapper.fromCustomerData(cd.getCustomer(), false);

        return new Contract(cd.getId(), cd.getType(), cd.getCreatedDate(), cd.getState(), subscriptionList, customer, contact);
    }

    public List<Contract> fromContractData(List<ContractData> contractDataList) {
        return contractDataList.stream().map(x -> fromContractData(x, false)).collect(Collectors.toList());
    }
}
