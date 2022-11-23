package com.example.myquizapp;

import static com.example.myquizapp.PutKeys.PUT_CHECKED_BUTTON_KEY;
import static com.example.myquizapp.PutKeys.PUT_HINT_ID_KEY;
import static com.example.myquizapp.PutKeys.PUT_QUESTION_INDEX_KEY;
import static com.example.myquizapp.PutKeys.PUT_SCORE_KEY;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityHintBinding;

public class HintActivity extends AppCompatActivity {
    ActivityHintBinding activityHintBinding;

    private int hintTextId;
    private int questionIndex;
    private int score;
    private int checkedBtn;

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
            hintTextId = data.getInt(PUT_HINT_ID_KEY, 0);
            questionIndex = data.getInt(PUT_QUESTION_INDEX_KEY, 0);
            score = data.getInt(PUT_SCORE_KEY, 0);
            checkedBtn = data.getInt(PUT_CHECKED_BUTTON_KEY, 0);
        }

        activityHintBinding.hintText.setText(hintTextId);


        activityHintBinding.returnToQuestionsButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(HintActivity.this, MainActivity.class);

            mainIntent.putExtra(PUT_QUESTION_INDEX_KEY, questionIndex);
            mainIntent.putExtra(PUT_SCORE_KEY, score);
            mainIntent.putExtra(PUT_CHECKED_BUTTON_KEY, checkedBtn);

            startActivity(mainIntent);
        });
    }
}