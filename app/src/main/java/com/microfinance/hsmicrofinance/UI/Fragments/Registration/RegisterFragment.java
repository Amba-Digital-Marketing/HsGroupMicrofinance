package com.microfinance.hsmicrofinance.UI.Fragments.Registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.microfinance.hsmicrofinance.UI.BaseLogin;
import com.microfinance.hsmicrofinance.UI.LoginActivity;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentRegisterBinding;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;


    private static final String TAG = "RegisterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);


        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.bgHeader);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        binding.bgHeader.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BaseLogin.class);
            startActivity(intent);
                }

        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.RegisterBtn.setOnClickListener(view1 -> {
            getUserInput();
        });

        //sets up login activity
        binding.loginText.setOnClickListener(v -> {
            setLoginActivity();
        });
        binding.loginText1.setOnClickListener(v -> {
            setLoginActivity();
        });

    }


    private UserRegistrationDetails getUserInput() {
        UserRegistrationDetails userRegistrationDetails = null;
        String name, email, password, confirmPassword;

        name = binding.signupFullname.getText().toString();
        email = binding.signupEmail.getText().toString();
        password = binding.signupPassword.getText().toString();
        confirmPassword = binding.signupRenterpassword.getText().toString();

        if (name.trim() != "" && name.trim().length() > 2) {
            if (email.trim() != "" && email.trim().length() > 6) {
                email = email.trim().toLowerCase();
                if (password.trim() != ""){
                    if(password.trim().length() > 6){
                        if (password.equals(confirmPassword)) {
                            RegisterPersonalInfo fragment = new RegisterPersonalInfo(name, email, password, password);
                            setFragment(fragment);
                        } else {
                            binding.tvconfirmpasswordaler.setText("Passwords do not match");
                        }
                    }else{
                        binding.tvconfirmpasswordaler.setText("Passwords Must be more than 6 digits");
                    }

                } else {
                    binding.tvpasswordAlert.setText("Password is Required");
                }
            } else {
                Toast.makeText(getContext(), "Enter correct Email", Toast.LENGTH_LONG).show();
                binding.tvemailAlert.setText("Enter correct Email");
            }
        } else {
            binding.tvnameAlert.setText(" Name is required");
        }


        return userRegistrationDetails;
    }

    //login activity
    private void setLoginActivity() {
        String email = binding.signupEmail.getText().toString().trim();
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        intent.putExtra("PUT_EMAIL", email);
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }


    public void setFragment(Fragment fragment) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

    }

}