package com.diegomorales.evaluacionu2;

import static java.sql.Types.INTEGER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseSQLite extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NOMBRE ="db.sensores";
        public static final String TABLE_SENSOR = " tabla_sensores";


    public DataBaseSQLite(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SENSOR + "("+
                " id_sensor INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " nom_sensor TEXT UNIQUE NOT NULL," +
                " valor_sensor TEXT NOT NULL," +
                " fecha_sensor TEXT," +
                " hora_sensor TEXT," +
                " obs_sensor TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SENSOR);
        onCreate(sqLiteDatabase);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
