package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.Countries;
import com.microfinance.hsmicrofinance.Network.entity.Currencies;
import com.microfinance.hsmicrofinance.Network.models.AccountOtpConfirmation;
import com.microfinance.hsmicrofinance.Network.models.AccountPasswordChange;
import com.microfinance.hsmicrofinance.Network.models.DashboardData;
import com.microfinance.hsmicrofinance.Network.models.UpdateAccountInfo;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.UI.LoginActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentPasswordChangeBinding;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivityViewModel extends ViewModel {
    String TAG = "HAViewModel";
    Context context;
    private DashboardData dashboardData;
    private MutableLiveData<UpdateAccountInfo.UpdatedDetails> mDetailsMutableLiveData;
    private APIService mApi;
    private Call<UpdateAccountInfo> mAccountInfoCall;

    // public HomeActivityViewModel(Context context){
    //      this.context = context;
    //  }

    public MutableLiveData<DashboardData> dashBoardDataMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AccountPasswordChange.DataSent> mChangeMutableLiveData;
    private APIService mChangePService;
    private Call<AccountPasswordChange> mChangeCall;
    private APIService mOtpService;
    private Call<AccountOtpConfirmation> mConfirmationCall;
    private APIService mResendService;
    private Call<String> mStringCall;
    private APIService mApiPassChange;
    private Call<AccountOtpConfirmation> mOtpConfirmationCall;

    public HomeActivityViewModel() {
        mDetailsMutableLiveData = new MutableLiveData<>();
        mChangeMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<AccountPasswordChange.DataSent> getChangeMutableLiveData() {
        return mChangeMutableLiveData;
    }

    public MutableLiveData<UpdateAccountInfo.UpdatedDetails> getDetailsMutableLiveData() {
        return mDetailsMutableLiveData;
    }

    public void getUserData() {
        UserDao db = UserDB.getDbInstance(context).userDao();

        Runnable dahboardRunnable = () -> {
            UserEntity user = db.loaduserById(1);
            dashboardData = new DashboardData(user.uid, user.id, user.name, user.email, user.KRAPin, user.phone, user.accNo, user.accBallance, user.pin, user.usertoken, user.verificationStatus);
            dashBoardDataMutableLiveData.postValue(dashboardData);
        };

        new Thread(dahboardRunnable).start();
    }

    public void upDateAccountInfo(Context context, String name, String email, String phone, String street, String city, String state, String postCode, String country, View view) {
        mApi = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mAccountInfoCall = mApi.updateAccountInfo(name, email, phone, street, city, state, postCode, country);
        mAccountInfoCall.enqueue(new Callback<UpdateAccountInfo>() {
            @Override
            public void onResponse(Call<UpdateAccountInfo> call, Response<UpdateAccountInfo> response) {
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                dialog.setTitleText("Success")
                        .setContentText(response.body().get0())
                        .setConfirmClickListener(onclick -> {
                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_accountSetting_to_basicDashboard);
                            onclick.dismiss();
                        })
                        .setConfirmButtonBackgroundColor(Color.parseColor("#297545")).setCancelable(false);

                dialog.show();
            }

            @Override
            public void onFailure(Call<UpdateAccountInfo> call, Throwable t) {
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Oops...")
                        .setContentText("Something went wrong!").setCancelable(false);
                dialog.show();
            }
        });


    }

    public void changePassword(Context context, String current, String password, String newPassword, View view, FragmentPasswordChangeBinding binding) {
        mChangePService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mChangeCall = mChangePService.changePassword(current, password, newPassword);
        mChangeCall.enqueue(new Callback<AccountPasswordChange>() {
            @Override
            public void onResponse(Call<AccountPasswordChange> call, Response<AccountPasswordChange> response) {


                if (response.body() != null) {
                    Timber.tag("Pass").d("code otp+++++++++++%s", response.body().getDataSent().getVerificationCode());
                    SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                    dialog.setTitleText(response.body().get0())
                            .setContentText("Confirmation Code Sent to " + response.body().getDataSent().getEmail())
                            .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                            .setConfirmClickListener(onClick -> {
                                NavController navController = Navigation.findNavController(view);
                                navController.navigate(R.id.action_passwordChange_to_updateSecurityCode);
                                onClick.dismiss();
                            }).setCancelable(false);
                    dialog.show();
                    mChangeMutableLiveData.postValue(response.body().getDataSent());
                }

            }

            @Override
            public void onFailure(Call<AccountPasswordChange> call, Throwable t) {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .setConfirmClickListener(onclick -> {
                            binding.progresbar.setVisibility(View.GONE);
                            onclick.dismiss();
                        })
                        .show();
            }
        });
    }

    public void ConfirmAccountChangePassword(Context context, int otp, String password) {
        mApiPassChange = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mOtpConfirmationCall = mApiPassChange.confirmOtp(otp, password);
        mOtpConfirmationCall.enqueue(new Callback<AccountOtpConfirmation>() {
            @Override
            public void onResponse(Call<AccountOtpConfirmation> call, Response<AccountOtpConfirmation> response) {
                if (response.isSuccessful()) {
                    new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(response.body().get0())
                            .setContentText("Confirmation Code Sent to " + response.body().get0())
                            .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                            .setConfirmClickListener(onClick -> {
                                UserDao db = UserDB.getDbInstance(context).userDao();
                                UserEntity entity = db.loaduserById(1);
                                context.startActivity(new Intent(context, LoginActivity.class));
                                db.delete(entity);
                                context.getApplicationContext();
                            })
                            .show();
                }
            }

            @Override
            public void onFailure(Call<AccountOtpConfirmation> call, Throwable t) {
                Timber.tag("Reponse").d("err===========%s", t.getMessage());
            }
        });
    }

    public void resendOtpForaccount(Context context) {
        mResendService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mStringCall = mChangePService.resendOtpForAccount();
        mStringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    Timber.tag("Reponse").d("message===========%s", response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Timber.tag("Reponse").d("message******************%s", t.getMessage());
            }
        });
    }


    //get bank details
    APIService mApiService;
    private Call<Banks> mBankDetailsCall;
    public MutableLiveData<Banks> mBankDetailsLiveData = new MutableLiveData<>();
    public Banks listOfBanks;

    public Banks getListOfBanks() {
        return listOfBanks;
    }

    public void setListOfBanks(Banks listOfBanks) {
        this.listOfBanks = listOfBanks;
    }

    public Banks getBankDetails(Context context) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mBankDetailsCall = mApiService.getOtherBankDetails();
        mBankDetailsCall.enqueue(new Callback<Banks>() {
            @Override
            public void onResponse(Call<Banks> call, Response<Banks> response) {
                mBankDetailsLiveData.postValue(response.body());
                setListOfBanks(mBankDetailsLiveData.getValue());
                Log.d(TAG, "onResponse Banks: " + response.body());
            }

            @Override
            public void onFailure(Call<Banks> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mBankDetailsLiveData.postValue(null);
            }
        });

        return listOfBanks;
    }


    //get Countries

    private Call<Countries> mCountriesDetailsCall;
    public MutableLiveData<Countries> mCountriesDetailsLiveData = new MutableLiveData<>();
    public Countries listOfCountries;

    public Countries getListOfCountries() {
        return listOfCountries;
    }

    public void setListOfCountries(Countries listOfCountries) {
        this.listOfCountries = listOfCountries;
    }

    public Countries getCountriesDetails(Context context) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCountriesDetailsCall = mApiService.getCountries();
        mCountriesDetailsCall.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                mCountriesDetailsLiveData.postValue(response.body());
                setListOfCountries(mCountriesDetailsLiveData.getValue());
               // Log.d(TAG, "onResponse Countries: " + response.body());
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mBankDetailsLiveData.postValue(null);
            }
        });
        return listOfCountries;
    }

    //get currencies  details
    private Call<Currencies> mCurrencyDetailsCall;
    private Call<ResponseBody> mTokenall;
    public MutableLiveData<Currencies> mCurrenciesDetailsLiveData = new MutableLiveData<>();
    public Currencies listOfCurrencies;

    public Currencies getListOfCurrencies() {
        return listOfCurrencies;
    }

    public void setListOfCurrencies(Currencies listOfCurrencies) {
        this.listOfCurrencies = listOfCurrencies;
    }

    public Currencies getCurrenciesDetails(Context context) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCurrencyDetailsCall = mApiService.getCurrencies();
        mCurrencyDetailsCall.enqueue(new Callback<Currencies>() {
            @Override
            public void onResponse(Call<Currencies> call, Response<Currencies> response) {
                mCurrenciesDetailsLiveData.postValue(response.body());
                setListOfCurrencies(mCurrenciesDetailsLiveData.getValue());
                Log.d(TAG, ": " + response.body());
            }

            @Override
            public void onFailure(Call<Currencies> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mCurrenciesDetailsLiveData.postValue(null);
            }
        });
        return listOfCurrencies;
    }

    public void displayCurrencies(Context context) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);

        mCurrencyDetailsCall = mApiService.getCurrencies();
        mCurrencyDetailsCall.enqueue(new Callback<Currencies>() {
            @Override
            public void onResponse(Call<Currencies> call, Response<Currencies> response) {
                mCurrenciesDetailsLiveData.postValue(response.body());
                Log.d(TAG, "onResponse Currencies: " + response.body());
            }

            @Override
            public void onFailure(Call<Currencies> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
                mCurrenciesDetailsLiveData.postValue(null);
            }
        });

    }


    public MutableLiveData<Integer> notifiedMLD = new MutableLiveData<>();
    LiveData<Integer> notifiedLD;

    public MutableLiveData<Integer> getNotifiedMLD() {
        notifiedMLD = (MutableLiveData<Integer>) notifiedLD;
        return notifiedMLD;
    }

    public MutableLiveData<Integer> setNotifiedMLD(int notified) {
        notifiedMLD.postValue(notified);
        setNotifiedLD(notifiedMLD);
        return notifiedMLD;
    }

    public LiveData<Integer> getNotifiedLD() {
        return notifiedLD;
    }

    public LiveData<Integer> setNotifiedLD(MutableLiveData notifiedMLD) {
        notifiedLD = notifiedMLD;
        return notifiedLD;
    }


    public void sendTokenToServer(Context context, String email, String token) {
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        Log.d(TAG, "sendTokenToServer: " + email);
        Log.d(TAG, "sendTokenToServer: " + token);

        mTokenall = mApiService.updateFCM(email, token);
        mTokenall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse FCM: " + response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t);
                System.out.println(call);
            }
        });

    }
}