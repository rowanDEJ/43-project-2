package com.assistant.aiassistant;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;



public class FileIOManager {
    private static final String FILE_PATH = "files/";
    private static final String SEPARATOR = "/~/";

    private static final String conversationsFolder = "conversations/";
    private static final String usersFile = "gebruikers.txt";



    // Hieronder staan alle methodes om gebruikers te lezen, schrijven, bewerken en verwijderen
    // leest een bestand uit en zet de data in een ArrayList
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
        ArrayList<String> lines = readFile(FILE_PATH + usersFile);

        ArrayList<User> usersReadFromFile = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);

            // elke part is een attribuut van de gebruiker
            User userReadFromFile = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);

            // voeg de gebruiker toe aan de ArrayList met alle gebruikers
            usersReadFromFile.add(userReadFromFile);
        }

        // return de ArrayList met alle gebruikers
        return usersReadFromFile;
    }

    //krijg alle beschikbare talen uit beschikbareTalen.txt
    public ArrayList<Language> getAvailableLanguages() {
        ArrayList<String> lines = readFile(FILE_PATH + "beschikbareTalen.txt");
        ArrayList<Language> foundLanguages = new ArrayList<>();
        for (String line : lines) {
            foundLanguages.add(new Language(line));
        }
        return foundLanguages;
    }

    // slaat een gebruiker op in het gebruikers.txt bestand
    public void saveUserToFile(User userToSave) {
        try {
            // maak een nieuwe regel in gebruikers.txt en slaat de gebruiker daar op
            FileWriter myWriter = new FileWriter(FILE_PATH + usersFile, true);

            // schrijf de gebruiker naar het bestand
            myWriter.write(userToSave.getUsername() + SEPARATOR + userToSave.getPassword() + SEPARATOR + userToSave.getEmail() + SEPARATOR + userToSave.getFirstName() + SEPARATOR + userToSave.getLastName() + SEPARATOR + userToSave.getPreferredLanguage() + SEPARATOR + userToSave.getAiLanguage() + "\n");

            myWriter.close();
            saveUserInConversationFolder(userToSave);
        } catch (IOException e) {
            System.out.println("Er ging iets mis");
        }
    }

    public static void saveUserInConversationFolder(User userToSave) {
        try {
            // maak een directory aan in de conversations directory met de username als naam
            Files.createDirectories(Paths.get(FILE_PATH + conversationsFolder + userToSave.getUsername()));

        } catch (IOException e) {
            System.out.println("Er ging iets mis in het aanmaken van de user conversations directory");
        }
    }

    // Herschrijft alle gebruikers in het gebruikers.txt bestand
    // Pas op: dit kan alle gebruikers in de lijst verwijderen!
    public void rewriteUsersToFile(ArrayList<User> users) {
        try {
            new FileWriter(FILE_PATH + usersFile, false).close();
            for (User user : users) {
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
    public void editUser(User user, String nieuw, String aspect) {
        ArrayList<User> users = getUsersFromFile();

        for (User u : users) {
            if (user.getUsername().equals(u.getUsername())) {
                switch (aspect) {
                    case "password":
                        u.setPassword(nieuw);
                        break;
                    case "email":
                        u.setEmail(nieuw);
                        break;
                    case "firstname":
                        u.setFirstName(nieuw);
                        break;
                    case "lastname":
                        u.setLastName(nieuw);
                        break;
                    case "preferredLanguage":
                        u.setPreferredLanguage(nieuw);
                        break;
                    case "aiLanguage":
                        u.setAiLanguage(nieuw);
                        break;
                    default:
                        System.out.println("Er ging iets mis.");
                        break;
                }
            }
        }
        rewriteUsersToFile(users);
    }

    // Hieronder staan alle methodes voor de gesprekken (uit de oude IOFileManager class)
    // leest een bestand uit
    public static void saveConversation(Conversation conversation) {
        User user = AccountManager.getInstance().getActiveUser();

        if(!Files.exists(Paths.get(FILE_PATH + conversationsFolder + user.getUsername()))) {
            saveUserInConversationFolder(user);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + conversationsFolder + user.getUsername() + "/" + conversation.getTopic() + ".txt"))) {
            for (String msg : conversation.getMessages()) {
                writer.write(msg);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // laadt een gesprek
    public static ArrayList<Conversation> getSavedConversations () {
        ArrayList<Conversation> savedConversations = new ArrayList<>();
        File folder = new File(FILE_PATH + conversationsFolder + AccountManager.getInstance().getActiveUser().getUsername());
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String topic = file.getName().replace(".txt", "");
                    Conversation conversation = new Conversation(topic, new ArrayList<>());
                    savedConversations.add(conversation);
                }
            }

        }
        return savedConversations;
    }

    public static void addMessageToConversation(String message, Conversation conversation) {
        conversation.addMessage(message);
        saveConversation(conversation);
    }

    // laadt een gesprek
    public void loadConversation(Conversation conversation){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + conversationsFolder + AccountManager.getInstance().getActiveUser().getUsername() + "/" + conversation.getTopic() + ".txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                conversation.addMessage(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // verwijdert een gesprek
    public static void deleteConversation(Conversation conversation) {
        String filePath = FILE_PATH + conversationsFolder + AccountManager.getInstance().getActiveUser().getUsername() + "/" + conversation.getTopic() + ".txt";

        File file = new File(filePath);
        if (!file.delete()) {
            System.out.println("Failed to delete the conversation");
        }
    }

    public static boolean changeConversationTopic(Conversation conversation, String newTopic) {
        String path = FILE_PATH + conversationsFolder + AccountManager.getInstance().getActiveUser().getUsername() + "/" + conversation.getTopic() + ".txt";
        String path2 = FILE_PATH + conversationsFolder + AccountManager.getInstance().getActiveUser().getUsername() + "/" + newTopic + ".txt";

        File fileToRename = new File(path);
        File fileWithNewName = new File(path2);

        if (fileWithNewName.exists()) {
            System.out.println("chat with this topic already exists");
            return false;
        }

        return fileToRename.renameTo(fileWithNewName); // returns boolean if it was successful or not
    }
}

