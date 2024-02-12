package ru.develonica.manager;

import ru.develonica.model.Contact;

import java.util.HashMap;
import java.util.HashSet;

public class InMemoryContactManager extends ContactManager {
    private final HashSet<Contact> contacts = new HashSet<>();
    private int lastEvent = 0;
    private final HashMap<Integer, Contact> lastSearch = new HashMap<>();

    @Override
    public void addContact(Contact contact) {
        lastEvent = 1;
        contacts.add(contact);
        System.out.println("Контакт успешно добавлен.");
    }

    @Override
    public void displayContacts() {
        lastEvent = 2;
        if (contacts.isEmpty()) {
            System.out.println("Список контактов пуст.");
        } else {
            System.out.println("Список контактов:");
            int index = 1;
            for (Contact contact : contacts) {
                System.out.println(index + ". " + contact.toString());
                index++;
            }
        }
    }

    @Override
    public void searchContact(String name) {
        lastEvent = 3;
        lastSearch.clear();
        int index = 1;
        for (Contact contact : contacts) {
            if (contact.getFirstName().toLowerCase().contains(name.toLowerCase())
                    || contact.getLastName().toLowerCase().contains(name.toLowerCase())) {
                if (index == 1) System.out.println("Результаты поиска:");
                lastSearch.put(index, contact);
                System.out.println(index + ". " + contact);
                index++;
            }
        }
    }

    @Override
    public void deleteContact(int index) {
        if (lastEvent != 3) {
            System.out.println("Для удаления контакта необходимо найти его с помощью поиска (пункт 3) и запомнить его порядковый номер");
        } else {
            contacts.remove(lastSearch.get(index));
            System.out.println("Контакт удален");
        }
        lastEvent = 4;
    }
}
