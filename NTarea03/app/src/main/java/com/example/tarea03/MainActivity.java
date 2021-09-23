package com.example.tarea03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SyncAdapterType;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
        private Spinner spinner;
        private ArrayAdapter<CharSequence> arrayAdapter;

        private ArrayList<String> datosRecycler;
        private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner) findViewById(R.id.spinner);
        arrayAdapter=ArrayAdapter.createFromResource(
                this, R.array.ciudades_array,
                android.R.layout.simple_spinner_item
        );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        //------------------------------------------------------------------
        recyclerView= (RecyclerView) findViewById(R.id.Recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false));
        datosRecycler=new ArrayList<>();
        for (int i=8; i<=50; i++){
            datosRecycler.add("Dato "+i+".");
        }
        AdapterRecycler adapterRecycler = new AdapterRecycler(datosRecycler);
        recyclerView.setAdapter(adapterRecycler);
    }
}