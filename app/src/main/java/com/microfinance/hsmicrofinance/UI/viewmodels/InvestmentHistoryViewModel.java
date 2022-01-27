package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.InvestmentHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class InvestmentHistoryViewModel extends ViewModel {
    public APIService mService;
    public Call<InvestmentHistory>mCall;
    public MutableLiveData<List<InvestmentHistory.InvestementHistoryDetail>>mInvestmentHistoryMutableLiveData;


    public InvestmentHistoryViewModel() {
        mInvestmentHistoryMutableLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<List<InvestmentHistory.InvestementHistoryDetail>>getInvestmentHistoryMutableLiveDataObserver(){
        return mInvestmentHistoryMutableLiveData;
    }
    public void makeApiCall(Context context){
        mService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mCall = mService.getAllInvestmentHistory();
        mCall.enqueue(new Callback<InvestmentHistory>() {
            @Override
            public void onResponse(Call<InvestmentHistory> call, Response<InvestmentHistory> response) {

            Timber.tag("investhistory").d("history%s", response.body().getInvestementHistoryDetails());
                mInvestmentHistoryMutableLiveData.postValue(response.body().getInvestementHistoryDetails());
            }

            @Override
            public void onFailure(Call<InvestmentHistory> call, Throwable t) {
                Timber.tag("investhistory").d("failure%s", t.getMessage());

            }
        });

        }

    }



