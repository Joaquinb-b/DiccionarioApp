package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.entidades.Palabras;

import java.util.ArrayList;

public class DbPalabras extends DbHelper{

    Context context;

    public DbPalabras(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPalabras(String palabra, String definicion){
        long id = 0;
    try {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //funcion para insertar el registro
        ContentValues values = new ContentValues();
        values.put("palabra", palabra);
        values.put("definicion", definicion);

        //Esta funcion va a insertar el registro a nuestra tabla, y nos va a regresar la id
        id = db.insert(TABLE_PALABRAS, null, values);
    }catch (Exception ex){
        ex.toString();
    }
        return id;
    }

    public ArrayList<Palabras> mostrarPalabras(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Palabras> listaPalabras = new ArrayList<>();
        Palabras palabra = null;
        Cursor cursorPalabras = null;

        cursorPalabras = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);

        if(cursorPalabras.moveToFirst()){
            do{
                palabra = new Palabras();
                palabra.setId(cursorPalabras.getInt(0));
                palabra.setPalabra(cursorPalabras.getString(1));
                palabra.setDefinicion(cursorPalabras.getString(2));
                listaPalabras.add(palabra);
            }while (cursorPalabras.moveToNext());
        }

        cursorPalabras.close();

        return listaPalabras;
    }

    public Palabras verPalabras(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Palabras palabra = null;
        Cursor cursorPalabras;

        cursorPalabras = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorPalabras.moveToFirst()){
            palabra = new Palabras();
            palabra.setId(cursorPalabras.getInt(0));
            palabra.setPalabra(cursorPalabras.getString(1));
            palabra.setDefinicion(cursorPalabras.getString(2));
        }

        cursorPalabras.close();

        return palabra;
    }

    public boolean editarPalabras(int id, String palabra, String definicion){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Esta funcion va a insertar el registro a nuestra tabla, y nos va a regresar la id
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET palabra = '"+palabra+"', definicion = '"+definicion+"' WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean borrarPalabras(int id){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            //Esta funcion va a insertar el registro a nuestra tabla, y nos va a regresar la id
            db.execSQL("DELETE FROM " + TABLE_PALABRAS + " WHERE id = '"+ id +"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
