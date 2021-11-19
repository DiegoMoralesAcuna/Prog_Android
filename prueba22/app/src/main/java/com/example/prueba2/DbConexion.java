package com.example.prueba2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbConexion extends ConecctionSqlite{

    Context context;

    public DbConexion(@Nullable Context context) {
        super(context);
        this.context=context;
    }

//    public void insertarDatos(){
//
//            ConecctionSqlite conecctionSqlite= new ConecctionSqlite(context);
//            SQLiteDatabase db =conecctionSqlite.getWritableDatabase();
//
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("sensor",datos1);
//            contentValues.put("valores",valores+"");
//
//            db.insertOrThrow(TABLE_SENSOR,null,contentValues);
//        }

    }

