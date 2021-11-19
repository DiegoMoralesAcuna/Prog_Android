package com.diegomorales.EvaluacionU2;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static final int BD_version=2;
    public static final String TABLE_NOMBRE ="SQLiteGPS";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DIRECCION="direccion";
    public static final String COLUMN_TIPO_SENSOR = "sensortipo";
    public static final String COLUMN_VALOR_SENSOR = "valorsensor";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_OBSERVACION = "observacion";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NOMBRE, null, BD_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLE_NOMBRE
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_DIRECCION + " VARCHAR ,"
                + COLUMN_TIPO_SENSOR + " VARCHAR ,"
                + COLUMN_VALOR_SENSOR + " VARCHAR ,"
                + COLUMN_FECHA + "VARCHAR , "
                + COLUMN_HORA + "VARCHAR ,"
                + COLUMN_OBSERVACION+"VARCHAR); ";
                 db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_ADD_FIELDS = "ALTER TABLE " + TABLE_NOMBRE + " ADD   " +
                COLUMN_TIPO_SENSOR + "VARCHAR "+
                COLUMN_VALOR_SENSOR + "VARCHAR"+
                COLUMN_FECHA + "VARCHAR"+
                COLUMN_HORA + "VARCHAR"+
                COLUMN_OBSERVACION + "VARCHAR";
        db.execSQL(SQL_ADD_FIELDS);
    }

    public boolean addData (String insertar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DIRECCION,insertar);
        long result = db.insert(TABLE_NOMBRE, null,contentValues);
        if (result== -1){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList getListaContenidos(){
        ArrayList<String> Listados = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String q =" SELECT * FROM " + TABLE_NOMBRE;
        Cursor data = db.rawQuery(q, null);
        if(data.moveToFirst()){
            do{
                Listados.add(data.getString(1));
            }while(data.moveToNext());
        }
        return Listados;
    }
    public boolean eliminarContacto(int id) {
        boolean correcto = false;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_NOMBRE + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public Sensores verSensor(int id) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Sensores sensores = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NOMBRE + " WHERE id = " + id + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            sensores = new Sensores();
            sensores.setId(cursor.getInt(0));
            sensores.setDireccion(cursor.getString(1));
            sensores.setSensortipo(cursor.getString(2));
            sensores.setValorsensor(cursor.getString(3));
        }

        cursor.close();

        return sensores;
    }


}
