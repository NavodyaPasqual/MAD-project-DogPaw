package com.example.dogpaw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminPhotographyManagemnt extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_photography_managemnt);
        Intent intent = getIntent();
    }

    public void massegeOfPhotographDelete(View view) {
        Intent intent = new Intent(this, AdminPhotographyManagemnt.class);
        Button button = (Button) findViewById(R.id.button4);

        Context context = getApplicationContext();
        CharSequence message = "You just delete a photography package.";

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();

        startActivity(intent);
    }

    public void sendToPhotographUpdate(View view) {
        Intent intent = new Intent(this, AdminPhotographyUpdate.class);
        Button button = (Button) findViewById(R.id.button4);
        Button button1 = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

    public void sendToPhotographAdd(View view) {
        Intent intent = new Intent(this, AdminPhotographyAdd.class);
        Button button = (Button) findViewById(R.id.button7);
        startActivity(intent);
    }
}