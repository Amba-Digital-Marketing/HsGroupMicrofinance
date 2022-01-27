package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.LoginActivity;
import com.microfinance.hsmicrofinance.UI.MyTimber;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPinLoginBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPinlockBinding;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class PinLoginFragment extends Fragment {
    private FragmentPinlockBinding binding;
    int dbPin;

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    public PinLoginFragment(String pin) {
        if (pin != null && !pin.equals("")) {
            if (pin != "null") {
                try {
                    this.dbPin = Integer.parseInt(pin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPinlockBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPinLockView = binding.pinLockView;
        mIndicatorDots = binding.indicatorDots;

        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);

        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }


    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {

            verifyPIN(pin);

        }

        @Override
        public void onEmpty() {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };

    private void verifyPIN(String pin) {
        binding.progrebar.setVisibility(View.VISIBLE);
        if (pin.hashCode() == dbPin) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                mPinLockView.resetPinLockView();
                binding.progrebar.setVisibility(View.GONE);
                try {

                    Intent intent = new Intent(requireActivity(), HomeActivity.class);
                    getActivity().overridePendingTransition(0, 0);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }, 1000);
        } else {
            binding.progrebar.setVisibility(View.GONE);
            mPinLockView.resetPinLockView();
            Toast.makeText(getContext(), "Check Pin and try again", Toast.LENGTH_SHORT).show();

        }
    }
}
//   FragmentPinLoginBinding fragmentPinLoginBinding;
//
//    int dbPin;
//    List inputpins = new ArrayList();
//    int inputTrials;
//    private static  final int TOTALTRIALS = 3;
//
//    PinLoginViewModel viewModel;

//    public PinLoginFragment(String pin){
//        if(pin != null && !pin.equals("")){
//            if(pin != "null"){
//                try{
//                    this.dbPin = Integer.parseInt(pin);
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//                }
//            }
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.inputTrials = MyTimber.getPinTrials();
//
//        viewModel = new ViewModelProvider(requireActivity()).get(PinLoginViewModel.class);
//        viewModel.pinloginTrialsMLiveData.setValue(inputTrials);
//
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
////        fragmentPinLoginBinding = FragmentPinLoginBinding.inflate(inflater,container,false);
//        fragmentPinLoginBinding = FragmentPinlockBinding.inflate(inflater,container,false);
//        return fragmentPinLoginBinding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        fragmentPinLoginBinding.progrebar.setVisibility(View.INVISIBLE);
//        fragmentPinLoginBinding.tvPinLogin.setGravity(Gravity.CENTER);


//        directUser();
//    }


//    private void setSubmitBUtton(){
//        fragmentPinLoginBinding.tvsubmit.setOnClickListener(view -> {
//            try {
//                validatePin(setSubmitPad());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//    }

//    private TextView[] setClickListenersToPinLoginPad(){
//        TextView[] pins =  new TextView[12];
//        pins[0] = fragmentPinLoginBinding.tvno0;
//        pins[1] = fragmentPinLoginBinding.tvno1;
//        pins[2] = fragmentPinLoginBinding.tvno2;
//        pins[3] = fragmentPinLoginBinding.tvno3;
//        pins[4] = fragmentPinLoginBinding.tvno4;
//        pins[5] = fragmentPinLoginBinding.tvno5;
//        pins[6] = fragmentPinLoginBinding.tvno6;
//        pins[7] = fragmentPinLoginBinding.tvno7;
//        pins[8] = fragmentPinLoginBinding.tvno8;
//        pins[7] = fragmentPinLoginBinding.tvno7;
//        pins[8] = fragmentPinLoginBinding.tvno8;
//        pins[9] = fragmentPinLoginBinding.tvno9;
//
//        return  pins;
//    }
//    private void setDeletePad(){
//        fragmentPinLoginBinding.tvdelete.setOnClickListener(v->{
//                    fragmentPinLoginBinding.etpinShow.setText("");
//                    inputpins.clear();
//                }
//
//
//        );
//    }
//
//    private String setSubmitPad(){
//        String input = fragmentPinLoginBinding.etpinShow.getText().toString();
//        return input;
//    }
//
//    private void setPinShowET(TextView [] textViews){
//        for (TextView textview:
//                textViews) {
//            if(textview != null){
//                textview.setOnClickListener(v ->{
//
//                            setUserPinDisplay( textview.getText().toString());
//                        }
//
//                );
//            }
//        }
//
//
//    }
//
//    private void setUserPinDisplay(String pinValue){
//        String pin= "";
//        inputpins.add(pinValue);
//        for (Object inputpin: inputpins
//        ) {
//            pin += inputpin;
//            fragmentPinLoginBinding.etpinShow.setText(pin);
//
//        }
//
//        setPinShowET(setClickListenersToPinLoginPad());
//
//    }
//
//    private void validatePin(String Pin) {
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_check_24);
//        fragmentPinLoginBinding.progrebar.setVisibility(View.VISIBLE);
//        //on validation success
//        if (Pin.hashCode() == dbPin) {
//
//            //@TODO Move to dashboard
//            // on validation failed
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.postDelayed(() -> {
//                fragmentPinLoginBinding.ivpinLock.setImageDrawable(drawable);
//                fragmentPinLoginBinding.progrebar.setVisibility(View.INVISIBLE);
//                inputTrials = 0;
//                MyTimber.setPinTrials(inputTrials);
//                try {
//
//                    Intent intent = new Intent(requireActivity(), HomeActivity.class);
//                    getActivity().overridePendingTransition(0, 0);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                    startActivity(intent);
//                    getActivity().finish();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }, 2000);
//
//        } else {
//            if(viewModel.pinloginTrialsLiveData != null){
//                inputTrials = viewModel.pinloginTrialsLiveData.getValue();
//            }
//
//            inputTrials+=1;
//
//            viewModel.setPinloginTrialsMLiveData(inputTrials);
//            checkLoginTrials();
//        }
//    }
//
//    private void checkLoginTrials() {
//
//        if(inputTrials != 0) {
//            inputpins.clear();
//
//            if(inputTrials == TOTALTRIALS){
//                fragmentPinLoginBinding.progrebar.setVisibility(View.INVISIBLE);
//
//
//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(() -> {
//                    Intent intent = new Intent(getContext(), LoginActivity.class);
//                    intent.putExtra("PUT_EMAIL","Pinsetter");
//                    startActivity(intent);
//                    getActivity().finishAffinity();
//                }, 1000);
//
//
//
//                 MyTimber.setPinTrials(inputTrials);
//
//            }
//            else{
//                fragmentPinLoginBinding.progrebar.setVisibility(View.INVISIBLE);
//                fragmentPinLoginBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//                fragmentPinLoginBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
//
//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.postDelayed(() -> {
//                    int attempts = TOTALTRIALS - inputTrials;
//                    fragmentPinLoginBinding.tvPinLogin.setText(" Try Again \n You have " + attempts +" more attempts");
//                    fragmentPinLoginBinding.etpinShow.setText("");
//                    inputpins.clear();
//                }, 1000);
//
//                MyTimber.setPinTrials(inputTrials);
//            }
//        }else{
//            fragmentPinLoginBinding.progrebar.setVisibility(View.INVISIBLE);
//            fragmentPinLoginBinding.tvPinLogin.setText("Wrong Pin");
//            fragmentPinLoginBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//            fragmentPinLoginBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
//            Handler handler = new Handler();
//            handler.postDelayed(() -> {
//                fragmentPinLoginBinding.tvPinLogin.setText(" Try Again");
//                fragmentPinLoginBinding.etpinShow.setText("");
//                inputpins.clear();
//            }, 1000);
//        }
//        }
//
//        // direct User
//
//    private void directUser(){
//        int attempts = TOTALTRIALS - inputTrials;
//        if(attempts > 0 ){
//
//            if (attempts == TOTALTRIALS){
//                setPinShowET(setClickListenersToPinLoginPad());
//                setDeletePad();
//
//                setSubmitBUtton();
//            }else{
//                fragmentPinLoginBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//                fragmentPinLoginBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
//
//                fragmentPinLoginBinding.tvPinLogin.setText(" Try Again \n You have " + attempts +" more attempts");
//
//                setPinShowET(setClickListenersToPinLoginPad());
//                setDeletePad();
//
//                setSubmitBUtton();
//
//            }
//
//
//        }else {
//            fragmentPinLoginBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//
//            fragmentPinLoginBinding.tvPinLogin.setText("You have no more attempts \n You will be redirected to Login  ");
//
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.postDelayed(() -> {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                intent.putExtra("PUT_EMAIL","Pinsetter");
//                startActivity(intent);
//            }, 3000);
//
//
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//       MyTimber.setPinTrials(inputTrials);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        directUser();
//    }
//}