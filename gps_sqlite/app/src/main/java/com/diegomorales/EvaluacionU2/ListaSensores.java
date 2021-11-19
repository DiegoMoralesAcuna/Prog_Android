package com.diegomorales.EvaluacionU2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ListaSensores extends AppCompatActivity {
    EditText Valorsensor,Tiposensor,Fechasensor,Horasensor,observacion;
    DatabaseHelper miBD;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sensores);

        Tiposensor = (EditText) findViewById(R.id.txtTipoSensor1);
        Valorsensor = (EditText) findViewById(R.id.txtValorSensor2);
        Fechasensor = (EditText) findViewById(R.id.txtFechaSensor3);
        Horasensor = (EditText) findViewById(R.id.txtHoraSensor4);
        observacion = (EditText) findViewById(R.id.txtObservacionSensor5);
        miBD = new DatabaseHelper(this);
        btn = (Button) findViewById(R.id.btnGuardarSensor);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertaRegistro();
            }
        });
    }
    public void insertaRegistro(){
        DatabaseHelper databaseHelper= new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TIPO_SENSOR,Tiposensor.getText().toString());
        values.put(DatabaseHelper.COLUMN_VALOR_SENSOR,Valorsensor.getText().toString());
        values.put(DatabaseHelper.COLUMN_FECHA,Fechasensor.getText().toString());
        values.put(DatabaseHelper.COLUMN_HORA,Horasensor.getText().toString());
        values.put(DatabaseHelper.COLUMN_OBSERVACION,observacion.getText().toString());

        Long resultado=sqLiteDatabase.insert(DatabaseHelper.TABLE_NOMBRE,DatabaseHelper.COLUMN_ID,values);

        Toast.makeText(getApplicationContext(),"Registrado",Toast.LENGTH_LONG).show();
        sqLiteDatabase.close();
        }

}