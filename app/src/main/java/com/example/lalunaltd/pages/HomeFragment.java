package com.example.lalunaltd.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.product.AddProductFragment;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private FirebaseServices fbs;
    private Button btnWater,btnBeverages,btnArabicCans,btnAdd,btnCart;
    private TextView tvSignout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void onStart() {
        super.onStart();
        fbs=FirebaseServices.getInstance();
        btnAdd=getView().findViewById(R.id.btnAddHomeFragment);
        btnWater=getView().findViewById(R.id.btnWaterHome);
        btnBeverages=getView().findViewById(R.id.btnBeveragesHome);
        tvSignout=getView().findViewById(R.id.tvSignoutHomeFragment);
        btnCart=getView().findViewById(R.id.btnCartHomeFragment);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCartFragment();
            }
        });
        btnArabicCans=getView().findViewById(R.id.btnArabicHome);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAddProductFragment();
            }
        });
        tvSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbs.getAuth().signOut();
                Toast.makeText(getActivity(), "Successfully Signed Out", Toast.LENGTH_SHORT).show();
                gotoLoginFragment();
            }
        });
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWaterFragment();
            }
        });
        btnBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoBeveragesFragment();
            }
        });
        btnArabicCans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoArabicCansFragment();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void gotoWaterFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new WaterFragment());
        ft.commit();
    }
    private void gotoLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new LoginFragment());
        ft.commit();
    }
    private void gotoCartFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new CartFragment());
        ft.commit();
    }
    public void gotoBeveragesFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new BeveragesFragment());
        ft.commit();
    }
    private void gotoAddProductFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new AddProductFragment());
        ft.commit();
    }
    public void gotoArabicCansFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new ArabicCansFragment());
        ft.commit();
    }
}