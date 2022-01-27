package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.models.AccountSettingConfirmation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AccountSettingsViewModel extends ViewModel {

    private APIService mApiService;
    private Call<AccountSettingConfirmation> mConfirmationCall;
    private MutableLiveData<Integer> mAccountSettingConfirmationMutableLiveData;

    public AccountSettingsViewModel() {
        mAccountSettingConfirmationMutableLiveData = new MutableLiveData<Integer>();

    }

    public MutableLiveData<Integer> getAccountSettingConfirmationMutableLiveData() {
        return mAccountSettingConfirmationMutableLiveData;
    }



    public void confirmAccountSettings (Context context, String password){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mConfirmationCall = mApiService.confirmAccountPassword(password);
        mConfirmationCall.enqueue(new Callback<AccountSettingConfirmation>() {
            @Override
            public void onResponse(Call<AccountSettingConfirmation> call, Response<AccountSettingConfirmation> response) {
                Timber.tag("ConfirmSettings").d("code:::: %s", response.code());

                    mAccountSettingConfirmationMutableLiveData.postValue(response.code());

            }

            @Override
            public void onFailure(Call<AccountSettingConfirmation> call, Throwable t) {
                Timber.tag("ConfirmSettings").d("Error%s", t.getMessage());
            }
        });

    }


}
