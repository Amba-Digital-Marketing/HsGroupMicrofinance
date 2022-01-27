package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.BanksCurrencyDetails;
import com.microfinance.hsmicrofinance.Network.entity.OtherBankTransferDetails;
import com.microfinance.hsmicrofinance.Network.entity.OtherBankTransferResponse;
import com.microfinance.hsmicrofinance.Network.entity.TransferSuccesful;
import com.microfinance.hsmicrofinance.UI.MyTimber;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class OtherBankTransferViewModel extends ViewModel {

    private static final String TAG = "OBTransfer";
    private APIService mApiService;
    private Call<OtherBankTransferResponse> mCall;
    public MutableLiveData<OtherBankTransferResponse> mOtherBankTransferMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<OtherBankTransferResponse> mLiveData = new MutableLiveData<>();

    public MutableLiveData<OtherBankTransferResponse> getUserObserver() {
        return mOtherBankTransferMutableLiveData;
    }

    public void transferToOtherBanks(Context context, OtherBankTransferDetails details) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        Timber.d("onResponseMoney: " + (details.getBank() + "..." + details.getCurrency() + "..." + details.getBranch() + "..." + details.getAccount_holder() + "..." + details.getAccount_no() + "..." + details.getAmount()));

        mCall = mApiService.transferToOtherBanks(details.getBank(), details.getCurrency(), details.getBranch(),
                details.getAccount_holder(), details.getAccount_no(), details.getAmount());
        mCall.enqueue(new Callback<OtherBankTransferResponse>() {
            @Override
            public void onResponse(Call<OtherBankTransferResponse> call, Response<OtherBankTransferResponse> response) {

                if (response.isSuccessful() && response.code() >= 200 && response.code() < 300) {
                    mOtherBankTransferMutableLiveData.postValue(response.body());
                    getTransferOTP(context, details);
                    Log.d(TAG, "onResponseMoney: " + response.body());
                    Log.d(TAG, "onResponseMoneyCode: " + response.code());

                } else {
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Failed To Transfer")
                            .setContentText("Try Again shortly")
                            .setNeutralButtonTextColor(Color.parseColor("#297545"))
                            .show();
                }
            }

            @Override
            public void onFailure(Call<OtherBankTransferResponse> call, Throwable t) {
                mOtherBankTransferMutableLiveData.postValue(null);
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Failed To Transfer")
                        .setContentText("Try Again shortly")
                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                        .show();
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }


    private Call<String> transferrOtpCall;
    public MutableLiveData<String> motpresponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> motpresponseLiveDataCode = new MutableLiveData<>();

    public MutableLiveData<String> getTransferOTPObserver() {
        return motpresponseMutableLiveData;
    }

    public void getTransferOTP(Context context, OtherBankTransferDetails details) {

        try {
            String currency = details.getCurrency();
            int bankid = details.getBank();
            String branch = details.getBranch();
            String account_no = details.getAccount_no();
            String accountHolderName = details.getAccount_holder();
            double amount = details.getAmount();

            mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
            transferrOtpCall = mApiService.getHSOtherBankTransferOTP(currency,
                    bankid,
                    branch,
                    account_no,
                    accountHolderName,
                    amount);

            transferrOtpCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() >= 200 && response.code() < 300) {
                        motpresponseMutableLiveData.postValue(response.body());
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Verification Sent to Email \n")
                                .setContentText("Check your Email Inbox")
                                .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                .show();
                        motpresponseLiveDataCode.postValue(response.code());
                        Log.d(TAG, "onResponseOTP: " + response.body());
                        Log.d(TAG, "onResponseOTPCode: " + response.code());
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    motpresponseMutableLiveData.postValue(null);
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Failed To Transfer")
                            .setContentText("There is an error Sending OTP")
                            .setNeutralButtonTextColor(Color.parseColor("#297545"))
                            .show();
                    Log.w(TAG, "onResponse: " + t);

                    System.out.println(call);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private Call<TransferSuccesful> cofirmOTPCall;
    public MutableLiveData<TransferSuccesful> mconfirmotpMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mconfirmotpLiveDataCode = new MutableLiveData<>();

    public MutableLiveData<TransferSuccesful> confirmTransferOTPObserver() {
        return mconfirmotpMutableLiveData;
    }

    public void sendTransferOTPToServer(Context context, String otp,OtherBankTransferResponse response) {

       try {
           int currency = Integer.parseInt(response.getDataInSession().getCurrency_id());
           double amount = Double.parseDouble(response.getDataInSession().getAmount());
           String accountHolderName = response.getDataInSession().getAccount_holder_name();
           String account_no =response.getDataInSession().getAccount_no();
           int currency_id = Integer.parseInt(response.getDataInSession().getCurrency_id());
           double currency_rate = Double.parseDouble(response.getDataInSession().getCurrency_rate());
           String branch = response.getDataInSession().getBranch();
           String bank = response.getDataInSession().getBank();
           String total_charge = response.getDataInSession().getTotal_charge();


           mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
           cofirmOTPCall = mApiService.sendHSOtherBankTransferOTP(currency,
                   otp,
                   amount,
                   accountHolderName,
                   account_no,
                   currency_id,
                   currency_rate,
                   branch,
                   bank,
                   total_charge);
           cofirmOTPCall.enqueue(new Callback<TransferSuccesful>() {
               @Override
               public void onResponse(Call<TransferSuccesful> call, Response<TransferSuccesful> response) {
                   mconfirmotpMutableLiveData.postValue(response.body());
                   Log.d(TAG, "onResponse: " + response.body());
                   mconfirmotpLiveDataCode.postValue(response.code());

               }

               @Override
               public void onFailure(Call<TransferSuccesful> call, Throwable t) {
                   mconfirmotpMutableLiveData.postValue(null);
                   Log.w(TAG, "onResponse: " + t);
                   System.out.println(call);
               }
           });
       }catch (Exception e){
           e.printStackTrace();
           Toast.makeText(context.getApplicationContext(), "Please try again", Toast.LENGTH_SHORT).show();
       }

    }


    private Call<BanksCurrencyDetails> banksCurrencyDetailsCall;
    public MutableLiveData<BanksCurrencyDetails> mbanksMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mCodeMutableLiveData = new MutableLiveData<>();

    public void getOtherBankDetailsFromCountryId(Context context, String countryid) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        banksCurrencyDetailsCall = mApiService.getOtherBankDetailsFromCountryId(countryid);
        banksCurrencyDetailsCall.enqueue(new Callback<BanksCurrencyDetails>() {
            @Override
            public void onResponse(Call<BanksCurrencyDetails> call, Response<BanksCurrencyDetails> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mbanksMutableLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                    mCodeMutableLiveData.postValue(response.code());
                }

            }

            @Override
            public void onFailure(Call<BanksCurrencyDetails> call, Throwable t) {
                mbanksMutableLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });

    }


}
