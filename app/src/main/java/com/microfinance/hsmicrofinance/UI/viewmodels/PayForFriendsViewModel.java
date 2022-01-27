package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.InternalTransferResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PayForFriendsViewModel extends ViewModel {
    private static final String TAG =  "vmInternalTransfer";
    private APIService mApiService;


    private Call<InternalTransferResponse> mCall;
    private MutableLiveData<InternalTransferResponse> mOtherBankTransferMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer>mLiveDataCode = new MutableLiveData<>();

    public MutableLiveData<InternalTransferResponse> getUserObserver(){
        return  mOtherBankTransferMutableLiveData;
    }

    public void transferToHSAccountBanks(Context context, String account, double amount){



        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCall = mApiService.transferToHSAccount(account,String.valueOf(amount));

        mCall.enqueue(new Callback<InternalTransferResponse>() {
            @Override
            public void onResponse(Call<InternalTransferResponse> call, Response<InternalTransferResponse> response) {
                mLiveDataCode.postValue(response.code());
               if(response.code() >= 200 && response.code()<300){
                mOtherBankTransferMutableLiveData.postValue(response.body());
               getInternalTransferOTP(context,(int)amount, account);
                getTransferOTPObserver();
                Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<InternalTransferResponse> call, Throwable t) {
                mLiveDataCode.postValue(null);
                mOtherBankTransferMutableLiveData.postValue(null);
              SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Failed To Transfer")
                        .setContentText("Try Again shortly")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                        dialog.show();
                Log.w(TAG, "onResponseError: " + t);
                System.out.println(call);
            }
        });
    }


    private Call<String> transferrOtpCall;
    public String OTPRespose;
    public MutableLiveData<String> motpresponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mutableLiveDataTransferOTP = new MutableLiveData<>();
    MutableLiveData<String>motpresponseLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getTransferOTPObserver(){
        return  motpresponseMutableLiveData;
    }

    public void getInternalTransferOTP(Context context,int amount,String account){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        transferrOtpCall = mApiService.getHSTransferOTP(amount,account);
        transferrOtpCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              //  Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                mutableLiveDataTransferOTP.postValue(response.code());
                if(response.code() >= 200 && response.code()<300) {
                    motpresponseMutableLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse getInternalTransferOTP: " + response.body());
                    OTPRespose = motpresponseMutableLiveData.getValue();
                    Log.d(TAG, "onResponse: " + response.body());
                }else{
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                motpresponseMutableLiveData.postValue(null);
             SweetAlertDialog dialog=   new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Failed To Transfer")
                        .setContentText("There is an error Sending OTP")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                        dialog.show();
                Log.w(TAG, "onResponse otp get fail: " + t);
                System.out.println(call);
            }
        });
    }

    private Call<String> cofirmOTPCall;
    public MutableLiveData<String> mconfirmotpMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mconfirmotpLiveData = new MutableLiveData<>();

    public MutableLiveData<String> confirmTransferOTPObserver(){
        return  mconfirmotpMutableLiveData;
    }

    public void sendTransferOTP(Context context,String otp){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        cofirmOTPCall = mApiService.sendHSTransferOTP(otp);
        cofirmOTPCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                mconfirmotpMutableLiveData.postValue(response.body());
                mconfirmotpLiveData.postValue(response.code());
                Log.d(TAG, "onResponse: " + response.body());

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mconfirmotpMutableLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }
}
