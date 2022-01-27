package com.microfinance.hsmicrofinance.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.ActivityForgotPasswordBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {
    public String confirmedEmail = null;
    public String userEmail = "";
    private ActivityForgotPasswordBinding mForgotPasswordActivityBinding;
    private ForgotPasswordViewModel forgotPasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForgotPasswordActivityBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());

       setSupportActionBar(mForgotPasswordActivityBinding.bgHeader);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");
        mForgotPasswordActivityBinding.bgHeader.setNavigationIcon(R.drawable.backarrow);

        mForgotPasswordActivityBinding.bgHeader.setNavigationOnClickListener(v -> {
                    Intent intent = new Intent(this, BaseLogin.class);
                    startActivity(intent);
                }

        );


        setContentView(mForgotPasswordActivityBinding.getRoot());
        setActivityLayout();


        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);

        mForgotPasswordActivityBinding.progrebar.setVisibility(View.INVISIBLE);

        this.userEmail = getIntent().getExtras().getString("PUT_EMAIL");
        mForgotPasswordActivityBinding.etForgorEmail.setText(userEmail);

        mForgotPasswordActivityBinding.btnchangepassword.setOnClickListener(v -> {
            requestPasswordChange();
        });
        mForgotPasswordActivityBinding.logo.setOnClickListener(v ->moveToLogin(userEmail));
    }
    private String requestPasswordChange() {
        String email = null;
        if (!mForgotPasswordActivityBinding.etForgorEmail.getText().toString().trim().isEmpty()) {
            email = mForgotPasswordActivityBinding.etForgorEmail.getText().toString().trim();
            if (!email.equals("") && !email.equals(null)) {
                mForgotPasswordActivityBinding.progrebar.setVisibility(View.VISIBLE);

                forgotPasswordViewModel.createforgotPassword(this,email );
            }else{
            mForgotPasswordActivityBinding.tvuseralert.setText("Fill in Your Email");
        }
        }else{
            mForgotPasswordActivityBinding.tvuseralert.setText("Input Email First");
        }
        forgotPasswordViewModel.mfpLiveDatacode.observe(this, response ->{
              mForgotPasswordActivityBinding.progrebar.setVisibility(View.INVISIBLE);

            
        });
        return email;
    }

    public void setActivityLayout() {

        if (confirmedEmail == null || confirmedEmail == "") {
            mForgotPasswordActivityBinding.etForgotPasswordConfirm.setVisibility(TextView.INVISIBLE);
            mForgotPasswordActivityBinding.etForgotPasswordInput.setVisibility(TextView.INVISIBLE);
        }
    }

   private void  moveToLogin(String email){
         if(email == null || email.equals(""))
             email = mForgotPasswordActivityBinding.etForgorEmail.getText().toString().trim();

        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("PUT_EMAIL",email);
        startActivity(intent);

        this.finish();
    }


}