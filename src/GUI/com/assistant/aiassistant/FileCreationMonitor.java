package com.assistant.aiassistant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCreationMonitor extends Thread {
    private final File folder;
    private final List<FileCreationObserver> observers;

    public FileCreationMonitor(File folder) {
        this.folder = folder;
        this.observers = new ArrayList<>();
    }

    public void addObserver(FileCreationObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void run() {
        long lastCheck = System.currentTimeMillis();
        while (true) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.lastModified() > lastCheck && file.getName().endsWith(".txt")) {
                        for (FileCreationObserver observer : observers) {
                            observer.onFileCreated(file.getName());
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
