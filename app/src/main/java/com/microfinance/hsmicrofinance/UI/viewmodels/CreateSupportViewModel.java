package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.SupportResponse;
import com.microfinance.hsmicrofinance.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSupportViewModel extends ViewModel {

    private static final String TAG =  "CreateSupport";
    private APIService mApiService;
    private Call<SupportResponse> mCall;

    public MutableLiveData<SupportResponse> mSupportResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mSupportResponseCodeLiveData = new MutableLiveData<>();
    private NavController navController;

    public void createSupport(Context context, View view, String title, String comment){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        navController = Navigation.findNavController(view);
        mCall = mApiService.createASupport(title, comment);
        mCall.enqueue(new Callback<SupportResponse>() {
            @Override
            public void onResponse(Call<SupportResponse> call, Response<SupportResponse> response) {


                mSupportResponseCodeLiveData.postValue(response.code());
                observeAPIResponse(response, context);
                if(response.body() != null){
                    mSupportResponseLiveData.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<SupportResponse> call, Throwable t) {
                mSupportResponseCodeLiveData.postValue(null);
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mSupportResponseLiveData.postValue(null);
            }
        });
    }
    private void observeAPIResponse(Response response,Context context){
        try{
            if (response.isSuccessful()) {
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Success")
                        .setContentText("Your Message has been sent Successfully")
                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                        .show();
                navController.navigate(R.id.action_addSupportFragment_to_basicSupport);
            } else {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Sorry!!")
                        .setContentText("We could not process your request now\n" +
                                "check your internet connection and try again")
                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                        .show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
