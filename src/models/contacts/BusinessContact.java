package models.contacts;

import enums.ContactType;
import enums.State;

import java.util.Date;

public class BusinessContact extends Contact{
    private String customerName;

    public BusinessContact(int id, ContactType idType, Date createdDate, State state, String customerName) {
        super(id, idType, createdDate, state);
        this.customerName = customerName;
    }
}
