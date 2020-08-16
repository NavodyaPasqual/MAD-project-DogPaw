package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Photography_first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_first);
        Intent intent = getIntent();
    }

    public void sendToForm(View view) {
        Intent intent = new Intent(this, Photography_second.class);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageButton6);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageButton2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageButton3);
        startActivity(intent);
    }
}