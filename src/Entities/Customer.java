package Entities;

import Entities.Contacts.Contact;
import Enums.CustomerType;
import Enums.State;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer {
    private long id;
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
