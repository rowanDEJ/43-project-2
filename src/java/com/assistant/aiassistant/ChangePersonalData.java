package com.assistant.aiassistant;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangePersonalData {

    public FileIOManager fileIOManager = new FileIOManager();
    public ArrayList<User> users = new ArrayList<>();

    // Dit kan later nog worden weggehaald aan de hand van de applicatie en het scherm om de gegevens te veranderen
    public void dataToChange(String aspect, User selectedUser) {
        Scanner scanner = new Scanner(System.in);

        switch (aspect) {
            case "username":
                changeUsername(selectedUser, scanner);
                break;
            case "password":
                changePassword(selectedUser, scanner);
                break;
            case "email":
                changeEmail(selectedUser, scanner);
                break;
            case "firstname":
                changeFirstname(selectedUser, scanner);
                break;
            case "lastname":
                changeLastname(selectedUser, scanner);
                break;
            case "preferredlanguage":
                changePreferredLanguage(selectedUser, scanner);
                break;
            default:
                // invalid aspect
                break;
        }
    }

    protected void changeUsername(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige gebruikersnaam is: " + selectedUser.getUsername());
        System.out.println("Waar wilt u uw gebruikersnaam in veranderen?");
        String newUsername = scanner.nextLine();
        while (checkUsername(newUsername)) {
            newUsername = scanner.nextLine();
        }
        fileIOManager.editUser(selectedUser, newUsername, "username");
        System.out.println("Uw gebruikersnaam is succesvol veranderd naar: " + selectedUser.getUsername());
    }

    protected void changePassword(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Waar wilt u uw wachtwoord in veranderen?");
        String newPassword = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newPassword, "password");
        System.out.println("Uw wachtwoord is succesvol veranderd.");
    }

    protected void changeEmail(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige email is: " + selectedUser.getUsername());
        System.out.println("Waar wilt u uw gebruikersnaam in veranderen?");
        String newEmail = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newEmail, "email");
        System.out.println("Uw email adres is succesvol veranderd naar: " + selectedUser.getEmail());
    }

    protected void changeFirstname(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige voornaam is: " + selectedUser.getFirstName());
        System.out.println("Waar wilt u uw voornaam in veranderen?");
        String newFirstName = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newFirstName, "firstname");
        System.out.println("Uw voornaam is succesvol veranderd naar: " + selectedUser.getFirstName());
    }

    protected void changeLastname(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige achternaam is: " + selectedUser.getUsername());
        System.out.println("Waar wilt u uw achternaam in veranderen?");
        String newLastName = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newLastName, "lastname");
        System.out.println("Uw achternaam is succesvol veranderd naar: " + selectedUser.getLastName());
    }

    protected void changePreferredLanguage(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige voorkeurstaal is: " + selectedUser.getPreferredLanguage());
        System.out.println("Selecteer uw nieuwe voorkeurstaal:");
        System.out.println("nl, en, de, fr, es, it, pt, pl, ru, zh, ja, ko");
        String newPreferredLanguage = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newPreferredLanguage, "preferredlanguage");
        System.out.println("Uw voorkeurstaal is succesvol veranderd naar: " + selectedUser.getPreferredLanguage());
    }

    public boolean checkUsername(String newUsername) {
        users = fileIOManager.getUsersFromFile();

        for (User u : users) {
            if (u.getUsername().equals(newUsername)) {
                System.out.println("Deze gebruikersnaam bestaat al!");
                System.out.println("Kies een andere gebruikersnaam:");
                return false;
            }
        }
        return true;
    }
}
