package models.contacts;

import enums.ContactType;
import enums.State;
import models.Contract;

import java.util.Date;
import java.util.Objects;

public abstract class Contact {
    private int id;
    private ContactType type;
    private Date createdDate;
    private State state;

    protected Contact(int id, ContactType type, Date createdDate, State state) {
        this.id = id;
        this.type = type;
        this.createdDate = createdDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

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
