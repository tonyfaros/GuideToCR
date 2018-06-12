package com.example.anthony_pc.guidetocr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.Activities.Lugar_activity;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;
import java.util.List;


public class Lugar_adapter extends ArrayAdapter {
    private ArrayList<Lugar> lista_lugares = new ArrayList<>();
    Context context;
    ListView listView;



    public Lugar_adapter(Context context, int textViewResourceId, ArrayList<Lugar> lista_lugares, ListView listView) {
        super(context,0,lista_lugares);
        this.context = context;
        this.lista_lugares = lista_lugares;
        this.listView = listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.list_view_items_lugares, null);

        TextView textView = v.findViewById(R.id.titulo);
        ImageView imageView = v.findViewById(R.id.imagen);


        textView.setText(lista_lugares.get(position).getNombre());
        imageView.setImageBitmap(lista_lugares.get(position).getFotoDef());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.e("position",listView.getItemAtPosition(i).toString());
                Log.e("positio2",String.valueOf(lista_lugares.get(i).getId()));

                Intent intent = new Intent();
                intent.setClass(context, Lugar_activity.class);
                intent.putExtra("mensaje", String.valueOf(lista_lugares.get(i).getId())); //Optional parameters/
                context.startActivity(intent);
            }
        });

        return v;

    }

}
