package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPhotographyUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photography_update);
        Intent intent = getIntent();
    }

    public void sendToPhotographAll(View view) {
        Intent intent = new Intent(this, AdminPhotographyManagemnt.class);
        Button button = (Button) findViewById(R.id.button2);
        Button button1 = (Button) findViewById(R.id.button8);
        startActivity(intent);
    }

    public void sendToPhotographAdd(View view) {
        Intent intent = new Intent(this, AdminPhotographyAdd.class);
        Button button = (Button) findViewById(R.id.button7);
        startActivity(intent);
    }
}