package com.example.lalunaltd.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lalunaltd.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";

    private EditText nameOnCard;
    private EditText cardNumber;
    private EditText expiryDate;
    private EditText postalCode;
    private Button payButton;

    private String name;
    private String address;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name    Parameter 1.
     * @param address Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */

    public static CheckoutFragment newInstance(String name, String address) {
        CheckoutFragment fragment = new CheckoutFragment();
        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        args.putString(KEY_ADDRESS, address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(KEY_NAME);
            address = getArguments().getString(KEY_ADDRESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        // Initialize views
        nameOnCard = getView().findViewById(R.id.name_on_card);
        cardNumber = getView().findViewById(R.id.card_number);
        expiryDate = getView().findViewById(R.id.expiry_date);
        postalCode = getView().findViewById(R.id.postal_code);
        payButton = getView().findViewById(R.id.pay_button);

        // Set onClick listener for pay button
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input fields
                if (validateInputFields()) {
                    // Display payment successful message
                    Toast.makeText(requireContext(), "Payment successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean validateInputFields() {
        // Check if all input fields are not empty
        if (nameOnCard.getText().toString().isEmpty() ||
                cardNumber.getText().toString().isEmpty() ||
                expiryDate.getText().toString().isEmpty() ||
                postalCode.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if card number is valid
        if (!isValidCardNumber(cardNumber.getText().toString())) {
            Toast.makeText(requireContext(), "Invalid card number", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if expiry date is valid
        if (!isValidExpiryDate(expiryDate.getText().toString())) {
            Toast.makeText(requireContext(), "Invalid expiry date", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check if postal code is valid
        if (!isValidPostalCode(postalCode.getText().toString())) {
            Toast.makeText(requireContext(), "Invalid postal code", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private boolean isValidCardNumber(String cardNumber) {
        // Implement card number validation logic here
        return true;
    }
    private boolean isValidPostalCode(String postalCode) {
        // Check if the postal code is not empty
        if (postalCode.isEmpty()) {
            return false;
        }

        // Check if the postal code is a valid Canadian postal code
        // You can use a regular expression or a library to do this
        if (!postalCode.matches("^[ABCEGHJKLMNPRSTVXY]\\d[ABCEGHJKLMNPRSTVWXYZ] ?\\d[ABCEGHJKLMNPRSTVWXYZ]\\d$")) {
            return false;
        }

        return true;
    }

    private boolean isValidExpiryDate(String expiry) {
        // Check if the expiry date is not empty
        if (expiry.isEmpty()) {
            return false;
        }
        return true;
    }}