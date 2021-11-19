package com.diegomorales.sql1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Producto> arrayAdapter;
    private TextView tvPHeader;
    private List<Producto> productos;
    private ListView lvProductos;
    private FloatingActionButton fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productos = new ArrayList<>();

        tvPHeader = new TextView(this);
        tvPHeader.setText("Lista de productos");
        tvPHeader.setTextSize(24f);
        tvPHeader.setTextColor(Color.CYAN);

        lvProductos = findViewById(R.id.lv_productos);
        lvProductos.addHeaderView(tvPHeader);
        arrayAdapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1, productos);
        lvProductos.setAdapter(arrayAdapter);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>=0){
                    Intent intent = new Intent(getApplicationContext(),DetalleProducto.class);
                    intent.putExtra("id_producto",productos.get(i-1).getIdProducto());
                    intent.putExtra("nom_producto",productos.get(i-1).getNomProducto());
                    intent.putExtra("valor_neto",productos.get(i-1).getValorNeto());
                    intent.setAction("VIEW");
                    startActivity(intent);
                }

            }
        });
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DetalleProducto.class);
                intent.setAction("ADD");
                startActivity(intent);
            }
        });
    }

    public void loadProductos(){
        productos.clear();
        MyDbSqliteHelper myDbSqliteHelper = new MyDbSqliteHelper(this,"productosDB",null,1);
        SQLiteDatabase productosDB = myDbSqliteHelper.getWritableDatabase();

        Cursor cursor = productosDB.rawQuery("SELECT id_producto, nom_producto, valor_neto FROM productos ORDER BY nom_producto ",null);
        Log.i("NUMERO REGISTROS", Integer.toString(cursor.getCount()));

        if(cursor.moveToFirst()){
            do{
                Log.i("PRODUCTO", String.format("%d, %s, %.1f\n",cursor.getInt(0),cursor.getString(1),cursor.getFloat(2)));
                Producto producto = new Producto(cursor.getInt(0),cursor.getString(1),cursor.getFloat(2));
                productos.add(producto);
            }while(cursor.moveToNext());
            arrayAdapter.notifyDataSetChanged();
        }
        //productosDB.close();
    }
    @Override
    protected void onResume(){
        super.onResume();
        loadProductos();
    }
}