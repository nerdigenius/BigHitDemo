package com.example.ashiq.bighitdemo.Model;

public class QuestionsModel {

    String questions,optionA,optionB,optionc,optionD;
    int Answer;

    public QuestionsModel(String questions, String optionA, String optionB, String optionc, String optionD, int answer) {
        this.questions = questions;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionc = optionc;
        this.optionD = optionD;
        this.Answer = answer;
    }

    public String getQuestions() {
        return questions;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionc;
    }

    public String getOptionD() {
        return optionD;
    }

    public int getAnswer() {
        return Answer;
    }
}
