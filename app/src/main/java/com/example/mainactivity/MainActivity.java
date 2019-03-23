package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EntryDatabase db;       // database for the journal entries
    private EntryAdapter adapter;   // adapter puts entries from the database in a list

    /** recreate the activity in case it was killed */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the action bar at the top
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // find the ListView and add OnItemClickListener and OnItemLongClickListener
        ListView listView = findViewById(R.id.listJournalEntries);
        listView.setOnItemLongClickListener(new OnItemLongClickListener());
        listView.setOnItemClickListener(new OnItemClickListener());
        // fill the list with the database entries using the adapter
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, R.layout.entry_row, db.selectAll());
        listView.setAdapter(adapter);
    }

    /** when coming back from other activities update the data */
    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    /** update the list with the latest data*/
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    /** direct to InputActivity when the floating action button is clicked */
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    /** direct to the right DetailActivity (entry) when clicked on a entry in the list */
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // find the position of the click
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            cursor.moveToNext();
            // match the position with the right entry
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            JournalEntry entry = new JournalEntry(id, title, content, mood, timestamp);
            // direct to the DetailActivity with the entry that was clicked
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }

    /** delete an entry when LongClick is used */
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            // find the position of the LongClick
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
            // delete the entry
            db.deleteEntry(cursor.getInt(cursor.getColumnIndex("_id")));
            // update the list so the user knows the entry was deleted
            updateData();
            // return true when no other actions should be taken
            return true;
        }
    }
}
