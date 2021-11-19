package com.diegomorales.evaluacionu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NewActivity2 extends AppCompatActivity {
    EditText txtnombre,txtvalor,txtfecha,txthora,txtobservacion;
    Button bntguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);

        txtnombre=(EditText) findViewById(R.id.txt_nombre);
        txtvalor=(EditText) findViewById(R.id.txt_valor_sensor);
        txtfecha=(EditText) findViewById(R.id.txt_fecha);
        txthora=(EditText) findViewById(R.id.txt_hora);
        txtobservacion=(EditText) findViewById(R.id.txt_observacion);

        //BOTON QUE GUARDA SENSOR BASE DE DATOS
        bntguardar=(Button) findViewById(R.id.btn_guardar);
        bntguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbConexion dbConexion = new DbConexion(NewActivity2.this);
                long id = dbConexion.insertarSensor(txtnombre.getText().toString(),txtvalor.getText().toString(),txtfecha.getText().toString(),txthora.getText().toString(),txtobservacion.getText().toString());
                if (id>0){
                    Toast.makeText(NewActivity2.this,"Registro Exitoso ",Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(NewActivity2.this,"Error de almacenamiento de registro ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void limpiarCampos(){
        txtnombre.setText("");
        txtvalor.setText("");
        txtfecha.setText("");
        txthora.setText("");
        txtobservacion.setText("");
    }
}