package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MA5 extends AppCompatActivity {

    private EditText fullName, email, pNumber, userName, password;
    private DatabaseReference databaseReference;
    private User user;
    private Button buttonShow, buttonUpdate, buttonDelete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_a5);

        fullName = findViewById(R.id.editTextTextPersonName5);
        email = findViewById(R.id.editTextTextPersonName4);
        pNumber = findViewById(R.id.editTextNumber2);
        userName = findViewById(R.id.editTextTextPersonName7);
        password = findViewById(R.id.editTextTextPersonName9);

        buttonShow = findViewById(R.id.button18);
        buttonUpdate = findViewById(R.id.button17);
        buttonDelete = findViewById(R.id.button4);

        user = new User();

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("user3");
                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()){
                                fullName.setText(dataSnapshot.child("fullName").getValue().toString());
                                email.setText(dataSnapshot.child("email").getValue().toString());
                                pNumber.setText(dataSnapshot.child("pNumber").getValue().toString());
                                userName.setText(dataSnapshot.child("userName").getValue().toString());
                                password.setText(dataSnapshot.child("password").getValue().toString());
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("User");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("user3")){
                            try{
                                user.setFullName(fullName.getText().toString().trim());
                                user.setEmail(email.getText().toString().trim());
                                user.setpNumber(pNumber.getText().toString().trim());
                                user.setUserName(userName.getText().toString().trim());
                                user.setPassword(password.getText().toString().trim());

                                databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child("user3");
                                databaseReference.setValue(user);
                                clearControls();

                                //feedback to the user
                                Toast.makeText(getApplicationContext(), "Data Updated Succesfully", Toast.LENGTH_SHORT).show();

                            }
                            catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No Console to update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("User");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("user3")){
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child("user3");
                            databaseReference.removeValue();
                            clearControls();

                            Toast.makeText(getApplicationContext(), "Account Deleted Succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No console to delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }

    public void clearControls() {
        fullName.setText("");
        email.setText("");
        pNumber.setText("");
        userName.setText("");
        password.setText("");
    }
}