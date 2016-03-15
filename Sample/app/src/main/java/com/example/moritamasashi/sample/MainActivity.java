package com.example.moritamasashi.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//define the variable
    private int counter;
    private Button add,sub;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defenision of the variables
        counter = 0;
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        display = (TextView)findViewById(R.id.score);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                display.setText("Your total is " + counter);
            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                display.setText("Your total is " + counter);
            }
        });



       
    }
}
