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
        float amount;
        //If  no of dogs < 5, then amount will be number*500
        //if(no < 5 && days < 3)
          //  amount = days * no * 800;
            //If  no of dogs >= 5, then amount will be number*450
        //else
            amount = days * no * 550;
        //If amount is >= 2000 then will give a discount
        if(amount >= 3500){
            float discount = amount * 5/100;
            amount = amount - discount;
            result.setText(String.valueOf(amount));
        }
        else
            result.setText(String.valueOf(amount));
    }
    public void sendBackToHome(View view) {
        Intent intent = new Intent(this, QuoteActivity.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

}