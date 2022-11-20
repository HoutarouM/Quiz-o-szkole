package com.example.myquizapp;

import static com.example.myquizapp.PutKeys.PUT_SCORE_KEY;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityEndBinding;

public class EndActivity extends AppCompatActivity {
    ActivityEndBinding activityEndBinding;

    Intent mainIntent;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityEndBinding = ActivityEndBinding.inflate(getLayoutInflater());

        setContentView(activityEndBinding.getRoot());


//        read and set saved data
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(PUT_SCORE_KEY, 0);
        }

        Bundle data = getIntent().getExtras();
        if (data != null) {
            score = data.getInt("score", 0);
        }


        mainIntent = new Intent(EndActivity.this, MainActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        activityEndBinding.scoreTextView.setText("Score: " + Integer.toString(score));

        activityEndBinding.closeButton.setOnClickListener(view -> {
            startActivity(mainIntent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PUT_SCORE_KEY, score);
    }
}