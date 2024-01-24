package Entities.Contacts;

import Enums.ContactType;
import Enums.State;

import java.util.Date;

public class BusinessContact extends Contact{
    private String customerName;

    public BusinessContact(long id, ContactType idType, Date createdDate, State state, String customerName) {
        super(id, idType, createdDate, state);
        this.customerName = customerName;
    }
}
