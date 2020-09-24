package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Payment extends AppCompatActivity {

    EditText txtNo, txtIformat, txtType, amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        final TextView result = (TextView) findViewById(R.id.editText1);
        result.setEnabled(false);

        final int number = intent.getIntExtra(Photography_second.EXTRA_NUMBER,0);
        float amount;
        if(number < 5)
            amount = number * 500;
        else
            amount = number * 450;
        if(amount >= 2000){
            float discount = amount * 5/100;
            amount = amount - discount;
            result.setText(String.valueOf(amount));
        }
        else
            result.setText(String.valueOf(amount));
    }
    public void sendBackToHome(View view) {
        Intent intent = new Intent(this, PhotographyBooking.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }
}
