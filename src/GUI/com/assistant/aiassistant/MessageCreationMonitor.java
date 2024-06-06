package com.assistant.aiassistant;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MessageCreationMonitor extends Thread{
    private final File file;
    private long lastModified;
    private final List<MessageCreationObserver> observers;

    public MessageCreationMonitor(File file) {
        this.file = file;
        this.lastModified = file.lastModified();
        this.observers = new ArrayList<>();
    }

    public void addObserver(MessageCreationObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void run() {
        while (true) {
            long newLastModified = file.lastModified();
            if (newLastModified > lastModified) {
                lastModified = newLastModified;
                notifyObservers();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyObservers() {
        String message = readLastLineFromFile();
        for (MessageCreationObserver observer : observers) {
            observer.onFileModified(message);
        }
    }

    private String readLastLineFromFile() {
        String lastLine = "";
        try (RandomAccessFile fileHandler = new RandomAccessFile(file, "r")) {
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }

                sb.append((char) readByte);
            }

            lastLine = sb.reverse().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}
