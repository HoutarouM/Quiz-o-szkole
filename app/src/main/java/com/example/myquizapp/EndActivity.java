package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityEndBinding;

public class EndActivity extends AppCompatActivity {
    ActivityEndBinding activityEndBinding;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityEndBinding = ActivityEndBinding.inflate(getLayoutInflater());

        setContentView(activityEndBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

//        read and set saved data
        Bundle data = getIntent().getExtras();

        if (data != null) {
            score = data.getInt("score", 0);
        }

        activityEndBinding.scoreTextView.setText("Score: " + Integer.toString(score));

        activityEndBinding.closeButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(EndActivity.this, MainActivity.class);

            startActivity(mainIntent);
        });
    }
}