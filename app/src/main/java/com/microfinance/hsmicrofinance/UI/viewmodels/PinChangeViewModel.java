package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.PinChangeResponse;
import com.microfinance.hsmicrofinance.Network.entity.UserVerificationResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class PinChangeViewModel extends ViewModel {

    private APIService mApiService;
    private static final String TAG ="PinChangeViewModel" ;
    private Call<PinChangeResponse> updatepinCall;
    private MutableLiveData<PinChangeResponse> updatepinLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> updatepinCodeLiveData = new MutableLiveData<>();

    public void updatepin(Context context,String updated_pin, String current_pin){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        updatepinCall = mApiService.updatepin(updated_pin,current_pin);
        updatepinCall.enqueue(new Callback<PinChangeResponse>() {
            @Override
            public void onResponse(Call<PinChangeResponse> call, Response<PinChangeResponse> response) {
                updatepinCodeLiveData.postValue(response.code());
               if(response.isSuccessful() && response.body()!= null) {
                    updatepinLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<PinChangeResponse> call, Throwable t) {
                updatepinLiveData.postValue(null);
                updatepinCodeLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }


}
