package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.Network.entity.LoginResponse;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoginViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.PinLoginChangeViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.SignUpViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPinSetterAccountSettingBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPinSettterLoginBinding;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PinSettterLogin extends Fragment {
    FragmentPinSettterLoginBinding binding;


    String TAG = "PinSetterFragment";
    PinLoginChangeViewModel viewModel;
    List inputpins = new ArrayList();
    int userPin, confirmPin = 0;
    UserDao db;
    LoginResponse loginResponse;

    public PinSettterLogin(LoginResponse loginResponse) {
        if(loginResponse != null){
            this.loginResponse = loginResponse;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PinLoginChangeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinSettterLoginBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.progrebar.setVisibility(View.INVISIBLE);
        db = UserDB.getDbInstance(this.getContext()).userDao();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.etpinShow.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method;
                method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(binding.etpinShow, false);
            } catch (Exception e) {
                // ignore
            }
        }

        setPinShowET(setClickListenersToPinLoginPad());
        setDeletePad();
        setSubmitBUtton();
    }




    private void setSubmitBUtton(){
        binding.tvsubmit.setOnClickListener(view -> {
            try {
                validatePin(setSubmitPad());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private TextView[] setClickListenersToPinLoginPad(){
        TextView[] pins =  new TextView[12];
        pins[0] = binding.tvno0;
        pins[1] = binding.tvno1;
        pins[2] = binding.tvno2;
        pins[3] = binding.tvno3;
        pins[4] = binding.tvno4;
        pins[5] = binding.tvno5;
        pins[6] = binding.tvno6;
        pins[7] = binding.tvno7;
        pins[8] = binding.tvno8;
        pins[7] = binding.tvno7;
        pins[8] = binding.tvno8;
        pins[9] = binding.tvno9;

        return  pins;
    }
    private void setDeletePad(){
        binding.tvdelete.setOnClickListener(v->{
                    binding.etpinShow.setText("");
                    inputpins.clear();
                }


        );
    }

    private int setSubmitPad(){
        String input = binding.etpinShow.getText().toString();
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
            binding.etpinShow.setText(pin);
        }

        setPinShowET(setClickListenersToPinLoginPad());

    }


    // user validation and  authentication
    @SuppressLint("ResourceAsColor")
    private void validatePin(int Pin){


        binding.progrebar.setVisibility(View.VISIBLE);

        //on validation success
        if(String.valueOf(userPin).length() <4 ) {
            if (String.valueOf(Pin).length() == 4) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    binding.progrebar.setVisibility(View.INVISIBLE);
                    binding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
                    inputpins.clear();
                    binding.tvPinLogin.setText(" Confirm Your Pin ");
                    binding.etpinShow.setText("");
                    userPin = Pin;
                }, 1000);

            } else {
                Toast.makeText(getContext(), "User False", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    inputpins.clear();
                    binding.tvPinLogin.setText(" You need a 4 digit pin");
                    binding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
                    binding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                }, 1000);

            }
        }else if(String.valueOf(userPin).length() == 4){
            confirmPin = Pin;
            if(confirmPin == userPin) {
                binding.progrebar.setVisibility(View.VISIBLE);
                // on validation failed
                savePin(String.valueOf(confirmPin));
                callApi(String.valueOf(confirmPin));
            }else{
                binding.progrebar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                binding.tvPinLogin.setText(" Pins are not equal");
                binding.tvPinLogin.setTextColor(Color.parseColor("#E0223C"));
                binding.etpinShow.setTextColor(Color.parseColor("#E0223C"));
                handler.postDelayed(() -> {
                    binding.progrebar.setVisibility(View.INVISIBLE);
                    binding.tvPinLogin.setText("Input Again");
                    binding.tvPinLogin.setTextColor(Color.parseColor("#297545"));
                    binding.etpinShow.setTextColor(Color.parseColor("#FF000000"));
                    binding.etpinShow.setText("");
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
        if(pin != null){
         try{
             viewModel.updatepin(this,loginResponse,pin);
         }catch (Exception e){
             e.printStackTrace();
         }

           // signUpViewModel.registerUser(userRegistrationDetails, this, binding);
        }
    }



    //proceed to login
    private void proceedToLogin(){
        binding.parentconstraint.setActivated(false);
        Drawable drawable =  getResources().getDrawable(R.drawable.ic_baseline_check_24);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            binding.tvPinLogin.setText("Pin Set Successful");
            binding.ivpinLock.setImageDrawable(drawable);
            binding.progrebar.setVisibility(View.VISIBLE);
            // setFragment(new LoginFragment());
        }, 1000);
    }











}