package com.assistant.aiassistant;

import java.util.Scanner;


// zorgt ervoor dat de user kan in- en uitloggen
public class Security {
    private static Security instance = null;
    private User activeUser;
    FileIOManager fileManager = new FileIOManager();


    private Security() {
        setUser(null); // null betekent dat er niemand is ingelogd
    }

    // als er nog geen instance is, wordt er een aangemaakt
    public static Security getInstance() {
        if (instance == null) {
            instance = new Security();
        }
        return instance;
    }

    private void setUser(String userName) {
        // als de gebruikersnaam gelijk is aan de gebruikersnaam in het bestand, wordt de gebruiker actief
        fileManager.getUsersFromFile().forEach(user -> {
            if (user.getUsername().equals(userName)) {
                this.activeUser = user;
            }
        });
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void logout() {
        setUser(null);
    }

    // is er ingelogd of niet?
    public boolean isLoggedIn() {
        return getActiveUser() != null;
    }

    public boolean checkUser(String userName, String password) {
        // Check alle gebruikers in het bestand, als de gebruikersnaam gelijk is aan het wachtwoord, return true
        for (User user : fileManager.getUsersFromFile()) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        boolean isLoggedIn = false; // logt uit


        // blijft proberen in te loggen tot je een bestaande gebruikersnaam invoert
        while (!isLoggedIn) {
            System.out.print("Voer een gebruikersnaam in: ");
            String userName = scanner.nextLine();
            System.out.print("Voer een wachtwoord in: ");
            String password = scanner.nextLine();

            if (checkUser(userName, password)) {
                isLoggedIn = true;
            } else {
                System.out.println("Gebruikersnaam of wachtwoord verkeerd.");
            }

            setUser(userName);
        }
    }
}



