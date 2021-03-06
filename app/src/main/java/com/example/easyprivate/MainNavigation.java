package com.example.easyprivate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.easyprivate.fragments.AbsenFragment;
import com.example.easyprivate.fragments.HomeFragment;
import com.example.easyprivate.fragments.PesananFragment;
import com.example.easyprivate.fragments.ProfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainNavigation extends AppCompatActivity {
    private BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        bnv = findViewById(R.id.nav_bottom);

        //Default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, new HomeFragment(), "currFrag").commit();

        //Nav onclick listener
        BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = getSupportFragmentManager().findFragmentByTag("currFrag");

                switch (item.getItemId()){
                    //Home
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    //Pesanan
                    case R.id.nav_pemesanan:
                        selectedFragment = new PesananFragment();
                        break;
                    //QR Scanner
                    case R.id.nav_qr:
                        selectedFragment = new AbsenFragment();
                        break;
                    //Profil
                    case R.id.nav_profile:
                        selectedFragment = new ProfilFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, selectedFragment, "currFrag").commit();
                return true;
            }
        };

        bnv.setOnNavigationItemSelectedListener(navListener);
    }
    }