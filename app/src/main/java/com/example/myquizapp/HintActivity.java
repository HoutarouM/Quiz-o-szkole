package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityHintBinding;

public class HintActivity extends AppCompatActivity {
    ActivityHintBinding activityHintBinding;

    private int hintTextId;
    private int questionIndex;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityHintBinding = ActivityHintBinding.inflate(getLayoutInflater());

        setContentView(activityHintBinding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        //        read and set saved data
        Bundle data = getIntent().getExtras();

        if (data != null) {
            hintTextId = data.getInt("hint_text_id", 0);
            questionIndex = data.getInt("question_index", 0);
            score = data.getInt("score", 0);
        }

        activityHintBinding.hintText.setText(hintTextId);


        activityHintBinding.returnToQuestionsButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(HintActivity.this, MainActivity.class);

            mainIntent.putExtra("question_index", questionIndex);
            mainIntent.putExtra("score", score);

            startActivity(mainIntent);
        });
    }
}