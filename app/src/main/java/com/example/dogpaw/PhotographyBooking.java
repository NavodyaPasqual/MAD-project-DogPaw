package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotographyBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_booking);
    }

    public void sendToExtend(View view) {
        Intent intent = new Intent(this, ExtendPhotography.class);
        Button button = (Button) findViewById(R.id.button1);
        startActivity(intent);
    }

    public void sendToBook(View view) {
        Intent intent = new Intent(this, Photography_first.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }
}