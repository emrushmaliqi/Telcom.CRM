package entities;

import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.contacts.Contact;
import models.services.Service;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SUBSCRIPTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "SubscriptionData.findById", query = "SELECT s FROM SubscriptionData s WHERE s.id = :id"),
        @NamedQuery(name = "SubscriptionData.findAll", query = "SELECT s FROM SubscriptionData s")
})
public class SubscriptionData {

    @Id
    private int id;

    @Column
    private String phoneNumber;


    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "SUBSCRIPTION_PRODUCT",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<ProductData> products;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactData contact;
}
