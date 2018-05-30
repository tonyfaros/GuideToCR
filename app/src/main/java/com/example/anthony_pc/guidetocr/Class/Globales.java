package com.example.anthony_pc.guidetocr.Class;

import java.util.ArrayList;

/**
 * Created by Anthony-PC on 29/5/2018.
 */

public class Globales {

    private static ArrayList<Usuario> lista_usuarios = new ArrayList<>();
    private static ArrayList<Lugar> lista_lugares = new ArrayList<>();
    private static ArrayList<Palabra> lista_palabras = new ArrayList<>();

    private static Usuario usuario_actual;

    private static Globales instance;

    public static Globales getInstance(){
        if(instance == null){
            instance = new Globales();
            usuario_actual = null;
        }
        return instance;
    }

    public void setListNull(){
        lista_usuarios = new ArrayList<>();
        lista_lugares = new ArrayList<>();
        lista_palabras = new ArrayList<>();
    }




}
