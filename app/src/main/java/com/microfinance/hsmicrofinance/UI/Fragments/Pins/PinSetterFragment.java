package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import android.annotation.SuppressLint;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.BaseLogin;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentPinSetterAccountSettingBinding;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;
import com.microfinance.hsmicrofinance.UI.viewmodels.SignUpViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPinlockBinding;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PinSetterFragment extends Fragment {

    String TAG = "PinSetterFragment";
    SignUpViewModel signUpViewModel;
    List inputpins = new ArrayList();
    int userPin, confirmPin = 0;
    //    FragmentPinSetterAccountSettingBinding fragmentPinSetterBinding;
    FragmentPinlockBinding binding;
    UserDao db;
    UserRegistrationDetails details;


    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;


    public PinSetterFragment(UserRegistrationDetails userRegistrationDetails) {
        if (userRegistrationDetails != null) {
            this.details = userRegistrationDetails;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding = FragmentPinlockBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPinLockView = binding.pinLockView;
        mIndicatorDots = binding.indicatorDots;
        binding.profileName.setText("Create your Pin ");
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

            verifyPIN(Integer.parseInt(pin));

        }

        @Override
        public void onEmpty() {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };

    private void verifyPIN(int Pin) {

        binding.progrebar.setVisibility(View.VISIBLE);
        if (String.valueOf(userPin).length() < 4) {
            if (String.valueOf(Pin).length() == 4) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    binding.progrebar.setVisibility(View.GONE);
//                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
//                    inputpins.clear();
//                    fragmentPinSetterBinding.tvPinLogin.setText(" ");
//                    fragmentPinSetterBinding.etpinShow.setText("");
                    binding.profileName.setText("Confirm Your Pin ");
                    mPinLockView.resetPinLockView();
                    userPin = Pin;
                }, 1000);

            } else {
                Toast.makeText(getContext(), "User False", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    mPinLockView.resetPinLockView();
                    binding.profileName.setText(" You need a 4 digit pin");
//                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//                    fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                }, 1000);

            }
        } else if (String.valueOf(userPin).length() == 4) {
            confirmPin = Pin;
            if (confirmPin == userPin) {
                binding.progrebar.setVisibility(View.VISIBLE);
                // on validation failed
                savePin(String.valueOf(confirmPin));
                callApi(String.valueOf(confirmPin));
            } else {
                binding.progrebar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                binding.profileName.setText(" Pins are not equal");
//                fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
//                fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                handler.postDelayed(() -> {
                    binding.progrebar.setVisibility(View.GONE);
                    binding.profileName.setText("Input Again");
//                    fragmentPinSetterBinding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
//                    fragmentPinSetterBinding.etpinShow.setTextColor(Color.parseColor("#FF000000"));
//                    fragmentPinSetterBinding.etpinShow.setText("");
                    inputpins.clear();
                }, 1000);

            }
        }

    }


    public void savePin(String userpin) {
        Runnable pinSetterRunnable = () -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                db.updateId(String.valueOf(userpin.hashCode()), 1);
            });
        };
        new Thread(pinSetterRunnable).start();
    }

    private void callApi(String pin) {
        if (details != null) {
            UserRegistrationDetails userRegistrationDetails = new UserRegistrationDetails(
                    details.getName(), details.getEmail(), details.getPhone_number(), details.getKra(), details.getPassword(), details.getPassword(), details.getBitmap(), details.getTerm_condition(), pin);
            signUpViewModel.registerUser(userRegistrationDetails, this, binding);
        }
    }


}
/*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        // Inflate the layout for this fragment
        fragmentPinSetterBinding = FragmentPinSetterAccountSettingBinding.inflate(inflater,container,false);
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




    private void setSubmitBUtton(){
        fragmentPinSetterBinding.tvsubmit.setOnClickListener(view -> {
            try {
                validatePin(setSubmitPad());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private TextView[] setClickListenersToPinLoginPad(){
        TextView[] pins =  new TextView[12];
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

        return  pins;
    }
    private void setDeletePad(){
   fragmentPinSetterBinding.tvdelete.setOnClickListener(v->{
               fragmentPinSetterBinding.etpinShow.setText("");
               inputpins.clear();
           }


           );
    }

    private int setSubmitPad(){
        String input = fragmentPinSetterBinding.etpinShow.getText().toString();
        return Integer.parseInt(input);
    }

     private void setPinShowET(TextView [] textViews){
         for (TextView textview:
         textViews) {
             if(textview != null){
             textview.setOnClickListener(v ->{

                         setUserPinDisplay( textview.getText().toString());
                     }

                     );
             }
         }


     }

       private void setUserPinDisplay(String pinValue){
        String pin= "";
         inputpins.add(pinValue);
           for (Object inputpin: inputpins
           ) {
               pin += inputpin;
               fragmentPinSetterBinding.etpinShow.setText(pin);
           }

           setPinShowET(setClickListenersToPinLoginPad());

       }


     // user validation and  authentication
     @SuppressLint("ResourceAsColor")
     private void validatePin(int Pin){


         fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);

         //on validation success
         if(String.valueOf(userPin).length() <4 ) {
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
         }else if(String.valueOf(userPin).length() == 4){
             confirmPin = Pin;
             if(confirmPin == userPin) {
                 fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
                 // on validation failed
                 savePin(String.valueOf(confirmPin));
                 callApi(String.valueOf(confirmPin));
             }else{
                 fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
                 Handler handler = new Handler(Looper.getMainLooper());
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


     public void savePin(String userpin){
         Runnable pinSetterRunnable  = () -> {
             Handler handler = new Handler(Looper.getMainLooper());
             handler.post(() -> {
           db.updateId(String.valueOf(userpin.hashCode()),1);
             });
             };
         new Thread(pinSetterRunnable).start();
     }

    private void callApi(String pin){
        if(details != null){
            fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
            UserRegistrationDetails userRegistrationDetails = new UserRegistrationDetails(
                    details.getName(),details.getEmail(),details.getPhone_number(), details.getKra(), details.getPassword(), details.getPassword(), details.getBitmap(), details.getTerm_condition(), pin);
            signUpViewModel.registerUser(userRegistrationDetails, this, fragmentPinSetterBinding);
        }
    }



    //proceed to login
    private void proceedToLogin(){
        fragmentPinSetterBinding.parentconstraint.setActivated(false);
        Drawable drawable =  getResources().getDrawable(R.drawable.ic_baseline_check_24);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            fragmentPinSetterBinding.tvPinLogin.setText("Pin Set Successful");
            fragmentPinSetterBinding.ivpinLock.setImageDrawable(drawable);
            fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
           // setFragment(new LoginFragment());
        }, 1000);
    }




}*/
