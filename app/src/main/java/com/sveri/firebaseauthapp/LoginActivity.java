package com.sveri.firebaseauthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegLink;
    EditText etEmail, etPwd;
    Button btnSubmit,btnCancel;
    ProgressBar progressBar;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialiseWidgets();

        mAuth = FirebaseAuth.getInstance();
        tvRegLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    public void initialiseWidgets(){

        etEmail = findViewById(R.id.editTextEmailAddress);
        etPwd = findViewById(R.id.editTextPassword);
        tvRegLink = findViewById(R.id.textViewRegisterLink);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnCancel = findViewById(R.id.buttonCancel);
        progressBar = findViewById(R.id.progressBarLogin);
    }
}