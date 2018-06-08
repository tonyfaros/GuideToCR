package com.example.anthony_pc.guidetocr.Class;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Anthony-PC on 4/6/2018.
 */

public class Download{

    public String url_users = "";
    public String url_palabras = "";
    public String url_lugares = "";
    public RequestQueue mQueue;

    private Globales instance= Globales.getInstance();


    public Download(Context context) {
        mQueue = Volley.newRequestQueue(context);
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
                                String nombre = String.valueOf(response.getJSONObject(i).getString("seguidor"));
                                String correo = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                String contrasena = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                boolean admin = Boolean.parseBoolean(response.getJSONObject(i).getString("admin"));

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

                                int id = Integer.parseInt(response.getJSONObject(i).getString("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).getString("seguidor"));
                                String correo = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                String contrasena = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                boolean admin = Boolean.parseBoolean(response.getJSONObject(i).getString("admin"));

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

    public void get_palabras(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                int id = Integer.parseInt(response.getJSONObject(i).getString("id"));
                                String nombre = String.valueOf(response.getJSONObject(i).getString("seguidor"));
                                String correo = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                String contrasena = String.valueOf(response.getJSONObject(i).getString("seguido"));
                                boolean admin = Boolean.parseBoolean(response.getJSONObject(i).getString("admin"));

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
    }
}
