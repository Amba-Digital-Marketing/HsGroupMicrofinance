package com.microfinance.hsmicrofinance.UI.Fragments.UserVerification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.UI.BaseLogin;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinLoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentUserVerificationBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.UserVerificationViewModel;

public class UserVerificationFragment extends Fragment {
    FragmentUserVerificationBinding fragmentUserVerificationBinding;

    String otp = "";
    String email = "";
    String token = "";
    int verificationStatus;
    UserVerificationViewModel userVerificationViewModel;
    UserDao db;

    public  UserVerificationFragment(String email, int verificationStatus, String token){
        this.email = email;
        this.verificationStatus = verificationStatus;
        this.token = token;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUserVerificationBinding = FragmentUserVerificationBinding.inflate(inflater,container,false);

        return fragmentUserVerificationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = UserDB.getDbInstance(getContext()).userDao();
        fragmentUserVerificationBinding.etEmail.setText(email);
        fragmentUserVerificationBinding.etOTP.setInputType(View.AUTOFILL_TYPE_TEXT);

        // resend code button visibility
        fragmentUserVerificationBinding.llresendCode.setVisibility(View.INVISIBLE);

        //progress  bar visibility
        fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);

        userVerificationViewModel = new ViewModelProvider(requireActivity()).get(UserVerificationViewModel.class);


        //make api call if verification status is -1
        if(verificationStatus == -1){
            fragmentUserVerificationBinding.etOTP.setVisibility(View.INVISIBLE);

            fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
            fragmentUserVerificationBinding.btnProceedToPin.setText("Get OTP");
            fragmentUserVerificationBinding.btnProceedToPin.setVisibility(View.INVISIBLE);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
                fragmentUserVerificationBinding.btnProceedToPin.setVisibility(View.VISIBLE);
                userVerificationViewModel.makeApiCallForVerification(getContext(),  email,token);
                observeVerificationStatus();
            }, 1000);
            fragmentUserVerificationBinding.llresendCode.setVisibility(View.INVISIBLE);
        }

        //observeVerificationStatus();

        fragmentUserVerificationBinding.btnResendCode.setOnClickListener(v ->{
            fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
            fragmentUserVerificationBinding.etOTP.setText("");
            userVerificationViewModel.makeApiCallForVerification(getContext(),email,token);
            observeOTP();
        });

        //observeOTP();


        fragmentUserVerificationBinding.etOTP.setOnClickListener(v ->{
            fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
            userVerificationViewModel.OTPCodeLiveData.observe(getViewLifecycleOwner(), OTP ->{
                this.otp = OTP;
            });
        });

        fragmentUserVerificationBinding.tvchangeEmail.setOnClickListener(v ->{
            onUserLogout(1);
            setFragment(new LoginFragment(),"login");

        });
        fragmentUserVerificationBinding.btnProceedToPin.setOnClickListener(v ->{
                    String myOtp = fragmentUserVerificationBinding.etOTP.getText().toString().trim();
                    String btntext = fragmentUserVerificationBinding.btnProceedToPin.getText().toString().trim();
                    if(btntext.equals("Get OTP")){
                        fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
                        fragmentUserVerificationBinding.etOTP.setVisibility(View.VISIBLE);
                        fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
                        fragmentUserVerificationBinding.btnProceedToPin.setText("Proceed");
                        userVerificationViewModel.makeApiCallForVerification(getContext(),  email,token);
                        userVerificationViewModel.LiveDatauserVerificationStatus.postValue(verificationStatus);
                        observeOTP();

                    }else if(btntext.equals("Proceed")){
                        if(!myOtp.equals("") && !myOtp.equals("") && !myOtp.equals(0)){
                            fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
                            fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
                            validateOTP(myOtp);
                            System.out.println(otp + "otp");
                        }else{
                            fragmentUserVerificationBinding.tvalert.setText("Fill in OTP First");
                        }

                    }
                }
                );

    }

    private String observeOTP(){
        userVerificationViewModel.OTPCodeLiveData.observe(getViewLifecycleOwner(), OTP ->{
            try{   if(otp != null){
                fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
                this.otp = OTP;
            }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return otp;
    }


    private int observeVerificationStatus(){
        userVerificationViewModel.LiveDatauserVerificationStatus.observe(requireActivity(), status->{
            if (status == 0){
                verificationStatus = status;
                fragmentUserVerificationBinding.btnProceedToPin.setText("Proceed");
                fragmentUserVerificationBinding.etOTP.setVisibility(View.VISIBLE);
                fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
                fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
            }

        });
        return  verificationStatus;
    }

    private void validateOTP(String myOtp) {
        userVerificationViewModel.OTPCodeLiveData.observe(getViewLifecycleOwner(), OTP ->{


         this.otp = OTP;
        });
        userVerificationViewModel.ErrorLiveData.observe(getViewLifecycleOwner(), error ->{
            fragmentUserVerificationBinding.tvalert.setText("Fill in OTP First");
            fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
            fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
        });
        if(otp.equals("") || otp.equals(null)){
            Runnable dbRunnable = () -> {
                UserDao db = UserDB.getDbInstance(getContext()).userDao();
                otp = String.valueOf(db.getVerificationCode(1)).trim();



            };
            new  Thread(dbRunnable).start();
            Handler handler1 = new Handler();
            handler1.postDelayed(() -> {
                if(myOtp.trim().equals(otp)){

                    userVerificationViewModel.updateVerificationStatus(getContext(),Integer.parseInt(otp),1);
                    proceedToLogin();

                }else{
                    fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
                        fragmentUserVerificationBinding.tvalert.setText("Wrong  OTP");
                        fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
                    }, 1000);

                    /*Manual Verification*/
/*
                    userVerificationViewModel.updateVerificationStatus(getContext(),Integer.parseInt(otp),1);
                    proceedToLogin();*/

                }
            }, 1000);

        }else {
            if (myOtp.trim().equals(otp)) {
                userVerificationViewModel.updateVerificationStatus(getContext(), Integer.parseInt(otp), 1);
                proceedToLogin();

            } else {
                fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    fragmentUserVerificationBinding.llresendCode.setVisibility(View.VISIBLE);
                    fragmentUserVerificationBinding.tvalert.setText("Wrong or Expired OTP");
                    fragmentUserVerificationBinding.progrebar.setVisibility(View.INVISIBLE);
                }, 1000);

                /*Manual Verification*/
//               userVerificationViewModel.updateVerificationStatus(getContext(), Integer.parseInt(otp), 1);
//                proceedToLogin();
            }
        }
    }

    private void proceedToLogin(){
        fragmentUserVerificationBinding.progrebar.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    selectUserFromLocalStorage();
                }, 1000);

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
                                        setFragment(pinLoginFragment,"pinLogin");
                                    } else {
                                        //set pin setter fragment
                                        PinSetterFragment pinSetterFragment = new PinSetterFragment(null);
                                        setFragment(pinSetterFragment,"pinSetter");

                                    }
                                }
                            }
                        }
                    }
            );

        };

        new Thread(loginActivityRunnable).start();
    }

    public void setFragment(Fragment fragment,String name){
        this.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .setReorderingAllowed(true)
                .commit();

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