package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.services.Service;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ProductData.findById", query = "SELECT p FROM ProductData p WHERE p.id = :id"),
        @NamedQuery(name = "ProductData.findAll", query = "SELECT p FROM ProductData p"),
        @NamedQuery(name = "ProductData.findCheaperThanX", query = "SELECT p FROM ProductData p WHERE p.price < :price"),
        @NamedQuery(name = "ProductData.findExpiringInXDays", query = "SELECT p FROM ProductData p WHERE :days < p.toDateTime")
})
public class ProductData {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private double price;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fromDateTime;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime toDateTime;

    @ManyToMany
    private List<ServiceData> services;


    @ManyToMany(mappedBy = "products")
    private List<SubscriptionData> subscriptions;
}
