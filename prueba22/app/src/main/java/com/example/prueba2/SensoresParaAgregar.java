package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensoresParaAgregar extends AppCompatActivity implements SensorEventListener {
    TextView valores, datos1;
    Sensor magnetic;
    SensorManager sm;
    List<Sensor> sensores1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_para_agregar);

        datos1=(TextView)findViewById(R.id.datos1);
        valores=(TextView)findViewById(R.id.valores_sensor);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensores1=sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);

        magnetic = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);

        btn1=(Button) findViewById(R.id.btnGuardar1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarDatos();

            }
        });
    }

        private void RegistrarDatos() {
            ConecctionSqlite conecctionSqlite= new ConecctionSqlite(this);
            SQLiteDatabase db =conecctionSqlite.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(ConecctionSqlite.CAMPO_DATOS,datos1.getText().toString());
            values.put(ConecctionSqlite.CAMPO_SENSOR,valores.getText().toString());
            Long idresultado=db.insert(ConecctionSqlite.TABLE_SENSOR,ConecctionSqlite.CAMPO_DATOS,values);
            Toast.makeText(getApplicationContext(),"Sensor Registrado" ,Toast.LENGTH_LONG).show();
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_MAGNETIC_FIELD:
                valores.setText(String.format("%f",sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}