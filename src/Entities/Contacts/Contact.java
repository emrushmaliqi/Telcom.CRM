package Entities.Contacts;

import Enums.ContactType;
import Enums.State;

import java.util.Date;
import java.util.Objects;

public abstract class Contact {
    private long id;
    private ContactType idType;
    private Date createdDate;
    private State state;

    protected Contact(long id, ContactType idType, Date createdDate, State state) {
        this.id = id;
        this.idType = idType;
        this.createdDate = createdDate;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContactType getIdType() {
        return idType;
    }

    public void setIdType(ContactType idType) {
        this.idType = idType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(!(o instanceof Contact)) {
            return false;
        }

        Contact contact = (Contact) o;
        return contact.id == id && contact.idType == idType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idType);
    }
}
