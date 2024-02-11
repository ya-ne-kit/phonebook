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
        System.out.println("����� ���������� � ���������� \"������ ���������\"!");
        System.out.println("1. �������� �������");
        System.out.println("2. ����������� ������ ���������");
        System.out.println("3. ����� ������� �� �����");
        System.out.println("4. ������� �������");
        System.out.println("5. �����");
        System.out.println("--------------------------------------------------------");

        System.out.print("�������� �������� (������� �����): ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("������������ ����");
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
                System.out.print("������� ��� ��� ������: ");
                String searchName = scanner.nextLine();
                contactManager.searchContact(searchName);
                break;
            case 4:
                System.out.print("������� ����� �������� ��� ��������: ");
                int deleteIndex = scanner.nextInt();
                contactManager.deleteContact(deleteIndex);
                break;
            case 5:
                System.out.println("�� ��������!");
                System.exit(0);
            default:
                System.out.println("������������ ��������. ����������, �������� �����.");
        }
    }

    public static Contact addContactFromInput(Scanner scanner) {
        System.out.print("������� ��� ��������: ");
        String firstName = scanner.nextLine();
        System.out.print("������� ������� ��������: ");
        String lastName = scanner.nextLine();
        System.out.print("������� ����� �������� (������ �����): ");
        String phoneNumber = scanner.nextLine();

        if (validatePhoneNumber(phoneNumber)) {
            System.out.print("������� ����� ����������� �����: ");
            String email = scanner.nextLine();

            if (validateEmail(email)) {
                return new Contact(firstName, lastName, phoneNumber, email);
            } else {
                System.out.println("������������ email. ����������, ����������� ������ example@example.com");
            }
        } else {
            System.out.println("������������ ����� ��������. ����������, ������� ������ �����.");
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