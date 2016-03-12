package com.example.moritamasashi.gesture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by moritamasashi on 16/01/26.
 */
public class GestureView extends View implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    private ArrayList<String> info;
    private GestureDetector gestureDetector;

    public GestureView(Context text){
        super(text);
        setBackgroundColor(Color.WHITE);

        info = new ArrayList<String>();
        info.add("Gesture ");

        gestureDetector = new GestureDetector(text, this);
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        for(int i = 0; i< info.size(); i++){
            canvas.drawText((String)info.get(i) , 0 , 60 + 60*i, paint);
        }
    }

    //Adding infomation but if there are more than 30 lines, remove 1 and add 1
    //And also it is private function.
    private void addInfo(String str){
        info.add(1, str);
        while(info.size() > 30){
            info.remove(info.size()-1);
        }
        invalidate();
    }

    //it occured when something touch
    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return true;
    }

    //it calls when down
    public boolean onDown(MotionEvent event){
        addInfo("Down");
        return false;
    }

    //It calls when the finger presses screen whith no movement
    public void onShowPress(MotionEvent event){
        addInfo("ShowPress");
    }

    //It calls when up
    public boolean onSingleTapUp(MotionEvent event){
        addInfo("UP");
        return false;
    }

    //It calls when long press
    public void onLongPress(MotionEvent event){
        addInfo("LongPress");
    }

    //It calls when fling
    public boolean onFling(MotionEvent e0,MotionEvent e1, float velocityX,float velocityY){
        addInfo("Fling("+(int)velocityX + ","+(int)velocityY+")");
        return false;
    }

    //It calls when scrolling
    public boolean onScroll(MotionEvent e0,MotionEvent e1,float distanceX,float distanceY){
        addInfo("Scroll(" + (int)distanceX + "," + (int)distanceY + ")");
        return false;
    }

    //It calls when single tap
    public boolean onSingleTapConfirmed(MotionEvent event){
        addInfo("SingleTap");
        return false;
    }

    //It calls when Double tap
    public boolean onDoubleTap(MotionEvent event){
        addInfo("DoubleTap");
        return false;
    }

    //It calls when double tap event happens
    public boolean onDoubleTapEvent(MotionEvent event){
        addInfo("DoubleTapEvent");
        return false;
    }





}
