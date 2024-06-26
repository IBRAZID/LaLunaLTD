package com.example.lalunaltd.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lalunaltd.Activities.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    private EditText etUsername,etPassword;
    private Button btnSignup;
    private FirebaseServices fbs;
    private ImageButton btnBack;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs=FirebaseServices.getInstance();
        etUsername=getView().findViewById(R.id.etUsernameSignup);
        etPassword=getView().findViewById(R.id.etPasswordSignup);
//        btnBack=getView().findViewById(R.id.btnBackSignup);
        btnSignup=getView().findViewById(R.id.btnSignupSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=etUsername.getText().toString();
                String Password=etPassword.getText().toString();
                if (Username.trim().isEmpty()||Password.trim().isEmpty()){
                    Toast.makeText(getActivity(), "Some Fields Are Empty!", Toast.LENGTH_SHORT).show();
                    return;}
                fbs.getAuth().createUserWithEmailAndPassword(Username,Password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Successfully Signed Up!", Toast.LENGTH_SHORT).show();
                            ((MainActivity)getActivity()).setUsernameMain(String.valueOf(fbs.getAuth().getCurrentUser().getEmail()));
                            gotoHomeFragment();
                            fbs.getAuth().signInWithEmailAndPassword(Username,Password);
                        }
                        else{
                            //TODO: decide what to do
                            Toast.makeText(getActivity(), "Signup Failed Check Input!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoLoginFragment();
//            }
//        });
    }
    private void gotoLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
    public void gotoHomeFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new HomeFragment());
        ft.commit();
    }
}