package com.microfinance.hsmicrofinance.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.Registration.RegisterFragment;
import com.microfinance.hsmicrofinance.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding mActivitySignUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignUpBinding.getRoot());
        loadSignUpActivity();
    }

    private void loadSignUpActivity() {

        setFragment(new RegisterFragment());
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();

    }
}