package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class addSensores extends AppCompatActivity {
    Button sensor1,sensor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensores);

        sensor1=(Button) findViewById(R.id.idSensor1);
        sensor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addSensores.this,SensoresParaAgregar.class);
                startActivity(intent);

            }
        });
        sensor2=(Button) findViewById(R.id.idSensor2);
    }
}