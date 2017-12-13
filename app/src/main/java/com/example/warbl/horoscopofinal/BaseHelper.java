package com.example.warbl.horoscopofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WarBl on 08-12-2017.
 */

public class BaseHelper extends SQLiteOpenHelper {

    String usuario = "CREATE TABLE usuarios(Correo text primary key, Nombre text, Apellido text, Password text)";
    String horoscopo = "CREATE TABLE prediccion(signo text primary key, amor text, salud text, dinero text)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(usuario);
        db.execSQL(horoscopo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE usuarios");
        db.execSQL("DROP TABLE prediccion");
        onCreate(db);


    }
}
