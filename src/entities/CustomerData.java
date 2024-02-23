package entities;

import enums.CustomerType;
import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Contract;
import models.contacts.Contact;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CustomerData.findById", query = "SELECT c FROM CustomerData c WHERE c.id = :id"),
        @NamedQuery(name = "CustomerData.findAll", query = "SELECT c FROM CustomerData c")
})
public class CustomerData {
    @Id
    private int id;

    @Enumerated(value = EnumType.STRING)
    private CustomerType type;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(value = EnumType.ORDINAL)
    private State state;

    @OneToOne
    private ContactData contact;


    @OneToMany(mappedBy = "customer")
    private List<ContractData> contracts;
}
