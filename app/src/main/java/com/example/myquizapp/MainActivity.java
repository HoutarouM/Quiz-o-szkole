package com.example.myquizapp;

import static com.example.myquizapp.PutKeys.PUT_ANSWER_ID_KEY;
import static com.example.myquizapp.PutKeys.PUT_HINT_ID_KEY;
import static com.example.myquizapp.PutKeys.PUT_QUESTION_INDEX_KEY;
import static com.example.myquizapp.PutKeys.PUT_SCORE_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    //    intents
    private Intent hintIntent;
    private Intent endIntent;

    private ArrayList<Question> questions = new ArrayList<>();

    private int questionIndex;
    private int score;
    private int answerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());


//        read and set saved data
        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(PUT_QUESTION_INDEX_KEY, 0);
            score = savedInstanceState.getInt(PUT_SCORE_KEY, 0);
            answerId = savedInstanceState.getInt(PUT_ANSWER_ID_KEY, 0);
        }

        Bundle data = getIntent().getExtras();
        if (data != null) {
            questionIndex = data.getInt(PUT_QUESTION_INDEX_KEY, 0);
            score = data.getInt(PUT_SCORE_KEY, 0);
        }


//        intents initialization
        hintIntent = new Intent(MainActivity.this, HintActivity.class);
        endIntent = new Intent(MainActivity.this, EndActivity.class);


//        add questions
        addQuestion(R.drawable.dog, R.string.first_question_text, R.string.first_question_hint,
                R.string.first_question_answer_a, R.string.first_question_answer_b, R.string.first_question_answer_c, 0);

        addQuestion(R.drawable.dog, R.string.second_question_text, R.string.second_question_hint,
                R.string.second_question_answer_a, R.string.second_question_answer_b, R.string.second_question_answer_c, 0);

        addQuestion(R.drawable.dog, R.string.first_question_text, R.string.first_question_hint,
                R.string.first_question_answer_a, R.string.first_question_answer_b, R.string.first_question_answer_c, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setDataToViews(questions.get(questionIndex));
        

//        buttons on click change background and text colors
        activityMainBinding.firstRadioBtn.setOnClickListener(view -> {
            clearButton(activityMainBinding.secondRadioBtn);
            clearButton(activityMainBinding.thirdRadioBtn);

            activityMainBinding.firstRadioBtn.setBackgroundColor(getResources().getColor(R.color.main_school_color));
            activityMainBinding.firstRadioBtn.setTextColor(getResources().getColor(R.color.white));
        });

        activityMainBinding.secondRadioBtn.setOnClickListener(view -> {
            clearButton(activityMainBinding.firstRadioBtn);
            clearButton(activityMainBinding.thirdRadioBtn);

            activityMainBinding.secondRadioBtn.setBackgroundColor(getResources().getColor(R.color.main_school_color));
            activityMainBinding.secondRadioBtn.setTextColor(getResources().getColor(R.color.white));
        });

        activityMainBinding.thirdRadioBtn.setOnClickListener(view -> {
            clearButton(activityMainBinding.firstRadioBtn);
            clearButton(activityMainBinding.secondRadioBtn);

            activityMainBinding.thirdRadioBtn.setBackgroundColor(getResources().getColor(R.color.main_school_color));
            activityMainBinding.thirdRadioBtn.setTextColor(getResources().getColor(R.color.white));
        });


//        change question on click
        activityMainBinding.answerBtn.setOnClickListener(view -> {
//            buttons to default
            clearButton(activityMainBinding.firstRadioBtn);
            clearButton(activityMainBinding.secondRadioBtn);
            clearButton(activityMainBinding.thirdRadioBtn);

            answerId = getAnswerIdByButton(activityMainBinding.radioGroup.getCheckedRadioButtonId());

//            increment score if answer is right
            if (questions.get(questionIndex).checkAnswer(answerId)) {
//                increment user score
                score++;
            }

            questionIndex++;

//            change activity when quiz is end
            if (questionIndex == questions.size()) {
                endIntent.putExtra(PUT_SCORE_KEY, score);

                startActivity(endIntent);
            } else {
                activityMainBinding.radioGroup.clearCheck();

                setDataToViews(questions.get(questionIndex));
            }
        });

        activityMainBinding.hintBtn.setOnClickListener(view -> {
//            substrate 1 point for hint using
            if (score > 0) {
                score--;
            }

//            put data to hint activity
            hintIntent.putExtra(PUT_HINT_ID_KEY, questions.get(questionIndex).getHintTextId());
            hintIntent.putExtra(PUT_QUESTION_INDEX_KEY, questionIndex);
            hintIntent.putExtra(PUT_SCORE_KEY, score);

            startActivity(hintIntent);
        });
    }

    private void clearButton(RadioButton btn) {
        btn.setTextColor(getResources().getColor(R.color.no_select_text));
        btn.setBackground(getResources().getDrawable(R.drawable.border));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        save data for read
//        if user rotate the device
        outState.putInt(PUT_QUESTION_INDEX_KEY, questionIndex);
        outState.putInt(PUT_SCORE_KEY, score);
        outState.putInt(PUT_ANSWER_ID_KEY, answerId);
    }

    private void setDataToViews(Question q) {
//        set data to views
        activityMainBinding.imageView.setImageResource(q.getImageId());

        activityMainBinding.questionTextView.setText(q.getQuestionTextId());

        activityMainBinding.firstRadioBtn.setText(q.getAnswersIds().get(0));
        activityMainBinding.secondRadioBtn.setText(q.getAnswersIds().get(1));
        activityMainBinding.thirdRadioBtn.setText(q.getAnswersIds().get(2));
    }

    private void addQuestion(int imageId, int questionTextId, int questionHintId, int answAId, int answBId, int answCId, int correctAnswId) {
        Question question = new Question(imageId, questionTextId, questionHintId, answAId, answBId, answCId, correctAnswId);

        questions.add(question);
    }

    private int getAnswerIdByButton(int buttonId) {
        if (activityMainBinding.firstRadioBtn.getId() == buttonId) return 0;
        else if (activityMainBinding.secondRadioBtn.getId() == buttonId) return 1;
        else return 2;
    }
}