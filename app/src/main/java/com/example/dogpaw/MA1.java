package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MA1 extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_a1);

        button = findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {

            DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child("user3");

            @Override
            public void onClick(View view) {
                openMA5();
            }
        });

    }

    public void openMA5(){
        Intent intent = new Intent(this, MA5.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        Intent intent = new Intent(this, CustomerLoginRegisterActivity.class);
        Button button = (Button) findViewById(R.id.button3);
        startActivity(intent);
    }

}