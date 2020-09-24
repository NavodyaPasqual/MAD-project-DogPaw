package com.example.dogpaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

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
    //private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mTroggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mTroggle);
        mTroggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mTroggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendToPhotography(View view) {
        Intent intent = new Intent(this, PhotographyBooking.class);
        Button button = (Button) findViewById(R.id.button6);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.home){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(id==R.id.profile){
            Toast.makeText(this,"Your Book is Extended.",Toast.LENGTH_SHORT).show();
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