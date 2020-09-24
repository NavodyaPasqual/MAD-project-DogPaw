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

public class ExtendPhotography extends AppCompatActivity {

    EditText txtNo, txtBreed, txtTime, txtDate, txtInfo, txtWeight, txtIformat, txtType;
    DatabaseReference dbRef;
    Photography photography;
    Button extend, delete, read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_photography);
        txtNo = findViewById(R.id.editText1);
        txtBreed = findViewById(R.id.editText2);
        txtTime = findViewById(R.id.editText4);
        txtDate = findViewById(R.id.editTextDate);
        txtInfo = findViewById(R.id.editText5);
        txtWeight = findViewById(R.id.editText6);
        txtIformat = findViewById(R.id.editText8);
        txtType = findViewById(R.id.editText7);
        read = findViewById(R.id.button3);
        extend = findViewById(R.id.button);
        delete = findViewById(R.id.button2);
        setEditTestReadOnly();

        photography = new Photography();

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Photography").child("photography1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtNo.setText(dataSnapshot.child("no").getValue().toString());
                            txtBreed.setText(dataSnapshot.child("breed").getValue().toString());
                            txtTime.setText(dataSnapshot.child("time").getValue().toString());
                            txtDate.setText(dataSnapshot.child("date").getValue().toString());
                            txtInfo.setText(dataSnapshot.child("info").getValue().toString());
                            txtWeight.setText(dataSnapshot.child("weight").getValue().toString());
                            txtIformat.setText(dataSnapshot.child("format").getValue().toString());
                            txtType.setText(dataSnapshot.child("type").getValue().toString());

                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Booking to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Photography");


                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("photography1")){
                                photography.setTime(txtTime.getText().toString().trim());
                                photography.setDate(txtDate.getText().toString().trim());
                                photography.setFormat(txtIformat.getText().toString().trim());
                                photography.setInfo(txtInfo.getText().toString().trim());
                                photography.setType(txtType.getText().toString().trim());
                                photography.setWeight(txtWeight.getText().toString().trim());
                                photography.setBreed(txtBreed.getText().toString().trim());
                                photography.setNo(Integer.parseInt(txtNo.getText().toString().trim()));

                                dbRef= FirebaseDatabase.getInstance().getReference().child("Photography").child("photography1");
                                dbRef.setValue(photography);
                                clearControls();
                                Toast.makeText(getApplicationContext(),"Your Book is Extended.",Toast.LENGTH_SHORT).show();

                        }else
                            Toast.makeText(getApplicationContext(),"Sorry your boooking cannot be extended",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(ExtendPhotography.this, PhotographyBooking.class);
                startActivity(intent);


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Photography");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("photography1")){
                            dbRef= FirebaseDatabase.getInstance().getReference().child("Photography").child("photography1");
                            dbRef.setValue(photography);
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Your Book has been deleted",Toast.LENGTH_SHORT).show();

                        }else
                            Toast.makeText(getApplicationContext(),"No Booking to deleted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(ExtendPhotography.this, PhotographyBooking.class);
                startActivity(intent);

            }
        });

    }

    private void setEditTestReadOnly(){
        txtNo.setEnabled(false);
        txtBreed.setEnabled(false);
        txtInfo.setEnabled(false);
        txtWeight.setEnabled(false);
        txtIformat.setEnabled(false);
        txtType.setEnabled(false);

    }

    private void clearControls(){
        txtNo.setText("");
        txtBreed.setText("");
        txtTime.setText("");
        txtDate.setText("");
        txtInfo.setText("");
        txtWeight.setText("");
        txtIformat.setText("");
        txtType.setText("");
    }

}