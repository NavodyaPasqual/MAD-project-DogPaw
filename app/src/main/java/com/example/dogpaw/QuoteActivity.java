package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
    }

    public void sendToRequest(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Button button = (Button) findViewById(R.id.button1);
        startActivity(intent);
    }

    public void sendToContactUs(View view) {
        Intent intent = new Intent(this, RequestActivity.class);
        Button button = (Button) findViewById(R.id.button8);
        startActivity(intent);
    }
}