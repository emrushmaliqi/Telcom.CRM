package Entities;

import Entities.Services.ServiceType;
import java.time.LocalDateTime;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private List<ServiceType> serviceTypes;

}
