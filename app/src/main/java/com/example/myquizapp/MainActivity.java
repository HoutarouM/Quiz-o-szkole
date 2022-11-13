package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquizapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    //    intents
    private Intent hintIntent;
    private Intent endIntent;

    private ArrayList<Question> questions = new ArrayList<>();

    private int questionIndex = 0;
    private int score = 0;
    private int answerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityMainBinding.getRoot());


//        intents initialization
        hintIntent = new Intent(MainActivity.this, HintActivity.class);
        endIntent = new Intent(MainActivity.this, EndActivity.class);


        //        add questions
        addQuestion(R.drawable.dog, R.string.first_question_text, R.string.first_question_hint,
                R.string.first_question_answer_a, R.string.first_question_answer_b, R.string.first_question_answer_c, 0);

        addQuestion(R.drawable.dog2, R.string.first_question_text, R.string.first_question_hint,
                R.string.first_question_answer_a, R.string.first_question_answer_b, R.string.first_question_answer_c, 0);

        addQuestion(R.drawable.dog3, R.string.first_question_text, R.string.first_question_hint,
                R.string.first_question_answer_a, R.string.first_question_answer_b, R.string.first_question_answer_c, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //        read and set saved data
        Bundle data = getIntent().getExtras();

        if (data != null) {
            questionIndex = data.getInt("question_index", 0);
        }

        setDataToViews(questions.get(questionIndex));

//        change question on click
        activityMainBinding.answerButton.setOnClickListener(view -> {
            answerId = getAnswerIdByButton(activityMainBinding.radioGroup.getCheckedRadioButtonId());

//            increment score if answer is right
            if (questions.get(questionIndex).checkAnswer(answerId)) {
//                increment user score
                score++;
            }

            questionIndex++;

//            change activity when quiz is end
            if (questionIndex == questions.size()) {
                endIntent.putExtra("score", score);

                startActivity(endIntent);
            } else {
                activityMainBinding.radioGroup.clearCheck();

                setDataToViews(questions.get(questionIndex));
            }
        });

        activityMainBinding.hintButton.setOnClickListener(view -> {
            hintIntent.putExtra("hint_text_id", questions.get(questionIndex).getHintTextId());
            hintIntent.putExtra("question_index", questionIndex);

            startActivity(hintIntent);
        });
    }

    private void setDataToViews(Question q) {
        //        set data to views
        activityMainBinding.logoImageView.setImageResource(q.getImageId());

        activityMainBinding.questionTextView.setText(q.getQuestionTextId());

        activityMainBinding.firstRadioButton.setText(q.getAnswersIds().get(0));
        activityMainBinding.secondRadioButton.setText(q.getAnswersIds().get(1));
        activityMainBinding.thirdRadioButton.setText(q.getAnswersIds().get(2));
    }

    private void addQuestion(int imageId, int questionTextId, int questionHintId, int answAId, int answBId, int answCId, int correctAnswId) {
        Question question = new Question(imageId, questionTextId, questionHintId, answAId, answBId, answCId, correctAnswId);

        questions.add(question);
    }

    private int getAnswerIdByButton(int buttonId) {
        if (activityMainBinding.firstRadioButton.getId() == buttonId) return 0;
        else if (activityMainBinding.secondRadioButton.getId() == buttonId) return 1;
        else return 2;
    }
}