package com.example.anthony_pc.guidetocr.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anthony_pc.guidetocr.Class.DownUpload;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.Class.RedditAPI;
import com.example.anthony_pc.guidetocr.Class.Usuario;
import com.example.anthony_pc.guidetocr.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextInputEditText emailTxt, passwordTxt;
    private Globales instance= Globales.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance.setListNull();



        getSupportActionBar().hide();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        emailTxt = (TextInputEditText) findViewById(R.id.correoTxt);
        passwordTxt = (TextInputEditText) findViewById(R.id.passwordTxt);
        emailTxt.setText("marilau63@gmail.com");
        passwordTxt.setText("1234");
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Profile profile = Profile.getCurrentProfile();
                        displayInfo(object,profile);

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields","first_name, last_name, email, id");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("prueba","preuba");

        DownUpload d = new DownUpload(this);


        //Palabra p = new Palabra(1,"Weiso","Feo,malo,solo","que weiso que este lloviendo",false);
        //send_post_word(p);
        Log.e("lugar",String.valueOf(instance.getLista_lugares().size()));
        Log.e("pal",String.valueOf(instance.getLista_palabras().size()));



    }



    public Usuario checkLogin(String correo,String contrasena){
        Log.e("usuar",String.valueOf(instance.getLista_usuarios().size()));
        for(Usuario i : instance.getLista_usuarios()){
            if(i.getCorreo().equals(correo) && i.getContrasena().equals(contrasena)){
                return i;
            }
        }return null;
    }

    public void ingresar(View view){
        Log.e("usuar",String.valueOf(instance.getLista_usuarios().size()));
        Usuario user = checkLogin(emailTxt.getText().toString(), passwordTxt.getText().toString());
        if( user != null){
            instance.setUsuario_actual(user);
            Intent intent;
            if(instance.getUsuario_actual().isAdmin())
                intent = new Intent(this, InicioAdmin.class);
            else
                intent = new Intent(this, Inicio.class);
            startActivity(intent);
            finish();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso")
                    .setMessage("Usuario no encontrado")
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }

    public void crearCuenta(View view){
        Intent intent = new Intent(this, Registrar_activity.class);
        //intent.putExtra("first_name", "");
        //intent.putExtra("last_name", "");
        //intent.putExtra("email", "");
        startActivity(intent);
    }

    private void send_post_word(Palabra palabra){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://guidetocr.herokuapp.com/words.json/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RedditAPI api = retrofit.create(RedditAPI.class);
        Call<Palabra> call = api.create_word(palabra);

        call.enqueue(new Callback<Palabra>() {
            @Override
            public void onResponse(Call<Palabra> call, retrofit2.Response<Palabra> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Palabra> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void displayInfo(JSONObject object, Profile profile ){
        String first_name , last_name, email;
        email = "";
        last_name = "";
        first_name = "";
        if(profile != null) {
            try {
                first_name = object.getString("first_name");
                last_name = object.getString("last_name");
                email = object.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(checkEmail(email) == null){
                Intent intent = new Intent(this, Registrar_activity.class);
                intent.putExtra("first_name", first_name);
                intent.putExtra("last_name", last_name);
                intent.putExtra("email", email);

                //intent.putExtra("persona", profile.getProfilePictureUri(100, 100).toString());

                startActivity(intent);
            }


        }else{
            Toast.makeText(this,"Perfil de facebook no existe",Toast.LENGTH_SHORT).show();
        }
    }

    public Usuario checkEmail(String email){
        for(Usuario i: instance.getLista_usuarios()){
            if(i.getCorreo().equals(email)){
                instance.setUsuario_actual(i);
                Intent intent = new Intent(this,Inicio.class);
                startActivity(intent);
            }
        }
        return null;
    }


}
