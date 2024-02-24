package mappers;

import entities.ContactData;
import entities.ContractData;
import entities.ProductData;
import entities.SubscriptionData;
import models.Contract;
import models.Product;
import models.Subscription;
import models.contacts.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionMapper {

    private static ContactMapper contactMapper = new ContactMapper();
    private static ProductMapper productMapper = new ProductMapper();
    private  static ContractMapper contractMapper = new ContractMapper();


    public SubscriptionData toSubscriptionData(Subscription subscription) {
        ContactData contactData = contactMapper.toContactData(subscription.getContact());
        ContractData contractData = contractMapper.toContractData(subscription.getContract());
        List<ProductData> productDataList = productMapper.toProductData(subscription.getProducts());

        return new SubscriptionData(subscription.getId(), subscription.getPhoneNumber(), subscription.getCreatedDate(), subscription.getState(), contractData,productDataList, subscription.getServiceTypes(), contactData);
    }

    public List<SubscriptionData> toSubscriptionData(List<Subscription> subscriptionList) {
        return subscriptionList.stream().map(this::toSubscriptionData).collect(Collectors.toList());
    }

    public Subscription fromSubscriptionData(SubscriptionData sd) {
        Contact contact = contactMapper.fromContactData(sd.getContact());
        Contract contract = contractMapper.fromContractData(sd.getContract(), false);
        List<Product> productDataList = productMapper.fromProductData(sd.getProducts());

        return new Subscription(sd.getId(), sd.getPhoneNumber(), sd.getCreatedDate(), sd.getState(), contract, productDataList, sd.getServiceTypes(), contact);

    }

    public List<Subscription> fromSubscriptionData(List<SubscriptionData> subscriptionDataList) {
        return subscriptionDataList.stream().map(this::fromSubscriptionData).collect(Collectors.toList());
    }
}
