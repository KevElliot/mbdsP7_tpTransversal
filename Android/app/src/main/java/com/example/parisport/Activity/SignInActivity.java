package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.example.parisport.Interface.async_call_back_login;
import com.example.parisport.Interface.async_call_back_paris;
import com.example.parisport.Modele.HistoriquePari;
import com.example.parisport.Modele.User;
import com.example.parisport.R;
import com.example.parisport.Service.Service;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SignInActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    ImageView backButton;
    ImageView qrButton;
    Button inButton;
    TextView register;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AndroidNetworking.initialize(getApplicationContext());

        List<HistoriquePari> historiquePariList = Service.getHistoriqueParisToJson();

        Log.d(TAG, "HISTO SIZE() : "+historiquePariList.size());

        /*
        Service.getListesParisParClient(new async_call_back_paris() {
            @Override
            public void getListesParisParClient(ArrayList<HistoriquePari> parisArrayList) {
                Log.d(TAG, "HISTO SIZE() : "+parisArrayList.size());
            }
        });
         */

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // initialisation
        edtEmail = (EditText) findViewById(R.id.editTextTextPersonName);
        edtPassword = (EditText) findViewById(R.id.editTextTextPassword);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        inButton = findViewById(R.id.signinBtn);

        inButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                Service.loginPost(email, password, sharedPreferences, getApplicationContext());

            }
        });

        backButton = findViewById(R.id.backBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }); 

        qrButton = findViewById(R.id.qrBtn);

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, QrActivity.class);
                startActivity(intent);
            }
        });

    }
}