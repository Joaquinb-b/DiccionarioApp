package com.example.myapplication.entidades;

public class Palabras {

    private int id;
    private String Palabra;
    private String Definicion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String palabra) {
        Palabra = palabra;
    }

    public String getDefinicion() {
        return Definicion;
    }

    public void setDefinicion(String definicion) {
        Definicion = definicion;
    }
}
