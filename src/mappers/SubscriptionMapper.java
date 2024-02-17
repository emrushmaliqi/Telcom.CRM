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

    private ContactMapper contactMapper;
    private ProductMapper productMapper;

    public SubscriptionMapper() {
        contactMapper = new ContactMapper();
        productMapper = new ProductMapper();
    }

    public SubscriptionData toSubscriptionData(Subscription subscription) {
        ContactData contactData = contactMapper.toContactData(subscription.getContact());
        List<ProductData> productDataList = productMapper.toProductData(subscription.getProducts());

        return new SubscriptionData(subscription.getId(), subscription.getPhoneNumber(), subscription.getCreatedDate(), subscription.getState(),productDataList, contactData);
    }

    public List<SubscriptionData> toSubscriptionData(List<Subscription> subscriptionList) {
        return subscriptionList.stream().map(this::toSubscriptionData).collect(Collectors.toList());
    }

    public Subscription fromSubscriptionData(SubscriptionData sd) {
        Contact contact = contactMapper.fromContactData(sd.getContact());
        List<Product> productDataList = productMapper.fromProductData(sd.getProducts());

        return new Subscription(sd.getId(), sd.getPhoneNumber(), sd.getCreatedDate(), sd.getState(), productDataList, contact);

    }

    public List<Subscription> fromSubscriptionData(List<SubscriptionData> subscriptionDataList) {
        return subscriptionDataList.stream().map(this::fromSubscriptionData).collect(Collectors.toList());
    }
}
