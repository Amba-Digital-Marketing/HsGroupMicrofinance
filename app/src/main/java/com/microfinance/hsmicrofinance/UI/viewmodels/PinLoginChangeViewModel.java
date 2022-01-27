package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.LoginResponse;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.UserVerification.UserVerificationFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinLoginChangeViewModel extends ViewModel {

    private APIService mApiService;
    private static final String TAG ="PinLoginVM" ;
    private Call<String> updatepinCall;
    private MutableLiveData<String> updatepinLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> updatepinCodeLiveData = new MutableLiveData<>();

    public void updatepin(Fragment host, LoginResponse loginResponse, String current_pin){
        try{
            LoginResponse.User user = loginResponse.getUser();

            mApiService = RetrofitInstance.getRetroClient(host.getContext()).create(APIService.class);
            updatepinCall = mApiService.updatepinOnLogin(user.getEmail(), current_pin);
            updatepinCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    updatepinCodeLiveData.postValue(response.code());
                    if (response.isSuccessful() && response.body() != null) {
                        updatepinLiveData.postValue(response.body());
                        setVerificationActivity(host, user.getEmail(), -1, loginResponse.getToken());
                        Log.d(TAG, "onResponse: " + response.body());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    updatepinLiveData.postValue(null);
                    updatepinCodeLiveData.postValue(null);
                    Log.w(TAG, "onResponse: " + t);
                    System.out.println(call);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setVerificationActivity(Fragment host, String email, int verificationStatus, String token) {
        UserVerificationFragment fragment = new UserVerificationFragment(email,verificationStatus,token);
        replaceFragment(host,fragment);

    }

    public void replaceFragment(Fragment host, Fragment fragment) {
        host.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

    }

}
