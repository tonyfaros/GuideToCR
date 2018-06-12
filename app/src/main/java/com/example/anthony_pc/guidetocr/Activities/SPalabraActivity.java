package com.example.anthony_pc.guidetocr.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.anthony_pc.guidetocr.R;

public class SPalabraActivity extends AppCompatActivity {

    boolean sugerencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalabra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{
            String mensaje = getArguments().getString("sugerencia");
            Log.e("sugerencia1111111",mensaje);
            sugerencia = true;
        }catch (Exception e){
            sugerencia = false;
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
