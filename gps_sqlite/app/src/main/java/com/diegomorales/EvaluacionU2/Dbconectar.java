package com.diegomorales.EvaluacionU2;

import android.content.Context;

import androidx.annotation.Nullable;

public class Dbconectar extends DatabaseHelper{
    Context context;
    public Dbconectar(@Nullable Context context) {
        super(context);
        this.context=context;
    }
//    public long insertarDatos(String nombre, String valor, String hora, String fecha, String observacion){
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(context);
//        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.COLUMN_TIPO_SENSOR);
//
//    }
}
