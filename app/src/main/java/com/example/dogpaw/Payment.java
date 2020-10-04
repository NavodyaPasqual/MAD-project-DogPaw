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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        final TextView result = (TextView) findViewById(R.id.editText1);
        result.setEnabled(false);

        final int number = intent.getIntExtra(Photography_second.EXTRA_NUMBER,0);
        float amount = calPayment(number);
        result.setText(String.valueOf(amount));

    }
    public void sendBackToHome(View view) {
        Intent intent = new Intent(this, PhotographyBooking.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }
    //Calculation of Photography Booking
    public float calPayment(int num){
        float amount;
        //If  no of dogs < 5, then amount will be number*500
        if(num < 5)
            amount = num * 500;
            //If  no of dogs >= 5, then amount will be number*450
        else
            amount = num * 450;
        //If amount is >= 2000 then will give a discount
        if(amount >= 2500){
            float discount = amount * 5/100;
            amount = amount - discount;
        }
        return amount;
    }
}
