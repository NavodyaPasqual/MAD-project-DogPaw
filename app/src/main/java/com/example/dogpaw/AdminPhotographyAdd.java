package com.example.dogpaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPhotographyAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photography_add);
        Intent intent = getIntent();
    }

    public void sendToPhotographAll(View view) {
        Intent intent = new Intent(this, AdminPhotographyManagemnt.class);
        Button button = (Button) findViewById(R.id.button2);
        Button button2 = (Button) findViewById(R.id.button8);
        startActivity(intent);
    }

    public void sendToPhotographUpdate(View view) {
        Intent intent = new Intent(this, AdminPhotographyUpdate.class);
        Button button = (Button) findViewById(R.id.button4);
        startActivity(intent);
    }


}