package com.example.stepdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView2 ,textView3;
    private SensorManager sensorManager;
    private Sensor nStepCounter , mStepDetector;
    private boolean isCounterSensorPresent , isDetectorSensorPresent;
    int stepCount = 0 ,stepDetect = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.activity_main);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) !=  null)
        {
            nStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }else {
            textView2.setText("This sensor is not available on your Device");
            isCounterSensorPresent = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null){
            mStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

            isDetectorSensorPresent= true;

        }
        else {
            textView3.setText("this sensor is not availble");
            isDetectorSensorPresent = false;
        }



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == nStepCounter){
            stepCount = (int) event.values[0];
            textView2.setText("Total step"+String.valueOf(stepCount));

        }else if (event.sensor==mStepDetector){
            stepDetect = (int) (stepDetect+event.values[0]);
            textView3.setText(String.valueOf(stepDetect));


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.registerListener(this,nStepCounter,SensorManager.SENSOR_DELAY_NORMAL);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null)
            sensorManager.registerListener(this,mStepDetector,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null)
            sensorManager.unregisterListener(this,nStepCounter);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null)if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!= null)
            sensorManager.unregisterListener(this,mStepDetector);

    }
}