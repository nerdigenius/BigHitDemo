package com.example.ashiq.bighitdemo;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ashiq.bighitdemo.Model.QuestionsModel;
import com.example.ashiq.bighitdemo.Model.ScoreQuestionSingleton;

import java.util.ArrayList;


public class quizScreen extends AppCompatActivity implements View.OnClickListener{


    private Button start,reset;
    private int[] btn_id = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
    private TextView timeTextView;
    private ProgressBar barProgress;
    private static final long START_TIME_IN_MILLISECONDS=10000;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long mTimeLeftInMilliseonds=START_TIME_IN_MILLISECONDS;
    private ListView listView;
    private Adapter adapter;
    private Button[] btn = new Button[4];
    private Button btn_unfocus;
    private ScoreQuestionSingleton scoreQuestionSingleton;
    ArrayList<QuestionsModel> questionsModelsArray;
    private TextView questionText;
    private int QuestionNumber;
    private ConstraintLayout QuestionLayout;
    private Animation fab_open,fab_close;
    private Boolean EndOfQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        start=(Button)findViewById(R.id.start);
        reset=(Button)findViewById(R.id.btnreset);
        timeTextView=(TextView) findViewById(R.id.time);
        barProgress=(ProgressBar) findViewById(R.id.progressBar);
        questionText=(TextView) findViewById(R.id.questionText);
        listView=(ListView)findViewById(R.id.list) ;
        adapter=new Adapter(getApplicationContext());
        scoreQuestionSingleton = new ScoreQuestionSingleton(getApplicationContext());
        questionsModelsArray = scoreQuestionSingleton.getQuestions();
        QuestionNumber=0;
        QuestionLayout=(ConstraintLayout) findViewById(R.id.questionLayout);
        fab_open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        EndOfQuiz=false;

        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        listView.setAdapter(adapter);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.buttonborder);
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];

        fab_close.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                QuestionNumber++;
                updateQuestion(QuestionNumber);
                resetTimer();

                QuestionLayout.startAnimation(fab_open);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        updateCountDownText();
        updateQuestion(QuestionNumber);

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.start:
                if(timerRunning){
                    pauseTimer();
                }

                else{
                    startTimer();
                }
                break;
            case R.id.btnreset:
                    resetTimer();
                break;


            case R.id.button1:
                setFocus(btn_unfocus, btn[0]);
                break;

            case R.id.button2:
                setFocus(btn_unfocus, btn[1]);
                break;

            case R.id.button3:
                setFocus(btn_unfocus, btn[2]);
                break;

            case R.id.button4:
                setFocus(btn_unfocus, btn[3]);
                break;


        }
    }





    private void startTimer(){
        countDownTimer= new CountDownTimer(mTimeLeftInMilliseonds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                                mTimeLeftInMilliseonds=millisUntilFinished;
                                updateCountDownText();
            }

            @Override
            public void onFinish() {
                start.setText(R.string.Start);
                reset.setEnabled(true);
                timerRunning=false;
                updateCountDownText();
                if(EndOfQuiz!=true)
                {
                    checkAnswer();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    QuestionLayout.startAnimation(fab_close);

                }




            }
        }.start();
        timerRunning=true;
        start.setText(R.string.Pause);
        reset.setEnabled(false);
    }


    private void checkAnswer(){

        QuestionsModel questionsModel = questionsModelsArray.get(QuestionNumber);
        if ( btn_unfocus.getId() == btn_id[questionsModel.getAnswer()])
        {
            btn_unfocus.setBackgroundColor(0xFFFF0000);
        }
        else
        {

        }

    }



    private void resetTimer(){

        mTimeLeftInMilliseonds = START_TIME_IN_MILLISECONDS;
        updateCountDownText();
        reset.setEnabled(false);

    }



    private void pauseTimer(){
        countDownTimer.cancel();
        timerRunning=false;
        start.setText(R.string.Start);
        reset.setEnabled(true);
    }



    private void updateCountDownText(){

        long seconds = mTimeLeftInMilliseonds/1000;

        String progressPercent=Long.toString(seconds*10);

        timeTextView.setText(Long.toString(seconds));
        barProgress.setProgress(Integer.parseInt(progressPercent));

    }



    private void updateQuestion (int incomingQuestionNumber)
    {


        if(incomingQuestionNumber<questionsModelsArray.size())
        {
            QuestionsModel questionsModel = questionsModelsArray.get(incomingQuestionNumber);
            questionText.setText(questionsModel.getQuestions());


            btn[0].setText(questionsModel.getOptionA());
            btn[1].setText(questionsModel.getOptionB());
            btn[2].setText(questionsModel.getOptionC());
            btn[3].setText(questionsModel.getOptionD());
        }

        else
        {

            EndOfQuiz=true;
            questionText.setText("End of Questions!!!");

            btn[0].setText("");
            btn[1].setText("");
            btn[2].setText("");
            btn[3].setText("");
        }



    }



    private void setFocus(Button btn_unfocus, Button btn_focus){

        btn_unfocus.setBackgroundResource(R.drawable.buttonborder);
        btn_focus.setBackgroundResource(R.drawable.buttonborder2);
        this.btn_unfocus = btn_focus;
    }



}
