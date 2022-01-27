package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.AccountExtraInfoResponse;
import com.microfinance.hsmicrofinance.Network.entity.PinChangeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountExtraInfoViewModel extends ViewModel {
    private APIService mApiService;
    private static final String TAG ="AccountInfo" ;
    private Call<AccountExtraInfoResponse> getExtraAccountInfo;
    public MutableLiveData<AccountExtraInfoResponse> extraUserInfoLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> extraCodeLiveData = new MutableLiveData<>();

    public void getExtraAccountInfo(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        getExtraAccountInfo = mApiService.AccountExtraInfoResponse();
        getExtraAccountInfo.enqueue(new Callback<AccountExtraInfoResponse>() {
            @Override
            public void onResponse(Call<AccountExtraInfoResponse> call, Response<AccountExtraInfoResponse> response) {
                extraCodeLiveData.postValue(response.code());
                if(response.isSuccessful() && response.body()!= null) {
                    extraUserInfoLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<AccountExtraInfoResponse> call, Throwable t) {
                extraUserInfoLiveData.postValue(null);
                extraCodeLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }


}
