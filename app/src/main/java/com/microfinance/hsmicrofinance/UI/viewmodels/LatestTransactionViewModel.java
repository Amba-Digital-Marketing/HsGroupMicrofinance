package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.TransactionHistory;
import com.microfinance.hsmicrofinance.databinding.FragmentStatementGroupTransferBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestTransactionViewModel extends ViewModel {
    private static final String TAG = "TRX";
    private APIService mApiService;
    private Call<TransactionHistory> mCall;
    private MutableLiveData<List<TransactionHistory.TransactionHistoryDetail>> mLatestTransactionMutableLiveData;

    public LatestTransactionViewModel() {
        mLatestTransactionMutableLiveData = new MutableLiveData<>();

    }
    public MutableLiveData<List<TransactionHistory.TransactionHistoryDetail>> getLatestTransactionObserver(){
        return  mLatestTransactionMutableLiveData;
    }
    public void makeAPIcall(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mCall = mApiService.getAllTransactions();
        mCall.enqueue(new Callback<TransactionHistory>() {
            @Override
            public void onResponse(Call<TransactionHistory> call, Response<TransactionHistory> response) {
                TransactionHistory transactionHistory = response.body();
                List<TransactionHistory.TransactionHistoryDetail>transactionHistoryDetails= transactionHistory.getTransactionHistoryDetails();

                Log.d(TAG, "onResponse: "+ transactionHistoryDetails.toString());
                mLatestTransactionMutableLiveData.postValue(transactionHistoryDetails);
            }

            @Override
            public void onFailure(Call<TransactionHistory> call, Throwable t) {

            }
        });

    }


}
