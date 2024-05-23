package com.assistant.aiassistant;
public class ExitApplicationAction implements Action {
    @Override
    public void execute() {
        System.out.println("Applicatie wordt afgesloten...");
        System.exit(0);
    }
}