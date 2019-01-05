package com.example.ashiq.bighitdemo.Model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ScoreQuestionSingleton {


    private Context context;
    public ScoreQuestionSingleton(Context context) {

        this.context=context;

    }

    public ArrayList<QuestionsModel> getQuestions()
    {
        ArrayList <QuestionsModel> questionsModelsArray = new ArrayList<QuestionsModel>();
        QuestionsModel questionsModel;

        try{
            InputStream is= context.getAssets().open("questions.json");
            int size= is.available();
            byte [] buffer= new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer,"UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject obj= jsonArray.getJSONObject(i);
                String question= obj.getString("questions");
                String optionA= obj.getString("optionA");
                String optionB= obj.getString("optionB");
                String optionC=obj.getString("optionC");
                String optionD=obj.getString("optionD");
                int Answer=obj.getInt("Answer");

                questionsModel = new QuestionsModel(question,optionA,optionB,optionC,optionD,Answer);

                questionsModelsArray.add(questionsModel);

            }

            return questionsModelsArray;

        }
        catch (IOException e)

        {
            e.printStackTrace();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;


    }
}
