package com.example.anthony_pc.guidetocr.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.anthony_pc.guidetocr.Fragments.InicioFragment;
import com.example.anthony_pc.guidetocr.Fragments.Palabras;
import com.example.anthony_pc.guidetocr.R;
import com.facebook.login.LoginManager;

public class InicioAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InicioFragment.OnFragmentInteractionListener,  Palabras.OnFragmentInteractionListener {

    Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Guide To CR");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        fragment = new InicioFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportActionBar().setTitle("Guide To CR");
            // Handle the camera action
            fragment = new InicioFragment();
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } else if (id == R.id.nav_place) {
            Intent intent = new Intent(this,Lista_lugares.class);
            startActivity(intent);

        } else if (id == R.id.nav_diccionario) {
            getSupportActionBar().setTitle("Diccionario");
            fragment = new Palabras();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

        } else if (id == R.id.nav_sugerencia) {
            getSupportActionBar().setTitle("Sugerencias");

        } else if (id == R.id.nav_exit) {
            LoginManager.getInstance().logOut();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void lugares(View view){
        Intent intent = new Intent(this,Lista_lugares.class);
        startActivity(intent);
    }

    public void diccionario(View view){
        fragment = new Palabras();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
