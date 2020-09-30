package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MA6 extends AppCompatActivity {

    private Button button;
    private EditText fullName, email, pNumber, userName, password;
    private DatabaseReference databaseReference;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_a6);

        fullName = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextPersonName2);
        pNumber = findViewById(R.id.editTextNumber);
        userName = findViewById(R.id.editTextTextPersonName6);
        password = findViewById(R.id.editTextTextPassword);

        user = new User();

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openMA1();

                databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

                //get inputs from user and assign them to the variables
                try {
                    if(TextUtils.isEmpty(fullName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter full name", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter email", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(pNumber.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your phone number", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(userName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter username for app", Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(password.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter password for app", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        user.setFullName(fullName.getText().toString().trim());
                        user.setEmail(email.getText().toString().trim());
                        user.setpNumber(pNumber.getText().toString().trim());
                        user.setUserName(userName.getText().toString().trim());
                        user.setPassword(password.getText().toString().trim());
                    }

                    //Insert data to the database
                    //databaseReference.push().setValue(user);
                    databaseReference.child("user3").setValue(user);

                    //feedback to the user
                    Toast.makeText(getApplicationContext(),"Registration Succesfull", Toast.LENGTH_SHORT).show();
                    clearControlls();

                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Input valid Phone number", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void openMA1() {
        Intent intent = new Intent(this, MA1.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        Intent intent = new Intent(this, CustomerLoginRegisterActivity.class);
        Button button = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

    public void clearControlls(){
        fullName.setText("");
        email.setText("");
        pNumber.setText("");
        userName.setText("");
        password.setText("");

    }


}