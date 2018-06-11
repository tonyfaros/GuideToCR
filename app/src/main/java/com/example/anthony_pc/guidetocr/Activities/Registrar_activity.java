package com.example.anthony_pc.guidetocr.Activities;

import android.content.Context;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Usuario;
import com.example.anthony_pc.guidetocr.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

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

        if(getIntent().getExtras().getString("first_name") != null){
            editTextName.setText(getIntent().getExtras().getString("first_name")+ " " +getIntent().getExtras().getString("last_name"));
            editTextEmail.setText(getIntent().getExtras().getString("email"));
        }
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

            postNewComment(this,user.getNombre(),user.getCorreo(),user.getContrasena());

        }

        finish();

    }


    public void Atras(View view){

        finish();
    }

    public static void postNewComment(Context context, final String user, final String email, final String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST,"https://guidetocr.herokuapp.com/usuarios/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nombre",user);
                params.put("email",email);
                params.put("password",password);
                params.put("admin",String.valueOf(false));
                Log.e("asdf","Json:"+ new JSONObject(params));
                return params;


            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(sr);
    }

}
