package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbPalabras;
import com.example.myapplication.entidades.Palabras;


public class editarActivity extends AppCompatActivity {

    boolean correcto = false;
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

        btnEditar.setVisibility(View.INVISIBLE);
        btnBorrar.setVisibility(View.INVISIBLE);

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

        DbPalabras dbPalabras = new DbPalabras( editarActivity.this);
        palabra = dbPalabras.verPalabras(id);

        if (palabra != null){
            txtPalabra.setText(palabra.getPalabra());
            txtDefinicion.setText(palabra.getDefinicion());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPalabra.getText().toString().equals("") && !txtPalabra.getText().toString().equals("")){
                    correcto = dbPalabras.editarPalabras(id, txtPalabra.getText().toString(), txtDefinicion.getText().toString());
                    
                    if (correcto){
                        Toast.makeText(editarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else {
                        Toast.makeText(editarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(editarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}