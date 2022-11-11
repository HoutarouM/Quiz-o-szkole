package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HintActivity extends AppCompatActivity {
    TextView hintTextView;

    Button returnButton;

    private int hintTextId;
    private int questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            hintTextId = data.getInt("hint_text_id");
            questionIndex = data.getInt("question_index");
        }

        hintTextView = findViewById(R.id.hint_text);

        returnButton = findViewById(R.id.return_to_questions_button);

        hintTextView.setText(hintTextId);

        returnButton.setOnClickListener(view -> {
            Intent mainIntent = new Intent(HintActivity.this, MainActivity.class);
            mainIntent.putExtra("question_index", questionIndex);

            int resCode = 0;

            startActivityForResult(mainIntent, resCode);
        });
    }
}