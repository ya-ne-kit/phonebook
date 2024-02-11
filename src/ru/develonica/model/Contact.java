package ru.develonica.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + phoneNumber + " - " + email;
    }
}
