package Entities;

import Entities.Contacts.Contact;
import Entities.Services.Service;
import Entities.Services.ServiceType;
import Enums.State;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class Subscription {
    private long id;
    private String phoneNumber;
    private Date currentDate;
    private State state;
    private Dictionary<ServiceType, Service> services;
    private Contact contact;
    public Subscription() {
    }
}
