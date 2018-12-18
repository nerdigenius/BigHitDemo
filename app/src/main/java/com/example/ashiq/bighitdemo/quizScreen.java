package com.example.ashiq.bighitdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class quizScreen extends AppCompatActivity implements View.OnClickListener{


    private Button start,reset;
    private int[] btn_id = {R.id.button, R.id.button2, R.id.button3, R.id.button4};
    private TextView timeTextView;
    private ProgressBar barProgress;
    private static final long START_TIME_IN_MILLISECONDS=10000;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long mTimeLeftInMilliseonds=START_TIME_IN_MILLISECONDS;
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        start=(Button)findViewById(R.id.start);
        reset=(Button)findViewById(R.id.btnreset);
        timeTextView=(TextView) findViewById(R.id.time);
        barProgress=(ProgressBar) findViewById(R.id.progressBar);
        listView=(ListView)findViewById(R.id.list) ;
        adapter=new Adapter(getApplicationContext());

        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        listView.setAdapter(adapter);
        QuestionsFragment questionsFragment=new QuestionsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2,questionsFragment).commit();





        updateCountDownText();

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
            }
        }.start();
        timerRunning=true;
        start.setText(R.string.Pause);
        reset.setEnabled(false);
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

    private void updateQuestion ()
    {
        QuestionsFragment questionsFragment=new QuestionsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2,questionsFragment).commit();
    }





}
