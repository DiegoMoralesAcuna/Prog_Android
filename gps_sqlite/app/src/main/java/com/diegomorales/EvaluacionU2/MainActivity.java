package com.diegomorales.EvaluacionU2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper miDB;
    ListView listac;
    FloatingActionButton coordenadas;
    ArrayList<String> Listados;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listac = (ListView) findViewById(R.id.lv_coordenadas);
        miDB = new DatabaseHelper(this);

        Listados = miDB.getListaContenidos();
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,Listados);
        listac.setAdapter(adapter);


//        Listados = new ArrayList<>();
//        ArrayList<String> Listados = new ArrayList<>();
//        ArrayList data = miDB.getListaContenidos();
//        if (data.getCount() == 0 ){
//            Toast.makeText(this,"No hay lista que mostrar", Toast.LENGTH_LONG).show();
//        }else{
//            while (data.moveToNext()){
//                Listados.add(data.getString(1));
//                ListAdapter listAdapter = new ArrayAdapter<>(
//                        this, android.R.layout.simple_list_item_1,Listados);
//                listac.setAdapter(listAdapter);
//            }
//        }
        listac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto =(listac.getItemAtPosition(position).toString());
                String[] partes = texto.split(" ");
                String lat = partes[partes.length - 2 ];
                String lon = partes[partes.length - 1 ];

                Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:" + lat + "," + lon));
                Intent chooser = Intent.createChooser(i, "Ir al mapa");
                startActivity(chooser);
            }
        });

        coordenadas=(FloatingActionButton)findViewById(R.id.coordenadas);
        coordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,ListaCoordenadas.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nuevo_registro:
                Intent intent = new Intent(MainActivity.this,ListaSensores.class);
                startActivity(intent);
                break;
            case R.id.menu_eliminar:
                Intent i = new Intent(MainActivity.this,Listado.class);
                startActivity(i);
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }

}