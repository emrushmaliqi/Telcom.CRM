package entities;

import enums.OptionalServiceType;
import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String phoneNumber;


    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @ManyToOne
    private ContractData contract;

    @ManyToMany
    private List<ProductData> products;


    @ElementCollection
    @CollectionTable(
            name = "SUBSCRIPTION_SERVICE_TYPE", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.ORDINAL)
    private List<OptionalServiceType> serviceTypes;

    @OneToOne
    private ContactData contact;

}
