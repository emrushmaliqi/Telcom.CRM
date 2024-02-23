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
public class ServiceData {
    @Id
    private long id;

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
