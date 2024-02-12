package ru.develonica;

import ru.develonica.manager.ContactManager;
import ru.develonica.manager.InMemoryContactManager;
import ru.develonica.model.Contact;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager contactManager = new InMemoryContactManager();
        while (true) {
            displayMenu(contactManager);
        }
    }

    public static void displayMenu(ContactManager contactManager) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--------------------------------------------------------");
        System.out.println("Добро пожаловать в приложение \"Список контактов\"!");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть список контактов");
        System.out.println("3. Найти контакт по имени");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выход");
        System.out.println("--------------------------------------------------------");

        System.out.print("Выберите действие (введите номер): ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Недопустимый ввод");
        }

        switch (choice) {
            case -1:
                break;
            case 1:
                Contact contact = addContactFromInput(scanner);
                if (contact != null) contactManager.addContact(contact);
                break;
            case 2:
                contactManager.displayContacts();
                break;
            case 3:
                System.out.print("Введите имя для поиска: ");
                String searchName = scanner.nextLine();
                contactManager.searchContact(searchName);
                break;
            case 4:
                System.out.print("Введите номер контакта для удаления: ");
                int deleteIndex = scanner.nextInt();
                contactManager.deleteContact(deleteIndex);
                break;
            case 5:
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Недопустимая операция. Пожалуйста, выберите снова.");
        }
    }

    public static Contact addContactFromInput(Scanner scanner) {
        System.out.print("Введите имя контакта: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию контакта: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите номер телефона (только цифры): ");
        String phoneNumber = scanner.nextLine();

        if (validatePhoneNumber(phoneNumber)) {
            System.out.print("Введите адрес электронной почты: ");
            String email = scanner.nextLine();

            if (validateEmail(email)) {
                return new Contact(firstName, lastName, phoneNumber, email);
            } else {
                System.out.println("Некорректный email. Пожалуйста, используйте формат example@example.com");
            }
        } else {
            System.out.println("Некорректный номер телефона. Пожалуйста, вводите только цифры.");
        }
        return null;
    }

    public static boolean validateEmail(String email) {
        return email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d+");
    }

}
