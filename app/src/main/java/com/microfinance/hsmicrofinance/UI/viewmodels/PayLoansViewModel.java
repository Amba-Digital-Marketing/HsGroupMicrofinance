package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.InternalTransferResponse;
import com.microfinance.hsmicrofinance.Network.entity.PayLoanResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayLoansViewModel extends ViewModel {
    private static final String TAG =  "vmInternalTransfer";
    private APIService mApiService;

    private Call<PayLoanResponse> mCall;
    private MutableLiveData<PayLoanResponse> mOtherBankTransferMutableLiveData = new MutableLiveData<>();
    MutableLiveData<PayLoanResponse>mLiveData = new MutableLiveData<>();

    public MutableLiveData<PayLoanResponse> getUserObserver(){
        return  mOtherBankTransferMutableLiveData;
    }


    public void payLoans(Context context,int loanid){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCall = mApiService.payLoan(loanid);

        mCall.enqueue(new Callback<PayLoanResponse>() {
            @Override
            public void onResponse(Call<PayLoanResponse> call, Response<PayLoanResponse> response) {
                if(response.isSuccessful()){
                    mOtherBankTransferMutableLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }else{

                }
            }

            @Override
            public void onFailure(Call<PayLoanResponse> call, Throwable t) {
                mOtherBankTransferMutableLiveData.postValue(null);
              SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Failed To Pay the Loan")
                        .setContentText("Try Again shortly")
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                        dialog.show();
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });
    }

}
