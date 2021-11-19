package com.diegomorales.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {
    Sensor magnetic;
    SensorManager sm;
    TextView X, Y, Z;
    List<Sensor> sensores1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        X = (TextView) findViewById(R.id.x);
        Y = (TextView) findViewById(R.id.y);
        Z = (TextView) findViewById(R.id.z);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensores1=sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

        magnetic = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_MAGNETIC_FIELD:
                X.setText(String.format(" X: %f", sensorEvent.values[0]) + " μT");
                Y.setText(String.format(" Y: %f", sensorEvent.values[1]) + " μT");
                Z.setText(String.format(" Z: %f", sensorEvent.values[2]) + " μT");
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
