package com.diegomorales.sql1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbSqliteHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_PRODUCTOS ="CREATE TABLE productos(" +
            " id_producto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " nom_producto TEXT UNIQUE NOT NULL," +
            " valor_neto DECIMAL NOT NULL)";

    private static final String SQL_DROP_TABLE_PRODCUTOS = "DROP TABLE IF EXISTS productos ";


    public MyDbSqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        sqliteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {
        sqliteDatabase.execSQL(SQL_DROP_TABLE_PRODCUTOS);
        onCreate(sqliteDatabase);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        super.onDowngrade(db,oldVersion,newVersion);
    }
}
