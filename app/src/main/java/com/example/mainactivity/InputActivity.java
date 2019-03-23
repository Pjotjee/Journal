package com.example.mainactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class InputActivity extends AppCompatActivity {
    String mood; // the mood entry

    //** create the activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the action bar at the top
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_input);
    }

    //** add another entry in the database*/
    public void addEntry(View view) {
        // initialize the entry and get the values
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        TextView title = (TextView) findViewById(R.id.editText);
        TextView content = (TextView) findViewById(R.id.editText2);
        String Title = title.getText().toString();
        String Content = content.getText().toString();
        String time = DateFormat.getDateTimeInstance().format(new Date());
        // put the entries into the database
        JournalEntry entry = new JournalEntry(1,Title,Content,mood,time);
        db.insert(entry);
        // initialize the new intent and start the activity with that intent
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //** connect the ImageButton with the right mood */
    public void moodClick(View view){
        // initialize the button and the mood
        ImageButton button = (ImageButton) view;
        mood = button.getContentDescription().toString();
        button.setBackgroundColor(Color.GREEN);
        ImageButton button1 = (ImageButton) findViewById(R.id.verySad);
        ImageButton button2 = (ImageButton) findViewById(R.id.sad);
        ImageButton button3 = (ImageButton) findViewById(R.id.happy);
        ImageButton button4 = (ImageButton) findViewById(R.id.veryHappy);
        // after clicking, disable the button
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }
}