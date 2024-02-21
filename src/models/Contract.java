package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import lombok.Setter;
import models.contacts.Contact;
import enums.ContractType;
import enums.State;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Getter
@Setter
@AllArgsConstructor
public class Contract {
    private int id;
    private ContractType type;
    private Date createdDate;
    private State state;
    private List<Subscription> subscriptions;
    private Customer customer;
    private Contact contact;

    public List<Product> getProductsCheaperThanX(List<Product> products, double x) {
        return products.stream()
                .filter(product -> product.getPrice() < x)
                .collect(Collectors.toList());
    }
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
