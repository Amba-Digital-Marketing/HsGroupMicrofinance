package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.DetailsForWithdraw;
import com.microfinance.hsmicrofinance.Network.models.WithDrawOTPResponse;
import com.microfinance.hsmicrofinance.Network.models.WithdrawDetail;
import com.microfinance.hsmicrofinance.Network.models.WithrawOTPRequest;
import com.microfinance.hsmicrofinance.R;

import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import timber.log.Timber;

public class WithdrawViewModel extends ViewModel {
    MutableLiveData<List<WithdrawDetail.WithdrawMethodDetail>> mLiveDataofWithdrawDetail;
    MutableLiveData<DetailsForWithdraw.Details> mDetailsMutableLiveData;
    private APIService mApiService;
    private Call<WithdrawDetail> mDetailCall;
    private APIService mApiService1;
    private Call<DetailsForWithdraw> mDetailsForWithdrawCall;
    private MutableLiveData<WithrawOTPRequest.Details> mData;
    private APIService mApi;
    private Call<WithrawOTPRequest> mRequestCall;
    private APIService mService;
    private Call<WithDrawOTPResponse> mOtpResponseCall;

    public WithdrawViewModel() {
        mLiveDataofWithdrawDetail = new MutableLiveData<>();
        mDetailsMutableLiveData = new MutableLiveData<>();
        mData = new MutableLiveData<>();
    }

    public MutableLiveData<List<WithdrawDetail.WithdrawMethodDetail>> getLiveDataofWithdrawDetailObserver() {
        return mLiveDataofWithdrawDetail;
    }

    public MutableLiveData<DetailsForWithdraw.Details> getDetailsMutableLiveDataObserver() {
        return mDetailsMutableLiveData;
    }

    public MutableLiveData<WithrawOTPRequest.Details> getOTPObserver() {
        return mData;
    }

    public void apiForWithdrawdetails(Context context) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mDetailCall = mApiService.getWithdrawalDetails();
        mDetailCall.enqueue(new Callback<WithdrawDetail>() {
            @Override
            public void onResponse(Call<WithdrawDetail> call, Response<WithdrawDetail> response) {

                Timber.tag("WithrawDetail").d("List:: %s", response.body().getWithdrawMethodDetails().toString());
                if (response.isSuccessful()) {

                    mLiveDataofWithdrawDetail.postValue(response.body().getWithdrawMethodDetails());
                }

            }

            @Override
            public void onFailure(Call<WithdrawDetail> call, Throwable t) {
                Timber.tag("WithrawDetailError").d("Error:: %s", t.getMessage());
            }
        });
    }

    public void withdraw(Context context, int id, Double amount, int termId, View view) {
        mApiService1 = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mDetailsForWithdrawCall = mApiService1.withDraw(id, amount, termId);
        mDetailsForWithdrawCall.enqueue(new Callback<DetailsForWithdraw>() {
            @Override
            public void onResponse(Call<DetailsForWithdraw> call, Response<DetailsForWithdraw> response) {
                if (response.code() == 401) {
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Insufficient acc balance!")
                            .show();

                } else {
                    try {

                        mDetailsMutableLiveData.postValue(response.body().getDetails());
                        Timber.tag("Det").d("details%%%%%%%" + response.body().getDetails().toString());
                    SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);

                                dialog.setTitleText("Success")
                                .setContentText(response.body().get_0())

                                .setConfirmClickListener(onClick -> {
                                    NavController navController = Navigation.findNavController(view);

                                    navController.navigate(R.id.action_basicWithdrawMoney_to_confirmTransfer2);

                                    onClick.dismiss();
                                })

                                .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                        .setCancelable(false);
                                dialog.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Timber.tag("Det").d("details%%%%%%% " + response.body().getDetails().toString());
                    }

                }

            }

            @Override
            public void onFailure(Call<DetailsForWithdraw> call, Throwable t) {
                Timber.tag("Error").d("error%s", t.getMessage());
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();

            }
        });

    }


    public void getOTPforWithdraw(Context context, int amount, String account, View view) {

        mApi = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mRequestCall = mApiService.getWithDrawOtp(amount, account, 26, 2);
        mRequestCall.enqueue(new Callback<WithrawOTPRequest>() {
            @Override
            public void onResponse(Call<WithrawOTPRequest> call, Response<WithrawOTPRequest> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.code() == 200) {
                        mData.postValue(response.body().details);
                        Timber.tag("detailbody").d("body" + response.body().details.verificationCode);
                      SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setTitleText("Success")
                                .setContentText("Otp sent to your email")
                                .setConfirmClickListener(onClick -> {
                                    NavController navController = Navigation.findNavController(view);
                                    navController.navigate(R.id.action_confirmTransfer_to_tranferOTPconfirm);
                                    onClick.dismiss();
                                })
                                .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                               dialog.show();
                    } else if (response.code() != 200) {

                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .show();
                    }
                } else {

                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }

            }

            @Override
            public void onFailure(Call<WithrawOTPRequest> call, Throwable t) {
                Timber.tag("Erroe").d("err" + t.getMessage());
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();

            }
        });

    }


    public void confrimOTp(Context context, String otp, View view) {

        String amount = Objects.requireNonNull(mDetailsMutableLiveData.getValue()).amountWithdraw.toString().trim();
        String total_amount = mDetailsMutableLiveData.getValue().amountUsd.toString().trim();
        String total_charge = mDetailsMutableLiveData.getValue().fee.toString().trim();
        String account_no = mDetailsMutableLiveData.getValue().accountNumber;

        Timber.tag("Post Data ").d("Confirm Withdrawal amount%s", amount);
        Timber.tag("Post Data ").d("Confirm Withdrawal total_amount%s", total_amount);
        Timber.tag("Post Data ").d("Confirm Withdrawal total_charge%s", total_charge);
        Timber.tag("Post Data ").d("Confirm Withdrawal account_no%s", account_no);


        mService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mOtpResponseCall = mService.confirmOTpforWithdraw(otp, amount, total_amount, total_charge, account_no);
        mOtpResponseCall.enqueue(new Callback<WithDrawOTPResponse>() {
            @Override
            public void onResponse(Call<WithDrawOTPResponse> call, Response<WithDrawOTPResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                 SweetAlertDialog dialog=   new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Success")
                            .setContentText(Objects.requireNonNull(response.body())._0)
                            .setConfirmClickListener(onClick -> {
                                NavController navController = Navigation.findNavController(view);
                                navController.navigate(R.id.action_tranferOTPconfirm_to_basicLatestTransactions2);
                                onClick.dismiss();
                            })
                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                            dialog.show();

                } else {
                 SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                            dialog.setTitleText("Oops...")
                            .setConfirmClickListener(onClick -> {
                                NavController navController = Navigation.findNavController(view);
                                navController.navigate(R.id.back_to_initiate_withdraw);
                                onClick.dismiss();
                            })
                            .setContentText("Request failed, please try again later").setCancelable(false);
                            dialog.show();
                }
            }


            @Override
            public void onFailure(Call<WithDrawOTPResponse> call, Throwable t) {
                Timber.tag("otperr").d("errormsg%s", t.getMessage());
                SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Oops...")
                        .setConfirmClickListener(onClick -> {
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_tranferOTPconfirm_to_confirmTransfer);
                            onClick.dismiss();
                        })
                        .setContentText("Verification Code Does Not Match !!").setCancelable(false);
                        dialog.show();

            }

        });
    }
}
