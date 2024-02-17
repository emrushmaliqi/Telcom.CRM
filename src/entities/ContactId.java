package entities;

import enums.ContactType;

import java.io.Serializable;
import java.util.Objects;

public class ContactId implements Serializable {
    private int id;
    private ContactType type;

    public ContactId(int id, ContactType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactId contactId = (ContactId) o;
        return id == contactId.id && type == contactId.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
