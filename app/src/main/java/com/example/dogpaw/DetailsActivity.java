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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    EditText txtName,txtNoOfDogs,txtBreed,txtSize,txtSitter,txtDays;
    Button btnSave,btnShow,btnUpdate,btnDelete;
    DatabaseReference dbRef;
    Dog dg;
    public static final String EXTRA_NUMBER = "com.example.dogpaw.EXTRA_NUMBER";
    public static final String EXTRA_DAYS = "com.example.dogpaw.EXTRA_DAYS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        txtName = findViewById(R.id.etinputFname);
        txtNoOfDogs = findViewById(R.id.etinputLname);
        txtBreed = findViewById(R.id.etinputPhone);
        txtSize = findViewById(R.id.etinputEmail);
        txtSitter = findViewById(R.id.etinputSubject);
        txtDays = findViewById(R.id.etinputMsg);
        btnSave = findViewById(R.id.butSave);
        btnShow = findViewById(R.id.butShow);
        btnUpdate = findViewById(R.id.butUpdate);
        btnDelete = findViewById(R.id.butDelete);

        dg = new Dog();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Dog").child("dg1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Dog");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("dg1")) {
                            try {
                                dg.setName(txtName.getText().toString().trim());
                                dg.setNoofdogs(Integer.parseInt(txtNoOfDogs.getText().toString().trim()));
                                dg.setBreed(txtBreed.getText().toString().trim());
                                dg.setSize(Integer.parseInt(txtSize.getText().toString().trim()));
                                dg.setSitter(txtSitter.getText().toString().trim());
                                dg.setDays(Integer.parseInt(txtDays.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Dog").child("dg1");
                                dbRef.setValue(dg);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid day count", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            ;

        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Dog/dg1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtNoOfDogs.setText(dataSnapshot.child("noofdogs").getValue().toString());
                            txtBreed.setText(dataSnapshot.child("breed").getValue().toString());
                            txtSize.setText(dataSnapshot.child("size").getValue().toString());
                            txtSitter.setText(dataSnapshot.child("sitter").getValue().toString());
                            txtDays.setText(dataSnapshot.child("days").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            ;

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Dog");

                Intent intent = new Intent(DetailsActivity.this, Payment2.class);
                //Extra no
                EditText editText1 = (EditText) findViewById(R.id.etinputLname);
                int number = Integer.parseInt(editText1.getText().toString());
                intent.putExtra(EXTRA_NUMBER, number);
                EditText editText2 = (EditText) findViewById(R.id.etinputMsg);
                int number2 = Integer.parseInt(editText2.getText().toString());
                intent.putExtra(EXTRA_DAYS, number2);
                startActivity(intent);

                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtNoOfDogs.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtBreed.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Breed", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtSize.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Size", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtSitter.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Sitter", Toast.LENGTH_SHORT).show();
                    else {
                        dg.setName(txtName.getText().toString().trim());
                        dg.setNoofdogs(Integer.parseInt(txtNoOfDogs.getText().toString().trim()));
                        dg.setBreed(txtBreed.getText().toString().trim());
                        dg.setSize(Integer.parseInt(txtSize.getText().toString().trim()));
                        dg.setSitter(txtSitter.getText().toString().trim());
                        dg.setDays(Integer.parseInt(txtDays.getText().toString().trim()));

                        dbRef.child("dg1").setValue(dg);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid day count", Toast.LENGTH_SHORT).show();
                }

            }

            ;
        });

    }
    private void clearControls(){
        txtName.setText("");
        txtNoOfDogs.setText("");
        txtBreed.setText("");
        txtSize.setText("");
        txtSitter.setText("");
        txtDays.setText("");
    }

    }