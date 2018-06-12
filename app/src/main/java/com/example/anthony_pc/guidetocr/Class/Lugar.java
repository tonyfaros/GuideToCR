package com.example.anthony_pc.guidetocr.Class;

import android.graphics.Bitmap;

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
    private String url;
    private String categoria;
    private Bitmap fotoDef;
    private String provincia;

    public Lugar(int id, String nombre, String clima, String cobro, String descripcion, String datos, String ubicacion, boolean aceptado, String url,
                 String categoria,Bitmap fotoDef, String provincia) {
        this.id = id;
        this.nombre = nombre;
        this.clima = clima;
        this.cobro = cobro;
        this.descripcion = descripcion;
        this.datos = datos;
        this.ubicacion = ubicacion;
        this.aceptado = aceptado;
        this.url = url;
        this.categoria = categoria;
        this.fotoDef = fotoDef;
        this.provincia = provincia;
    }

    /*public Lugar(int id, String nombre, String clima, String cobro, String descripcion, String datos, String ubicacion, boolean aceptado) {
        this.id = id;
        this.nombre = nombre;
        this.clima = clima;
        this.cobro = cobro;
        this.descripcion = descripcion;
        this.datos = datos;
        this.ubicacion = ubicacion;
        this.aceptado = aceptado;
    }
*/
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Bitmap getFotoDef() {
        return fotoDef;
    }

    public void setFotoDef(Bitmap fotoDef) {
        this.fotoDef = fotoDef;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
