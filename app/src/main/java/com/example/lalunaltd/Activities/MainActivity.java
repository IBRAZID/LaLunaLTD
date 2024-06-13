package com.example.lalunaltd.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.lalunaltd.Classes.ItemInOrder;
import com.example.lalunaltd.Classes.Order;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.Utils.Utils;
import com.example.lalunaltd.pages.CartFragment;
import com.example.lalunaltd.pages.HomeFragment;
import com.example.lalunaltd.pages.LoginFragment;
import com.example.lalunaltd.pages.WaterFragment;
import com.example.lalunaltd.product.AddProductFragment;
import com.example.lalunaltd.Classes.Product;
import com.example.lalunaltd.Utils.ProductAdapter;
import com.google.type.DateTime;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    FirebaseServices fbs;
    Utils utl;
    public ArrayList<Product> allprods;
    private ProductAdapter adapter;
    ArrayList<Product> cartArray;
    private ArrayList<ItemInOrder> items;
    public Order order;
    public String UsernameMain;

    public String getUsernameMain() {
        return UsernameMain;
    }

    public void setUsernameMain(String usernameMain) {
        UsernameMain = usernameMain;
    }

    public Integer TotalBillMain;

    public Integer getTotalBillMain() {
        return TotalBillMain;
    }

    public void setTotalBillMain(Integer totalBillMain) {
        TotalBillMain = totalBillMain;
    }

    public CartFragment cf;


    public CartFragment getCf() {
        return cf;
    }


    public void setCf(CartFragment cf) {
        this.cf = cf;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
        TotalBillMain=0;
//        allprods = new ArrayList<>();
//        fbs.getFire().collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                for (DocumentSnapshot dataSnapshot: queryDocumentSnapshots.getDocuments()){
//                    Product prod = dataSnapshot.toObject(Product.class);
//                    allprods.add(prod);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//        });

        setContentView(R.layout.activity_main);
        fbs=FirebaseServices.getInstance();
        cartArray = new ArrayList<>();
        // TODO: add date to C'tor call
        utl=new Utils();
            order=new Order(utl.getDateNow());
        if(fbs.getAuth().getCurrentUser()==null){
        gotoLoginFragment();
        }
        else {
            UsernameMain= String.valueOf(fbs.getAuth().getCurrentUser().getEmail());
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