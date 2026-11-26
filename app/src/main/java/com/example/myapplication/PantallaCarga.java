package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaCarga extends AppCompatActivity {

    private volatile boolean activo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        Thread timer = new Thread(() -> {
            try {
                // 5 pasos de 500 ms = 2.5 segundos
                for (int i = 0; i < 5; i++) {
                    if (!activo) return;
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (activo) {
                    Intent abreVentanaPrincipal = new Intent(PantallaCarga.this, MainActivity.class);
                    startActivity(abreVentanaPrincipal);
                    finish(); // Cierra esta actividad para no regresar con el botón atrás
                }
            }
        });
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activo = false;
    }
}