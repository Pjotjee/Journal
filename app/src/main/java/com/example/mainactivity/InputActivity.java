package com.example.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;


public class InputActivity extends AppCompatActivity {
    String mood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }
    public void addEntry(View view) {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        TextView title = (TextView) findViewById(R.id.editText);
        TextView content = (TextView) findViewById(R.id.editText2);

        String Title = title.getText().toString();
        String Content = content.getText().toString();
        String time = DateFormat.getDateTimeInstance().format(new Date());
        JournalEntry entry = new JournalEntry(1,Title,Content,mood,time);
        db.insert(entry);

        Intent intent = new Intent(this, MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void moodClick(View view){
        ImageButton button = (ImageButton) view;
        mood = button.getContentDescription().toString();

        button.setBackgroundColor(Color.GRAY);
        ImageButton button1 = (ImageButton) findViewById(R.id.verySad);
        ImageButton button2 = (ImageButton) findViewById(R.id.sad);
        ImageButton button3 = (ImageButton) findViewById(R.id.happy);
        ImageButton button4 = (ImageButton) findViewById(R.id.veryHappy);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);

    }
}