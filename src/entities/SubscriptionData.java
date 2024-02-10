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

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactData contact;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "SUBSCRIPTION_PRODUCT",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<ProductData> products;
}
