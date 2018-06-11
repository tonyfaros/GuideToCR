package com.example.anthony_pc.guidetocr.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.anthony_pc.guidetocr.Adapters.Lugar_adapter;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;


public class Lugares extends Fragment {


    ArrayList<Lugar> List = new ArrayList<>();
    String[] nombre = {"RECETAS SALUDABLES","RECETAS COMIDA RÁPIDA","RECETAS DULCES", "TODAS LAS RECETAS"};
    String[] category = {"saludable","comida_rapida","dulce", "todas"};
    int[] images = {R.drawable.puente};

    Lugar_adapter adapter;
    ListView list;



    private OnFragmentInteractionListener mListener;

    public Lugares() {
        // Required empty public constructor
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




        adapter = new Lugar_adapter(getContext(),R.layout.list_view_items_lugares,List,list);
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
