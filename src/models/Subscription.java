package models;

import enums.OptionalServiceType;
import enums.OptionalServiceType;
import exceptions.SubscriptionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.contacts.Contact;
import models.services.Service;
import enums.State;
import models.services.SimCard;
import models.services.Voice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class Subscription {
    @Setter(AccessLevel.NONE)
    private int id;
    @Setter(AccessLevel.NONE)
    private String phoneNumber;
    private Date createdDate;
    private State state;
    private Contract contract;
    private List<Product> products;
    private List<OptionalServiceType> serviceTypes;
    private Contact contact;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final String phoneNumberRegex = "\\+3834[456][1-9]\\d{5}";

    public Subscription(int id, String phoneNumber, Date createdDate, State state, Contract contract, List<Product> products, List<OptionalServiceType> serviceTypes, Contact contact) throws SubscriptionException {
        if(!phoneNumber.matches(phoneNumberRegex))
            throw new SubscriptionException("Phone number supported format is: (+3834(4|5|6){1-9}xxxxx)");

        boolean hasSim = false;
        boolean hasVoice = false;

        for (Product product : products) {
            for (Service service : product.getServices()) {
                if (service.getType() instanceof SimCard) hasSim = true;
                if (service.getType() instanceof Voice) hasVoice = true;
            }
        }
        if(!(hasSim && hasVoice))
            throw new SubscriptionException("Subscription must have a SimCard and Voice service");

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
        this.contract = contract;
        this.products = products;
        this.serviceTypes = serviceTypes;
        this.contact = contact;
    }


    public Subscription(int id, String phoneNumber, Date createdDate, State state, Contract contract, List<Product> products, Contact contact) throws SubscriptionException {
        this(id, phoneNumber, createdDate, state, contract, products, new ArrayList<>(), contact);
    }

    public void setPhoneNumber(String phoneNumber) throws SubscriptionException {
        if(!phoneNumber.matches(phoneNumberRegex))
            throw new SubscriptionException("Phone number supported format is: (+3834(4|5|6){1-9}xxxxx)");
        this.phoneNumber = phoneNumber;
    }

    public List<Subscription> getSubscribersForProduct(List<Subscription> subscriptions, int productId) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getProducts().stream().anyMatch(product -> product.getId() == productId))
                .collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return "Subscription has id=" + id + ", phoneNumber='" + phoneNumber + '\'' + ", createdDate=" + createdDate + ", state=" + state + ", contract=" + contract + ", products=" + products + ", serviceTypes=" + serviceTypes + ", contact=" + contact;
    }

}
