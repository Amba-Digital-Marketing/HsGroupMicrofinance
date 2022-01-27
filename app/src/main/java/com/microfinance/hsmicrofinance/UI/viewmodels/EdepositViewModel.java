package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.Network.models.CreditTransferDeposit;
import com.microfinance.hsmicrofinance.Network.models.ManualDeposit;
import com.microfinance.hsmicrofinance.Network.models.ManualPaymentDetails;
import com.microfinance.hsmicrofinance.Network.models.MpesaDepositCallBack;
import com.microfinance.hsmicrofinance.Network.models.MpesaResponseBody;
import com.microfinance.hsmicrofinance.Network.models.MpesaStatus;
import com.microfinance.hsmicrofinance.Network.models.PaymentGateWays;
import com.microfinance.hsmicrofinance.Network.models.SubmitCreditDebitPayment;
import com.microfinance.hsmicrofinance.Network.models.UploadDetails;
import com.google.gson.JsonArray;
import com.microfinance.hsmicrofinance.databinding.FragmentMobileMoneyPaymentBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPayFriendsViaMpesaBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class EdepositViewModel extends ViewModel {
    private static final String TAG = "EdepositViewModel";
    MutableLiveData<CreditTransferDeposit.PaymentDetails>mPaymentDetailsMutableLiveData;
    MutableLiveData<SubmitCreditDebitPayment.EnteringTransactionDetailsInDepositTable>mTableMutableLiveData;
    private APIService mApiService;
    private Call<CreditTransferDeposit> mDepositCall;
    private APIService mSubmitService;
    private Call<SubmitCreditDebitPayment> mSubmitCreditDebitPaymentCall;
    MutableLiveData<List<PaymentGateWays.EdepostGateway>>mLiveDataofPaymentgateways;
    private APIService mGatewaysservicxe;
    private Call<PaymentGateWays> mPaymentGateWaysCall;
    MutableLiveData<MpesaResponseBody>mMpesaResponseBodyMutableLiveData;
    private APIService mMpesaService;
    private Call<MpesaResponseBody> mMpesaResponseBodyCall;
    MutableLiveData<List<MpesaDepositCallBack.MpesaPaymentDetail>>mListOfMpesaDetails;
    private APIService mPesahistory;
    private Call<MpesaDepositCallBack> mMpesaDepositCallBackCall;
    MutableLiveData<List<MpesaStatus.MpesaDetail>>mListofMpesaStatus;
    private APIService mStatusService;
    MutableLiveData<ManualPaymentDetails.PaymentDetails>mDataOfManualDeposit;
    MutableLiveData<ManualDeposit>mManualresponse;
    private APIService mManual;
    public static final int KENYANCURRENCY = 26;

    public EdepositViewModel() {
        mPaymentDetailsMutableLiveData = new MutableLiveData<>();
        mTableMutableLiveData = new MutableLiveData<>();
        mLiveDataofPaymentgateways= new MutableLiveData<>();
        mPaymentDetailsMutableLiveData = new MutableLiveData<>();
        mListOfMpesaDetails = new MutableLiveData<>();
        mListofMpesaStatus = new MutableLiveData<>();
        mDataOfManualDeposit = new MutableLiveData<>();
        mManualresponse = new MutableLiveData<>();
    }

    public MutableLiveData<ManualDeposit> getManualresponseObsever() {
        return mManualresponse;
    }

    public MutableLiveData<ManualPaymentDetails.PaymentDetails> getDataOfManualDepositObserver() {
        return mDataOfManualDeposit;
    }

    public MutableLiveData<List<MpesaDepositCallBack.MpesaPaymentDetail>> getListOfMpesaDetailsObserver() {
        return mListOfMpesaDetails;
    }

    public MutableLiveData<List<MpesaStatus.MpesaDetail>> getListofMpesaStatus() {
        return mListofMpesaStatus;
    }

    public MutableLiveData<MpesaResponseBody> getMpesaResponseBodyMutableLiveData() {
        return mMpesaResponseBodyMutableLiveData;
    }

    public MutableLiveData<CreditTransferDeposit.PaymentDetails> getPaymentDetailsObserver() {
        return mPaymentDetailsMutableLiveData;
    }

    public MutableLiveData<SubmitCreditDebitPayment.EnteringTransactionDetailsInDepositTable> getTableMutableLiveDataObserver() {
        return mTableMutableLiveData;
    }

    public MutableLiveData<List<PaymentGateWays.EdepostGateway>> getLiveDataofPaymentgatewaysObserver() {
        return mLiveDataofPaymentgateways;
    }

    public void manulDepositSubmit(Context context, UploadDetails uploadDetails,View view){
        try{

            File file = persistImage( context, uploadDetails.getBitmap(),"mpesa");
            RequestBody requestFile = RequestBody.create(file,MediaType.parse("*/*"));
            RequestBody comment = RequestBody.create(uploadDetails.getComment(),MediaType.parse("text/plain"));
            RequestBody currency = RequestBody.create(String.valueOf(KENYANCURRENCY),MediaType.parse("text/plain"));
          //  RequestBody id= RequestBody.create( uploadDetails.getId(),MediaType.parse("text/plain"));
            RequestBody amount = RequestBody.create( uploadDetails.getAmount().substring(4),MediaType.parse("text/plain"));
            Timber.tag("uploadservice").d("Dep res %s "," Amount: " +uploadDetails.getAmount().substring(4) +"  Id : "+ uploadDetails.getId());
            int myamount = (int) Double.parseDouble(uploadDetails.getAmount().substring(4));
            APIService uploadservice = RetrofitInstance.getRetroClient(context).create(APIService.class);

            //@TODO add request file
            Call<ManualDeposit>manualDepositCall = uploadservice.manualDepositdetails(11,myamount,comment,requestFile,currency);


            manualDepositCall.enqueue(new Callback<ManualDeposit>() {
                @Override
                public void onResponse(Call<ManualDeposit> call, Response<ManualDeposit> response) {
                    try {
                        if(response != null){
                            Timber.tag("Image").d("Uploads"+response.body().toString());
                            Timber.tag("uploadservice").d("Dep res %s "," Currency: " + currency +"  Id : "+ uploadDetails.getId());
                            mManualresponse.postValue(response.body());
                          SweetAlertDialog dialog=new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                                    dialog.setTitleText("Success")
                                    .setContentText(response.body().get_0())

                                    .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                                    .setConfirmClickListener(on->{
                                        NavController navController = Navigation.findNavController(view);
                                        navController.navigate(R.id.action_HSPaymentsSubmit_to_basicDepositHistory);
                                        on.dismiss();
                                    })
                                            .setCancelable(false);
                                    dialog.show();
                        }
                        else{
                            Timber.tag("Image").d("Response  is null" );

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ManualDeposit> call, Throwable t) {
                    Timber.tag("failure").d("failure"+t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private File persistImage(Context context, Bitmap bitmap, String name) {
        File filesDir = context.getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(TAG, "Error writing bitmap", e);
        }
        return  imageFile;
    }
public void checkManualDeposit(Context context, int id, int amount, View view)
{
    mManual = RetrofitInstance.getRetroClient(context).create(APIService.class);
    Call<ManualPaymentDetails>manualPaymentDetailsCall = mManual.manualDetailcheck(id, amount);
    manualPaymentDetailsCall.enqueue(new Callback<ManualPaymentDetails>() {
        @Override
        public void onResponse(Call<ManualPaymentDetails> call, Response<ManualPaymentDetails> response) {
            try{
                if(response.code() == 200){
                    mDataOfManualDeposit.postValue(response.body().getPaymentDetails());
                    Timber.tag("Res").d("Dep res %s", response.body().getPaymentDetails());
                  SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                            dialog.setTitleText("Success")
                            .setContentText(response.body().get_0())
                            .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                            .setConfirmClickListener(on->{
                                NavController navController = Navigation.findNavController(view);
                                navController.navigate(R.id.action_HSPay_to_HSPaymentsSubmit);
                                on.dismiss();
                            }).setCancelable(false);
                           dialog .show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ManualPaymentDetails> call, Throwable t) {
            Timber.tag("Error").d("Dep err%s", t.getMessage());
        }
    });

}
    public void callForCreditDebitDeposit(Context context, String amount, int id){
        mApiService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mDepositCall = mApiService.makeEdepositViaCreditDebit(id,amount);
        mDepositCall.enqueue(new Callback<CreditTransferDeposit>() {
            @Override
            public void onResponse(Call<CreditTransferDeposit> call, Response<CreditTransferDeposit> response) {
                mPaymentDetailsMutableLiveData.postValue(response.body().getPaymentDetails());
               SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                       dialog .setTitleText("Success")
                        .setContentText(response.body().get0())
                        .setConfirmButtonBackgroundColor(Color.parseColor("#297545")).setCancelable(false);

                        dialog.show();
            }

            @Override
            public void onFailure(Call<CreditTransferDeposit> call, Throwable t) {
             SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Oops...")
                        .setContentText("Something went wrong!").setCancelable(false);
                       dialog .show();

            }
        });
    }
    public void callForCreditDebitSubmission(Context context,Double amount, String Credentials, int type,int id){
        mSubmitService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mSubmitCreditDebitPaymentCall = mApiService.submitCreditDebitPayment(amount,Credentials,type,id);
        mSubmitCreditDebitPaymentCall.enqueue(new Callback<SubmitCreditDebitPayment>() {
            @Override
            public void onResponse(Call<SubmitCreditDebitPayment> call, Response<SubmitCreditDebitPayment> response) {
                //mTableMutableLiveData.postValue(response.body().getEnteringTransactionDetailsInDepositTable());
                if(response.code()==200) {

               SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Success")
                        .setContentText(response.body().get_0())
                        .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                        dialog.show();
            }
            }

            @Override
            public void onFailure(Call<SubmitCreditDebitPayment> call, Throwable t) {
                Timber.tag("server").d("res%s", t.getMessage());
               SweetAlertDialog dialog= new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Oops...")
                        .setContentText("Something went wrong!").setCancelable(false);
                       dialog .show();

            }
        });
    }
    public void getAllDepositGateways(Context context){
        mGatewaysservicxe = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mPaymentGateWaysCall = mGatewaysservicxe.getPaymentGateWays();
        mPaymentGateWaysCall.enqueue(new Callback<PaymentGateWays>() {
            @Override
            public void onResponse(Call<PaymentGateWays> call, Response<PaymentGateWays> response) {
                if(response.isSuccessful()){
                    mLiveDataofPaymentgateways.postValue(response.body().getEdepostGateways());
                }
            }

            @Override
            public void onFailure(Call<PaymentGateWays> call, Throwable t) {
                Timber.tag("Gateways").d("error%s", t.getMessage());
            }
        });

    }
    public  void mpesaDeposit(Context context, int amount, String phonenumber, View view, FragmentMobileMoneyPaymentBinding binding){
        mMpesaService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mMpesaResponseBodyCall = mMpesaService.depositMobileMoney(amount, phonenumber);
        mMpesaResponseBodyCall.enqueue(new Callback<MpesaResponseBody>() {
            @Override
            public void onResponse(Call<MpesaResponseBody> call, Response<MpesaResponseBody> response) {
                Timber.tag("MpesaResponse").d("Mpesa@@@@@@@@@%s", response.body().getCode());
                if(response.isSuccessful()){
                    try{
                        if(response.body().getCode()==200){
                            binding.progrebar.setVisibility(View.VISIBLE);
                        }
                        Timber.tag("MpesaResponse").d("body****%s", response.body());
                        mMpesaResponseBodyMutableLiveData.postValue(response.body());
                      SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                                dialog.setTitleText("Success")
                                .setContentText("Success")
                                .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                .setConfirmClickListener(click ->{
                                    NavController navController= Navigation.findNavController(view);

                                    navController.navigate(R.id.action_mobileMoneyPayment_to_basicDashboard);
                                    click.dismiss();
                                }).setCancelable(false);
                                dialog.show();
                    }catch (Exception e){

                    }

                }

            }

            @Override
            public void onFailure(Call<MpesaResponseBody> call, Throwable t) {
                Timber.tag("error").d("error"+t.getMessage());

            }
        });
    }
    public  void mpesaDepositPayFriend(Context context, int amount, String phonenumber, View view, FragmentPayFriendsViaMpesaBinding binding){
        mMpesaService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mMpesaResponseBodyCall = mMpesaService.depositMobileMoney(amount, phonenumber);
        mMpesaResponseBodyCall.enqueue(new Callback<MpesaResponseBody>() {
            @Override
            public void onResponse(Call<MpesaResponseBody> call, Response<MpesaResponseBody> response) {
                Timber.tag("MpesaResponse").d("Mpesa@@@@@@@@@%s", response.body().getCode());
                if(response.isSuccessful()){
                    try{
                        if(response.body().getCode()==200){
                            binding.progrebar.setVisibility(View.VISIBLE);
                        }
                        Timber.tag("MpesaResponse").d("body****%s", response.body());
                        mMpesaResponseBodyMutableLiveData.postValue(response.body());
                      SweetAlertDialog dialog=  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                               dialog .setTitleText("Success")
                                .setContentText("Success")
                                .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                .setConfirmClickListener(click ->{
                                    NavController navController= Navigation.findNavController(view);

                                    navController.navigate(R.id.action_mobileMoneyPayment_to_basicDashboard);
                                    click.dismiss();
                                }).setCancelable(false);
                                dialog.show();
                    }catch (Exception e){

                    }

                }

            }

            @Override
            public void onFailure(Call<MpesaResponseBody> call, Throwable t) {
                Timber.tag("error").d("error"+t.getMessage());

            }
        });
    }
    public void getMpesadeposithistory(Context context){
        mPesahistory = RetrofitInstance.getRetroClient(context).create(APIService.class);
        mMpesaDepositCallBackCall = mPesahistory.getMpesadepositHistory();
        mMpesaDepositCallBackCall.enqueue(new Callback<MpesaDepositCallBack>() {
            @Override
            public void onResponse(Call<MpesaDepositCallBack> call, Response<MpesaDepositCallBack> response) {


                    try{
                        mListOfMpesaDetails.postValue(response.body().getMpesaPaymentDetails());
                        Timber.tag("Mmm").d("pesa==="+response.body().getMpesaPaymentDetails());
                    }catch (Exception e){
                        e.printStackTrace();
                    }


            }

            @Override
            public void onFailure(Call<MpesaDepositCallBack> call, Throwable t) {
                Timber.tag("M").d("pesa==="+t.getMessage());

            }
        });
    }
    public void getMpesaStatus(Context context){
        mStatusService = RetrofitInstance.getRetroClient(context).create(APIService.class);
        Call<MpesaStatus>mpesaStatusCall= mStatusService.getMpesaStatus();
        mpesaStatusCall.enqueue(new Callback<MpesaStatus>() {
            @Override
            public void onResponse(Call<MpesaStatus> call, Response<MpesaStatus> response) {
                try{
                    Timber.tag("Status").d("status==="+response.body().getMpesaDetails());
                    mListofMpesaStatus.postValue(response.body().getMpesaDetails());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MpesaStatus> call, Throwable t) {


            }
        });
    }

    public void deletePending(Context context, int id){
        APIService delete = RetrofitInstance.getRetroClient(context).create(APIService.class);
        Call<JsonArray>jsonArrayCall= delete.deletePending(id);
        jsonArrayCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Timber.tag("delete").d("del"+t.getMessage());
            }
        });
    }
}
