package com.example.anthony_pc.guidetocr.Class;

import android.util.Log;

import com.example.anthony_pc.guidetocr.Fragments.Palabras;

import java.lang.reflect.Array;
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

    public Usuario getUsuario_actual() {
        return usuario_actual;
    }

    public void setUsuario_actual(Usuario usuario_actual) {
        Globales.usuario_actual = usuario_actual;
    }

    public ArrayList<Usuario> getLista_usuarios() {
        return lista_usuarios;
    }

    public ArrayList<Lugar> getLista_lugares() {
        return lista_lugares;
    }

    public ArrayList<Palabra> getLista_palabras() {
        return lista_palabras;
    }


    public void agregar_usuario(Usuario usuario){
        lista_usuarios.add(usuario);
    }

    public void agregar_lugar(Lugar lugar){
        lista_lugares.add(lugar);
    }

    public void agregar_palabra(Palabra palabra){
        lista_palabras.add(palabra);
    }

    public Lugar get_lugar(int id_lugar){
        for(Lugar i : lista_lugares){
            if(i.getId() == id_lugar){
                return i;
            }
        }
        return null;
    }

    public Palabra get_palabra(int id_palabra){
        for(Palabra i : lista_palabras){
            if(i.getId() == id_palabra){
                return i;
            }
        }
        return null;
    }


    public ArrayList<Palabra> return_palabras(){
        ArrayList<Palabra> lista_palabras = new ArrayList<>();
        //Log.e("LARGO LISTA SEGUIDORES", String.valueOf(seguidoresList.size()));
        for(Palabra i : lista_palabras) {

        }
        return lista_palabras;
    }

    public int return_last_id_palabra(){
        int cont = 0;
        if(lista_palabras.isEmpty())
            return 0;
        else {
            for (Palabra i : lista_palabras) {
                if (i.getId() > cont) {
                    cont = i.getId();
                }
            }
        }
        return cont;
    }

    public int return_last_id_lugar(){
        int cont = 0;
        if(lista_lugares.isEmpty())
            return 0;
        else {
            for (Lugar i : lista_lugares) {
                if (i.getId() > cont) {
                    cont = i.getId();
                }
            }
        }
        return cont;
    }

    public int return_last_id_user(){
        int cont = 0;
        if(lista_usuarios.isEmpty())
            return 0;
        else {
            for (Usuario i : lista_usuarios) {
                if (i.getId() > cont) {
                    cont = i.getId();
                }
            }
        }
        return cont;
    }

    public ArrayList<Palabra> get_palabras_user(){
        ArrayList<Palabra> list = new ArrayList<>();
        for(Palabra i : lista_palabras){
            if(i.isAceptado()){
                list.add(i);
            }
        }
        return list;
    }

    public ArrayList<Palabra> get_palabras_admin(){
        ArrayList<Palabra> list = new ArrayList<>();
        for(Palabra i : lista_palabras){
            if(!i.isAceptado()){
                list.add(i);
            }
        }
        return list;
    }

    public ArrayList<Lugar> get_lugares_user(){
        ArrayList<Lugar> list = new ArrayList<>();
        for(Lugar i : lista_lugares){
            if(i.isAceptado()){
                list.add(i);
            }
        }
        return list;
    }

    public ArrayList<Lugar> get_lugares_admin(){
        ArrayList<Lugar> list = new ArrayList<>();
        for(Lugar i : lista_lugares){
            if(!i.isAceptado()){
                list.add(i);
            }
        }
        return list;
    }

    public ArrayList<Palabra> return_list_letter(String letter){
        char letra = letter.charAt(0);
        Log.e("letra", letter);
        Log.e("letra1", String.valueOf(letra));

        ArrayList<Palabra> list = new ArrayList<>();
        for(Palabra i : lista_palabras){
            Log.e("palabra1", String.valueOf(i.getPalabra().charAt(0)));

            if(i.getPalabra().charAt(0) >= letra){
                Log.e("letra", i.getPalabra());
                list.add(i);
            }
        }
        return list;
    }

}
