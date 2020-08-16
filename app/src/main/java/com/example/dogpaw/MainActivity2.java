package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.main_join_now_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMainActivity5();
            }
        });
        button1 = (Button) findViewById(R.id.main_login_btn);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMapsActivity();
            }
        });
    }
    public void openMainActivity5(){
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);


    }
    public void openMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

}

