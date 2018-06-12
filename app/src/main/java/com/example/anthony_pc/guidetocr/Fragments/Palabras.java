package com.example.anthony_pc.guidetocr.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;

import com.example.anthony_pc.guidetocr.Adapters.Dic_adapter;
import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Class.ItemDic;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;


public class Palabras extends Fragment {


    Dic_adapter adapter;
    GridView grid;
    ArrayList<ItemDic> List = new ArrayList<>();
    String[] letra = {"abc","A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] color = {"#117A65","#D1352B","#5DADE2","#58D68D","#F4D03F","#95A5A6","#5B2C6F","#D88FBD","#E82E69","#2EE8DA","#5F3A3A","#E67E22","#D1352B","#9C428C","#A93226","#16A085","#A93226","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B","#D1352B"};



    private OnFragmentInteractionListener mListener;

    public Palabras() {
        // Required empty public constructor
    }

    private Globales instance= Globales.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance.return_list_letter("C");
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_palabras, container, false);

        grid = (GridView) view.findViewById(R.id.grid);



        for(int i = 0;i<letra.length;i++){

            List.add(new ItemDic(letra[i],color[i]));
        }


        adapter = new Dic_adapter(getContext(),R.layout.activity_letra,List,grid);
        grid.setAdapter(adapter);


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
