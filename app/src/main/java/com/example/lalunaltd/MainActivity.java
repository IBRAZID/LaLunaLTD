package com.example.lalunaltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       gotoHomeFragment();
    }

    private void gotoLoginFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new LoginFragment());
        ft.commit();
    }
    private void gotoHomeFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new HomeFragment());
        ft.commit();
    }
}