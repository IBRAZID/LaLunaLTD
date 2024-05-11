                                                                                                                                                                  package com.example.lalunaltd.product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalunaltd.Classes.Product;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.pages.BeveragesFragment;
import com.example.lalunaltd.pages.HomeFragment;
import com.example.lalunaltd.pages.SnacksFragment;
import com.example.lalunaltd.pages.WaterFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

                                                                                                                                                                  /**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    private static final int PERMISSION_SEND_SMS = 1;
    private static final int REQUEST_CALL_PERMISSION = 2;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvPrice;
    private TextView tvCategory;
    private ImageView ivImage;
    private ImageButton btnBack;
    private ArrayList<Product> ProductList;
    private FirebaseServices fbs;

    private Product prod;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        tvName=getView().findViewById(R.id.tvNameDetailsFragment);
        tvCategory=getView().findViewById(R.id.tvCategoryDetailsFragment);
        tvDescription=getView().findViewById(R.id.tvDescriptionDetailsFragment);
        ivImage=getView().findViewById(R.id.ivProductPhotoDetailsFragment);
        tvPrice=getView().findViewById(R.id.tvPriceDetailsFragment);
        btnBack=getView().findViewById(R.id.btnBackDetailsFragment);
        Bundle args = getArguments();
        if (args != null) {
            prod = args.getParcelable("product");
            if (prod != null) {
                //String data = myObject.getData();
                // Now you can use 'data' as needed in FragmentB.
                tvDescription.setText(prod.getDescription());
                tvName.setText(prod.getName());
                tvPrice.setText(String.valueOf(prod.getPrice())+" ₪");
                tvCategory.setText(prod.getCategory().toString());
                if (prod.getImage() == null || prod.getImage() .isEmpty())
                {
                    Picasso.get().load(R.drawable.ic_launcher_background).into(ivImage);
                }
                else {
                    Picasso.get().load(prod.getImage()).into(ivImage);
                }

            }
        }


    }

    public void gotoSnacksFragment() {
      FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.FrameLayoutMain,new SnacksFragment());
        ft.addToBackStack(null);
      ft.commit();
    }
    public void gotoWaterFragment() {
      FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.FrameLayoutMain,new WaterFragment());
        ft.addToBackStack(null);
      ft.commit();
    }
      public void gotoBeveragesFragment() {
      FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.FrameLayoutMain,new BeveragesFragment());
          ft.addToBackStack(null);
      ft.commit();
    }
    private void gotoHomeFragment(){
      FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.addToBackStack(null);
      ft.commit();
    }

      @Override
      public void onPause() {
          super.onPause();
      }

      @Override
      public void onResume() {
          super.onResume();
      }
                                                                                                                                                                  }