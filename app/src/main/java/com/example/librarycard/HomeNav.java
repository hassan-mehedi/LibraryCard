package com.example.librarycard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.provider.Telephony;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    DatabaseHelper databaseHelper;
    AlertDialog.Builder alertdiaglogbuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav);

        drawerLayout = findViewById(R.id.drawerId);
        NavigationView navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(HomeNav.this);
        navigationView.setItemIconTintList(null);

        toggle = new ActionBarDrawerToggle(HomeNav.this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            alertdiaglogbuilder = new AlertDialog.Builder(HomeNav.this);
            alertdiaglogbuilder.setTitle("Exit!");
            alertdiaglogbuilder.setIcon(R.drawable.exit);
            alertdiaglogbuilder.setMessage("Do you want to exit!");
            alertdiaglogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertdiaglogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = alertdiaglogbuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        if (menuItem.getItemId()==R.id.myInfoId){
            fragment = new MyInfo();
        }
        if (menuItem.getItemId()==R.id.myShelveId){
            fragment = new MyShelve();
        }
        if (menuItem.getItemId()==R.id.searchId){
            fragment = new Search();
        }
        if (menuItem.getItemId()==R.id.ebookId){
            fragment = new Ebook();
        }
        if (menuItem.getItemId()==R.id.tutorialId){
            Intent intent = new Intent(HomeNav.this,Tutorial.class);
            startActivity(intent);
        }
        if (menuItem.getItemId()==R.id.logOutId){
            Intent intent = new Intent(HomeNav.this,StartPage.class);
            startActivity(intent);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        if(fragment!=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        }

        return true;
    }
}
