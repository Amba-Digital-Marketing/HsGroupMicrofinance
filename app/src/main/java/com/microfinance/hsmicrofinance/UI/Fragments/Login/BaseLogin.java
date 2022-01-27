package com.microfinance.hsmicrofinance.UI.Fragments.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.SignUpActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentBaseLoginBinding;

public class BaseLogin extends Fragment {
    FragmentBaseLoginBinding baseLoginBinding;
    public BaseLogin() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baseLoginBinding = FragmentBaseLoginBinding.inflate(inflater, container,false);
        return  baseLoginBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        baseLoginBinding.btnRegister.setOnClickListener(v -> loadSignUpActivity());
        baseLoginBinding.btnsignin.setOnClickListener(v -> loadSignInActivity());
    }


    private void loadSignUpActivity() {
        Intent intent = new Intent(getContext(), SignUpActivity.class);
        getActivity().overridePendingTransition(0,0);
        startActivity(intent);
    }

    private void loadSignInActivity() {
        LoginFragment loginFragment = new LoginFragment();
        setFragment(loginFragment);
    }

    private void setFragment(Fragment fragment){

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();

    }

    @Override
    public void onResume() {
        super.onResume();

        setFragment(this);
    }
}