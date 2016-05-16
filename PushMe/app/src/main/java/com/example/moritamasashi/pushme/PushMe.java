package com.example.moritamasashi.pushme;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PushMe extends Activity implements View.OnClickListener{

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private TextView text;
    private int Count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        text = new TextView(this);
        text.setTextColor(Color.BLACK);
        text.setTextSize(20);
        text.setText("Your Total is " + Count);
        text.setLayoutParams(new LinearLayout.LayoutParams(MP, WC));
        layout.addView(text);


        layout.addView(makeButton("Push Me","push"));
    }


    private Button makeButton(String name,String tag){
        Button button  = new Button(this);
        button.setOnClickListener(this);
        button.setText(name);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        button.setGravity(Gravity.CENTER_VERTICAL);
        button.setTag(tag);
        return button;
    }

    public void onClick(View view){
        String tag = (String)view.getTag();
        if(tag.equals("push")){
            Count++;
            text.setText("Your Total is " + Count);
        }
    }


}
