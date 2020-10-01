package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;



import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mTroggle;

    private List<Slide> slideList = new ArrayList<>();
    private ViewPager pager;
    private PagerAdapter adaptor;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigation drawer
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mTroggle);
        mTroggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

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


    public void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void sendToPhotography(View view) {
        Intent intent = new Intent(this, PhotographyBooking.class);
        Button button = (Button) findViewById(R.id.button6);
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
            Intent intent = new Intent(this, PhotographyBooking.class);
            startActivity(intent);
        }
        if(id==R.id.aLogin){
            Intent intent = new Intent(this, PhotographyBooking.class);
            startActivity(intent);
        }
        if(id == R.id.uLogin){
            Intent intent = new Intent(this, PhotographyBooking.class);
            startActivity(intent);
        }
        return false;
    }

}