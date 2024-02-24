package entities;

import enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.services.ServiceType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SERVICE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ServiceData.findById", query = "SELECT s FROM ServiceData s WHERE s.id = :id"),
        @NamedQuery(name = "ServiceData.findAll", query = "SELECT s FROM ServiceData s")
})
public class ServiceData {
    @Id
    private int id;

    private double data;

    private int minutes;

    private int messages;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @ManyToMany(mappedBy = "services")
    private List<ProductData> products;
}
