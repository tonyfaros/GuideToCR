package com.example.anthony_pc.guidetocr.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

public class Lugar_activity extends AppCompatActivity {

    ArrayList<String> datos = new ArrayList<>();
    LinearLayout lay_datos;

    ImageView image;

    Lugar lugar = null;
    private Globales instance= Globales.getInstance();

    TextView nombreTV, provinciaTV, climaTV, tarifaTV, descripcionTV, notasTV, datosTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreTV = findViewById(R.id.nombre);
        provinciaTV = findViewById(R.id.provincia);
        climaTV = findViewById(R.id.clima);
        tarifaTV = findViewById(R.id.tarifa);
        descripcionTV = findViewById(R.id.descripcion_text);
        datosTV = findViewById(R.id.title_datos);
        image = findViewById(R.id.imagen);

        try{
            String mensaje = "";
            Intent intent = getIntent();
            mensaje = intent.getStringExtra("mensaje");
            Log.e("lugar1111111",mensaje);
        }catch (Exception e){
            Log.e("error","error");
        }

        if(getIntent().getExtras().getString("mensaje") != null){

            lugar = instance.get_lugar(Integer.parseInt(getIntent().getStringExtra("mensaje")));
            Log.e("lugar1111111",getIntent().getExtras().getString("mensaje"));

            nombreTV.setText(lugar.getNombre());
            provinciaTV.setText(lugar.getProvincia());
            climaTV.setText(lugar.getClima());
            tarifaTV.setText(lugar.getCobro());
            descripcionTV.setText(lugar.getDescripcion());
            image.setImageBitmap(lugar.getFotoDef());
            //datosTV.setText(lugar.getDatos());

         }





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lay_datos = (LinearLayout) findViewById(R.id.datos_layout);

        getDatosList(lugar.getDatos().split(","));
        //datos.add("como");
        //datos.add("estas");
        //datos.add("?");
        populateDatos();

    }

    public void getDatosList(String[] datosList){

        for(String i: datosList){
            datos.add(i);
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


    private void populateDatos() {

        for(int i = 0; i<datos.size();i++){
            final TextView dato = new TextView(this);
            dato.setText("   " + datos.get(i));
            dato.setTextColor(Color.parseColor("#000000"));
            dato.setId(i);

            Drawable img = ContextCompat.getDrawable(getApplicationContext(),R.drawable.dot_icon);
            img.setBounds(0, 0, 95, 95);
            dato.setCompoundDrawables(img, null, null, null);
            //dato.setCompoundDrawablesWithIntrinsicBounds( R.drawable.dot_icon, 0, 0, 0);
            lay_datos.addView(dato);

        }
    }
}
