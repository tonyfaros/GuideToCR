package com.example.anthony_pc.guidetocr.Activities;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

public class Lugar_activity extends AppCompatActivity {

    ArrayList<String> datos = new ArrayList<>();
    LinearLayout lay_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lay_datos = (LinearLayout) findViewById(R.id.datos_layout);

        datos.add("hola");
        datos.add("como");
        datos.add("estas");
        datos.add("?");
        populateDatos();

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


    private void populateDatos() {

        for(int i = 0; i<datos.size();i++){
            final TextView dato = new TextView(this);
            dato.setText("•   " + datos.get(i));
            dato.setTextColor(Color.parseColor("#000000"));
            dato.setId(i);
            dato.setCompoundDrawablesWithIntrinsicBounds( R.drawable.dot_icon, 0, 0, 0);
            lay_datos.addView(dato);

        }
    }
}
