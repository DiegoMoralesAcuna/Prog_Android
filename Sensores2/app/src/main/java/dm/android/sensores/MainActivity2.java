package dm.android.sensores;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements  SensorEventListener {
    TextView X, Y, Z, datos1, datos2, txt_presion, txt_info, txt_info2;
    Sensor magnetic, presion;
    SensorManager sm;
    List<Sensor> sensores1,sensores2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //TextView sensor magnetico
        datos1=(TextView)findViewById(R.id.datos1);
        X = (TextView) findViewById(R.id.x);
        Y = (TextView) findViewById(R.id.y);
        Z = (TextView) findViewById(R.id.z);
        //TxtView sensor gravedad
        datos2=(TextView)findViewById(R.id.datos2);
        txt_presion=(TextView)findViewById(R.id.txt_presion);
        txt_info=(TextView)findViewById(R.id.txt_info);
        txt_info2=(TextView)findViewById(R.id.txt_info2);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensores1=sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        sensores2=sm.getSensorList(Sensor.TYPE_PRESSURE);

        int i = 1;
        for (Iterator<Sensor> it = sensores1.iterator(); it.hasNext(); ){
            Sensor sensor = it.next();
            txt_info.append(String.format("Name: %s \nPower: %s \nVendor: %s \nVersion: %s",sensor.getName(), sensor.getPower(), sensor.getVendor(), sensor.getVersion()));
        }
        int i1 = 1;
        for (Iterator<Sensor> it = sensores2.iterator(); it.hasNext(); ){
            Sensor sensor = it.next();
            txt_info2.append(String.format("Name:%s \nPower:%s  \nVendor:%s \nVersion: %s",sensor.getName(), sensor.getPower(), sensor.getVendor(), sensor.getVersion()));
        }

        magnetic = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);

        presion= sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener(this, presion, SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_MAGNETIC_FIELD:
                X.setText(String.format(" X: %f",sensorEvent.values[0]) + " μT");
                Y.setText(String.format(" Y: %f",sensorEvent.values[1]) + " μT");
                Z.setText(String.format(" Z: %f",sensorEvent.values[2]) + " μT");
                break;
            case Sensor.TYPE_PRESSURE:
                txt_presion.setText(String.format(" %f",sensorEvent.values[0]) + " hPa");
                break;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
