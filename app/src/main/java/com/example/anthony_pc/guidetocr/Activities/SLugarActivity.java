package com.example.anthony_pc.guidetocr.Activities;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;



public class SLugarActivity extends AppCompatActivity {

    EditText nombreET,descripcionET,provinciaET,datosET,ubicacionET;
    RadioGroup radioGroup;
    Spinner spinner;


    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slugar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreET = findViewById(R.id.nombreLugarTxt);
        descripcionET = findViewById(R.id.descripcionTxt);
        provinciaET = findViewById(R.id.provinciaTxt);
        datosET = findViewById(R.id.datosTxt);
        ubicacionET = findViewById(R.id.ubicacionTxt);
        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.spinnerClima);

    }

    public void buttonAgregar(View view){
        String nombre,descripcion,provincia,datos,ubicacion,clima;
        nombre = nombreET.getText().toString();
        descripcion = descripcionET.getText().toString();
        provincia = provinciaET.getText().toString();
        datos = datosET.getText().toString();
        ubicacion = ubicacionET.getText().toString();
        TextView spinner3 = (TextView) spinner.getSelectedView();
        clima = spinner3.getText().toString();

        int id = radioGroup.getCheckedRadioButtonId();

        RadioButton rb = findViewById(id);
        String tarifa = rb.getText().toString();




        if(nombre.equals(" ") || descripcion.equals("") || provincia.equals("") || datos.equals("") || ubicacion.equals("")){
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
            Lugar l = new Lugar(instance.return_last_id_lugar(),nombre,clima,tarifa,descripcion,datos,ubicacion,false,"","lugar", BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.puente),provincia);
            instance.agregar_lugar(l);
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

    public void agarrarDatos(View view) {
    }

    public void agarrarImagenGaleria(View view) {
    }
}
