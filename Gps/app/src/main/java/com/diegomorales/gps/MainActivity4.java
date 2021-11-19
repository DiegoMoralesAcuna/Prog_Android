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

public class MainActivity4 extends AppCompatActivity implements SensorEventListener {

    Sensor presion;
    SensorManager sm;
    TextView txtpresion;
    List<Sensor> sensores2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtpresion=(TextView) findViewById(R.id.txt_presion);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensores2=sm.getSensorList(Sensor.TYPE_PRESSURE);

        presion = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener(this, presion, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_PRESSURE:
                txtpresion.setText(String.format(" %f", sensorEvent.values[0]) + " hPa");
                break;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}