package models.services;

import enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Product;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Service {


    private long id;
    private ServiceType type;
    private Date createdDate;
    private State state;
    private List<Product> products;

}
