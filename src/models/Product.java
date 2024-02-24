package models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.services.Service;
import repositories.impl.ProductJpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private double price;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private List<Service> services;

    private List<Subscription> subscriptions;

    public List<Product> getProductsCheaperThanX(List<Product> products, double x) {
        return products.stream()
                .filter(product -> product.getPrice() < x)
                .collect(Collectors.toList());
    }
    public List<Product> getProductsExpiringInXDays(List<Product> products, int x) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime xDaysLater = now.plusDays(x);

        return products.stream()
                .filter(product -> product.getToDateTime().isAfter(now) && product.getToDateTime().isBefore(xDaysLater))
                .collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return "Product has id=" + id + ", name='" + name + '\'' + ", price=" + price + ", fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime + ", services=" + services + ", subscriptions=" + subscriptions ;
    }
}
