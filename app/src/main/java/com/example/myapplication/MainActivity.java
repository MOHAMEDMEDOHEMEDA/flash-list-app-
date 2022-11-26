package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
         //---------------------Hooks------------------------/
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.ViewId);
        toolbar = findViewById(R.id.toolbar);
        //---------------------toolbar------------------------/
        setSupportActionBar(toolbar);
        //---------------------navigation drawer menu------------------------/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.DeveloperId:
                Intent intent = new Intent(MainActivity.this, MainActivityDevelopers.class);
                startActivity(intent);
                break;
            case R.id.LogoutId:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
            case R.id.UserInfo:
                Intent intent3 = new Intent(MainActivity.this, UserInfo.class);
                startActivity(intent3);
        }
        return true;
    }
}