package models.contacts;

import enums.ContactType;
import enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IndividualContact extends Contact{
    private String name;
    private String lastName;
    private Date dateOfBirth;

    public IndividualContact(int id, ContactType idType, Date createdDate, State state, String name, String lastName, Date dateOfBirth) {
        super(id, idType, createdDate, state);
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Individual Contact id=" + getId() + ", type=" + getType() + ", createdDate=" + getCreatedDate() + ", state=" + getState() + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth ;
    }
}
