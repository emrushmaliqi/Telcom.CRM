package entities;

import enums.ContactType;
import enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@IdClass(ContactId.class)
@Table(name = "CONTACT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ContactData.findById", query = "SELECT c FROM ContactData c WHERE c.id = :id AND c.type = :type"),
        @NamedQuery(name = "ContactData.findAll", query = "SELECT c FROM ContactData c")
})
public class ContactData {
    @Id
    private int id;

    @Id
    @Enumerated(value = EnumType.STRING)
    private ContactType type;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(value = EnumType.ORDINAL)
    private State state;

    @Column
    private String customerName;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

}

