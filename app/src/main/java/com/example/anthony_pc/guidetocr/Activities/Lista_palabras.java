package com.example.anthony_pc.guidetocr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.anthony_pc.guidetocr.Adapters.Lugar_adapter;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.Class.Usuario;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

public class Lista_palabras extends AppCompatActivity {



    ListView list;

    ArrayList<String> listDef = new ArrayList<>();
    ArrayAdapter<String> adapter;

    ArrayList<String> lista_palabras = new ArrayList<>();

    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_palabras);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String mensaje;
        Intent intent = getIntent();
        mensaje = intent.getStringExtra("mensaje");
        if(mensaje.equals("abc")){
            for(Palabra i : instance.get_palabras_user()){
                lista_palabras.add(i.getPalabra());
                Log.e("palabra--",i.getPalabra());
            }
        }else{
            for(Palabra i : instance.return_list_letter(mensaje)){
                lista_palabras.add(i.getPalabra());
                Log.e("palabra--",i.getPalabra());
            }
        }

        listDef = lista_palabras;

        setTitle("Palabras");
        list = findViewById(R.id.list);

        Log.e("largooo",String.valueOf(lista_palabras.size()));

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista_palabras);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data=(String)parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), Palabra_activity.class);
                intent.putExtra("palabra",data);
                startActivity(intent);
                //Toast.makeText(getBaseContext(),lista_palabras.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
/*
    public String search_word(String word){
        for(String i : lista_palabras){
            if(i.equals(lista_palabras))
        }
    }*/
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                /*ArrayList<String> listaTemp = new ArrayList<>();
                if(s == null || s.trim().isEmpty()){
                    listaTemp = listDef;
                    return false;
                }
                for(String i : listDef){
                    Log.e("prueba--","prueba--");
                    if(i.contains(s.toLowerCase())){
                        listaTemp.add(i);
                    }
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listaTemp);
                list.setAdapter(adapter);*/
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SPalabraActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
