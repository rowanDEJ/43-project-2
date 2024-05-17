package com.assistant.aiassistant;

public class Document {
    private String fileName;
    private String content;

    public Document(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void save() {

    }

    public void open() {

    }

    public void close() {

    }
}
