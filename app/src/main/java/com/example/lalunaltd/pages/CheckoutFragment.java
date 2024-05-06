package com.example.lalunaltd.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    private TextView tvNameOnCard;
    private TextView tvCardNumber;
    private TextView tvExpDate;
    private TextView tvPostalCode;
    private TextView tvCvv;
    private Button btnPlaceOrder;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        tvNameOnCard =getView().findViewById(R.id.name_on_card);
        tvCardNumber=getView().findViewById(R.id.card_number);
        tvExpDate=getView().findViewById(R.id.expiry_date);
        tvPostalCode=getView().findViewById(R.id.postal_code);
        tvCvv=getView().findViewById(R.id.cvv);
        TextWatcher tw =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        }
        btnPlaceOrder=getView().findViewById(R.id.pay_button);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvNameOnCard.getText().toString().isEmpty()||tvCardNumber.getText().toString().isEmpty()||tvExpDate.getText().toString().isEmpty()||tvPostalCode.getText().toString().isEmpty()||tvCvv.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Please Check Your Details", Toast.LENGTH_SHORT).show();
                }
                    else{Toast.makeText(getActivity(), "Order Placed", Toast.LENGTH_SHORT).show();
                gotoHomeFragment();

                }
            }
        });
    }
    private void gotoHomeFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.commit();
    }
    private void gotoCartFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new CartFragment());
        ft.commit();
    }

}