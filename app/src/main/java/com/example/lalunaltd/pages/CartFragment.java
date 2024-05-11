package com.example.lalunaltd.pages;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.Classes.ItemInOrder;
import com.example.lalunaltd.Classes.Order;
import com.example.lalunaltd.Utils.CartAdapter;
import com.example.lalunaltd.Activities.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.Classes.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    private ImageButton btnBack;
    private FirebaseServices fbs;
    Context context;
    MainActivity mainAct;
    private Order order;
    private Integer BillFinal;
    private Integer BillT;
    public Integer getBillFinal() {
        return BillFinal;
    }

    public void setBillFinal(Integer billFinal) {
        BillFinal = billFinal;
    }

    private ArrayList<Product> CartArr;
    private RecyclerView rvCart;
    private CartAdapter adapter;
    private TextView Checkout;

    public TextView getTvTotal() {
        return tvTotal;
    }

    public void setTvTotal(TextView tvTotal) {
        this.tvTotal = tvTotal;
    }

    private TextView tvTotal;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        tvTotal=getView().findViewById(R.id.tvBillCartFragment);
        order=((MainActivity)getActivity()).getOrder();
        BillFinal =((MainActivity)getActivity()).getTotalBillMain();
        BillT=0;
   //     Bill = mainAct.getTotalBillMain();

        if (order.getItems().size() > 0)
            for (ItemInOrder i : order.getItems())
            {
                BillT = BillT + (i.getProd().getPrice() * i.getQuantity());
            }

                BillFinal=BillT;


        ((MainActivity)getActivity()).setTotalBillMain(BillFinal);
            tvTotal.setText("Total Bill:" + String.valueOf(BillFinal) + "₪");
            //adapter.notifyDataSetChanged();
            fbs = FirebaseServices.getInstance();
            //CartArr = new ArrayList<>();
            order = ((MainActivity) getActivity()).getOrder();
            rvCart = getView().findViewById(R.id.rvCartCartFragment);
            Checkout= getView().findViewById(R.id.tvCheckoutCartFragment);
            btnBack = getView().findViewById(R.id.btnBackCartFragment);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoHomeFragment();
                }
            });
           Checkout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (tvTotal.getText().toString().equals("Total Bill:0₪") == false){
                   gotoCheckoutFragment();}
                   else {
                       Toast.makeText(getActivity(), "Your Cart is Empty Go Fill it Up!", Toast.LENGTH_SHORT).show();
                       gotoHomeFragment();
                   }
               }
           });
            adapter = new CartAdapter(getActivity(), order.getItems());
            rvCart.setAdapter(adapter);
            rvCart.setHasFixedSize(true);
            rvCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        private void gotoHomeFragment () {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.FrameLayoutMain, new HomeFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
    private void gotoCheckoutFragment () {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new CheckoutFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    }