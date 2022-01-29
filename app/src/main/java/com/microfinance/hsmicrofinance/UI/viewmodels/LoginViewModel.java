package com.microfinance.hsmicrofinance.UI.viewmodels;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinLoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterAccountSetting;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSettterLogin;
import com.microfinance.hsmicrofinance.UI.Fragments.UserVerification.UserVerificationFragment;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.Network.entity.LoginResponse;
import com.microfinance.hsmicrofinance.Network.entity.UserVerificationetails;
import com.microfinance.hsmicrofinance.databinding.FragmentLoginBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPasswordChangeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    Runnable loginRunnable;
    UserVerificationetails userVerificationetails;

    private static final String TAG = "LoginViewModel";
    public MutableLiveData<Integer> LiveDatacode = new MutableLiveData<>();
    public MutableLiveData<UserVerificationetails> userVerificationStatusLiveData = new MutableLiveData<>();
    private APIService mApiService;
    private Call<LoginResponse> mCall;
    public MutableLiveData<String> mUserMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<LoginResponse> mLiveData = new MutableLiveData<>();

    public void loginUser(String email, String password, Fragment host, String message, View view, FragmentLoginBinding binding) {


        mApiService = RetrofitInstance.getRetroClient(host.getContext()).create(APIService.class);
        mCall = mApiService.loginUser(email, password);
        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                binding.progrebar.setVisibility(View.INVISIBLE);

                if (response.code() == 201 && response.isSuccessful()) {
                    LiveDatacode.postValue(response.code());
                    setLoginCodeMLiveData(LiveDatacode);
                    //@TODO check verification code failing when 200

                    if (response.body() != null && response.isSuccessful()) {
                        if (message.equals("Pinsetter")){
                            replaceFragment(host,new PinSetterAccountSetting("FORGOTPIN"));
                        }else if(response.isSuccessful()){
                            //mLiveData.postValue(response.body());
                            //Save user to local storage
                            saveUsertoLocalDB(response.body(), host);
                            if(response.body().getPin_status().equals("1")){
                                //setVerification fragment
                                setVerificationActivity(host, response.body().getUser().getEmail(), -1, response.body().getToken());
                            }else{
                                replaceFragment(host,new PinSettterLogin(response.body()));
                            }

                        }

                    }
                }else{
                    binding.tvloginalert.setText("Invalid USER");

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                System.out.println(call);
                System.out.println(t.getMessage());
                LiveDatacode.postValue(null);
                mUserMutableLiveData = null;


                    binding.tvloginalert.setText("Invalid Credentials");
                    binding.progrebar.setVisibility(View.INVISIBLE);

            }
        });
    }




    public MutableLiveData<Integer> logincodeMLiveData = new MutableLiveData<>();
    public LiveData<Integer> logincodeLiveData;

    public MutableLiveData<Integer> getLoginCodeMLiveData() {
        logincodeMLiveData = (MutableLiveData<Integer>) logincodeLiveData;
        return logincodeMLiveData;
    }

    public MutableLiveData setLoginCodeMLiveData(MutableLiveData logincode) {
        logincodeMLiveData = logincode;
        setLoginCodeLiveData( logincodeMLiveData);
        return logincodeMLiveData;
    }

    public LiveData<Integer> getLoginCodeLiveData() {
        return logincodeLiveData;
    }

    public LiveData<Integer> setLoginCodeLiveData(MutableLiveData pinloginTrialsMLiveData) {

        this.logincodeLiveData = pinloginTrialsMLiveData;
        return logincodeLiveData;
    }



    //saving to local storage
    private UserEntity saveUsertoLocalDB(LoginResponse user, Fragment host) {
        final UserEntity[] users = new UserEntity[1];
        String pin = "";
       try {
           if(user.getUser().getPin() != null){
               pin = String.valueOf(user.getUser().getPin().hashCode());
           }

           String finalPin = pin;
           new Thread(() -> {
                UserDao db = UserDB.getDbInstance(host.getContext()).userDao();

                String id = String.valueOf(user.getUser().getId());
                String name = user.getUser().getName();
                String email = user.getUser().getEmail();
                String kra = user.getUser().getKra();
                String phone = user.getUser().getPhone();
                String accNo = user.getUser().getAccountNumber();
                String accBallance = String.valueOf(user.getUser().getBalance());
                String token = user.getToken();
                int verificationStatus = -1;

                users[0] = new UserEntity(1, id, name, email, kra, phone, accNo, accBallance, finalPin, token, 0, verificationStatus);
                try {
                    db.insertAll(users[0]);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(host.getContext(), "Request Failed", Toast.LENGTH_LONG).show();
                }
            }).start();

        }catch (Exception e){
           e.printStackTrace();

       }
        return users[0];
    }

    //get Verification Status from database
    public UserVerificationetails getVerificationStatusFromDatabase(Context context) {
        UserDao dao = UserDB.getDbInstance(context).userDao();
        if (dao != null) {
            UserEntity user = dao.loaduserById(1);
            if (user != null) {
                UserVerificationetails userVerificationetails = new UserVerificationetails(user.verificationStatus, user.email, String.valueOf(user.verificationCode), user.pin, user.usertoken);
                userVerificationStatusLiveData.postValue(userVerificationetails);
            } else {
                UserVerificationetails userVerificationetails = new UserVerificationetails(-1, null, "", null, null);

                userVerificationStatusLiveData.postValue(userVerificationetails);
            }
        } else {
            UserVerificationetails userVerificationetails = new UserVerificationetails(-1, null, "", null, null);

            userVerificationStatusLiveData.postValue(userVerificationetails);
        }
        return userVerificationetails;
    }


    // codes to set or replace a fragment
    public void replaceFragment(Fragment host, Fragment fragment) {
        host.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

    }

    public void setFragment(Fragment host, Fragment fragment) {

        host.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();

    }

    private void setVerificationActivity(Fragment host, String email, int verificationStatus, String token) {
       UserVerificationFragment fragment = new UserVerificationFragment(email,verificationStatus,token);
        replaceFragment(host,fragment);

    }


    //select user from db used by login fragment

//    public void getUserFromDb(Fragment host) {
//
//        loginRunnable = () -> {
//            try {
//                UserDao db = UserDB.getDbInstance(host.getContext()).userDao();
//                if (db.getToken(1) != null) {
//                        try {
//                            String pin = db.getAll().get(0).pin;
//                            if (pin != null && !pin.equals("null")) {
//                                PinLoginFragment pinLoginFragment = new PinLoginFragment(pin);
//
//                                Handler handler = new Handler(Looper.getMainLooper());
//
//                                handler.post(() -> setFragment(host, pinLoginFragment));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        };
//        new Thread(loginRunnable).start();
//    }


}
