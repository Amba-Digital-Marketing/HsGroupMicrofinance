package com.microfinance.hsmicrofinance.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinLoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.databinding.ActivityBaseLoginBinding;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseLogin extends AppCompatActivity {
    ActivityBaseLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBaseLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> loadSignUpActivity());
        binding.btnsignin.setOnClickListener(v -> loadSignInActivity());

    }

    private void loadSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void loadSignInActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Confirmation")
                .setContentText("Are you sure you want to exit?")
                .setConfirmText("Okay")
                .setConfirmClickListener(sDialog -> {
                    this.finishAffinity();
                })
                .setCancelText("No")
                .setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();
                })
                .show();

    }
}