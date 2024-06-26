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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalunaltd.Activities.MainActivity;
import com.example.lalunaltd.R;
import com.example.lalunaltd.Utils.FirebaseServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private EditText etUsername,etPassword;
    private Button btnLogin;
    private TextView tvSignupLink;
    private FirebaseServices fbs;
    private  TextView tvReset;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        fbs=FirebaseServices.getInstance();
        etUsername=getView().findViewById(R.id.etUsernameLogin);
        etPassword=getView().findViewById(R.id.etPasswordLogin);
        btnLogin=getView().findViewById(R.id.btnLoginLogin);
        tvSignupLink=getView().findViewById(R.id.tvSignupLinkLogin);
        tvReset=getView().findViewById(R.id.tvResetLogin);
        tvSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignupFragment();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=etUsername.getText().toString();
                String Password=etPassword.getText().toString();
                if (Username.trim().isEmpty()||Password.trim().isEmpty()){
                    Toast.makeText(getActivity(), "Some Fields Are Empty!", Toast.LENGTH_SHORT).show();
                    return;}
                fbs.getAuth().signInWithEmailAndPassword(Username,Password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                            ((MainActivity)getActivity()).setUsernameMain(String.valueOf(fbs.getAuth().getCurrentUser().getEmail()));
                            gotoHomeFragment();
                            //TODO: decide what to do
                        }
                        else{
                            //TODO: decide what to do
                            Toast.makeText(getActivity(), "Login Failed Check Input!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoResetPassword();
            }
        });

    }
    private void gotoSignupFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new SignupFragment());
        ft.addToBackStack(null);
        ft.commit();

    }


    public void gotoResetPassword() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new ForgotFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
    public void gotoHomeFragment() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutMain,new HomeFragment());
        ft.commit();
    }
    public EditText getEtUsername() {
        return etUsername;
    }
}