package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myquizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityBinding;
    private QuestionViewModel questionViewModel;

    private int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bind activity
        activityBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(activityBinding.getRoot());

//        bind view model
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);


//        read and set saved data
        Bundle data = getIntent().getExtras();

        if (data != null) {
            questionIndex = data.getInt("question_index");
        }

        setDataToViews(questionViewModel.getQuestionList().get(questionIndex));


        //        add questions


//        change question on click
        activityBinding.answerButton.setOnClickListener(view -> {
            int answerId = getAnswerIdByButton(activityBinding.radioGroup.getCheckedRadioButtonId());

//            increment score if answer is right
            if (questionViewModel.getQuestionList().get(questionIndex).checkAnswer(answerId)) {
//                increment user score
                questionViewModel.setCounter(questionViewModel.getCounter() + 1);
            }

//            change activity when quiz is end
            if (questionIndex + 1 >= questionViewModel.getQuestionList().size()) {
                Intent endIntent = new Intent(MainActivity.this, EndActivity.class);

                endIntent.putExtra("score", questionViewModel.getCounter());

                startActivityForResult(endIntent, 0);

            }

            questionIndex++;

            activityBinding.radioGroup.clearCheck();

            setDataToViews(questionViewModel.getQuestionList().get(questionIndex));
        });

        activityBinding.hintButton.setOnClickListener(view -> {
            Intent hintIntent = new Intent(MainActivity.this, HintActivity.class);

            hintIntent.putExtra("hint_text_id", questionViewModel.getQuestionList().get(questionIndex).getHintTextId());
            hintIntent.putExtra("question_index", questionIndex);

            startActivityForResult(hintIntent, 0);
        });
    }

    private void setDataToViews(Question q) {
        //        set data to views
        activityBinding.logoImageView.setImageResource(q.getImageId());

        activityBinding.questionTextView.setText(q.getQuestionTextId());

        activityBinding.firstRadioButton.setText(q.getAnswersIds().get(0));
        activityBinding.secondRadioButton.setText(q.getAnswersIds().get(1));
        activityBinding.thirdRadioButton.setText(q.getAnswersIds().get(2));
    }

    private void addQuestion(int imageId, int questionTextId, int questionHintId, int answAId, int answBId, int answCId, int correctAnswId) {
        Question question = new Question(imageId, questionTextId, questionHintId, answAId, answBId, answCId, correctAnswId);

        questionViewModel.addQuestion(question);
    }

    private int getAnswerIdByButton(int buttonId) {
        if (activityBinding.firstRadioButton.getId() == buttonId) {
            return 0;
        } else if (activityBinding.secondRadioButton.getId() == buttonId) {
            return 1;
        } else {
            return 2;
        }
    }
}