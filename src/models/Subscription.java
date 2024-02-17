package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.contacts.Contact;
import models.services.Service;
import models.services.ServiceType;
import enums.State;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Subscription {
    private int id;
    private String phoneNumber;
    private Date createdDate;
    private State state;

    private List<Product> products;
    private Contact contact;

}
