package com.microfinance.hsmicrofinance.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinLoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.UserVerification.UserVerificationFragment;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoginViewModel;
import com.microfinance.hsmicrofinance.databinding.ActivitySplashScreenBinding;


public class SplashScreen extends AppCompatActivity {
    UserDao db;
    private int TIMER=5;
    ActivitySplashScreenBinding mActivitySplashScreenBinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        db = UserDB.getDbInstance(this).userDao();
        loginViewModel.getVerificationStatusFromDatabase(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivitySplashScreenBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(mActivitySplashScreenBinding.getRoot());


        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            watchVerificationStatus();

                }  ,1000 * TIMER
        );

        // Launcher launcher = new Launcher();
        //launcher.start();
    }
    private void watchVerificationStatus(){
        try{
            loginViewModel.userVerificationStatusLiveData.observe(this, verificationstatus -> {

                if (verificationstatus.getVarificationStatus() > -1) {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    overridePendingTransition(0, 0);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finishAffinity();
                } else {
                    Intent intent = new Intent(SplashScreen.this, BaseLogin.class);
                    overridePendingTransition(0, 0);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finishAffinity();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    private class Launcher extends Thread {
        public void run() {
            try {
                sleep(1000 * TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//
            watchVerificationStatus();

            SplashScreen.this.finish();
        }
    }
}