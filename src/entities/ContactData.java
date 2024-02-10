package entities;

import enums.ContactType;
import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CONTACT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactData {
    @Id
    private int id;

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

