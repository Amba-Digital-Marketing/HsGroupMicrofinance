package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.DepositHistory;
import com.microfinance.hsmicrofinance.Network.models.EdepositStatement;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoanHistoryViewModels extends ViewModel {
    public MutableLiveData<List<LoanHistories.LoanHistory>>mListMutableLiveData;
    public MutableLiveData<List<AllUsers.UserDetail>>mMutableLiveDataofAllUsers;
    public MutableLiveData<List<DepositHistory.Deposit>>mMutableLiveDataofDepositHistory;
    public MutableLiveData<List<EdepositStatement.EDepositStatement>>mMutableLiveDataofDepositStatement;
    private APIService mApiService;
    private Call<LoanHistories> mCall;
    private Call<AllUsers> mAllUsersCall;
    private Call<DepositHistory> mDepositHistoryCall;
    private APIService mApi;
    private APIService mDepStatement;
    private Call<EdepositStatement> mStatementCall;

    public LoanHistoryViewModels() {
        mListMutableLiveData = new MutableLiveData<>();
        mMutableLiveDataofAllUsers = new MutableLiveData<>();
        mMutableLiveDataofDepositHistory = new MutableLiveData<>();
        mMutableLiveDataofDepositStatement = new MutableLiveData<>();
    }
    public MutableLiveData<List<LoanHistories.LoanHistory>>getLoanHistoryObserver(){
        return mListMutableLiveData;
    }

    public MutableLiveData<List<EdepositStatement.EDepositStatement>> getMutableLiveDataofDepositStatement() {
        return mMutableLiveDataofDepositStatement;
    }

    public MutableLiveData<List<AllUsers.UserDetail>> getMutableLiveDataofAllUsers() {
        return mMutableLiveDataofAllUsers;
    }

    public MutableLiveData<List<DepositHistory.Deposit>> getMutableLiveDataofDepositHistory() {
        return mMutableLiveDataofDepositHistory;
    }

    public void makeAPIcall(Context context){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mCall = mApiService.getLoanhistory();
        mCall.enqueue(new Callback<LoanHistories>() {
            @Override
            public void onResponse(Call<LoanHistories> call, Response<LoanHistories> response) {
               if(response.body() != null){
                   LoanHistories loanHistories = response.body();
                   List<LoanHistories.LoanHistory>loanHistoryList = loanHistories.getLoanHistory();
                   mListMutableLiveData.postValue(loanHistoryList);
               }
            }

            @Override
            public void onFailure(Call<LoanHistories> call, Throwable t) {

            }
        });

    }
    public void fetchAllUsers(Context context){
        APIService allUser = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mAllUsersCall = allUser.getAllUsers();
        mAllUsersCall.enqueue(new Callback<AllUsers>() {
            @Override
            public void onResponse(Call<AllUsers> call, Response<AllUsers> response) {
                if(response.body()!= null){
                    AllUsers allUsers = response.body();
                    List<AllUsers.UserDetail> userDetailList = allUsers.getUserDetails();
                    mMutableLiveDataofAllUsers.postValue(userDetailList);
                }
            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {

            }
        });
    }
    public void fetchAllDepositHistory(Context context){
        mApi = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mDepositHistoryCall = mApi.getDepositHistory();
        mDepositHistoryCall.enqueue(new Callback<DepositHistory>() {
            @Override
            public void onResponse(Call<DepositHistory> call, Response<DepositHistory> response) {
                Timber.tag("DepHistory").d("size :%s", response.body().getDeposits().size());
                Timber.tag("DepHistory").d("history :%s", response.body().getDeposits().toString());
                mMutableLiveDataofDepositHistory.postValue(response.body().getDeposits());
            }

            @Override
            public void onFailure(Call<DepositHistory> call, Throwable t) {

                Timber.tag("DepHistory").d("Error :%s", t.getMessage());

            }
        });
    }
    public void fetchEdepositStatement(Context context){
        mDepStatement = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mStatementCall = mDepStatement.getEdepositStatement();
        mStatementCall.enqueue(new Callback<EdepositStatement>() {
            @Override
            public void onResponse(Call<EdepositStatement> call, Response<EdepositStatement> response) {
                Timber.tag("Edeposit").d("statement "+ response.body().getEDepositStatement().toString());
                mMutableLiveDataofDepositStatement.postValue(response.body().getEDepositStatement());
            }

            @Override
            public void onFailure(Call<EdepositStatement> call, Throwable t) {
                Timber.tag("Edeposit").d("statement "+t.getMessage());


            }
        });
    }


}
