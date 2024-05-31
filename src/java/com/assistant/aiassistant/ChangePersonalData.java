package com.assistant.aiassistant;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangePersonalData {

    public FileIOManager fileIOManager = new FileIOManager();
    public ArrayList<User> users = new ArrayList<>();

    // Testgebruiker, later weghalen en ingelogde gebruiker meegeven
//    public User testUser = new User("MeneerMart", "geheim", "Mart", "van der Veen", "martvaanderveen06@gmail.com", "Nederlands");

    // Dit kan later nog worden weggehaald aan de hand van de applicatie en het scherm om de gegevens te veranderen
    public void dataToChange(String aspect, User selectedUser) {
        Scanner scanner = new Scanner(System.in);

        switch (aspect){
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

    private void changeUsername(User selectedUser, Scanner scanner) {
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

    private void changePassword(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Waar wilt u uw wachtwoord in veranderen?");
        String newPassword = scanner.nextLine();
        while (checkUsername(newPassword)) {
            newPassword = scanner.nextLine();
        }
        fileIOManager.editUser(selectedUser, newPassword, "password");
        System.out.println("Uw gebruikersnaam is succesvol veranderd.");
    }

    private void changeEmail(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige email is: " + selectedUser.getUsername());
        System.out.println("Waar wilt u uw gebruikersnaam in veranderen?");
        String newEmail = scanner.nextLine();
        while (checkUsername(newEmail)) {
            newEmail = scanner.nextLine();
        }
        fileIOManager.editUser(selectedUser, newEmail, "email");
        System.out.println("Uw email adres is succesvol veranderd naar: " + selectedUser.getEmail());    }

    private void changeFirstname(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige voornaam is: " + selectedUser.getFirstName());
        System.out.println("Waar wilt u uw voornaam in veranderen?");
        String newFirstName = scanner.nextLine();
        while (checkUsername(newFirstName)) {
            newFirstName = scanner.nextLine();
        }
        fileIOManager.editUser(selectedUser, newFirstName, "fistname");
        System.out.println("Uw voornaam is succesvol veranderd naar: " + selectedUser.getFirstName());    }

    private void changeLastname(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige achternaam is: " + selectedUser.getUsername());
        System.out.println("Waar wilt u uw achternaam in veranderen?");
        String newLastName = scanner.nextLine();
        while (checkUsername(newLastName)) {
            System.out.println("Deze gebruikersnaam bestaat al!");
            System.out.println("Kies een andere gebruikersnaam:");
            newLastName = scanner.nextLine();
        }
        fileIOManager.editUser(selectedUser, newLastName, "lastname");
        System.out.println("Uw gebruikersnaam is succesvol veranderd naar: " + selectedUser.getLastName());    }

    // Hier misschien handig om opties te geven in welke taal de gebruiker zijn voorkeur heeft
    private void changePreferredLanguage(User selectedUser, Scanner scanner) {
        System.out.println();
        System.out.println("Uw huidige voorkeurstaal is: " + selectedUser.getPreferredLanguage());
        System.out.println("Selecteer uw nieuwe voorkeurstaal:");
        System.out.println("nl, en, de, fr, es, it, pt, pl, ru, zh, ja, ko");
        String newPreferredLanguage = scanner.nextLine();
        fileIOManager.editUser(selectedUser, newPreferredLanguage, "preferredlanguage");
        System.out.println("Uw voorkeurstaal is succesvol veranderd naar: " + selectedUser.getPreferredLanguage());
    }

    // Misschien deze methode verplaatsen naar een andere class
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
