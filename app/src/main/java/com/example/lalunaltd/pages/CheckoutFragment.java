package com.example.lalunaltd.pages;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.Activities.MainActivity;
import com.example.lalunaltd.Classes.Order;
import android.Manifest;
import com.example.lalunaltd.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    private EditText tvNameOnCard;
    private EditText tvCardNumber;
    private EditText tvExpDate;
    private EditText tvPostalCode;
    private TextInputEditText tvCvv;
    private Order order;
    private Button btnPlaceOrder;
   // private Button btnGotoCart;


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
        order = ((MainActivity) getActivity()).getOrder();
//        btnGotoCart=getView().findViewById(R.id.go_back_to_cart_button);
//        btnGotoCart.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//                                 gotoCartFragment();
//            }
//           });
//        TextWatcher tw =new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        }
                btnPlaceOrder = getView().findViewById(R.id.pay_button);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvNameOnCard.getText().toString().isEmpty()
                        ||tvCardNumber.getText().toString().length()!=19
                        ||tvExpDate.getText().toString().length() != 4
                        ||tvPostalCode.getText().toString().length()!=7
                        ||tvCvv.getText().toString().length() != 3)
                {
                Toast.makeText(getActivity(), "Please Check Your Details", Toast.LENGTH_SHORT).show();
                }
                    else{
                        Toast.makeText(getActivity(), "Order Placed", Toast.LENGTH_SHORT).show();
//                       if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                   // sendSMSMessage();
                    sendEmail("ibrazidan26@gmail.com","Order:  ",tvNameOnCard.getText().toString()+tvCardNumber.getText().toString()+tvExpDate.getText().toString()+tvPostalCode.getText().toString()+tvCvv.getText().toString());
                        order.getItems().clear();
                    gotoHomeFragment();
                }
            }
        });
    }
    public void sendEmail(String to, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            // Chooser for user preference (Gmail, Outlook, etc.)
            startActivity(Intent.createChooser(emailIntent, "Choose an Email Client:"));
        } catch (ActivityNotFoundException e) {
            // Handle case where NO email app is available
            showNoEmailAppDialog();
        }
    }

    // Helper method to display a user-friendly dialog
    private void showNoEmailAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("No Email App Found")
                .setMessage("Please install an email application to send emails.")
                .setPositiveButton("OK", null)
                .show();
    }


    private void gotoHomeFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new HomeFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
//    private void sendSMSMessage() {
//        String phoneNumber = "+972503030846"; // Replace with the actual phone number
//        String message = "tvNameOnCard.getText().toString()+tvCardNumber.getText().toString()+tvExpDate.getText().toString()+tvPostalCode.getText().toString()+tvCvv.getText().toString()";
//
//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
//            Toast.makeText(requireContext(), "SMS sent successfully", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(requireContext(), "SMS failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
    private void gotoCartFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new CartFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

}