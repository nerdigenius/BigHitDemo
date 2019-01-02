package com.example.ashiq.bighitdemo.Model;

public class QuestionsModel {

    String questions,optionA,optionB,optionc,optionD,Answer;

    public QuestionsModel(String questions, String optionA, String optionB, String optionc, String optionD, String answer) {
        this.questions = questions;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionc = optionc;
        this.optionD = optionD;
        Answer = answer;
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

    public String getAnswer() {
        return Answer;
    }
}
