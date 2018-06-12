package com.example.anthony_pc.guidetocr.Activities;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.anthony_pc.guidetocr.Adapters.Lugar_adapter;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.Fragments.Lugares;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

public class Lista_lugares extends AppCompatActivity {

    Lugar_adapter adapter;
    ListView list;

    int largoLista = 0;

    private Context context;

    private Globales instance= Globales.getInstance();



    private Lugares.OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lugares);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setTitle("Lugares");
        list = findViewById(R.id.list);


        largoLista = instance.get_lugares_user().size();

        Log.e("largooo",String.valueOf(instance.get_lugares_user().size()));

        adapter = new Lugar_adapter(this,R.layout.list_view_items_lugares,instance.get_lugares_user(),list);
        list.setAdapter(adapter);
    }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SLugarActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                //adapter.getFilter().filter(s);
                ArrayList<Lugar> listaTemp = new ArrayList<>();
                if(s == null || s.trim().isEmpty()){
                    resetSearch();
                    return false;
                }
                for(Lugar i : instance.get_lugares_user()){
                    if(i.getNombre().toLowerCase().contains(s.toLowerCase())){
                        listaTemp.add(i);
                    }
                }
                adapter = new Lugar_adapter(getApplicationContext(),R.layout.list_view_items_lugares,listaTemp,list);
                list.setAdapter(adapter);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public void resetSearch(){
        adapter = new Lugar_adapter(this,R.layout.list_view_items_lugares,instance.get_lugares_user(),list);
        list.setAdapter(adapter);
    }
}
