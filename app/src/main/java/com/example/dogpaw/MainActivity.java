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
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mTroggle;
    private List<Slide> slideList = new ArrayList<>();
    private ViewPager pager;
    private PagerAdapter adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button19);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMA6();
            }
        });

        button1 = findViewById(R.id.button20);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMA2();
            }
        });

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mTroggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mTroggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mTroggle);
        mTroggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mTroggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMA6(){
        Intent intent = new Intent(this, MA6.class);
        startActivity(intent);
    }

    public void openMA2(){
        Intent intent = new Intent(this, MA2.class);
        startActivity(intent);
    }
}