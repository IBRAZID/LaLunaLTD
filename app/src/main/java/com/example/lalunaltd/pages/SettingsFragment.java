package com.example.lalunaltd.pages;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.product.AddProductFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    private FirebaseServices fbs;
    private TextView tvAdd;
    private TextView tvSignout;
    private TextView tvHome;
    private TextView tvChangePassword;
    private TextView tvContactUs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        fbs= FirebaseServices.getInstance();
        tvAdd =getView().findViewById(R.id.tvAddSettingsFragment);
        tvSignout=getView().findViewById(R.id.tvSignoutSettingsFragment);
        tvHome=getView().findViewById(R.id.tvHomeSettingsFragment);
        tvContactUs = getView().findViewById(R.id.tvContactUsSettingsFragment);
        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Dialog dialog;
//                dialog=new Dialog(getActivity());
//                dialog.setContentView(R.layout.custom_dialog);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.background));
//                }
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//               // dialog.setCancelable(false);
//                dialog.show();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(android.net.Uri.parse("tel:0503030846"));
                startActivity(intent);
//
           }
        });
        tvChangePassword=getView().findViewById(R.id.tvChangePasswordSettingsFragment);
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoResetPassword();
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHomeFragment();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
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
    }
    private void gotoAddProductFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new AddProductFragment());
        ft.commit();
    }
    private void gotoLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new LoginFragment());
        ft.commit();
    }
    private void gotoHomeFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.commit();
    }
    public void gotoResetPassword() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new ForgotFragment());
        ft.commit();
    }
}