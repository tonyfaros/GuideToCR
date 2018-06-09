package com.example.anthony_pc.guidetocr.Class;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony-PC on 4/6/2018.
 */

public class DownUpload {

    public String url_users = "https://guidetocr.herokuapp.com/user.json";
    public String url_palabras = "https://guidetocr.herokuapp.com/words.json";
    public String url_lugares = "https://guidetocr.herokuapp.com/places.json";

    public String url_post_users = "";
    public String url_post_palabras = "https://guidetocr.herokuapp.com/words";
    public String url_post_lugares = "https://guidetocr.herokuapp.com/places";


    public RequestQueue mQueue;

    public Context contexto;
    private Globales instance= Globales.getInstance();


    public DownUpload(Context context) {
        mQueue = Volley.newRequestQueue(context);
        this.contexto = context;
        //get_users(url_users);
        //get_palabras(url_palabras);
        //get_lugares(url_lugares);
        post_sug_lug("Zurqui","Lluvioso","1000","Montañoso","Preparese para caminar","Ruta 32",false);
        post_sug_pal("Weiso","Feo,malo,solo","que weiso que este lloviendo",false);

        //new download_Users().execute();
    }

    public void get_users(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {



                                int id = Integer.parseInt(response.getJSONObject(i).getString("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).getString("nombre"));
                                String correo = String.valueOf(response.getJSONObject(i).getString("correo"));
                                String contrasena = String.valueOf(response.getJSONObject(i).getString("contrasena"));
                                boolean admin = Boolean.parseBoolean(response.getJSONObject(i).getString("admin"));

                                Log.e("nombre",nombre);
                                Log.e("correo",correo);
                                Log.e("contrasena",contrasena);
                                Log.e("admin",String.valueOf(admin));



                                Usuario usuario = new Usuario(id, nombre, correo, contrasena, admin);
                                instance.agregar_usuario(usuario);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonArrayRequest);
    }

    public void get_lugares(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                String id =  String.valueOf(response.getJSONObject(i).get("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).get("nombre"));
                                String clima = String.valueOf(response.getJSONObject(i).get("clima"));
                                String cobro = String.valueOf(response.getJSONObject(i).get("tarifa"));
                                String descripcion = String.valueOf(response.getJSONObject(i).get("descripcion"));
                                String datos = String.valueOf(response.getJSONObject(i).get("datos"));
                                String ubicacion = String.valueOf(response.getJSONObject(i).get("ubicacion"));
                                String aceptado = String.valueOf(response.getJSONObject(i).get("aceptado"));


                                boolean ac = Boolean.parseBoolean(aceptado);


                                Lugar lugar = new Lugar(Integer.parseInt(id), nombre, clima, cobro, descripcion, datos, ubicacion, ac);
                                instance.agregar_lugar(lugar);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonArrayRequest);
    }

    public void get_palabras(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Log.e("JSON",response.toString());
                                String id = String.valueOf(response.getJSONObject(i).get("id"));
                                String palabra = String.valueOf(response.getJSONObject(i).get("nombre"));
                                String descripcion = String.valueOf(response.getJSONObject(i).get("descripcion"));
                                String ejemplo = String.valueOf(response.getJSONObject(i).get("ejemplo"));
                                String aceptado = String.valueOf(response.getJSONObject(i).get("aceptado"));


                                Palabra pa = new Palabra(Integer.parseInt(id), palabra, descripcion, ejemplo, Boolean.parseBoolean(aceptado));
                                instance.agregar_palabra(pa);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonArrayRequest);
    }

    public void post_sug_pal(final String palabra, final String descripcion, final String ejemplo, final boolean aceptado){

        StringRequest ingredienteRequest = new StringRequest(Request.Method.POST, url_post_palabras, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(contexto, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre",palabra);
                params.put("descripcion",descripcion);
                params.put("ejemplo",ejemplo);
                params.put("aceptado",String.valueOf(aceptado));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(ingredienteRequest);

    }

    public void post_sug_lug(final String nombre, final String clima, final String cobro,
                             final String descripcion, final String datos, final String ubicacion,final boolean aceptado){

        StringRequest ingredienteRequest = new StringRequest(Request.Method.POST, url_post_lugares, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(contexto, "Success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre",nombre);
                params.put("clima",clima);
                params.put("tarifa",cobro);
                params.put("descripcion",descripcion);
                params.put("datos",datos);
                params.put("ubicacion",ubicacion);
                params.put("aceptado",String.valueOf(aceptado));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(ingredienteRequest);

    }





/*


    private class download_Users extends AsyncTask<Void,Void,Void>{



        @Override
        protected Void doInBackground(Void... voids) {
            for(int i =0 ; i< 5; i++) {
                Log.e("users", "users");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new download_Palabras().execute();
        }
    }

    private class download_Palabras extends AsyncTask<Void,Void,Void>{

        public RequestQueue mQueue;
        public Context context;


        @Override
        protected Void doInBackground(Void... voids) {
            mQueue = Volley.newRequestQueue(context);
            for(int i =0 ; i< 5; i++) {
                Log.e("palabras", "palabras");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new download_Lugares().execute();
        }
    }

    private class download_Lugares extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i =0 ; i< 5; i++) {
                Log.e("lugares", "lugares");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }*/
}
