package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.UI.BaseLogin;
import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.LoginActivity;
import com.microfinance.hsmicrofinance.UI.MyTimber;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.PinChangeViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPinSetterAccountSettingBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPinlockBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;

public class PinSetterAccountSetting extends Fragment {

    String TAG = "PinSetterFragment";
    List inputpins = new ArrayList();
    int userPin, confirmPin = 0;
    FragmentPinSetterAccountSettingBinding fragmentPinSetterBinding;
    FragmentPinlockBinding binding;
    UserDao db;
    PinChangeViewModel viewmodel;
    String updatedPin;
    String message;

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    public PinSetterAccountSetting() {
    }

    public PinSetterAccountSetting(String message) {
        this.message = message;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            viewmodel = new ViewModelProvider(requireActivity()).get(PinChangeViewModel.class);
            updatedPin = getArguments().getString("PUT_PIN");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPinlockBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPinLockView = binding.pinLockView;
        mIndicatorDots = binding.indicatorDots;
        binding.profileName.setText("Create your new PIN");
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        db = UserDB.getDbInstance(this.getContext()).userDao();
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

    private void verifyPIN(String Pin) {
        //on validation success
        if (String.valueOf(userPin).length() < 4) {
            if (String.valueOf(Pin).length() == 4) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    binding.profileName.setText("Confirm your Pin");
                    mPinLockView.resetPinLockView();
                    userPin = Integer.parseInt(Pin);
                }, 1000);

            } else {
                binding.progrebar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "User False", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    mPinLockView.resetPinLockView();
                    binding.profileName.setText("You need a 4 digit pin");

                }, 1000);

            }
        } else if (String.valueOf(userPin).length() == 4) {
            confirmPin = Integer.parseInt(Pin);
            if (confirmPin == userPin) {
                if (message != null && message.equals("FORGOTPIN")) {
                    try {
                        savePin(String.valueOf(confirmPin).hashCode());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {

                        try {

                            binding.profileName.setText("Pin Set Successful");
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            startActivity(intent);
                            getActivity().finishAffinity();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, 1000);
                } else {
                    savePin(String.valueOf(confirmPin).hashCode());
                    if (updatedPin != null) {
                        savePinToServer(String.valueOf(confirmPin), updatedPin);
                    }


                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {
                        try {
                            binding.profileName.setText("Pin Set Successful");
                            onUserLogout();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, 1000);

                }


            } else {
                Handler handler = new Handler();
                binding.profileName.setText(" Pins are not equal");
                handler.postDelayed(() -> {
                    binding.profileName.setText("Input Again");
                    inputpins.clear();
                }, 1000);

            }
        }

    }

    private void onUserLogout() {
        UserDao db = UserDB.getDbInstance(getContext()).userDao();
        UserEntity entity = db.loaduserById(1);
        db.delete(entity);
        startActivity(new Intent(getActivity(), BaseLogin.class));
        requireActivity().finishAffinity();

    }


    public void savePin(int pin) {
        MyTimber.setPinTrials(0);
        Runnable pinSetterRunnable = () -> {
            db.updateId(String.valueOf(pin), 1);
        };
        new Thread(pinSetterRunnable).start();

    }

    private void savePinToServer(String updated_pin, String current_pin) {
        viewmodel.updatepin(getContext(), updated_pin, current_pin);
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPinSetterBinding = FragmentPinSetterAccountSettingBinding.inflate(inflater, container, false);
        return fragmentPinSetterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentPinSetterBinding.progrebar.setVisibility(View.INVISIBLE);
        db = UserDB.getDbInstance(this.getContext()).userDao();
        setPinShowET(setClickListenersToPinLoginPad());
        setDeletePad();

        setSubmitBUtton();
    }


    private void setSubmitBUtton() {
        fragmentPinSetterBinding.tvsubmit.setOnClickListener(view -> {
            try {
                validatePin(setSubmitPad());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private TextView[] setClickListenersToPinLoginPad() {
        TextView[] pins = new TextView[12];
        pins[0] = fragmentPinSetterBinding.tvno0;
        pins[1] = fragmentPinSetterBinding.tvno1;
        pins[2] = fragmentPinSetterBinding.tvno2;
        pins[3] = fragmentPinSetterBinding.tvno3;
        pins[4] = fragmentPinSetterBinding.tvno4;
        pins[5] = fragmentPinSetterBinding.tvno5;
        pins[6] = fragmentPinSetterBinding.tvno6;
        pins[7] = fragmentPinSetterBinding.tvno7;
        pins[8] = fragmentPinSetterBinding.tvno8;
        pins[7] = fragmentPinSetterBinding.tvno7;
        pins[8] = fragmentPinSetterBinding.tvno8;
        pins[9] = fragmentPinSetterBinding.tvno9;

        return pins;
    }

    private void setDeletePad() {
        fragmentPinSetterBinding.tvdelete.setOnClickListener(v -> {
                    fragmentPinSetterBinding.etpinShow.setText("");
                    inputpins.clear();
                }


        );
    }

    private int setSubmitPad() {
        String input = fragmentPinSetterBinding.etpinShow.getText().toString();
        return Integer.parseInt(input);
    }

    private void setPinShowET(TextView[] textViews) {
        for (TextView textview :
                textViews) {
            if (textview != null) {
                textview.setOnClickListener(v -> {

                            setUserPinDisplay(textview.getText().toString());
                        }

                );
            }
        }


    }

    private void setUserPinDisplay(String pinValue) {
        String pin = "";
        inputpins.add(pinValue);
        for (Object inputpin : inputpins
        ) {
            pin += inputpin;
            fragmentPinSetterBinding.etpinShow.setText(pin);

            System.out.println(pin);
        }

        setPinShowET(setClickListenersToPinLoginPad());

    }


    // user validation and  authentication
    @SuppressLint("ResourceAsColor")
    private void validatePin(int Pin) {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_check_24);

        fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);

        //on validation success
        if (String.valueOf(userPin).length() < 4) {
            if (String.valueOf(Pin).length() == 4) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    fragmentPinSetterBinding.progrebar.setVisibility(View.INVISIBLE);
                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
                    inputpins.clear();
                    fragmentPinSetterBinding.tvPinLogin.setText(" Confirm Your Pin ");
                    fragmentPinSetterBinding.etpinShow.setText("");
                    userPin = Pin;
                }, 1000);

            } else {
                Toast.makeText(getContext(), "User False", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    inputpins.clear();
                    fragmentPinSetterBinding.tvPinLogin.setText(" You need a 4 digit pin");
                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
                    fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                }, 1000);

            }
        } else if (String.valueOf(userPin).length() == 4) {
            confirmPin = Pin;
            if (confirmPin == userPin) {
                if (message != null && message.equals("FORGOTPIN")) {
                    try {
                        savePin(String.valueOf(confirmPin).hashCode());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    fragmentPinSetterBinding.progrebar.setVisibility(View.INVISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {

                        try {
                            fragmentPinSetterBinding.tvPinLogin.setText("Pin Set Successful");
                            fragmentPinSetterBinding.ivpinLock.setImageDrawable(drawable);
                            fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);

                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            startActivity(intent);
                            getActivity().finishAffinity();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, 1000);
                } else {
                    savePin(String.valueOf(confirmPin).hashCode());
                    if (updatedPin != null) {
                        savePinToServer(String.valueOf(confirmPin), updatedPin);
                    }


                    fragmentPinSetterBinding.progrebar.setVisibility(View.INVISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(() -> {
                        try {
                            fragmentPinSetterBinding.tvPinLogin.setText("Pin Set Successful");
                            fragmentPinSetterBinding.ivpinLock.setImageDrawable(drawable);
                            fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
                            onUserLogout();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, 1000);

                }


            } else {
                fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                fragmentPinSetterBinding.tvPinLogin.setText(" Pins are not equal");
                fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
                fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                handler.postDelayed(() -> {
                    fragmentPinSetterBinding.progrebar.setVisibility(View.INVISIBLE);
                    fragmentPinSetterBinding.tvPinLogin.setText("Input Again");
                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
                    fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#FF000000"));
                    fragmentPinSetterBinding.etpinShow.setText("");
                    inputpins.clear();
                }, 1000);

            }
        }

    }

    private void onUserLogout() {
        UserDao db = UserDB.getDbInstance(getContext()).userDao();
        UserEntity entity = db.loaduserById(1);
        db.delete(entity);
        startActivity(new Intent(getActivity(), BaseLogin.class));
        requireActivity().finishAffinity();

    }


    public void savePin(int pin) {
        MyTimber.setPinTrials(0);
        Runnable pinSetterRunnable = () -> {
            db.updateId(String.valueOf(pin), 1);
        };
        new Thread(pinSetterRunnable).start();

    }

    private void savePinToServer(String updated_pin, String current_pin) {
        viewmodel.updatepin(getContext(), updated_pin, current_pin);
    }*/


}