package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbHelper;
import com.example.myapplication.db.DbPalabras;

public class MainActivity2 extends AppCompatActivity {

    EditText txtPalabra, txtDefinicion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtPalabra = findViewById(R.id.txtPalabra);
        txtDefinicion = findViewById(R.id.txtDefinicion);

        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPalabras dbPalabras = new DbPalabras(MainActivity2.this);
                long id = dbPalabras.insertarPalabras(txtPalabra.getText().toString(), txtDefinicion.getText().toString());

                if(id > 0){
                    Toast.makeText(MainActivity2.this, "Palabra Nueva Guardada", Toast.LENGTH_LONG).show();
                    limpiar();
                }else {
                    Toast.makeText(MainActivity2.this, "Error al Guardar Palabra", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtPalabra.setText("");
        txtDefinicion.setText("");
    }
}