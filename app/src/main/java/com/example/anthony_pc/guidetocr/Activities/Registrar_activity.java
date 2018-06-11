package com.example.anthony_pc.guidetocr.Activities;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Usuario;
import com.example.anthony_pc.guidetocr.R;

import java.io.ByteArrayOutputStream;

import static okhttp3.internal.Internal.instance;

public class Registrar_activity extends AppCompatActivity {

    TextInputEditText editTextName, editTextEmail, editTextPassword;

    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_activity);
        getSupportActionBar().hide();

        editTextEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        editTextName = (TextInputEditText) findViewById(R.id.editTextName);
        editTextPassword = (TextInputEditText) findViewById(R.id.editTextPassword);
    }

    public void registerClick(View view){
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();


        if(name.equals("") || email.equals("") || password.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso")
                    .setMessage("Favor ingrese todos los datos")
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }else{
            //insertUser("https://moviles-backoffice.herokuapp.com/persona/",name,email,password,imageString);
            Usuario user = new Usuario(instance.return_last_id_user()+1,name,email,password,false);

            instance.agregar_usuario(user);

        }

        finish();

    }


    public void Atras(View view){

        finish();
    }



}
