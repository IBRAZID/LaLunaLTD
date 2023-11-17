package com.example.lalunaltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs=FirebaseServices.getInstance();
        if(fbs.getAuth().getCurrentUser()==null){
        gotoLoginFragment();
        }
        else {
            gotoHomeFragment();
        }
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
    private void gotoAddProductFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new AddProductFragment());
        ft.commit();
    }
    private void gotoWaterFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new WaterFragment());
        ft.commit();
    }
}