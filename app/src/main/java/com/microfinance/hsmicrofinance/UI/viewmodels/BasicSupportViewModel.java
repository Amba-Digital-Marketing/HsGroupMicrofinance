package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.AddCommentSupportResponse;
import com.microfinance.hsmicrofinance.Network.entity.SingleSupportItem;
import com.microfinance.hsmicrofinance.Network.entity.SupportHistory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicSupportViewModel extends ViewModel {

    private static final String TAG =  "BasicSupport";
    private APIService mApiService;
    private Call<SupportHistory> mCall;

    public MutableLiveData<SupportHistory> mSupportItemsLiveData = new MutableLiveData<>();

    public void getSupportItems(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCall = mApiService.getSupportItems();
        mCall.enqueue(new Callback<SupportHistory>() {
            @Override
            public void onResponse(Call<SupportHistory> call, Response<SupportHistory> response) {
                mSupportItemsLiveData.postValue(response.body());
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<SupportHistory> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mSupportItemsLiveData.postValue(null);
            }
        });
    }

    private Call<SingleSupportItem> mCallSingleSupportItem;

    public MutableLiveData<SingleSupportItem> mSingleSupportItemLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mSingleSupportItemCodeLiveData = new MutableLiveData<>();
    public void getSingleSupportItem(Context context,int supportid){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCallSingleSupportItem = mApiService.getOneSupportItemById(supportid);
        mCallSingleSupportItem.enqueue(new Callback<SingleSupportItem>() {
            @Override
            public void onResponse(Call<SingleSupportItem> call, Response<SingleSupportItem> response) {
                mSingleSupportItemCodeLiveData.postValue(response.code());
                if(response.body() != null){
                   mSingleSupportItemLiveData.postValue(response.body());
                   Log.d(TAG, "onResponse: " + response.body());
               }
            }

            @Override
            public void onFailure(Call<SingleSupportItem> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                mSingleSupportItemCodeLiveData.postValue(null);
                System.out.println(call);
                mSingleSupportItemLiveData.postValue(null);
            }
        });
    }

// adding a support comment from the user to the server

    private Call<AddCommentSupportResponse> mCallAddCommentSupport;

    public MutableLiveData<AddCommentSupportResponse> mAddCommentLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> mAddCommentCodeLiveData = new MutableLiveData<>();

    public void addSupportComment(Context context, int id, String comment){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCallAddCommentSupport = mApiService.addCommenttoSupportItemById(id,comment);
        mCallAddCommentSupport.enqueue(new Callback<AddCommentSupportResponse>() {
            @Override
            public void onResponse(Call<AddCommentSupportResponse> call, Response<AddCommentSupportResponse> response) {
                mAddCommentCodeLiveData.postValue(response.code());
              if(response.body() != null){
                  mAddCommentLiveData.postValue(response.body());
                  Log.d(TAG, "onResponse: " + response.body());
              }
            }

            @Override
            public void onFailure(Call<AddCommentSupportResponse> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mAddCommentCodeLiveData.postValue(null);
                mSingleSupportItemLiveData.postValue(null);
            }
        });
    }
}
