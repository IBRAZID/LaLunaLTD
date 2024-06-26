package com.example.lalunaltd.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lalunaltd.Classes.Product;
import com.example.lalunaltd.Utils.ProductAdapter;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeveragesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeveragesFragment extends Fragment {
    //private ImageButton btnBack;
    private FirebaseServices fbs;
    private ArrayList<Product> prods;
    private RecyclerView rvBeverages;
    private ProductAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BeveragesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IsraeliCansFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeveragesFragment newInstance(String param1, String param2) {
        BeveragesFragment fragment = new BeveragesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beverages, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
//        btnBack=getView().findViewById(R.id.btnBackDetailsFragment);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoHomeFragment();
//            }
//        });
        fbs = FirebaseServices.getInstance();
        prods = new ArrayList<>();
        rvBeverages = getView().findViewById(R.id.rvBeveragesFragment);
        adapter = new ProductAdapter(getActivity(), prods);
        rvBeverages.setAdapter(adapter);
        rvBeverages.setHasFixedSize(true);
        rvBeverages.setLayoutManager(new LinearLayoutManager(getActivity()));
            fbs.getFire().collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (DocumentSnapshot dataSnapshot: queryDocumentSnapshots.getDocuments()){
                        Product prod = dataSnapshot.toObject(Product.class);

                        if (prod.getCategory().equals("Beverages"))
                            prods.add(prod);
                        }


                    adapter.notifyDataSetChanged();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                    Log.e("BeveragesFragment", e.getMessage());
                }
            });
    }


    private void gotoHomeFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
