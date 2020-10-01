package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity extends AppCompatActivity {

    EditText txtFname,txtLname,txtPhone,txtEmail,txtSubject,txtMessage;
    Button btnSend;
    DatabaseReference dbRef;
    Contact con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        txtFname = findViewById(R.id.etinputFname);
        txtLname = findViewById(R.id.etinputLname);
        txtPhone = findViewById(R.id.etinputPhone);
        txtEmail = findViewById(R.id.etinputEmail);
        txtSubject = findViewById(R.id.etinputSubject);
        txtMessage = findViewById(R.id.etinputMsg);
        btnSend = findViewById(R.id.butSend);

        con = new Contact();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Contact");
                try {
                    if (TextUtils.isEmpty(txtFname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty First Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtLname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Last Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtSubject.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Subject", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtMessage.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Message", Toast.LENGTH_SHORT).show();
                    else {
                        con.setFname(txtFname.getText().toString().trim());
                        con.setLname(txtLname.getText().toString().trim());
                        con.setPhone(Integer.parseInt(txtPhone.getText().toString().trim()));
                        con.setEmail(txtEmail.getText().toString().trim());
                        con.setSubject(txtSubject.getText().toString().trim());
                        con.setMsg(txtMessage.getText().toString().trim());

                        dbRef.child("con1").setValue(con);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid day count", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void clearControls() {
        txtFname.setText("");
        txtLname.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtSubject.setText("");
        txtMessage.setText("");
    }
}