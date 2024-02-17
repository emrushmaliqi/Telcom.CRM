package entities;
import enums.ContactType;
import enums.ContractType;
import enums.State;
import jakarta.persistence.*;
import lombok.*;
import models.Customer;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTRACT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ContractData.findById", query = "SELECT c FROM ContractData c WHERE c.id = :id"),
        @NamedQuery(name = "ContractData.findAll", query = "SELECT c FROM ContractData c")
})
public class ContractData {
    @Id
    private int id;

    @Enumerated(value = EnumType.STRING)
    private ContractType type;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(value = EnumType.ORDINAL)
    private State state;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscriptionData> subscriptions;

    @ManyToOne
    @JoinColumn(name = "id")
    private CustomerData customer;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private ContactData contact;
}
