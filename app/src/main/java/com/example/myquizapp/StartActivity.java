package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private ActivityStartBinding activityStartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityStartBinding = ActivityStartBinding.inflate(getLayoutInflater());

        setContentView(activityStartBinding.getRoot());

        Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);

        activityStartBinding.startButton.setOnClickListener(view -> {
            startActivity(mainIntent);
        });
    }
}