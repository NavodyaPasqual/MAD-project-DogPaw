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

public class CustomerLoginRegisterActivity extends AppCompatActivity
{

    private Button customerLoginButton;
    private Button customerRegisterButton;
    private TextView customerRegisterLink;
    private  TextView customerStatus;
    private EditText emailCustomer;
    private EditText passwordCustomer;
    private ProgressDialog loadingbar;
    private FirebaseAuth mAuth;
    private DatabaseReference customerDatabaseRef;
    private String onlinecustomerID;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        mAuth = FirebaseAuth.getInstance();


        customerLoginButton = (Button) findViewById(R.id.customer_login_btn);
        customerRegisterButton = (Button) findViewById(R.id.customer_register_btn);
        customerRegisterLink = (TextView) findViewById(R.id.customer_register_link);
        customerStatus = (TextView) findViewById(R.id.customer_status);

        emailCustomer = (EditText) findViewById(R.id.customer_email);
        passwordCustomer = (EditText) findViewById(R.id.customer_password);

        loadingbar = new ProgressDialog(this);

        customerRegisterButton.setVisibility(View.INVISIBLE);
        customerRegisterButton.setEnabled(false);

        customerRegisterLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                customerLoginButton.setVisibility(View.INVISIBLE);
                customerRegisterLink.setVisibility(View.INVISIBLE);
                customerStatus.setText("Register Customer");

                customerRegisterButton.setVisibility(View.VISIBLE);
                customerRegisterButton.setEnabled(true);
            }
        });

        customerRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email= emailCustomer.getText().toString();
                String password = passwordCustomer.getText().toString();

                RegisterCustomer(email, password);


            }
        });

        customerLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email= emailCustomer.getText().toString();
                String password = passwordCustomer.getText().toString();

                SignInCustomer (email, password);

            }
        });

    }

    private void SignInCustomer(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write Email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write password", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingbar.setTitle("CustomerLogin");
            loadingbar.setMessage("Please Wait Checking Credentials");
            loadingbar.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Intent customerIntent = new Intent(CustomerLoginRegisterActivity.this, CustomersMapActivity.class);
                        startActivity(customerIntent);

                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Login successful", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }

                    else
                    {
                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Login unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }
            });

        }
    }


    private void RegisterCustomer(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write Email", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(CustomerLoginRegisterActivity.this, "Please Write password", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingbar.setTitle("Customer Registration");
            loadingbar.setMessage("Please Wait Registering Data");
            loadingbar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {

                        onlinecustomerID = mAuth.getCurrentUser().getUid();
                        customerDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Clients").child("Customers").child(onlinecustomerID);
                        customerDatabaseRef.setValue(true);

                        Intent driverIntent = new Intent(CustomerLoginRegisterActivity.this, CustomersMapActivity.class);
                        startActivity(driverIntent);

                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register successful", Toast.LENGTH_SHORT).show();

                        loadingbar.dismiss();
                    }

                    else
                    {
                        Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Register unsuccessful", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();
                    }
                }
            });

        }
    }
}
