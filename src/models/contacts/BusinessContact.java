package models.contacts;

import enums.ContactType;
import enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BusinessContact extends Contact{
    private String customerName;

    public BusinessContact(int id, ContactType idType, Date createdDate, State state, String customerName) {
        super(id, idType, createdDate, state);
        this.customerName = customerName;
    }
}
