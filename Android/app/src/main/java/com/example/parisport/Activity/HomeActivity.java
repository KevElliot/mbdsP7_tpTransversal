package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.parisport.Adapter.ViewPagerAdapter;
import com.example.parisport.Fragments.HistoAllFragment;
import com.example.parisport.Fragments.HistoInProgressFragment;
import com.example.parisport.Fragments.HistoWinFragment;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;

import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // initialisation network

        AndroidNetworking.initialize(getApplicationContext());



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
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

        // navDetailsHostFragment

        NavigationView navigationView = findViewById(R.id.navigationTopNav);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);

        NavController navController = Navigation.findNavController(this,R.id.navHostFragment);

        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPagerAdapter.addFragment(new HistoAllFragment(), "Tous");
        viewPagerAdapter.addFragment(new HistoInProgressFragment(), "Encours");
        viewPagerAdapter.addFragment(new HistoWinFragment(), "Gagnes");
        viewPager.setAdapter(viewPagerAdapter);
    }
}