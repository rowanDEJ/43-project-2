package com.assistant.aiassistant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageCreationMonitor extends Thread{
    private final List<MessageCreationObserver> observers;

    public MessageCreationMonitor() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(MessageCreationObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void run() {
        long lastCheck = System.currentTimeMillis();
        while (true) {
            File[] files = new File("messages").listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.lastModified() > lastCheck && file.getName().endsWith(".txt")) {
                        for (MessageCreationObserver observer : observers) {
                            observer.onMessageCreated(file.getName());
                        }
                    }
                }
            }
            lastCheck = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
