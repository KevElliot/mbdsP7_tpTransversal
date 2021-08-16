package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class JetonActivity extends AppCompatActivity {

    Button valider;
    ImageView backButton;
    EditText value;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeton);

        AndroidNetworking.initialize(getApplicationContext());

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("name","");
        setName(account);

        backButton = findViewById(R.id.BtnjetonsBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        value = (EditText) findViewById(R.id.demandeJetonValue);

        valider = findViewById(R.id.BtnvaliderDemande);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service.demandeJetons(Integer.parseInt(value.getText().toString()), sharedPreferences, getApplicationContext());
            }
        });


    }

    public void setName(String x){
        EditText myaccount;
        myaccount = findViewById(R.id.demandeJetonUser);
        myaccount.setText(x);
    }
}