package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry journalDetail = (JournalEntry) intent.getSerializableExtra("entry");

        TextView titleView  = findViewById(R.id.textViewTitle);
        TextView moodView = findViewById(R.id.textViewMood);
        TextView detailsView = findViewById(R.id.textViewDetails);
        TextView timeView = findViewById(R.id.textViewTime);

        // fill the textViews with the journal information
        titleView.setText(journalDetail.getTitle());
        moodView.setText(journalDetail.getMood());
        detailsView.setText(journalDetail.getContent());
        timeView.setText(journalDetail.getTimestamp());


    }

    @Override
    public void onBackPressed() {
        Intent toHomeScreen = new Intent(this, MainActivity.class);
        startActivity(toHomeScreen);
    }
}
