package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class PhotographyBooking extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mTroggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_booking);

        //Navigation drawer
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mTroggle);
        mTroggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //set troggle
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mTroggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void sendToExtend(View view) {
        Intent intent = new Intent(this, ExtendPhotography.class);
        Button button = (Button) findViewById(R.id.button1);
        startActivity(intent);
    }

    public void sendToBook(View view) {
        Intent intent = new Intent(this, Photography_second.class);
        Button button = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

    //Set navigation Item select
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.home){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(id==R.id.profile){

            Intent intent = new Intent(this, MA5.class);
            startActivity(intent);
        }
        if(id==R.id.registration){
            Intent intent = new Intent(this, MA6.class);
            startActivity(intent);
        }
        if(id == R.id.uLogin){
            Intent intent = new Intent(this, CustomerLoginRegisterActivity.class);

            startActivity(intent);
        }
        return false;
    }
}