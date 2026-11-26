package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Diccionario.db";
    public static final String TABLE_PALABRAS = "t_palabras";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    // Este se va a ejecutar al momento de llamar a esta clase para que pueda crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        SQLiteDatabase.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "palabra TEXT NOT NULL," +
                "definicion TEXT NOT NULL)");
    }


    // Se ejecuta cuando cambie la version de la Base de Datos, lo que va a hacer es borras la base que tenia y volver a hacerla
    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase, int oldVersion, int newVersion) {
        SQLiteDatabase.execSQL("DROP TABLE " + TABLE_PALABRAS);
        onCreate(SQLiteDatabase);



    }
}
