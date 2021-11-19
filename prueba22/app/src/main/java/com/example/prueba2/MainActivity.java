package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
     private Button btnAgregarSensores , btnMap;
     ListView rv;
     List<sensor> sensoresArrayList;
     ArrayAdapter<sensor> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregarSensores = (Button) findViewById(R.id.btnAgregarSensor);
        btnAgregarSensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AgregarSensorI = new Intent(MainActivity.this,addSensores.class);
                startActivity(AgregarSensorI);
            }
        });

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapActivity = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapActivity);
            }
        });


        rv=(ListView) findViewById(R.id.idListaSensores);
        DbConexion dbConexion =new DbConexion(this);
        sensoresArrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<sensor>(this, android.R.layout.simple_list_item_1,sensoresArrayList);
        rv.setAdapter(arrayAdapter);
        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){

                }

            }
        });
    }
        public void loadProductos(){
            sensoresArrayList.clear();
            DbConexion dbConexion = new DbConexion(MainActivity.this,"tabla_sensores",null,1);

            SQLiteDatabase Conecction = dbConexion.getWritableDatabase();

            Cursor cursor = Conecction.rawQuery("SELECT sensor, valores FROM tabla_sensores ORDER BY sensores ",null);
            Log.i("NUMERO REGISTROS", Integer.toString(cursor.getCount()));

            if(cursor.moveToFirst()){
                do{
                    Log.i("SENSOR", String.format("%s, %.1f\n",cursor.getString(0),cursor.getFloat(1)));
                    sensor sensor1 = new sensor(cursor.getString(0),cursor.getFloat(1));
                    sensoresArrayList.add(sensor1);
                }while(cursor.moveToNext());
                arrayAdapter.notifyDataSetChanged();
            }
            //productosDB.close();
        }




    }
