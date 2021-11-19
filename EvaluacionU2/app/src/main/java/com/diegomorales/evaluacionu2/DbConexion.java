package com.diegomorales.evaluacionu2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.SimpleTimeZone;

public class DbConexion extends DataBaseSQLite {

    Context context;

    public DbConexion(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarSensor(String nombre, String valor, String fecha, String hora, String observaciones) {
        long id = 0;
        try {
            DataBaseSQLite dataBaseSQLite = new DataBaseSQLite(context);
            SQLiteDatabase db = dataBaseSQLite.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("nom_sensor", nombre);
            contentValues.put("valor_sensor", valor);
            contentValues.put("fecha_sensor", fecha);
            contentValues.put("hora_sensor", hora);
            contentValues.put("obs_sensor", observaciones);
//          contentValues.put("fecha_sensor",getNow());

            id = db.insert(TABLE_SENSOR, null, contentValues);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
//        private String getNow(){
//            //Set the Format to SQLiteDatabase Date Time
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date();
//            return dateFormat.format(date);
//    }

    public ArrayList<Sensores> leerSensores(){

        DataBaseSQLite dataBaseSQLite = new DataBaseSQLite(context);
        SQLiteDatabase db = dataBaseSQLite.getWritableDatabase();

        ArrayList<Sensores>listaSensores = new ArrayList<>();
        Sensores sensores = null;
        Cursor cursor = null;

        cursor= db.rawQuery("SELECT * FROM " + TABLE_SENSOR,null);

        if(cursor.moveToFirst()){
            do{
                sensores = new Sensores();
                sensores.setId(cursor.getInt(0));
                sensores.setNom_sensor(cursor.getString(1));
                sensores.setValor_sensor(cursor.getString(2));
                sensores.setFecha_sensor(cursor.getString(3));
                sensores.setHora_sensor(cursor.getString(4));
                sensores.setObservacion(cursor.getString(5));
                listaSensores.add(sensores);
            }while(cursor.moveToNext());
        }
        cursor.close();

        return listaSensores;
    }

}

