package models.services;

import enums.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Product;
import java.util.List;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Service {

    @Setter(AccessLevel.NONE)
    private int id;
    private ServiceType type;
    private Date createdDate;
    private State state;
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Service has " +
                "id=" + id +
                ", type=" + type +
                ", createdDate=" + createdDate +
                ", state=" + state +
                ", products=" + products;
    }
}
