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
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.Network.models.LoanPlans;
import com.microfinance.hsmicrofinance.Network.models.LoanRequest;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanPackageViewModel extends ViewModel {
    private static final String TAG = "LoanPackageViewModel";


    private APIService mAPIService;
    private Call<LoanPlans>mCall;
    private MutableLiveData<List<LoanPlans.Loan>>mListMutableLiveData;
    private Call<LoanRequest> mLoanRequestCall;

    public LoanPackageViewModel() {
        mListMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<LoanPlans.Loan>> getPackagesObserver(){
        return mListMutableLiveData;
    }



    public void makeAPIcall(Context context){
        mAPIService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mCall = mAPIService.getAllLoanPackages();
        mCall.enqueue(new Callback<LoanPlans>() {
            @Override
            public void onResponse(Call<LoanPlans> call, Response<LoanPlans> response) {
                LoanPlans loanPlans = response.body();
                List<LoanPlans.Loan>loans = loanPlans.loanPackages;
                Log.d(TAG, "onResponse: "+ response.body().getLoanPackages().toString());
                mListMutableLiveData.postValue(response.body().getLoanPackages());
            }

            @Override
            public void onFailure(Call<LoanPlans> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());

            }

        });
    }
    public  void apiCallForLoanRequest(Context context, int id, String amount, View view){
        mLoanRequestCall = mAPIService.requestLoan(id,amount);
        mLoanRequestCall.enqueue(new Callback<LoanRequest>() {
            @Override
            public void onResponse(Call<LoanRequest> call, Response<LoanRequest> response) {
                if(response.code() == 200){
                   SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                           dialog .setTitleText("Success")
                            .setContentText(response.body().get0())
                            .setNeutralButtonTextColor(Color.parseColor("#297545"))
                            .setConfirmClickListener(click ->{
                                NavController navController= Navigation.findNavController(view);
                                navController.navigate(R.id.action_requestLoan_to_basicLoanHistory);
                                click.dismiss();
                            }).setCancelable(false);
                            dialog.show();
                }
            }

            @Override
            public void onFailure(Call<LoanRequest> call, Throwable t) {
              SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Oops...")
                        .setContentText("Something went wrong!").setCancelable(false);
                        dialog.show();
            }
        });
    }

}
