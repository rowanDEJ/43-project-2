
package com.assistant.aiassistant;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, Action> actions = new HashMap<>();
    public static final boolean startMenu = true;

    public Menu() {
        actions.put(1, new StartNewConversationAction("", ""));
        actions.put(2, new LoadSavedConversationAction());
        actions.put(3, new ExitApplicationAction());
    }


    private int safeReadInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Dat is geen geldig nummer. Probeer het opnieuw:");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }
}