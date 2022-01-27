package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.BillStatement;
import com.microfinance.hsmicrofinance.Network.models.Bills;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class BillsViewModel extends ViewModel {
    Context mContext;
    private APIService mApiService;
    private Call<Bills> mBillsCall;
    MutableLiveData<List<BillStatement.BillStatementDetail>>mMutableLiveDataofBillStatement;
    private Call<BillStatement> mStatementCall;

    public BillsViewModel() {
        mMutableLiveDataofBillStatement = new MutableLiveData<>();
    }

    public MutableLiveData<List<BillStatement.BillStatementDetail>> getObserverofBillStatement() {
        return mMutableLiveDataofBillStatement;
    }
    public void apiForAllBillHistory(Context context){
        APIService apiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mStatementCall = apiService.getBillStatements();
        mStatementCall.enqueue(new Callback<BillStatement>() {
            @Override
            public void onResponse(Call<BillStatement> call, Response<BillStatement> response) {
                Timber.tag("BillHistory").d("history %s", response.body().getBillStatementDetails().toString());
                mMutableLiveDataofBillStatement.postValue(response.body().getBillStatementDetails());
            }

            @Override
            public void onFailure(Call<BillStatement> call, Throwable t) {
                Timber.tag("BillHistory").d("failure %s", t.getMessage());

            }
        });
    }

    public void apiForCreatingAbill(Context context, String email, String title, String description, String amount ){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mBillsCall = mApiService.createBill(email, title, description, amount);
        mBillsCall.enqueue(new Callback<Bills>() {
            @Override
            public void onResponse(Call<Bills> call, Response<Bills> response) {
            SweetAlertDialog dialog=new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Success")
                        .setContentText(response.body().get0())
                        .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                                .setCancelable(false);

                        dialog.show();
            }

            @Override
            public void onFailure(Call<Bills> call, Throwable t) {
            SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                       dialog .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                               .setCancelable(false);
                       dialog .show();
            }

        });


    }

}
