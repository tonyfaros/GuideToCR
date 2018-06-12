package com.example.anthony_pc.guidetocr.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.KeyEvent;

import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.anthony_pc.guidetocr.Adapters.Lugar_adapter;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Lugares extends ListFragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {


    ArrayList<Lugar> List = new ArrayList<>();
    String[] nombre = {"RECETAS SALUDABLES","RECETAS COMIDA RÁPIDA","RECETAS DULCES", "TODAS LAS RECETAS"};
    String[] category = {"saludable","comida_rapida","dulce", "todas"};
    int[] images = {R.drawable.puente};

    Lugar_adapter adapter;
    ListView list;

    int largoLista = 0;

    private Context context;

    private Globales instance= Globales.getInstance();



    private OnFragmentInteractionListener mListener;

    public Lugares() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu,inflater);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lugares, container, false);

        list = view.findViewById(R.id.list);

        //Bitmap image2 = ((BitmapDrawable)imageBtn.getBackground()).getBitmap();

        for(int i = 0;i<nombre.length;i++){

            List.add(new Lugar(i,nombre[i],"clima","cobro","descripcion","datos","ubicacion",true, BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.puente),category[i]));
        }

        largoLista = instance.get_lugares_user().size();

        Log.e("largooo",String.valueOf(instance.get_lugares_user().size()));

        adapter = new Lugar_adapter(getContext(),R.layout.list_view_items_lugares,instance.get_lugares_user(),list);
        list.setAdapter(adapter);
        return view;

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ArrayList<Lugar> listaTemp = new ArrayList<>();
        if(s == null || s.trim().isEmpty()){
            resetSearch();
            return false;
        }
        for(Lugar i : instance.get_lugares_user()){
            if(i.getNombre().toLowerCase().contains(s.toLowerCase())){
                listaTemp.add(i);
            }
        }
        adapter = new Lugar_adapter(getContext(),R.layout.list_view_items_lugares,listaTemp,list);
        list.setAdapter(adapter);

        return false;
    }

    public void resetSearch(){
        adapter = new Lugar_adapter(getContext(),R.layout.list_view_items_lugares,instance.get_lugares_user(),list);
        list.setAdapter(adapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
