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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getQuestionTextId() {
        return questionTextId;
    }

    public void setQuestionTextId(int questionTextId) {
        this.questionTextId = questionTextId;
    }

    public int getHintTextId() {
        return hintTextId;
    }

    public void setHintTextId(int hintTextId) {
        this.hintTextId = hintTextId;
    }

    public ArrayList<Integer> getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(ArrayList<Integer> answersIds) {
        this.answersIds = answersIds;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public boolean checkAnswer(int answerId) {
        if (answerId == correctAnswerId) {
            return true;
        }

        return false;
    }
}
