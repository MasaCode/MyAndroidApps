package com.example.moritamasashi.randomball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

/**
 * Created by moritamasashi on 16/01/26.
 */
public class RandomBallView extends View {

    private Random r = new Random();
    private int randomX=0,randomY=0;
    private int x=2,y=2;
    private int radius=0;
    private int moveX=1,moveY=1;
    private boolean flag = true;

    public RandomBallView(Context text){
        super(text);
        setBackgroundColor(Color.WHITE);

        radius = 20;
        x = radius + x;
        y = radius + y;
    }


    public void Drawing(Canvas canvas,Paint paint,int x , int y){
        canvas.drawCircle(x + radius, y + radius, radius, paint);
    }


    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        int speed=0;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        Paint point = new Paint();
        point.setColor(Color.GREEN);
        point.setStyle(Paint.Style.FILL);
        speed = r.nextInt(4);
       // do {
            if (flag == true) {
                randomX = r.nextInt((getWidth() - (radius * 2))) - radius;
                randomY = r.nextInt((getHeight() - (radius * 2))) - radius;
                flag = false;
            }


            if (getWidth() < x || x < 0) {
                moveX = -1 * (moveX+speed);
            }
            if (y < 0 || getHeight() < y) {
                moveY = -(moveY+speed);
            }

            x += moveX;
            y += moveY;
            Drawing(canvas,paint,x,y);
            Drawing(canvas, point, randomX, randomY);
            invalidate();
        //}while(randomX != (int)x && randomY != (int)y);


    }
}
