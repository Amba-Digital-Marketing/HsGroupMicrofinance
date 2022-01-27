package com.microfinance.hsmicrofinance.UI.billing;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.microfinance.hsmicrofinance.UI.MyTimber;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.Network.data.Constants;
import com.microfinance.hsmicrofinance.databinding.ActivityOtherBillBinding;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class OtherBillActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityOtherBillBinding activityOtherBillBinding;
    private Toolbar toolbar;
    private MaterialButton cirButton;
    private String bill, account_number;
    private TextView tvTitle, tvNumber;
    private TextInputEditText numberTF, billTF;
    private static String TAG = OtherBillActivity.class.getSimpleName();
    private APIService mApiService;
    HomeActivityViewModel homeActivityViewModel;
    private Dialog dialog;
    private TextView tvNo, tvYes;
    private LottieAnimationView lottieAnimationView;
    private String phone = "";
    private String amount = "";

    private Call<BillPayment> responseBodyCall;
    private Call<ResponseBody> responseBody;
    private SweetAlertDialog sweetAlertDialog;
    private String biller_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOtherBillBinding = ActivityOtherBillBinding.inflate(getLayoutInflater());
        setContentView(activityOtherBillBinding.getRoot());
        toolbar = activityOtherBillBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = RetrofitInstance.getRetroClient(OtherBillActivity.this).create(APIService.class);
        bill = MyTimber.getCurrentBill();
        biller_name = getbillerCode(bill);

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Connecting to " + bill);
        sweetAlertDialog.setContentText("Please wait...");
        sweetAlertDialog.setCancelable(false);
        initViews();
        populateData();
    }

    private String getbillerCode(String bill) {
        String biller_name = null;
        if (bill.equalsIgnoreCase("DSTV")) {
            biller_name = Constants.DSTV;
        }
        if (bill.equalsIgnoreCase("Zuku")) {
            biller_name = Constants.ZUKU;
        }
        if (bill.equalsIgnoreCase("GOtv")) {
            biller_name = Constants.GOTV;
        }
        if (bill.equalsIgnoreCase("Startimes")) {
            biller_name = Constants.STAR_TIMES;
        }
        if (bill.equalsIgnoreCase("Nairobi Water")) {
            biller_name = Constants.NAIROBI_WATER;
        }
        if (bill.equalsIgnoreCase("KPLC Prepaid")) {
            biller_name = Constants.KENYA_POWER_TOKEN;
        }
        if (bill.equalsIgnoreCase("KPLC Postpaid")) {
            biller_name = Constants.KENYA_POWER_POST;
        }
        return biller_name;
    }


    private void populateData() {
        homeActivityViewModel = new ViewModelProvider(OtherBillActivity.this).get(HomeActivityViewModel.class);
        getUserData();
        updateUI();
    }

    private void getUserData() {
        homeActivityViewModel.getUserData();
    }

    private void updateUI() {

        homeActivityViewModel.dashBoardDataMutableLiveData.observe(OtherBillActivity.this, dashboardData -> {
            Timber.tag(TAG).d("dashboardData :" + dashboardData.getName() + " " + dashboardData.getAccBallance());
            phone = dashboardData.getPhone();
        });

    }

    private void initViews() {
        cirButton = activityOtherBillBinding.cirButton;
        tvTitle = activityOtherBillBinding.tvTitle;
        tvNumber = activityOtherBillBinding.tvNumber;
        numberTF = activityOtherBillBinding.numberTF;
        billTF = activityOtherBillBinding.billTF;

        cirButton.setOnClickListener(this);

        tvTitle.setText(bill);
        if (!bill.isEmpty() && bill.equalsIgnoreCase("DSTV")) {
            tvNumber.setText(getString(R.string.smart_card));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        OtherBillActivity.this.finishAffinity();
        startActivity(new Intent(OtherBillActivity.this, BillingActivity.class));
    }

    @Override
    public void onClick(View v) {
        if (v == cirButton) {
            account_number = numberTF.getText().toString();
            amount = billTF.getText().toString();

            if (account_number.isEmpty()) {
                numberTF.setError("Enter Account Number");
                numberTF.requestFocus();
                return;
            }
            if (amount.isEmpty()) {
                billTF.setError("Enter Amount");
                billTF.requestFocus();
                return;
            }
            if (biller_name.isEmpty()) {
                Toast.makeText(OtherBillActivity.this, "Refresh App", Toast.LENGTH_SHORT).show();
                return;
            }
//            startActivity(new Intent(OtherBillActivity.this,WebviewActivity.class));
//            sweetAlertDialog.show();
//            executeBill(account_number, biller_name, phone, amount);
//            stripeDeposit(account_number, biller_name, phone, amount);
            comingSoon();

        }
    }

    private void comingSoon() {

        dialog = new Dialog(OtherBillActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_comming_soon);

        lottieAnimationView = dialog.findViewById(R.id.lottie);
        tvNo = dialog.findViewById(R.id.tvNo);
        tvYes = dialog.findViewById(R.id.tvYes);
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void executeBill(String account_number, String biller_name, String phone, String str_amount) {

        try {
            mApiService = RetrofitInstance.getRetroClient(OtherBillActivity.this).create(APIService.class);

            responseBodyCall = mApiService.buyAirtime(phone, biller_name, str_amount, phone, account_number);
            responseBodyCall.enqueue(new Callback<BillPayment>() {
                @Override
                public void onResponse(Call<BillPayment> call, Response<BillPayment> response) {

                    Timber.tag("onResponse").d("onResponse%s", response.body());
                    if (response.body() != null) {

                     /*  JSONObject result = null;
                       JSONObject errors = null; //your json value as parameter
                       try {
                           result = new JSONObject(response.body().toString());
                           String header_status = result.getString("header_status");
                           if (!header_status.isEmpty() && header_status.equalsIgnoreCase("200")) {

                           } else {
                               String error = result.getString("error");
                               errors = new JSONObject(error);
                               String inner_error = errors.getString("text");
                               Toast.makeText(OtherBillActivity.this, inner_error, Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(OtherBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                       }*/
                        String message = "Please enter your pin to complete transaction";
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Transaction started");
                        sweetAlertDialog.setContentText(message);
                        sweetAlertDialog.setConfirmText("Okay");
                        sweetAlertDialog.setConfirmClickListener(sDialog -> {
                            sDialog.cancel();
                            sweetAlertDialog.cancel();
                            startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        });


                    } else {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Error");
                        sweetAlertDialog.setContentText("Request Failed, try again later");
                        sweetAlertDialog.setConfirmText("Okay");
                        sweetAlertDialog.setConfirmClickListener(sDialog -> {
                            sDialog.cancel();
                            sweetAlertDialog.cancel();
                            startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        });
                    }
                }


                @Override
                public void onFailure(Call<BillPayment> call, Throwable t) {
                    call.cancel();
                    Timber.tag("onFailure").d("onFailure%s", t.toString());
                    Toast.makeText(OtherBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Error");
                    sweetAlertDialog.setContentText(t.getMessage());
                    sweetAlertDialog.setConfirmText("Okay");
                    sweetAlertDialog.setConfirmClickListener(sDialog -> {
                        sDialog.cancel();
                        sweetAlertDialog.cancel();
                        startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Timber.tag("Exception").d("Exception%s", e.getMessage());
        }
    }

    private void stripeDeposit(String account_number, String biller_name, String phone, String str_amount) {

        try {
            mApiService = RetrofitInstance.getRetroClient(OtherBillActivity.this).create(APIService.class);

            responseBody = mApiService.eDeposit(str_amount, "26");
            responseBody.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    Timber.tag("onResponse").d("onResponse%s", response.toString());
                    if (response.body() != null) {

                        JSONObject result = null;
                        JSONObject details = null; //your json value as parameter
                        try {
                            result = new JSONObject(response.body().string());
                            String header_status = result.getString("Payment Details");

                            details = new JSONObject(header_status);
                            String type = details.getString("type");
                            String message = "Please enter your pin to complete transaction";
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("Transaction started");
                            sweetAlertDialog.setContentText(message);
                            sweetAlertDialog.setConfirmText("Proceed");
                            sweetAlertDialog.setConfirmClickListener(sDialog -> {
                                sDialog.cancel();
//                                sweetAlertDialog.cancel();
                                startActivity(new Intent(OtherBillActivity.this, WebviewActivity.class).putExtra("amount",str_amount)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


                            });

                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            sweetAlertDialog.setTitleText("Error");
                            sweetAlertDialog.setContentText("Request Failed, try again later");
                            sweetAlertDialog.setConfirmText("Okay");
                            sweetAlertDialog.setConfirmClickListener(sDialog -> {
                                sDialog.cancel();
                                sweetAlertDialog.cancel();
                                startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                            });
                        }


                    } else {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Error");
                        sweetAlertDialog.setContentText("Request Failed, try again later");
                        sweetAlertDialog.setConfirmText("Okay");
                        sweetAlertDialog.setConfirmClickListener(sDialog -> {
                            sDialog.cancel();
                            sweetAlertDialog.cancel();
                            startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        });
                    }
                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    call.cancel();
                    Timber.tag("onFailure").d("onFailure%s", t.toString());
                    Toast.makeText(OtherBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Error");
                    sweetAlertDialog.setContentText(t.getMessage());
                    sweetAlertDialog.setConfirmText("Okay");
                    sweetAlertDialog.setConfirmClickListener(sDialog -> {
                        sDialog.cancel();
                        sweetAlertDialog.cancel();
                        startActivity(new Intent(OtherBillActivity.this, OtherBillActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Timber.tag("Exception").d("Exception%s", e.getMessage());
        }
    }
}