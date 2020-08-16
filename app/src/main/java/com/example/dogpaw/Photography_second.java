package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Photography_second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_second);
        Intent intent = getIntent();

    }

    public void sendToPayment(View view) {
        Intent intent = new Intent(this, Payment.class);
        Button button = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

}