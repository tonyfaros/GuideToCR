package com.example.anthony_pc.guidetocr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.Activities.Lugar_activity;
import com.example.anthony_pc.guidetocr.Class.ItemDic;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

/**
 * Created by mariapizarro on 6/11/18.
 */

public class Dic_adapter extends ArrayAdapter {
    private ArrayList<ItemDic> lista_letras = new ArrayList<>();
    Context context;
    GridView listView;

    public Dic_adapter(Context context, int textViewResourceId, ArrayList<ItemDic> objects, GridView listView) {
        super(context,textViewResourceId,objects);
        this.context = context;
        this.lista_letras = objects;
        this.listView = listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.list_view_items_lugares, null);

        TextView textView = v.findViewById(R.id.letra);
        //ImageView imageView = v.findViewById(R.id.color);


        textView.setText(lista_letras.get(position).getLetra());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(context, Lugar_activity.class);
                intent.putExtra("mensaje", lista_letras.get(i).getLetra()); //Optional parameters/
                context.startActivity(intent);
            }
        });

        return v;

    }

}
