package models;

import lombok.*;
import models.contacts.Contact;
import enums.CustomerType;
import enums.State;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Customer {
    // constructor i krym

    @Setter(AccessLevel.NONE)
    private int id;
    private CustomerType type;
    private Date createdDate;
    private State state;
    private Contact contact;
    private List<Contract> contracts;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
