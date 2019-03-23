package com.example.mainactivity;
import java.io.Serializable;
import java.util.Timer;

public class JournalEntry implements Serializable {
    private  long id;                                   // id of the entry
    private String title, content, mood, timestamp;     // details and content of the entry

    //** constructor of JournalEntry */
    public JournalEntry(long id, String title, String content, String mood, String timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    //** getters of JournalEntry */
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}