package models;

import models.services.Service;
import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private double price;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private List<Service> services;

    private List<Subscription> subscriptions;

}
