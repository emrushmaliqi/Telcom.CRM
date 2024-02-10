package models;

import models.contacts.Contact;
import enums.ContractType;
import enums.State;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Contract {
    private int id;
    private ContractType type;
    private Date createdDate;
    private State state;
    private List<Subscription> subscriptions;
    private Contact contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
