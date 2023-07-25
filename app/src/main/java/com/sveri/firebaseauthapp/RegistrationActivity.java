package com.sveri.firebaseauthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    TextView tvLoginLink;
    EditText etEmail, etPwd;
    Button btnSubmit,btnCancel;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initialiseWidgets();
        mAuth = FirebaseAuth.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerNewUser();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etEmail.setText(null);
                etPwd.setText(null);
                etPwd.requestFocus();
            }
        });

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    public void initialiseWidgets(){

        etEmail = findViewById(R.id.editTextRegEmailAddress);
        etPwd = findViewById(R.id.editTextRegPassword);
        tvLoginLink = findViewById(R.id.textViewLoginLink);
        btnSubmit = findViewById(R.id.buttonRegisster);
        btnCancel = findViewById(R.id.buttonRegCancel);
        progressBar = findViewById(R.id.progressBarRegister);
    }

    public void registerNewUser(){

        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Email cannot be empty");
            return;
        }
        if (pwd.isEmpty()){
            etPwd.setError("Password cannot be empty");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        }else{

                            Toast.makeText(RegistrationActivity.this, "Registeration Failed "+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                        etEmail.setText(null);
                        etPwd.setText(null);
                        etPwd.requestFocus();
                    }

                });
    }
}