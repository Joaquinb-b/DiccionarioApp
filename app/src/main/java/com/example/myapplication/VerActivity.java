package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPalabras;
import com.example.myapplication.entidades.Palabras;


public class VerActivity extends AppCompatActivity {

    EditText txtPalabra, txtDefinicion;
    Button btnGuarda, btnEditar, btnBorrar;
    Palabras palabra;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtPalabra = findViewById(R.id.txtPalabra);
        txtDefinicion = findViewById(R.id.txtDefinicion);
        btnGuarda = findViewById(R.id.btnGuardar);
        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);


        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbPalabras dbPalabras = new DbPalabras( VerActivity.this);
        palabra = dbPalabras.verPalabras(id);

        if (palabra != null){
            txtPalabra.setText(palabra.getPalabra());
            txtDefinicion.setText(palabra.getDefinicion());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtPalabra.setInputType(InputType.TYPE_NULL);
            txtDefinicion.setInputType(InputType.TYPE_NULL);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, editarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Deseas eliminar esta Palabra?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(dbPalabras.borrarPalabras(id)){
                            lista();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}