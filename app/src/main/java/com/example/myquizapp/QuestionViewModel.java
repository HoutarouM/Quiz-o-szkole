package com.example.myquizapp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class QuestionViewModel extends ViewModel {
    private ArrayList<Question> questionList = new ArrayList<>();

    private int counter = 0;


    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void addQuestion(Question q) {
        this.questionList.add(q);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
