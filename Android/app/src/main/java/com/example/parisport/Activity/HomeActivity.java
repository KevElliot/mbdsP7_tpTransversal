package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.androidnetworking.AndroidNetworking;

import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private boolean isChecked = false;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String SHARED_PREFS_PROFIL = "shared_prefs_profil";
    SharedPreferences sharedPreferences_profil;
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // initialisation network

        AndroidNetworking.initialize(getApplicationContext());

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPreferences_profil = getSharedPreferences(SHARED_PREFS_PROFIL, Context.MODE_PRIVATE);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        findViewById(R.id.imageProfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                Log.v("TONGA apres intent", "OK");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                // drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        String account = sharedPreferences.getString("name","");
        setMyaccount(account);
        String money = String.valueOf(sharedPreferences.getFloat("jetons",0.0f));
        setMymoney(money);

        String user = sharedPreferences.getString("_id","");

        Service.getUser(user, sharedPreferences_profil);

        // navDetailsHostFragment

        NavigationView navigationView = findViewById(R.id.navigationTopNav);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);

        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);

        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    public void setMyaccount(String x){
        TextView myaccount;
        myaccount = findViewById(R.id.myaccount);
        myaccount.setText(x);
    }

    public void setMymoney(String x){
        TextView mymoney;
        mymoney = findViewById(R.id.mymoney);
        mymoney.setText(x);
    }

}