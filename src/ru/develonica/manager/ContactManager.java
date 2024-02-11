package ru.develonica.manager;

import ru.develonica.model.Contact;

public abstract class ContactManager {
    public abstract void addContact(Contact contact);

    public abstract void displayContacts();

    public abstract void searchContact(String name);

    public abstract void deleteContact(int id);
}
