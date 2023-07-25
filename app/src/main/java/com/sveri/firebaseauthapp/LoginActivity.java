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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etEmail.setText(null);
                etPwd.setText(null);
                etPwd.requestFocus();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });
        tvRegLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void loginUser() {

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

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Successfull",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else {

                            Toast.makeText(LoginActivity.this, "Failed "+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        etEmail.setText(null);
                        etPwd.setText(null);
                        etEmail.requestFocus();
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