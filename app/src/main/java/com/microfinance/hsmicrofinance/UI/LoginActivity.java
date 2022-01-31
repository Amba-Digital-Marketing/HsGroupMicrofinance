package com.microfinance.hsmicrofinance.UI;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.BaseLogin;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinLoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.UserVerification.UserVerificationFragment;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.databinding.ActivityLoginBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoginViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

ActivityLoginBinding mActivityLoginBinding;

    private static final String TAG = "LOGIN ACTIVITY";
    UserDao db;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mActivityLoginBinding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        db = UserDB.getDbInstance(this).userDao();

        // check for UserVerification Status

        loginViewModel.getVerificationStatusFromDatabase(this);

        if(getIntent().getExtras() != null){
            String userEmail = getIntent().getExtras().getString("PUT_EMAIL");
            if (userEmail != null) {
                replaceFragment(new LoginFragment(userEmail));
            }

            else{
                replaceFragment(new LoginFragment());
            }
        }else{
            loginViewModel.userVerificationStatusLiveData.observe(this,verificationstatus -> {

                if(verificationstatus.getVarificationStatus() == 1){
                    selectUserFromLocalStorage();
                }else if(verificationstatus.getVarificationStatus() == 0){
                    UserVerificationFragment userVerificationFragment = new UserVerificationFragment(verificationstatus.getEmail(), verificationstatus.getVarificationStatus(), verificationstatus.getToken());
                    setFragment(userVerificationFragment);
                }else{

                    setFragment(new LoginFragment());
                }
            });
        }
    }


    public void setFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView,fragment)
                .commit();

    }
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();

    }
    private void selectUserFromLocalStorage(){

        Runnable loginActivityRunnable  = () -> {
           UserEntity userEntity = db.loaduserById(1);

           Handler handler = new Handler(Looper.getMainLooper());

           handler.post(() -> {

               if(userEntity != null){

               if (userEntity.usertoken != null) {
                   if(userEntity.verificationStatus == 0 ){

                   }else{
                       if (userEntity.pin != null && !userEntity.pin.equals("null")) {

                           PinLoginFragment pinLoginFragment = new PinLoginFragment(userEntity.pin);
                           setFragment(pinLoginFragment);
                       } else {
                           //set pin setter fragment
                           PinSetterFragment pinSetterFragment = new PinSetterFragment(null);
                           setFragment(pinSetterFragment);
                       }
                   }
               }
               }
           }
           );

        };

        new Thread(loginActivityRunnable).start();
    }


}