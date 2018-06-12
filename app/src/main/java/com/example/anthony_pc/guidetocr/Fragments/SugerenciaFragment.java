package com.example.anthony_pc.guidetocr.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anthony_pc.guidetocr.Activities.Palabra_activity;
import com.example.anthony_pc.guidetocr.Activities.SLugarActivity;
import com.example.anthony_pc.guidetocr.Activities.SPalabraActivity;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.Class.Palabra;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SugerenciaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SugerenciaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SugerenciaFragment() {
        // Required empty public constructor
    }

    ArrayList<String> lista_sugerencias = new ArrayList<>();
    private Globales instance= Globales.getInstance();
    ArrayAdapter<String> adapter;
    ListView list;
    String mensaje;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sugerencia, container, false);
        mensaje = getArguments().getString("mensaje");


        if(mensaje.equals("Palabra")){
            for(Palabra i : instance.get_palabras_admin()){
                lista_sugerencias.add(i.getPalabra());
                Log.e("palabra--",i.getPalabra());
            }
        }else{
            for(Lugar i : instance.get_lugares_admin()){
                lista_sugerencias.add(i.getNombre());
                Log.e("lugar--",i.getNombre());
            }
        }

        list = view.findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,lista_sugerencias);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String)parent.getItemAtPosition(position);

                Intent intent;
                if(mensaje.equals("Palabra"))
                    intent = new Intent(getContext(), SPalabraActivity.class);
                else
                    intent = new Intent(getContext(), SLugarActivity.class);
                intent.putExtra("sugerencia",data);
                startActivity(intent);
                //Toast.makeText(getBaseContext(),lista_palabras.get(position),Toast.LENGTH_SHORT).show();
            }
        });
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
