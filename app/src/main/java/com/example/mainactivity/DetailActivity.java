package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    /** recreate the activity in case it was killed */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the action bar at the top
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        // pass instances of the class using intent
        Intent intent = getIntent();
        JournalEntry journalDetail = (JournalEntry) intent.getSerializableExtra("entry");
        // initialize and fill the textViews with the journal information
        TextView titleView  = findViewById(R.id.textViewTitle);
        TextView moodView = findViewById(R.id.textViewMood);
        TextView detailsView = findViewById(R.id.textViewDetails);
        TextView timeView = findViewById(R.id.textViewTime);
        titleView.setText(journalDetail.getTitle());
        moodView.setText(journalDetail.getMood());
        detailsView.setText(journalDetail.getContent());
        timeView.setText(journalDetail.getTimestamp());
    }

    //** redirect back to the home screen when the go back button is pressed */
    @Override
    public void onBackPressed() {
        Intent toHomeScreen = new Intent(this, MainActivity.class);
        startActivity(toHomeScreen);
    }
}
