package com.example.anthony_pc.guidetocr.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anthony_pc.guidetocr.R;

public class Palabra_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabra_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
