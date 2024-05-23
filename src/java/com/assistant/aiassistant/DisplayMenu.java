package com.assistant.aiassistant;
public class DisplayMenu implements Action {

    @Override
    public void execute() {
        System.out.println("1. Start een nieuwe conversatie");
        System.out.println("2. Laad een opgeslagen conversatie");
        System.out.println("3. Verlaat de applicatie");
        System.out.print("Maak een keuze: ");
    }
}
