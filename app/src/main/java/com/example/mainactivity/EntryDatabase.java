package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase instance;  // the unique instance of the class EntryDatabase

    //** constructor of the EntryDatabase */
    public EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //** create the database journalEntries */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE journalEntries(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, content TEXT, " +
                "mood TEXT, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        // default entries
        db.execSQL("INSERT INTO journalEntries( title, content, mood) VALUES " +
                "('First', 'hello World!', 'verysad')");

    }

    //** drop the entries table (if it exists) and recreates it */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE journalEntries" );
        onCreate(db);
    }

    //** accept a context and return the value of instance if available,
    // otherwise call the constructor */
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, "instance",null, 1);
        }
        return instance;
    }

    //** open connection to the database, and select everything */
    public Cursor selectAll(){
        return getWritableDatabase().rawQuery("SELECT * FROM journalEntries",  null);
    }

    //** add a new entry to the database */
    public void insert(JournalEntry entry) {
        // retrieve the entry values
        String titleInfo = entry.getTitle();
        String contentInfo = entry.getContent();
        String moodInfo = entry.getMood();
        // insert the values in the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", titleInfo);
        contentValues.put("content", contentInfo);
        contentValues.put("mood", moodInfo);
        db.insert("journalEntries", null, contentValues);
    }

    //** delete entry from the database */
    public void deleteEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("journalEntries", " _id = "  + id, null);
    }
}
