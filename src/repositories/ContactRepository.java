package repositories;

import enums.ContactType;
import models.contacts.Contact;

import java.util.Optional;

public interface ContactRepository extends Repository<Contact> {
    Optional<Contact> findById(int id, ContactType type);
    boolean deleteById(int id, ContactType type);
}
