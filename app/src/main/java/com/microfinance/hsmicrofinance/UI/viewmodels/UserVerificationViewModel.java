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
import com.microfinance.hsmicrofinance.Network.entity.UserVerificationResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserVerificationViewModel extends ViewModel {

    private APIService mApiServiceVerification;
    private Call<UserVerificationResponse> mCallVerification;
    MutableLiveData<UserVerificationResponse>mLiveDataVerification = new MutableLiveData<>();
    Runnable verificationRunnable;
    private static final String TAG =  "VerificationViewModel";
    public MutableLiveData<Integer> LiveDatauserVerificationStatus = new MutableLiveData<>();
    public MutableLiveData<String> OTPCodeLiveData = new MutableLiveData<>();
    public MutableLiveData<String> ErrorLiveData = new MutableLiveData<>();
    private int verificationCode;
    private int verificationStatus;


    // call to get OTP
    public void makeApiCallForVerification(Context context, String email,String token){
        mApiServiceVerification = RetrofitInstance.getRetroClientWithToken(token).create(APIService.class);

        mCallVerification = mApiServiceVerification.checkUserVerification();

        mCallVerification.enqueue(new Callback<UserVerificationResponse>() {
            @Override
            public void onResponse(Call<UserVerificationResponse> call, Response<UserVerificationResponse> response) {
                mLiveDataVerification.postValue(response.body());

                if(response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().toString());

                  SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Verification Sent to \n"+ email)
                            .setContentText("Check your Email Inbox")
                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                            dialog.show();
                    try{

                        int OTP = response.body().getDataSentInEmail().getOtpNumber();

                        updateVerificationStatus( context,OTP,0);
                        OTPCodeLiveData.postValue(String.valueOf(OTP));
                        LiveDatauserVerificationStatus.postValue(0);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<UserVerificationResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t);
                System.out.println(call);
                LiveDatauserVerificationStatus.postValue(-1);
                ErrorLiveData.postValue(t.toString());
                Log.d(TAG, "onResponse: " + t.toString());

            }
        });
    }
//updating verification status in database
public void updateVerificationStatus(Context context,int verificationCode, int verificationStatus){
    UserDao db = UserDB.getDbInstance(context).userDao();
    verificationRunnable = () ->{
        db.updateVerificationCode(verificationCode,1);
        db.updateVerificationStatus( verificationStatus,1);




    };

    new Thread(verificationRunnable).start();
}


   public  MutableLiveData<String>mLiveDatasendOTPResponse = new MutableLiveData<>();

   public int getVerificationCodeDB(Context context){
       Runnable dbRunnable = () -> {
           UserDao db = UserDB.getDbInstance(context).userDao();
          verificationStatus = db.getVerificationStatus(1);
           verificationCode = db.getVerificationCode(1);
           postVerificationCode(verificationCode);
           OTPCodeLiveData.postValue(String.valueOf(verificationCode));
           LiveDatauserVerificationStatus.postValue(verificationStatus);
       };
       new  Thread(dbRunnable).start();

       return  verificationCode;

   }

   public int postVerificationCode(int code){
       this.verificationCode = code;
       OTPCodeLiveData.postValue(String.valueOf(verificationCode));
       LiveDatauserVerificationStatus.postValue(verificationStatus);
       return code;
   }



}

/*
*  public void makeApiCallConfirmVerification(Context context, String otp){
        mApiServicesendOTP = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCallsendOTP = mApiServicesendOTP.sendOTP(otp);

        mCallsendOTP.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mLiveDatasendOTPResponse.postValue(response.body());
                Log.d(TAG, "onResponse: " + response.body());
                if(response.body() != null && response.body().equals( "Success. After Calling this endpoint, redirect to dashboard")) {
                }else if(response.body() != null && response.body().equals( "Verfication Code not matched")) {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w(TAG, "onResponseError: " + t);
                System.out.println(call);
                Log.d(TAG, "onResponseError: " + t.toString());

            }
        });
    }
* */