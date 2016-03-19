package com.example.moritamasashi.mysensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MySensor extends Activity implements SensorEventListener {


    private final static String BR = System.getProperty("line.separator");
    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private TextView text;
    private SensorManager manager;

    private float[] inR = new float[16];
    private float[] outR = new float[16];

    private float[] accValues = new float[3];
    private float[] magValues = new float[3];
    private float[] oriValues = new float[3];
    private boolean accEnabled = false;
    private boolean magEnabled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);


        text = new TextView(this);
        text.setText("MySensor");
        text.setTextSize(24);
        text.setTextColor(Color.BLACK);
        text.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
        layout.addView(text);

        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }


    @Override
    public void onResume(){
        super.onResume();

        List<Sensor> sensors  = manager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor:sensors){
            int type = sensor.getType();
            if(type == Sensor.TYPE_ACCELEROMETER){
                manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
                accEnabled = true;
            }

            if(type == Sensor.TYPE_MAGNETIC_FIELD){
                manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
                magEnabled = true;
            }
        }
    }


    @Override
    public void onPause(){
        super.onPause();

        if(accEnabled || magEnabled){
            manager.unregisterListener(this);
            accEnabled = false;
            magEnabled = false;
        }
    }

    public void onSensorChanged(SensorEvent event){
        if(event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) return;

        int type = event.sensor.getType();

        if(type == Sensor.TYPE_ACCELEROMETER){
            accValues = event.values.clone();
        }
        else if(type == Sensor.TYPE_MAGNETIC_FIELD){
            magValues = event.values.clone();
        }

        if(accEnabled || magEnabled){
            SensorManager.getRotationMatrix(inR,null,accValues,magValues);

            SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Y, outR);

            SensorManager.getOrientation(outR, oriValues);

            oriValues[0] = (float)Math.toDegrees(oriValues[0]);
            oriValues[1] = (float)Math.toDegrees(oriValues[1]);
            oriValues[2] = (float)Math.toDegrees(oriValues[2]);

        }

        StringBuffer sb = new StringBuffer();
        sb.append("MySensor> " + BR);
        if(accEnabled){
            sb.append("Accelerometer[Division X] : "  + fm(accValues[0])+BR);
            sb.append("Accelerometer[Division Y] : "  + fm(accValues[1])+BR);
            sb.append("Accelerometer[Division Z] : "  + fm(accValues[2])+BR);
        }
        if(accEnabled && magEnabled){
            sb.append("Pitch[Division X] :" + fm(oriValues[1])+ BR);
            sb.append("Roll[Division Y] :" + fm(oriValues[2]) + BR);
            sb.append("Agimas[Division Z] :" + fm(oriValues[0]) + BR + BR);
        }
        text.setText(sb.toString());
    }

    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }

    private String fm(float value){
        return (value <= 0)?""+value:"+"+value;
    }


}
