package com.example.anthony_pc.guidetocr.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

public class SPalabraActivity extends AppCompatActivity {

    EditText palabraET,definicionET,ejemploET;
    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalabra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        palabraET = findViewById(R.id.palabraTxt);
        definicionET = findViewById(R.id.definicionTxt);
        ejemploET = findViewById(R.id.ejemploTxt);

    }

    public void buttonAgregar(View view){
        String palabra, definicion, ejemplo;
        palabra = palabraET.getText().toString();
        definicion = palabraET.getText().toString();
        ejemplo = palabraET.getText().toString();

        if(palabra.equals(" ") || definicion.equals("") || ejemplo.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso")
                    .setMessage("Complete los datos por favor")
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }else{
            Palabra p = new Palabra(instance.return_last_id_palabra(),palabra,definicion,ejemplo,false);
            instance.agregar_palabra(p);
            Toast.makeText(getBaseContext(),"Sugerencia enviada",Toast.LENGTH_SHORT).show();
            finish();
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
