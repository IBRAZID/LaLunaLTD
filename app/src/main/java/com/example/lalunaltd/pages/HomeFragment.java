package com.example.lalunaltd.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lalunaltd.Activities.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private FirebaseServices fbs;
    private Button btnBeverages, btnSnacks,btnWater;
    TextView tvSettings,btnCart;


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
        tvSettings=getView().findViewById(R.id.tvSettingsHomeFragment);
        btnWater=getView().findViewById(R.id.btnWaterHome);
        btnBeverages=getView().findViewById(R.id.btnBeveragesHome);
        btnCart=getView().findViewById(R.id.btnCartHomeFragment);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCartFragment();
            }
        });
        btnSnacks =getView().findViewById(R.id.btnSnacksHome);
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
        btnSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoArabicCansFragment();
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSettingsFragment();
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
        ft.addToBackStack(null);
        ft.commit();
    }
    public void gotoSettingsFragment (){
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new SettingsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void gotoCartFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        CartFragment cf = new CartFragment();
        ((MainActivity)getActivity()).setCf(cf);
        ft.replace(R.id.FrameLayoutMain, cf);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void gotoBeveragesFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new BeveragesFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void gotoArabicCansFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new SnacksFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}