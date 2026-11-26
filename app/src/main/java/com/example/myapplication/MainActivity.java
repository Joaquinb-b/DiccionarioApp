package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adaptadores.listaPalabrasAdapter;
import com.example.myapplication.db.DbHelper;
import com.example.myapplication.db.DbPalabras;
import com.example.myapplication.entidades.Palabras;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    FloatingActionButton fabAgregar;
    RecyclerView listaPalabras;
    ArrayList<Palabras> listaArrayPalabras;
    listaPalabrasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaPalabras = findViewById(R.id.listaPalabras);
        fabAgregar = findViewById(R.id.fabAgregar);
        listaPalabras.setLayoutManager(new LinearLayoutManager(this));

        DbPalabras dbPalabras = new DbPalabras(MainActivity.this);

        listaArrayPalabras = new ArrayList<>();

        adapter = new listaPalabrasAdapter(dbPalabras.mostrarPalabras());
        listaPalabras.setAdapter(adapter);

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }


}