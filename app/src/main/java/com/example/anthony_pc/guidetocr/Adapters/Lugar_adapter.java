package com.example.anthony_pc.guidetocr.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

/**
 * Created by Anthony-PC on 9/6/2018.
 */

public class Lugar_adapter extends ArrayAdapter {
    private ArrayList<Lugar> lista_lugares = new ArrayList<>();

    public Lugar_adapter(Context context, ArrayList<Lugar> lista_lugares) {
        super(context,0,lista_lugares);
        this.lista_lugares = lista_lugares;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.lugar_item, null);



        return v;

    }

}