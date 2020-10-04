package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DriverLoginRegisterActivity extends AppCompatActivity
{

    private Button driverLoginButton;
    private Button driverRegisterButton;
    private TextView driverRegisterLink;
    private  TextView driverStatus;
    private EditText emailDriver;
    private EditText passwordDriver;
    private ProgressDialog loadingbar;

    private FirebaseAuth mAuth;
    private DatabaseReference driverDatabaseRef;
    private String onlinedriverID;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        mAuth = FirebaseAuth.getInstance();

        driverLoginButton = (Button) findViewById(R.id.login_driver_btn);
        driverRegisterButton = (Button) findViewById(R.id.register_driver_btn);
        driverRegisterLink = (TextView) findViewById(R.id.driver_register_link);
        driverStatus = (TextView) findViewById(R.id.driver_status);

        emailDriver = (EditText) findViewById(R.id.driver_email);
        passwordDriver = (EditText) findViewById(R.id.driver_password);

        loadingbar = new ProgressDialog(this);

        driverRegisterButton.setVisibility(View.INVISIBLE);
        driverRegisterButton.setEnabled(false);

        driverRegisterLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                driverLoginButton.setVisibility(View.INVISIBLE);
                driverRegisterLink.setVisibility(View.INVISIBLE);
                driverStatus.setText("Register Driver");

                driverRegisterButton.setVisibility(View.VISIBLE);
                driverRegisterButton.setEnabled(true);
            }
        });

        driverRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email= emailDriver.getText().toString();
                String password = passwordDriver.getText().toString();
                RegisterDriver(email, password);

            }
        });

        driverLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email= emailDriver.getText().toString();
                String password = passwordDriver.getText().toString();

                SignInDriver(email, password);

            }
        });

    }

    private void SignInDriver(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write Email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write Password", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingbar.setTitle("Driver Login");
            loadingbar.setMessage("Please Wait Checking Credentials");
            loadingbar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriversMapActivity.class);
                        startActivity(driverIntent);
                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver Login successful", Toast.LENGTH_SHORT).show();

                        loadingbar.dismiss();
                    }

                    else
                    {
                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver Login unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }
            });

        }
    }


    private void RegisterDriver(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write Email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(DriverLoginRegisterActivity.this, "Please Write password", Toast.LENGTH_SHORT).show();
        }
        else
        {
           loadingbar.setTitle("Driver Registration");
            loadingbar.setMessage("Please Wait Registering Data");
            loadingbar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        onlinedriverID = mAuth.getCurrentUser().getUid();
                        driverDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Clients").child("Drivers").child(onlinedriverID);
                        driverDatabaseRef.setValue(true);

                        Intent driverIntent = new Intent(DriverLoginRegisterActivity.this, DriversMapActivity.class);
                        startActivity(driverIntent);

                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register successfully", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }

                    else
                    {
                        Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Failed", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }
            });

        }
    }
}
