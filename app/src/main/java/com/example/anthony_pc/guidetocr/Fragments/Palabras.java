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
import com.example.anthony_pc.guidetocr.Class.ItemDic;
import com.example.anthony_pc.guidetocr.Class.Lugar;
import com.example.anthony_pc.guidetocr.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Palabras.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Palabras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Palabras extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Adapter adapter;
    GridView grid;
    ArrayList<ItemDic> List = new ArrayList<>();
    String[] letra = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    //int[] color = {R.drawable.vegetales,R.drawable.comida_rapida,R.drawable.postres,R.drawable.carne};



    private OnFragmentInteractionListener mListener;

    public Palabras() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Palabras newInstance(String param1, String param2) {
        Palabras fragment = new Palabras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_palabras, container, false);

        grid = (GridView) view.findViewById(R.id.grid);



        for(int i = 0;i<letra.length;i++){

            List.add(new ItemDic(letra[i],"abc"));
        }


        adapter = new Dic_adapter(getContext(),R.layout.activity_letra,List,grid);
        //grid.setAdapter(adapter);


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
