package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.BankTransferHistory;
import com.microfinance.hsmicrofinance.Network.entity.Banks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementOtherBankTransferViewModel extends ViewModel {

    private static final String TAG =  "StatementVM";
    private APIService mApiService;
    private Call<BankTransferHistory> mCall;

    public MutableLiveData<BankTransferHistory> mLiveData = new MutableLiveData<>();

    public void getTransferHistory(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

       mCall = mApiService.getOtherBankStatement();
        mCall.enqueue(new Callback<BankTransferHistory>() {
            @Override
            public void onResponse(Call<BankTransferHistory> call, Response<BankTransferHistory> response) {
                mLiveData.postValue(response.body());
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<BankTransferHistory> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mLiveData.postValue(null);
            }
        });
    }

    private Call<Banks> mBankDetailsCall;

    public MutableLiveData<Banks> mBankDetailsLiveData = new MutableLiveData<>();

    public void getBankDetails(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mBankDetailsCall = mApiService.getOtherBankDetails();
        mBankDetailsCall.enqueue(new Callback<Banks>() {
            @Override
            public void onResponse(Call<Banks> call, Response<Banks> response) {
                mBankDetailsLiveData.postValue(response.body());
                Log.d(TAG, "onResponse Banks: " + response.body());
            }

            @Override
            public void onFailure(Call<Banks> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mBankDetailsLiveData.postValue(null);
            }
        });
    }
}
