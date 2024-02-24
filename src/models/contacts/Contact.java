package models.contacts;

import enums.ContactType;
import enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Contract;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public abstract class Contact {
    private int id;
    private ContactType type;
    private Date createdDate;
    private State state;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
