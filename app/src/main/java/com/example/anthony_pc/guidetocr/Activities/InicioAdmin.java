package com.example.anthony_pc.guidetocr.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.anthony_pc.guidetocr.Class.Globales;
import com.example.anthony_pc.guidetocr.Fragments.InicioFragment;
import com.example.anthony_pc.guidetocr.Fragments.Palabras;
import com.example.anthony_pc.guidetocr.R;
import com.facebook.login.LoginManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class InicioAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InicioFragment.OnFragmentInteractionListener,  Palabras.OnFragmentInteractionListener {

    Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private Globales instance= Globales.getInstance();
    CircleImageView profile_image;
    TextView nombreTxt,correoTxt;

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

        View viewNav = navigationView.getHeaderView(0);
        profile_image = viewNav.findViewById(R.id.profile_image);
        nombreTxt = viewNav.findViewById(R.id.nombreTxt);
        correoTxt = viewNav.findViewById(R.id.correoTxt);

        try {
            nombreTxt.setText(instance.getUsuario_actual().getNombre());
            correoTxt.setText(instance.getUsuario_actual().getCorreo());
            if(instance.getUsuario_actual().isAdmin())
                profile_image.setImageDrawable(getResources().getDrawable(R.drawable.images));
            else
                profile_image.setImageDrawable(getResources().getDrawable(R.drawable.manati));


        } catch (NullPointerException e) {

        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("¿Desea salir de la aplicacion?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();}

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
            getSupportActionBar().setTitle("Guide To CR");
            fragment = new InicioFragment();
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            Intent intent = new Intent(this,Lista_lugares.class);
            startActivity(intent);

        } else if (id == R.id.nav_diccionario) {
            getSupportActionBar().setTitle("Diccionario");
            fragment = new Palabras();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

        } else if (id == R.id.nav_sugerencia) {
            getSupportActionBar().setTitle("Guide To CR");
            fragment = new InicioFragment();
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            Intent intent = new Intent(this,Sugerencias.class);
            startActivity(intent);

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
