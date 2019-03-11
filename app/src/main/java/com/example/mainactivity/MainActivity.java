package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set OnItemLongClickListener
        ListView listView = findViewById(R.id.listJournalEntries);
        listView.setOnItemLongClickListener(new OnItemLongClickListener());
        listView.setOnItemClickListener(new OnItemClickListener());

        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, db.selectAll());
        listView.setAdapter(adapter);
        //updateData();
        //Log.e("The database ", String.valueOf(adapter)) ;
    }

    // update listview
    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }


    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);

    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            cursor.moveToNext();

            String title = cursor.getString(cursor.getColumnIndex("title"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            JournalEntry entry = new JournalEntry(id, title, content, mood, timestamp);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
            db.deleteEntry(cursor.getInt(cursor.getColumnIndex("_id")));
            updateData();
            return true;
        }
    }
}
