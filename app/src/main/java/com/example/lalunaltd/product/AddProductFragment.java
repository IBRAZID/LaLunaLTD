package com.example.lalunaltd.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.example.lalunaltd.Utils.Utils;
import com.example.lalunaltd.pages.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {
    private static final int GALLERY_REQUEST_CODE = 123;
    private Spinner spnrCategory;
    private Utils utils;
    private EditText etProductName, etCategory,etDescription,etPrice;

    private FirebaseServices fbs;


    private Button btnAdd;

    private ImageView ivShow;
    String[]Categories={"Select Category...","Water","Beverages","Snacks"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters,     String[]Categories={"Water","IsraeliCans","ArabicCans"};e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        connectComponents();

    }

    private void connectComponents() {
        fbs = FirebaseServices.getInstance();
        spnrCategory=getView().findViewById(R.id.spnrCategoryAddProduct);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.item_file,Categories);
        utils = Utils.getInstance();
        spnrCategory.setAdapter(adapter);
        spnrCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter.setDropDownViewResource(R.layout.item_file);
        etProductName=getView().findViewById(R.id.etPriceAddProduct);
        etDescription=getView().findViewById(R.id.etDescriptionAddProduct);
        etPrice=getView().findViewById(R.id.etPriceAddProduct);
        ivShow = getView().findViewById(R.id.ivShowAddProduct);
        ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btnAdd=getView().findViewById(R.id.btnAddAddProduct);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from screen
                String ProductName = etProductName.getText().toString();
                String Category=spnrCategory.getSelectedItem().toString();
                String Description =etDescription.getText().toString();
                Integer Price = Integer.parseInt(etPrice.getText().toString());


                // data validation


                // add data to firestore
                Product product = new Product(ProductName,Description,Category, fbs.getSelectedImageURL().toString(),Price);

                fbs.getFire().collection("products").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Successfully added your Product!", Toast.LENGTH_SHORT).show();
                        gotoHomeFragment();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Failure AddProduct: ", e.getMessage());
                    }


                });


            }
        });
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ivShow.setImageURI(selectedImageUri);
            utils.uploadImage(getActivity(), selectedImageUri);
        }
    }
    private void gotoHomeFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.commit();
    }
}