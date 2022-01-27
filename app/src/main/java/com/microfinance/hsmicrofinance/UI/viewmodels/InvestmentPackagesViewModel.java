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
import com.microfinance.hsmicrofinance.Network.models.InvestPlans;
import com.microfinance.hsmicrofinance.Network.models.InvestmentPlanDepositModel;
import com.microfinance.hsmicrofinance.databinding.InvestmentPlanDepositBinding;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class InvestmentPackagesViewModel extends ViewModel {
    private static final String TAG = "InvetsPKGS";
  private   MutableLiveData<List<InvestPlans.InvestmentPackage>>mInvestPackagesMutableliveData ;
    private APIService mApiService;
    private Call<InvestPlans> mCall;
    private Call<InvestmentPlanDepositModel> mDepositModelCall;

    public InvestmentPackagesViewModel() {
        mInvestPackagesMutableliveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<InvestPlans.InvestmentPackage>> getInvestPackagesObserver() {
        return mInvestPackagesMutableliveData;
    }
    public void makeAPIcall(Context context){
        mApiService = RetrofitInstance.getRetroClient( context).create(APIService.class);
        mCall = mApiService.getAllInvestmentPackages();
        mCall.enqueue(new Callback<InvestPlans>() {
            @Override
            public void onResponse(Call<InvestPlans> call, Response<InvestPlans> response) {
                if(response.isSuccessful() && response != null){
                    InvestPlans investPlans = response.body();
                    List<InvestPlans.InvestmentPackage> investmentPackages = investPlans.getInvestmentPackages();
                    Log.d(TAG, "onResponse: " + investmentPackages.toString());
                    mInvestPackagesMutableliveData.postValue(investmentPackages);
                }
            }

            @Override
            public void onFailure(Call<InvestPlans> call, Throwable t) {

                Timber.tag("failure").d("result: %s", t.getMessage());
            }
        });
    }
    public void apiCallForInvestPlanDeposit(Context context, int id, String amount, View view, InvestmentPlanDepositBinding binding){
        mDepositModelCall = mApiService.postFixedDepositRequest(id,amount);
        mDepositModelCall.enqueue(new Callback<InvestmentPlanDepositModel>() {
            @Override
            public void onResponse(Call<InvestmentPlanDepositModel> call, Response<InvestmentPlanDepositModel> response) {

                    if(response.isSuccessful()){
                     SweetAlertDialog dialog=   new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setTitleText("Success")
                                .setContentText(response.body().get0())
                                .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                                .setConfirmClickListener((onclick)->{
                                    NavController navController = Navigation.findNavController(view);
                                    navController.navigate(R.id.action_investmentPlansDeposit_to_basicInvestmentHistory);
                                    binding.etAmount.setText(null);
                                    binding.progrebar.setVisibility(View.GONE);
                                    onclick.dismiss();
                                }).setCancelable(false);

                                dialog.show();
                    }


            }

            @Override
            public void onFailure(Call<InvestmentPlanDepositModel> call, Throwable t) {
                Timber.tag("onFaulire").d("failure++ "+t.getMessage());
              SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Oops...")
                        .setContentText("Something went wrong!").setCancelable(false);
                        dialog.show();
            }
        });
    }
}
