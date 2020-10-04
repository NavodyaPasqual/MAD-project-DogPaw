package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payment2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);
        Intent intent = getIntent();
        final TextView result = (TextView) findViewById(R.id.editText1);
        result.setEnabled(false);

        final int no = intent.getIntExtra(DetailsActivity.EXTRA_NUMBER,0);
        final int days = intent.getIntExtra(DetailsActivity.EXTRA_DAYS,0);
        float amount = calPayment(no, days);
        result.setText(String.valueOf(amount));
    }

    public void sendBackToHome(View view) {
        Intent intent = new Intent(this, QuoteActivity.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

    //Calculation of Daycare
    public float calPayment(int no, int days){
        float amount = 0;
        //If  no of dogs < 5, and no of days <3, then amount will be days * no of dogs * 500
        if(no < 4 ) {
            if (days < 3)
                amount = days * no * 500;
        }
        //If  no of dogs >= 5, and no of days >=3 then amount will be number*450
        else
            amount = days * no * 450;
        //If amount is >= 7500 then will give a 5% discount
        if(amount >= 7500){
            float discount;
            discount = amount * 5/100;
            amount = amount - discount;
        }
        return amount;
    }
}