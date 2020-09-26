package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MA2 extends AppCompatActivity {

    private EditText adminEmail, adminPassword;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_a2);

        adminEmail =  (EditText) findViewById(R.id.editTextTextPersonName3);
        adminPassword = (EditText) findViewById(R.id.editTextTextPassword3);
        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.button7);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = adminEmail.getText().toString().trim();
                String password = adminPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    adminEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    adminPassword.setError("Password is required.");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MA2.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MA3.class));
                        }
                        else{
                            Toast.makeText(MA2.this, "Login Unsuccesfull" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }

}