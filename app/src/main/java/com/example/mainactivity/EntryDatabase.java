package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class EntryDatabase extends SQLiteOpenHelper {
    // create the variables
    private static EntryDatabase instance;

    public EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE journalEntries(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, content TEXT, " +
                "mood TEXT, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

        // some sample items to test
        db.execSQL("INSERT INTO journalEntries( title, content, mood) VALUES " +
                "('First', 'hello World!', 'normal')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE journalEntries" );
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, "instance",null, 1);
        }
        return instance;
    }

    public Cursor selectAll(){
        return getWritableDatabase().rawQuery("SELECT * FROM journalEntries",  null);
    }

    // add a new entry to the database
    public void insert(JournalEntry entry) {
        String titleInfo = entry.getTitle();
        String contentInfo = entry.getContent();
        String moodInfo = entry.getMood();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", titleInfo);
        contentValues.put("content", contentInfo);
        contentValues.put("mood", moodInfo);
        db.insert("journalEntries", null, contentValues);
    }

    // deletes certain entry from the database
    public void deleteEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("journalEntries", " _id = "  + id, null);
    }
}
