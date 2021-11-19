package com.example.prueba2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class ConecctionSqlite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE ="db.sensores";
    public static final String TABLE_SENSOR = " tabla_sensores";

    public static final String CAMPO_SENSOR="sensor";
    public static final String CAMPO_DATOS="valores";



    public ConecctionSqlite(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " +
                ""+TABLE_SENSOR+" ("+CAMPO_SENSOR+" "+
                " TEXT, "+CAMPO_DATOS+" DECIMAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS tabla_sensores");
    onCreate(db);
    }
}
