package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.InternalTransferResponse;
import com.microfinance.hsmicrofinance.Network.entity.SuccessfulTransfer;
import com.microfinance.hsmicrofinance.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InternalTransferViewModel extends ViewModel {
    private static final String TAG = "vmInternalTransfer";
    private APIService mApiService;

    HomeActivityViewModel homeActivityViewModel;
    private Call<InternalTransferResponse> mCall;
    public MutableLiveData<InternalTransferResponse> mOtherBankTransferMutableLiveData = new MutableLiveData<>();
    MutableLiveData<InternalTransferResponse> mLiveData = new MutableLiveData<>();

    public MutableLiveData<InternalTransferResponse> getUserObserver() {
        return mOtherBankTransferMutableLiveData;
    }


    private Call<String> transferrOtpCall;
    public String OTPRespose;
    public MutableLiveData<String> motpresponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mutableLiveDataTransferOTP = new MutableLiveData<>();
    MutableLiveData<String> motpresponseLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getTransferOTPObserver() {
        return motpresponseMutableLiveData;
    }


    private Call<SuccessfulTransfer> cofirmOTPCall;
    public MutableLiveData<SuccessfulTransfer> mconfirmotpMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mconfirmotpLiveData = new MutableLiveData<>();

    public MutableLiveData<SuccessfulTransfer> confirmTransferOTPObserver() {
        return mconfirmotpMutableLiveData;
    }

    public void getInternalTransferOTP(Context context, int amount, String account) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        transferrOtpCall = mApiService.getHSTransferOTP(amount, account);
        transferrOtpCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mutableLiveDataTransferOTP.postValue(response.code());
                if (response.code() == 200 && response.code() < 300 && response.isSuccessful()) {
                    Log.d(TAG, "onResponse getInternalTransferOTP: " + response.body());

                    motpresponseMutableLiveData.postValue(response.body());

                    OTPRespose = motpresponseMutableLiveData.getValue();
                    SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                    dialog.setTitleText("OTP Sent to Email \n")
                            .setContentText("Check your Email Inbox")
                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                    dialog.show();
                    Log.d(TAG, "onResponse: " + response.body());
                } else {
                    mutableLiveDataTransferOTP.postValue(null);
                    SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Failed To Transfer")
                            .setContentText(String.valueOf(response.code()))
                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                motpresponseMutableLiveData.postValue(null);
                mutableLiveDataTransferOTP.postValue(null);
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Failed To Transfer")
                        .setContentText("There is an error Sending OTP")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                dialog.show();

                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }


    public void transferToHSAccountBanks(Context context, String account, double amount) {
        Log.d(TAG, "transferToHSAccountBanks: amount"+amount+"account");

        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCall = mApiService.transferToHSAccount(account, String.valueOf(amount));

        mCall.enqueue(new Callback<InternalTransferResponse>() {
            @Override
            public void onResponse(Call<InternalTransferResponse> call, Response<InternalTransferResponse> response) {
                if (response.code() >= 200 && response.code() < 300 && response.isSuccessful()) {
                    try {
                        mOtherBankTransferMutableLiveData.postValue(response.body());
                        getInternalTransferOTP(context, (int) amount, account);
                        getTransferOTPObserver();
                        Log.d(TAG, "onResponse: " + response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    mutableLiveDataTransferOTP.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<InternalTransferResponse> call, Throwable t) {
                mOtherBankTransferMutableLiveData.postValue(null);
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Failed To Transfer")
                        .setContentText("Try Again shortly")

                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                dialog.show();
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mutableLiveDataTransferOTP.postValue(null);
            }
        });
    }

    public void sendTransferOTP(Context context, String otp) {

        String amount = String.valueOf(mOtherBankTransferMutableLiveData.getValue().transferInformation.amount);
        String total_amount = String.valueOf(mOtherBankTransferMutableLiveData.getValue().transferInformation.totalAmount);
        String total_charge = String.valueOf(mOtherBankTransferMutableLiveData.getValue().transferInformation.totalCharge);
        String account_no = String.valueOf(mOtherBankTransferMutableLiveData.getValue().transferInformation.accountNo);

        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        cofirmOTPCall = mApiService.sendTransferOTP(otp, amount, total_amount, total_charge, account_no);
        cofirmOTPCall.enqueue(new Callback<SuccessfulTransfer>() {
            @Override
            public void onResponse(Call<SuccessfulTransfer> call, Response<SuccessfulTransfer> response) {
                mutableLiveDataTransferOTP.postValue(response.code());
                mconfirmotpMutableLiveData.postValue(response.body());
                mconfirmotpLiveData.postValue(response.code());
                Log.d(TAG, "onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<SuccessfulTransfer> call, Throwable t) {
                mutableLiveDataTransferOTP.postValue(null);
                mconfirmotpMutableLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Failed To Transfer")
                        .setContentText("There is an error Verifying OTP")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                dialog.show();

            }
        });
    }


}
