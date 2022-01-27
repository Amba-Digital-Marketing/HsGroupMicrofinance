package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.UI.LoginActivity;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentPinLoginAccountSettingBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPinlockBinding;

import java.util.ArrayList;
import java.util.List;


public class PinLoginAccountSetting extends Fragment {
    private NavController mNavController;
    private FragmentPinlockBinding binding;
    int dbPin;

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    public PinLoginAccountSetting() {

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
        mNavController = Navigation.findNavController(view);
        this.dbPin = Integer.parseInt(getArguments().getString("pin"));
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
            mPinLockView.resetPinLockView();
            binding.progrebar.setVisibility(View.GONE);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {

                Bundle bundle = new Bundle();
                bundle.putString("PUT_PIN", pin);

                mNavController.navigate(R.id.action_pinLoginAccountSetting_to_pinSetterAccountSetting, bundle);
            }, 2000);
            new LoginActivity().finishAffinity();

        } else {
            mPinLockView.resetPinLockView();
            Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
//    FragmentPinLoginAccountSettingBinding mBinding;
//    int dbPin;
//    List inputpins = new ArrayList();
//
//
//    private NavController mNavController;
//
//    public PinLoginAccountSetting() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//
//        mBinding = FragmentPinLoginAccountSettingBinding.inflate(inflater, container, false);
//        // Inflate the layout for this fragment
//        return mBinding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mNavController = Navigation.findNavController(view);
//        mBinding.progrebar.setVisibility(View.INVISIBLE);
//        this.dbPin = Integer.parseInt(getArguments().getString("pin"));
//        setPinShowET(setClickListenersToPinLoginPad());
//        setDeletePad();
//
//        setSubmitBUtton();
//    }
//
//    private void setSubmitBUtton() {
//        mBinding.tvsubmit.setOnClickListener(view -> {
//            try {
//                validatePin(setSubmitPad());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//    }
//
//    private TextView[] setClickListenersToPinLoginPad() {
//        TextView[] pins = new TextView[12];
//        pins[0] = mBinding.tvno0;
//        pins[1] = mBinding.tvno1;
//        pins[2] = mBinding.tvno2;
//        pins[3] = mBinding.tvno3;
//        pins[4] = mBinding.tvno4;
//        pins[5] = mBinding.tvno5;
//        pins[6] = mBinding.tvno6;
//        pins[7] = mBinding.tvno7;
//        pins[8] = mBinding.tvno8;
//        pins[7] = mBinding.tvno7;
//        pins[8] = mBinding.tvno8;
//        pins[9] = mBinding.tvno9;
//
//        return pins;
//    }
//
//    private void setDeletePad() {
//        mBinding.tvdelete.setOnClickListener(v -> {
//                    mBinding.etpinShow.setText("");
//                    inputpins.clear();
//                }
//
//
//        );
//    }
//
//    private String setSubmitPad() {
//        String input = mBinding.etpinShow.getText().toString();
//        return input;
//    }
//
//    private void setPinShowET(TextView[] textViews) {
//        for (TextView textview :
//                textViews) {
//            if (textview != null) {
//                textview.setOnClickListener(v -> {
//
//                            setUserPinDisplay(textview.getText().toString());
//                        }
//
//                );
//            }
//        }
//
//
//    }
//
//    private void setUserPinDisplay(String pinValue) {
//        String pin = "";
//        inputpins.add(pinValue);
//        for (Object inputpin : inputpins
//        ) {
//            pin += inputpin;
//            mBinding.etpinShow.setText(pin);
//
//        }
//
//        setPinShowET(setClickListenersToPinLoginPad());
//
//    }
//
//
//    // user validation and  authentication
//    @SuppressLint("ResourceAsColor")
//    private void validatePin(String Pin) {
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_check_24);
//        mBinding.progrebar.setVisibility(View.VISIBLE);
//        //on validation success
//        if (Pin.hashCode() == dbPin) {
//
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.postDelayed(() -> {
//                mBinding.ivpinLock.setImageDrawable(drawable);
//                mBinding.progrebar.setVisibility(View.INVISIBLE);
//                Bundle bundle = new Bundle();
//                bundle.putString("PUT_PIN", Pin);
//
//                mNavController.navigate(R.id.action_pinLoginAccountSetting_to_pinSetterAccountSetting, bundle);
//            }, 2000);
//            new LoginActivity().finishAffinity();
//
//        } else {
//            // on validation failed
//            mBinding.progrebar.setVisibility(View.INVISIBLE);
//            mBinding.tvPinLogin.setText(" Wrong Pin");
//            mBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//            mBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
//            Handler handler = new Handler();
//            handler.postDelayed(() -> {
//                mBinding.tvPinLogin.setText(" Try Again");
//                mBinding.etpinShow.setText("");
//                inputpins.clear();
//            }, 1000);
//        }
//
//    }
//
//}