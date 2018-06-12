package com.example.anthony_pc.guidetocr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

public class Palabra_activity extends AppCompatActivity {

    TextView nombreTV, descripcionTV, ejemploTV;
    private Globales instance= Globales.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabra_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreTV = findViewById(R.id.nombre);
        descripcionTV = findViewById(R.id.descripcion_text);
        ejemploTV = findViewById(R.id.ejemplo_text);

        String mensaje;
        Intent intent = getIntent();
        mensaje = intent.getStringExtra("palabra");

        Log.e("largooo",mensaje);

        Palabra palabra = instance.get_pal_string(mensaje);
        if(palabra != null){
            nombreTV.setText(palabra.getPalabra());
            descripcionTV.setText(palabra.getDescripcion());
            ejemploTV.setText(palabra.getEjemplo());
        }
        else{
            Toast.makeText(getBaseContext(),"Error cargando la palabra",Toast.LENGTH_SHORT).show();
        }
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
}
