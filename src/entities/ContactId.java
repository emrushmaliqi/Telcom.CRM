package entities;

import enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ContactId implements Serializable {
    private int id;
    private ContactType type;

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
