package com.example.anthony_pc.guidetocr.Class;

/**
 * Created by Anthony-PC on 29/5/2018.
 */

public class Lugar {

    private int id;
    private String nombre;
    private String clima;
    private String cobro;
    private String descripcion;
    private String datos;
    private String ubicacion;
    private boolean aceptado;

    public Lugar(int id, String nombre, String clima, String cobro, String descripcion, String datos, String ubicacion,boolean aceptado) {
        this.id = id;
        this.nombre = nombre;
        this.clima = clima;
        this.cobro = cobro;
        this.descripcion = descripcion;
        this.datos = datos;
        this.ubicacion = ubicacion;
        this.aceptado = aceptado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getCobro() {
        return cobro;
    }

    public void setCobro(String cobro) {
        this.cobro = cobro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
}
