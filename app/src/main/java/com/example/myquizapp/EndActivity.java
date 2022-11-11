package com.example.myquizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {
    TextView scoreTextView;

    Button closeAppButton;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            score = data.getInt("score");
        }

        scoreTextView = findViewById(R.id.score_text_view);

        closeAppButton = findViewById(R.id.close_button);

        scoreTextView.setText("Score: " + Integer.toString(score));

        closeAppButton.setOnClickListener(view -> {
            finish();
        });
    }
}