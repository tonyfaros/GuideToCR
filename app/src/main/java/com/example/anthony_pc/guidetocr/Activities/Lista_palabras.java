package com.example.anthony_pc.guidetocr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anthony_pc.guidetocr.Adapters.Lugar_adapter;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.Class.Usuario;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

public class Lista_palabras extends AppCompatActivity {



    ListView list;



    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_palabras);

        String mensaje;
        Intent intent = getIntent();
        mensaje = intent.getStringExtra("mensaje");
        Log.e("letra1111111",mensaje);

        setTitle("Palabras");
        list = findViewById(R.id.list);

        final ArrayList<String> lista_palabras = new ArrayList<>();
        //Log.e("largooo",String.valueOf(instance.return_list_letter(mensaje).size()));

        for(Palabra i : instance.return_list_letter(mensaje)){
            lista_palabras.add(i.getPalabra());
            Log.e("palabra--",i.getPalabra());
        }
        Log.e("largooo",String.valueOf(lista_palabras.size()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista_palabras);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                Toast.makeText(getBaseContext(),lista_palabras.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        //largoLista = instance.get_lugares_user().size();



        //adapter = new Lugar_adapter(this,R.layout.list_view_items_lugares,instance.get_lugares_user(),list);



    }
}
