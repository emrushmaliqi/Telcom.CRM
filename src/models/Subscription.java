package models;

import exceptions.SubscriptionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.contacts.Contact;
import models.services.Service;
import models.services.ServiceType;
import enums.State;
import models.services.SimCard;
import models.services.Voice;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class Subscription {
    private int id;
    private String phoneNumber;
    private Date createdDate;
    private State state;
    private List<Product> products;
    private Contact contact;


    public Subscription(int id, String phoneNumber, Date createdDate, State state, List<Product> products, Contact contact) throws SubscriptionException {
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
        this.products = products;
        this.contact = contact;
    }



    public Subscription(int id, String phoneNumber, Date createdDate, State state, Contact contact) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
        this.contact = contact;
    }

    public List<Subscription> getSubscribersForProduct(List<Subscription> subscriptions, int productId) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getProducts().stream().anyMatch(product -> product.getId() == productId))
                .collect(Collectors.toList());
    }


}
