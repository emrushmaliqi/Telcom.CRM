package mappers;

import entities.ContactData;
import entities.ContractData;
import models.Contract;
import models.contacts.BusinessContact;
import models.contacts.Contact;
import models.contacts.IndividualContact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public ContactData toContactData(Contact contact) {
        if(contact.getClass() == IndividualContact.class) {
            IndividualContact individualContact = (IndividualContact) contact;
            return new ContactData(contact.getId(), contact.getType(), contact.getCreatedDate(), contact.getState(), null, individualContact.getName(), individualContact.getLastName(), individualContact.getDateOfBirth());
        }
        BusinessContact businessContact = (BusinessContact) contact;
        return new ContactData(contact.getId(), contact.getType(), contact.getCreatedDate(), contact.getState(), businessContact.getCustomerName(), null, null, null);
    }


    public List<ContactData> toContactData(List<Contact> contactList) {
        return contactList.stream().map(this::toContactData).collect(Collectors.toList());
    }

    public Contact fromContactData(ContactData cd) {
        if(cd.getCustomerName() != null) {
            return new BusinessContact(cd.getId(), cd.getType(), cd.getCreatedDate(), cd.getState(), cd.getCustomerName());
        }
        return new IndividualContact(cd.getId(), cd.getType(), cd.getCreatedDate(), cd.getState(), cd.getName(), cd.getLastName(), cd.getDateOfBirth());
    }

    public List<Contact> fromContactData(List<ContactData> contactDataList) {
        return contactDataList.stream().map(this::fromContactData).collect(Collectors.toList());
    }
}
