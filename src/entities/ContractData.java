package entities;
import enums.ContactType;
import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTRACT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractData {
    @Id
    private int id;

    @Enumerated(value = EnumType.STRING)
    private ContactType type;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(value = EnumType.ORDINAL)
    private State state;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscriptionData> subscriptions;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactData contact;
}
