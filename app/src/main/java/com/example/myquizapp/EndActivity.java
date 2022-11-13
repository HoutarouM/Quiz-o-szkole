package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

        Log.e("test_create", "create");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e("test_start", "start1");

//        read and set saved data
        Bundle data = getIntent().getExtras();

        if (data != null) {
            score = data.getInt("score", 0);
        }

        Log.e("test_start", "start2");

        activityEndBinding.scoreTextView.setText("Score: " + Integer.toString(score));

        Log.e("test_start", "start3");

        activityEndBinding.closeButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(EndActivity.this, MainActivity.class);

            startActivity(mainIntent);
        });
    }
}