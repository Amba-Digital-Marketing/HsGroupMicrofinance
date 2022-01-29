package com.microfinance.hsmicrofinance.UI.Fragments.Login;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.BaseLogin;
import com.microfinance.hsmicrofinance.UI.ForgotPasswordActivity;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoginViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    FragmentLoginBinding fragmentLoginBinding;
    LoginViewModel loginViewModel;
    private static final String TAG = "LOGINFRAGMENT";
    String myemail;

    public LoginFragment(String userEmail) {
        this.myemail = userEmail;
        // Required empty public constructor
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        ((AppCompatActivity) getActivity()).setSupportActionBar(fragmentLoginBinding.bgHeader);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragmentLoginBinding.bgHeader.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        fragmentLoginBinding.bgHeader.setTitle("");
        fragmentLoginBinding.bgHeader.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BaseLogin.class);
            startActivity(intent);

                }

        );


        return fragmentLoginBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onUserLogout(1);


        fragmentLoginBinding.progrebar.setVisibility(View.INVISIBLE);
        fragmentLoginBinding.progrebar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#E0223C")));


        // loginViewModel.getUserFromDB(this);
        fragmentLoginBinding.loginBtn.setOnClickListener(view1 -> {
                    fragmentLoginBinding.tvloginalert.setText("");
                    fragmentLoginBinding.progrebar.setVisibility(View.VISIBLE);
                    getUserInput(view);
                }
        );
        if (myemail != null && !myemail.equals("Pinsetter")) {
            fragmentLoginBinding.loginEmailInput.setText(myemail);
        }

        //forgot password activity
        loadForgotPasswordActivity();


    }

    private void getUserInput(View view) {
        String email = "";
        String password = "";
        try {
            email = fragmentLoginBinding.loginEmailInput.getText().toString();
            password = fragmentLoginBinding.loginPasswordInput.getText().toString();

            if (email != null && !email.trim().equals("") && password != null && !password.trim().equals("")) {
                if (myemail != null) {
                    if (this.myemail.equals("Pinsetter")) {
                        loginViewModel.loginUser(email, password, this, "Pinsetter", view, fragmentLoginBinding);
                    }
                }
             else{
                    loginViewModel.loginUser(email, password, this, "", view, fragmentLoginBinding);
               }
                // watchForLoginError();
            }else{
                fragmentLoginBinding.loginEmailInput.setHint("Email Is required");
                fragmentLoginBinding.loginPasswordInput.setHint("Password Is required");
                fragmentLoginBinding.progrebar.setVisibility(View.INVISIBLE);
                fragmentLoginBinding.loginEmailInput.setHintTextColor(Color.parseColor("#E0223C"));
                fragmentLoginBinding.loginPasswordInput.setHintTextColor(Color.parseColor("#E0223C"));
            }

            }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void loadForgotPasswordActivity() {

        fragmentLoginBinding.etForgotPassword.setOnClickListener(v->{
            String email = fragmentLoginBinding.loginEmailInput.getText().toString();
            Intent intent = new Intent(getContext(), ForgotPasswordActivity.class);
            intent.putExtra("PUT_EMAIL", email);
            getActivity().overridePendingTransition(0,0);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }

    private void onUserLogout(int uid) {
        try{
            UserDao db = UserDB.getDbInstance(requireActivity()).userDao();
            UserEntity entity = db.loaduserById(1);
            db.delete(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}