package com.example.myquizapp;

import java.util.ArrayList;

public class Question {
    private int imageId;

    private int questionTextId;
    private int hintTextId;

    private ArrayList<Integer> answersIds = new ArrayList<>();

    private int correctAnswerId;

    public Question() {
    }

    public Question(int imageId, int questionTextId, int hintTextId, int answerATextId, int answerBTextId, int answerCTextId, int correctAnswerId) {
        this.imageId = imageId;
        this.questionTextId = questionTextId;
        this.hintTextId = hintTextId;

//        add answers to array
        this.answersIds.add(answerATextId);
        this.answersIds.add(answerBTextId);
        this.answersIds.add(answerCTextId);

        this.correctAnswerId = correctAnswerId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getQuestionTextId() {
        return questionTextId;
    }

    public int getHintTextId() {
        return hintTextId;
    }

    public ArrayList<Integer> getAnswersIds() {
        return answersIds;
    }

    public boolean checkAnswer(int answerId) {
        if (answerId == correctAnswerId) {
            return true;
        }

        return false;
    }
}
