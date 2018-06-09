package com.example.anthony_pc.guidetocr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony-PC on 9/6/2018.
 */

public class Palabra_adapter extends ArrayAdapter{

    private ArrayList<Palabra> lista_palabras = new ArrayList<>();

    public Palabra_adapter(Context context, ArrayList<Palabra> lista_palabras) {
        super(context,0,lista_palabras);
        this.lista_palabras = lista_palabras;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.palabra_item, null);



        return v;

    }
}
