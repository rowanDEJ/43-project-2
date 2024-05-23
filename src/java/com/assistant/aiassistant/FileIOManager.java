package com.assistant.aiassistant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIOManager {
    private static final String PATH_GEBRUIKERS = "gebruikers.txt";

    private static final String SEPERATOR = "/~/";

    public ArrayList<String> readFile(String path) {
        ArrayList<String> data = new ArrayList<>();

        File fileOBJ = new File(path);

        try {
            Scanner fileReader = new Scanner(fileOBJ);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                data.add(line);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Bestand niet gevonden.");
            // e.printStackTrace();
        }
        return data;
    }

    // leest alle gebruikers uit het gebruikers.txt bestand en zet ze in een ArrayList
    public ArrayList<User> getUsersFromFile() {
        ArrayList<String> lines = readFile(PATH_GEBRUIKERS);

        ArrayList<User> usersReadFromFile = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(SEPERATOR);

            // elke part is een attribuut van de gebruiker
            User userReadFromFile = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);

            // voeg de gebruiker toe aan de ArrayList met alle gebruikers
            usersReadFromFile.add(userReadFromFile);
        }

        // return de ArrayList met alle gebruikers
        return usersReadFromFile;
    }

    // slaat een gebruiker op in het gebruikers.txt bestand
    public void saveUserToFile(User userToSave){
        try {
            // maak een nieuwe regel in gebruikers.txt en slaat de gebruiker daar op
            FileWriter myWriter = new FileWriter(PATH_GEBRUIKERS, true);

            // schrijf de gebruiker naar het bestand
            myWriter.write(userToSave.getUsername() + SEPERATOR + userToSave.getPassword() + SEPERATOR + userToSave.getEmail() + SEPERATOR + userToSave.getFirstName() + SEPERATOR + userToSave.getLastName() + SEPERATOR + userToSave.getPreferredLanguage() + "\n");

            myWriter.close();
        } catch (IOException e) {
            System.out.println("Er ging iets mis");
        }
    }

    // Herschrijft alle gebruikers in het gebruikers.txt bestand
    // Pas op: dit kan alle gebruikers in de lijst verwijderen!
    public void rewriteUsersToFile(ArrayList<User> users) {
        try {
            new FileWriter(PATH_GEBRUIKERS, false).close();
            for(User user : users) {
                saveUserToFile(user);
            }
        } catch (IOException e) {
            System.out.println("Er ging iets mis");
        }
    }

    // verwijdert een gebruiker uit het gebruikers.txt bestand
    public void removeUserFromFile(User selectedUser) {
        ArrayList<User> users = getUsersFromFile();

        // zoekt de geselecteerde gebruiker in de ArrayList en verwijder deze
        for (int i = 0; i < users.size(); i++) {
            if (selectedUser.getUsername().equals(users.get(i).getUsername())) {
                users.remove(i);
                break;
            }
        }
        rewriteUsersToFile(users);
    }

    // gebruiker bewerken
    // nieuw is de waarde waarin het attribuut van de gebruiker moet worden veranderd
    // aspect is het attribuut dat moet worden veranderd (wachtwoord, gebruikersnaam, email enz.)
    // vergeet niet het aspect mee te geven aan deze methode als er iets veranderd moet worden.
    public void editUser(User user, String nieuw, String aspect){
        ArrayList<User> users = getUsersFromFile();

        for (User u : users) {
            if (user.getUsername().equals(u.getUsername())) {
                switch (aspect) {
                    case "gebruikersnaam":
                        u.setUserName(nieuw);
                        break;
                    case "wachtwoord":
                        u.setPassword(nieuw);
                        break;
                    case "email":
                        u.setEmail(nieuw);
                        break;
                    case "voornaam":
                        u.setFirstName(nieuw);
                        break;
                    case "achternaam":
                        u.setLastName(nieuw);
                        break;
                    case "preferredLanguage":
                        u.setPreferredLanguage(nieuw);
                        break;
                    default:
                        System.out.println("Er ging iets mis.");
                }
            }
        }
        rewriteUsersToFile(users);
    }
}
