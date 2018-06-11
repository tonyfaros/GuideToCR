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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Anthony-PC on 4/6/2018.
 */

public class DownUpload {

    public String url_users = "https://guidetocr.herokuapp.com/usuarios.json";
    public String url_palabras = "https://guidetocr.herokuapp.com/words.json";
    public String url_lugares = "https://guidetocr.herokuapp.com/places.json";

    public String url_post_users = "";
    public String url_post_palabras = "https://guidetocr.herokuapp.com/words.json/";
    public String url_post_lugares = "https://guidetocr.herokuapp.com/places.json";

    public RequestQueue mQueue;

    public Context contexto;
    private Globales instance= Globales.getInstance();


    public DownUpload(Context context) {
        mQueue = Volley.newRequestQueue(context);
        this.contexto = context;
        get_users(url_users);
        get_palabras(url_palabras);
        get_lugares(url_lugares);
       // post_sug_lug("Zurqui","Lluvioso","1000","Montañoso","Preparese para caminar","Ruta 32",false);
        //post_sug_pal("Weiso","Feo,malo,solo","que weiso que este lloviendo",false);

        //new download_Users().execute();
    }

    public void get_users(String url) {

        Log.e("nombre","USERS");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                Log.e("JSON",response.toString());

                                String id = String.valueOf(response.getJSONObject(i).get("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).get("nombre"));
                                String correo = String.valueOf(response.getJSONObject(i).get("email"));
                                String contrasena = String.valueOf(response.getJSONObject(i).get("password"));
                                String admin = String.valueOf(response.getJSONObject(i).get("admin"));





                                Usuario usuario = new Usuario(Integer.parseInt(id), nombre, correo, contrasena, Boolean.parseBoolean(admin));
                                instance.agregar_usuario(usuario);
                                Log.e("usuarios--------",String.valueOf(instance.getLista_usuarios().size()));
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
        Log.e("usuarios",String.valueOf(instance.getLista_usuarios().size()));
        mQueue.add(jsonArrayRequest);
    }

    public void get_lugares(String url) {

        Log.e("nombre","LUGARES");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Log.e("JSON",response.toString());
                                String id =  String.valueOf(response.getJSONObject(i).get("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).get("nombre"));
                                String clima = String.valueOf(response.getJSONObject(i).get("clima"));
                                String cobro = String.valueOf(response.getJSONObject(i).get("tarifa"));
                                String descripcion = String.valueOf(response.getJSONObject(i).get("descripcion"));
                                String datos = String.valueOf(response.getJSONObject(i).get("datos"));
                                String ubicacion = String.valueOf(response.getJSONObject(i).get("ubicacion"));
                                String aceptado = String.valueOf(response.getJSONObject(i).get("aceptado"));

                                Log.e("id",String.valueOf(response.getJSONObject(i).get("id")));
                                Log.e("nombre",String.valueOf(response.getJSONObject(i).get("nombre")));
                                Log.e("clima",String.valueOf(response.getJSONObject(i).get("clima")));
                                Log.e("cobro",String.valueOf(response.getJSONObject(i).get("tarifa")));
                                Log.e("descripcion",String.valueOf(response.getJSONObject(i).get("descripcion")));


                                boolean ac = Boolean.parseBoolean(aceptado);


                                Lugar lugar = new Lugar(Integer.parseInt(id), nombre, clima, cobro, descripcion, datos, ubicacion, ac);
                                instance.agregar_lugar(lugar);
                                Log.e("lugar--------",String.valueOf(instance.getLista_lugares().size()));
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
        Log.e("lugar",String.valueOf(instance.getLista_lugares().size()));
        mQueue.add(jsonArrayRequest);
    }

    public void get_palabras(String url) {


        Log.e("nombre","PALABRAS");

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
                                Log.e("palabra--------",String.valueOf(instance.getLista_palabras().size()));
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
        Log.e("palabras",String.valueOf(instance.getLista_palabras().size()));
        mQueue.add(jsonArrayRequest);
    }

    public void post_sug_pal(final String palabra, final String descripcion, final String ejemplo, final boolean aceptado){

        RequestQueue requestQueue = Volley.newRequestQueue(contexto);

        StringRequest palabra_request = new StringRequest(Request.Method.POST, url_post_palabras,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nombre","weiso");
                //params.put("descripcion","que weiso");
                //params.put("ejemplo","muy weiso");
                //params.put("aceptado","false");



                return params;
            }
            public String getBodyContentType()
            {
                return "application/json";
            }

/*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                //headers.put("Content-Type", "application/json");
                return headers;
            }
*/
        };

        requestQueue.add(palabra_request);

    }





    public void post_sug_pal2(final String palabra, final String descripcion, final String ejemplo, final boolean aceptado){
        try{
            URL url = new URL(url_post_palabras);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

           /* conn.setRequestProperty("nombre",palabra);
            conn.setRequestProperty("descripcion",descripcion);
            conn.setRequestProperty("ejemplo",ejemplo);
            conn.setRequestProperty("aceptado",String.valueOf(aceptado));
*/
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("nombre",palabra);
            jsonParam.put("descripcion",descripcion);
            jsonParam.put("ejemplo",ejemplo);
            jsonParam.put("aceptado",aceptado);


            Log.e("JSON", jsonParam.toString());

            DataOutputStream dStream = new DataOutputStream(conn.getOutputStream());

            dStream.writeBytes(jsonParam.toString());

            dStream.flush();
            dStream.close();

            conn.disconnect();

            /*params.put("nombre","weiso");
                params.put("descripcion","que weiso");
                //params.put("ejemplo",ejemplo);
                //params.put("aceptado","false");*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void post_sug_pal3(final String palabra, final String descripcion, final String ejemplo, final boolean aceptado){


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

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("Content-Type", "application/x-www-form-urlencoded");
                return param;
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
