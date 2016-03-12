package com.example.moritamasashi.change;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void ChangeLabel(View myView) {
        int randNum=0;
        int range = (4 - 0) + 1;
        for(int i=0; i<10; i++) {
            randNum = (int) (Math.random() * range) + 0;
        }
        TextView label = (TextView)findViewById(R.id.MyTextView);
        if(randNum == 0){
            label.setText("Very Good!!");
        }else if(randNum == 1){
            label.setText("Good!!!");
        }else if(randNum == 2){
            label.setText("Nomal");
        }else if(randNum == 3){
            label.setText("Bad");
        }else if(randNum == 4){
            label.setText("Really Bad..");
        }else{
            label.setText("I don't know");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
