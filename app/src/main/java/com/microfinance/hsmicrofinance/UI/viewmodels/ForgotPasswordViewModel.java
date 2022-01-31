package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.ForgotPasswordResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends ViewModel {
    private static final String TAG =  "ForgotPassword";
    private APIService mApiService;
    private Call<ForgotPasswordResponse> mCall;

    public MutableLiveData<ForgotPasswordResponse> mfpLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mfpLiveDatacode = new MutableLiveData<>();


    public void createforgotPassword(Context context, String email ){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCall = mApiService.forgotpassword(email);
        mCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                mfpLiveDatacode.postValue(response.code());

                if(response.code() == 200 && response.body() != null){
                    mfpLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());

                   SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                           dialog .setTitleText("Verification Sent to \n"+ email)
                            .setContentText("Check your Email Inbox")
                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                            dialog.show();
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mfpLiveData.postValue(null);
                mfpLiveDatacode.postValue(null);
               // @TODO correct response
               SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Error")
                        .setContentText("Could Not Send Reset Link")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                       dialog .show();
            }
        });
    }

}
