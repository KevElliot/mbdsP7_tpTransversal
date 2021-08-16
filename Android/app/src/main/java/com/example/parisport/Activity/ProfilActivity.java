package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.example.parisport.R;
import com.example.parisport.Service.Service;

public class ProfilActivity extends AppCompatActivity {

    Button logout, modification, demande;
    EditText name, email;
    ImageView backButton;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String SHARED_PREFS_PROFIL = "shared_prefs_profil";
    SharedPreferences sharedPreferences_profil;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        AndroidNetworking.initialize(getApplicationContext());

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPreferences_profil = getSharedPreferences(SHARED_PREFS_PROFIL, Context.MODE_PRIVATE);

        String account = sharedPreferences_profil.getString("name","");
        setUtilisateur(account);

        String accountEmail = sharedPreferences_profil.getString("email","");
        setEmail(accountEmail);

        String accountJetons = String.valueOf(sharedPreferences_profil.getFloat("jetons",0.0f));
        setJetons(accountJetons);

        String accountPassword = sharedPreferences_profil.getString("password","");
        setPassword(accountPassword);

        name = (EditText) findViewById(R.id.profilUtilisateur);
        email = (EditText) findViewById(R.id.profilEmail);

        logout = findViewById(R.id.BtnprofilLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service.logout(sharedPreferences, getApplicationContext());
            }
        });

        modification = findViewById(R.id.BtnprofilModification);
        modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // modification utilisateur
                // name & email
                // rest sharedpreference
            }
        });

        demande = findViewById(R.id.BtnprofilJetons);
        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, JetonActivity.class);
                startActivity(intent);
            }
        });

        backButton = findViewById(R.id.BtnprofilBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setUtilisateur(String x){
        EditText myaccount;
        myaccount = (EditText) findViewById(R.id.profilUtilisateur);
        myaccount.setText(x);
    }

    public void setEmail(String x){
        EditText myaccount;
        myaccount = (EditText) findViewById(R.id.profilEmail);
        myaccount.setText(x);
    }

    public void setJetons(String x){
        EditText myaccount;
        myaccount = (EditText) findViewById(R.id.profilJetons);
        myaccount.setText(x);
    }

    public void setPassword(String x){
        EditText myaccount;
        myaccount = (EditText) findViewById(R.id.profilPassword);
        myaccount.setText(x);
    }
}