package com.example.lalunaltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.pages.HomeFragment;
import com.example.lalunaltd.pages.LoginFragment;
import com.example.lalunaltd.pages.WaterFragment;
import com.example.lalunaltd.product.AddProductFragment;
import com.example.lalunaltd.product.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseServices fbs;
    ArrayList<Product> cartArray;

    public ArrayList<Product> getCartArray() {
        return cartArray;
    }

    public void setArr(ArrayList<Product> arr) {
        this.cartArray = arr;
    }

    public ArrayList<Product> getArr() {
        return cartArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs=FirebaseServices.getInstance();
        cartArray = new ArrayList<>();
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