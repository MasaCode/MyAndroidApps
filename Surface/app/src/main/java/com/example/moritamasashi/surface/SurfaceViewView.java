package com.example.moritamasashi.surface;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by moritamasashi on 16/01/26.
 */
public class SurfaceViewView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Thread thread;

    private Bitmap image;
    private int px = 0;
    private int py = 0;
    private int vx = 6;
    private int vy = 6;

    public SurfaceViewView(Context text){
        super(text);

        //input the image
        Resources r = getResources();
        image = BitmapFactory.decodeResource(r,R.drawable.icon);

        holder = getHolder();
        holder.addCallback(this);
    }

    //Create surface
    public void surfaceCreated(SurfaceHolder holder){
        thread = new Thread(this);
        thread.start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {}

    public void surfaceDestroyed(SurfaceHolder holder){
        thread = null;
    }

    //loop
    public void run(){
        while(thread != null){
            long nextTime = System.currentTimeMillis() + 30;
            onTick();
            try{
                Thread.sleep(nextTime-System.currentTimeMillis());
            }catch(Exception e){
            }
        }
    }


    private void onTick(){
        Canvas canvas = holder.lockCanvas();
        if(canvas == null){
            return;
        }
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(image,px-120,py-120,null);
        holder.unlockCanvasAndPost(canvas);

        if(px < 0 || getWidth() < px){
            vx = -vx;
        }
        if(py < 0 || getHeight() < py){
            vy = -vy;
        }
        px += vx;
        py += vy;
    }
}
