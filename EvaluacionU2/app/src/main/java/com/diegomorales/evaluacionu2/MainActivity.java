package com.diegomorales.evaluacionu2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_sensores;
    ArrayList<Sensores> sensoresArrayList;
    //ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_sensores=(RecyclerView) findViewById(R.id.rv_sensores);
        rv_sensores.setLayoutManager(new LinearLayoutManager(this));

        DbConexion dbConexion = new DbConexion(MainActivity.this);

        sensoresArrayList = new ArrayList<>();

        Adaptador adaptador = new Adaptador(dbConexion.leerSensores());
        rv_sensores.setAdapter(adaptador);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menu1:
                newRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    private void newRegistro(){
        Intent intent = new Intent (this,NewActivity2.class);
        startActivity(intent);
    }
}