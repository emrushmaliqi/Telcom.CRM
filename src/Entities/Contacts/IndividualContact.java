package Entities.Contacts;

import Enums.ContactType;
import Enums.State;

import java.util.Date;

public class IndividualContact extends Contact{
    private String name;
    private String lastName;
    private Date dateOfBirth;

    public IndividualContact(long id, ContactType idType, Date createdDate, State state, String name, String lastName, Date dateOfBirth) {
        super(id, idType, createdDate, state);
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
