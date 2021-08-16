package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.parisport.R;
import com.example.parisport.Service.Service;

public class SignUpActivity extends AppCompatActivity {

    EditText regName, regPassword, regEmail, regJetons;
    Button regSignup;
    ImageView regBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.registerName);
        regPassword = findViewById(R.id.registerPassword);
        regEmail = findViewById(R.id.registerEmail);
        regJetons = findViewById(R.id.registerJetons);
        regSignup = findViewById(R.id.signupBtn);
        regBackBtn = findViewById(R.id.registerBackBtn);

        regSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = regName.getText().toString();
                String password = regPassword.getText().toString();
                String email = regEmail.getText().toString();
                String jetons = regJetons.getText().toString();
                // call service register
                Service.register(name, password, email, jetons, getApplicationContext());
            }
        });

        regBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}