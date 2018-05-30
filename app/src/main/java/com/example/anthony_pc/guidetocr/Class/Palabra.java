package com.example.anthony_pc.guidetocr.Class;

/**
 * Created by Anthony-PC on 29/5/2018.
 */

public class Palabra {
    private int id;
    private String palabra;
    private String descripcion;
    private String ejemplo;
    private boolean aceptado;

    public Palabra(int id, String palabra, String descripcion, String ejemplo,boolean aceptado) {
        this.id = id;
        this.palabra = palabra;
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.aceptado = aceptado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
}
