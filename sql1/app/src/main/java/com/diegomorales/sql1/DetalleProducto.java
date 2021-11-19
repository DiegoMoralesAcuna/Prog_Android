package com.diegomorales.sql1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DetalleProducto extends AppCompatActivity {

    private TextView etIdPrdoducto, etNomProducto, etValorneto;
    private Button btnGuardar;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        getSupportActionBar().setTitle("Detalles de Producto");

        etIdPrdoducto=(EditText)findViewById(R.id.et_idprod);
        etNomProducto=(EditText)findViewById(R.id.et_nomprod);
        etValorneto=(EditText)findViewById(R.id.et_valorneto);

        switch(getIntent().getAction().toUpperCase(Locale.ROOT)){
            case "VIEW":
                etIdPrdoducto.setFocusable(false);
                etNomProducto.setFocusable(false);
                etValorneto.setFocusable(false);
            case "EDIT":
                etIdPrdoducto.setText(String.format("%d", getIntent().getIntExtra("id_producto",0)));
                etNomProducto.setText(getIntent().getStringExtra("nom_producto"));
                etValorneto.setText(String.format("%.1f", getIntent().getFloatExtra("valor_neto",0f)));
                break;
            case "ADD":
                etIdPrdoducto.setText("");
                etNomProducto.setText("");
                etValorneto.setText("");
                getSupportActionBar().setTitle("Nuevo producto");
                break;
        }

        etNomProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("INFORMACION:","Antes de cambiar, " + etNomProducto.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("INFORMACION:","Cambiar, " + etNomProducto.getText().toString());
                btnGuardar.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("INFORMACION:","Cambió, " + etNomProducto.getText().toString());
            }
        });
        btnGuardar=(Button) findViewById(R.id.btnG);
        btnGuardar.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    public void guardar(View view){
        MyDbSqliteHelper myDbSqliteHelper = new MyDbSqliteHelper(DetalleProducto.this,"productosDB",null,1);
        SQLiteDatabase productosDB = myDbSqliteHelper.getWritableDatabase();

        String id = etIdPrdoducto.getText().toString();
        String nomProducto = etNomProducto.getText().toString();
        String valorNeto = etValorneto.getText().toString();

        if(!nomProducto.isEmpty()&&!valorNeto.isEmpty()){
            ContentValues rowProducto = new ContentValues();
            //rowProducto.put("id_producto",id);
            rowProducto.put("nom_producto",nomProducto);
            rowProducto.put("valor_neto",valorNeto);

            try {
                if(getIntent().getAction().equals("ADD")){
                    long idx = productosDB.insertOrThrow("productos",null,rowProducto);
                    etIdPrdoducto.setText(Long.toString(idx+1));
                    etNomProducto.setText("");
                    etValorneto.setText("");
                }else{
                    productosDB.update("productos",rowProducto,"id_producto=?",new String[]{id});
                    Toast.makeText(getBaseContext(),"Datos guardados",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getBaseContext(),"Datos almacenados",Toast.LENGTH_SHORT).show();
            }catch (SQLiteConstraintException e){
                Toast.makeText(getApplicationContext(),"id o nombre repetido",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            productosDB.close();
        }else{
            Toast.makeText(getBaseContext(),"Error faltan datos",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_producto,menu);
        if(getIntent().getAction().equals("ADD")){
            for(int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuNuevo:
                etIdPrdoducto.setFocusable(false);
                etNomProducto.setFocusable(true);
                etNomProducto.setFocusableInTouchMode(true);
                etNomProducto.setSelectAllOnFocus(true);
                etNomProducto.requestFocus();
                etValorneto.setFocusable(true);
                etNomProducto.setFocusableInTouchMode(true);
                break;
            case R.id.menu_elim:
                eliminar();
                break;
//            case R.id.home:
//                onBackPressed();
//                break;

            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }
    private void eliminar(){
        AlertDialog alertDialog = new AlertDialog.Builder(DetalleProducto.this)
                .setTitle("Eliminar")
                .setMessage("Eliminar el producto")
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDbSqliteHelper myDbSqliteHelper = new MyDbSqliteHelper(DetalleProducto.this,"productosDB",null,1);
                        SQLiteDatabase productosDB = myDbSqliteHelper.getWritableDatabase();
                        productosDB.delete("productos","id_productos=?",new String[]{etIdPrdoducto.getText().toString()});
                        Toast.makeText(getBaseContext(),"Producto eliminado",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
                })
                .create();
        alertDialog.show();

    }
}