package com.example.ashiq.bighitdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionsFragment extends Fragment implements View.OnClickListener {

    private Button[] btn = new Button[4];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.button, R.id.button2, R.id.button3, R.id.button4};


    public QuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment, container, false);

        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) view.findViewById(btn_id[i]);
            btn[i].setBackgroundResource(R.drawable.buttonborder);
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];

        return view;
    }

    private void setFocus(Button btn_unfocus, Button btn_focus){
        btn_unfocus.setBackgroundResource(R.drawable.buttonborder);
        btn_focus.setBackgroundResource(R.drawable.buttonborder2);
        this.btn_unfocus = btn_focus;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
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
}
