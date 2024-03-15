package com.example.lalunaltd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lalunaltd.Classes.ItemInOrder;
import com.example.lalunaltd.Classes.Order;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.pages.HomeFragment;
import com.example.lalunaltd.pages.LoginFragment;
import com.example.lalunaltd.pages.WaterFragment;
import com.example.lalunaltd.product.AddProductFragment;
import com.example.lalunaltd.Classes.Product;
import com.example.lalunaltd.product.ProductAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseServices fbs;
    public ArrayList<Product> allprods;
    private ProductAdapter adapter;
    ArrayList<Product> cartArray;
    private ArrayList<ItemInOrder> items;
    Order order;

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
        items=new ArrayList<>();
        order=new Order(items,"26/9/06");
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