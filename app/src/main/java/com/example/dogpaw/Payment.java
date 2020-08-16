package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
    }

    public void sendBackToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Button button = (Button) findViewById(R.id.button9);
        startActivity(intent);
    }
}