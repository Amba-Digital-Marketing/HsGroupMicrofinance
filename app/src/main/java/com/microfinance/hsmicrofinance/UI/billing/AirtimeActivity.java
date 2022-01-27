package com.microfinance.hsmicrofinance.UI.billing;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.microfinance.hsmicrofinance.UI.MyTimber;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.BillsAdapter;
import com.microfinance.hsmicrofinance.UI.adapters.RecyclerItemClickListener;
import com.microfinance.hsmicrofinance.Network.data.Constants;
import com.microfinance.hsmicrofinance.databinding.ActivityAirtimeBinding;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AirtimeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PHONE_NUMBER_PERMISSION = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Toolbar toolbar;
    private Dialog dialog;
    private TextView tvNo, tvYes;
    private static String TAG = AirtimeActivity.class.getSimpleName();
    private String bill, payer, str_phone, user_phone, str_bill_code, str_provider, str_amount;
    private MaterialButton cirButton;
    private TextInputEditText phoneTF, amountTF;
    private APIService mApiService;
    private RadioButton rbMine, rbOther;
    private ActivityAirtimeBinding activityAirtimeBinding;
    HomeActivityViewModel homeActivityViewModel;
    private static final int SELECT_CONTACT_REQUEST_CODE = 0;
    private LottieAnimationView lottieAnimationView;
    private int defaultPosition = 0;
    private Call<BillPayment> responseBodyCall;
    private SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAirtimeBinding = ActivityAirtimeBinding.inflate(getLayoutInflater());
        setContentView(activityAirtimeBinding.getRoot());
        toolbar = activityAirtimeBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = RetrofitInstance.getRetroClient(AirtimeActivity.this).create(APIService.class);
        defaultPosition = MyTimber.getCurrentAirtime();
        bill = MyTimber.getCurrentBill();

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Connecting to " + bill);
        sweetAlertDialog.setContentText("Please wait...");
        sweetAlertDialog.setCancelable(false);
        initViews();
        getPayer();

    }

    private void getPayer() {
        homeActivityViewModel = new ViewModelProvider(AirtimeActivity.this).get(HomeActivityViewModel.class);
        getUserData();
        updatePayer();
    }

    private void updatePayer() {
        homeActivityViewModel.dashBoardDataMutableLiveData.observe(AirtimeActivity.this, dashboardData -> {
            Timber.tag(TAG).d("dashboardData :" + dashboardData.getName() + " " + dashboardData.getAccBallance());
            payer = dashboardData.getPhone();
        });
    }

    private void populateData() {
        homeActivityViewModel = new ViewModelProvider(AirtimeActivity.this).get(HomeActivityViewModel.class);
        getUserData();
        updateUI();
    }

    private void getUserData() {
        homeActivityViewModel.getUserData();
    }

    private void updateUI() {

        homeActivityViewModel.dashBoardDataMutableLiveData.observe(AirtimeActivity.this, dashboardData -> {
            Timber.tag(TAG).d("dashboardData :" + dashboardData.getName() + " " + dashboardData.getAccBallance());
            phoneTF.setText(dashboardData.getPhone());
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        AirtimeActivity.this.finishAffinity();
        startActivity(new Intent(AirtimeActivity.this, BillingActivity.class));
    }

    private void initViews() {
        rbMine = activityAirtimeBinding.rbMine;
        rbOther = activityAirtimeBinding.rbOther;
        recyclerView = activityAirtimeBinding.recyclerView;
        phoneTF = activityAirtimeBinding.phoneTF;
        amountTF = activityAirtimeBinding.amountTF;
        cirButton = activityAirtimeBinding.cirButton;

        cirButton.setOnClickListener(this);
        rbMine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    populateData();
                }
            }
        });
        rbOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    phoneTF.setText("");
                }
            }
        });
        phoneTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    askPermissionAndBrowseFile();
                    rbOther.setChecked(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        ArrayList<BillPayment> bills = Constants.initAirtime();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(mLayoutManager);

        BillsAdapter adapter = new BillsAdapter(AirtimeActivity.this, bills, true);
        this.recyclerView.setAdapter(adapter);
        adapter.changeBackground(defaultPosition);
        this.recyclerView.smoothScrollToPosition(defaultPosition);

        BillPayment bill = adapter.getItem(defaultPosition);
        str_provider = bill.biller_name;
        str_bill_code = bill.biller_code;
        handleCheck(str_provider);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(AirtimeActivity.this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                adapter.changeBackground(position);
                                adapter.notifyDataSetChanged();

                                BillPayment bill = bills.get(position);
                                str_provider = bill.biller_name;
                                str_bill_code = bill.biller_code;
                                handleCheck(str_provider);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
    }

    private void handleCheck(String str_provider) {

        if (str_provider.equalsIgnoreCase("Safaricom")) {
            rbMine.setChecked(true);
            populateData();
        } else {
            rbOther.setChecked(true);
            phoneTF.setText("");
        }
    }

    private void askPermissionAndBrowseFile() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            // Check if we have Call permission
            int permisson = ActivityCompat.checkSelfPermission(AirtimeActivity.this,
                    Manifest.permission.READ_CONTACTS);

            if (permisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PHONE_NUMBER_PERMISSION
                );
                return;
            }
        }
        try {
            this.addContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addContacts() {
        Intent intent = new Intent(AirtimeActivity.this, PhoneBookActivity.class);
        startActivityForResult(intent, SELECT_CONTACT_REQUEST_CODE);


    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_CONTACT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    String phone = data.getStringExtra("phone");

                    Timber.tag("Received Data").e("phone number=========%s ", phone);

                    if (phone != null) {
                        String trimmed = phone.trim();
                        phoneTF.setText(trimmed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AirtimeActivity.this, "Experienced problems", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == cirButton) {
            str_phone = phoneTF.getText().toString();
            str_amount = amountTF.getText().toString();

            if (str_phone.isEmpty()) {
                phoneTF.setError("Enter Phone Number");
                phoneTF.requestFocus();
                return;
            }
            if (str_provider.isEmpty()) {
                Toast.makeText(AirtimeActivity.this, "Select Provider", Toast.LENGTH_SHORT).show();
                return;
            }
            if (str_amount.isEmpty()) {
                amountTF.setError("Enter Amount");
                amountTF.requestFocus();
                return;
            }
            if (payer.isEmpty()) {
                Toast.makeText(AirtimeActivity.this, "Please try again..", Toast.LENGTH_SHORT).show();
                return;
            }
//            sweetAlertDialog.show();
//            executeBuyAirtime(payer,str_phone, str_bill_code, str_amount);
            comingSoon();
        }
    }

    private void executeBuyAirtime(String sender, String str_phone, String str_provider, String str_amount) {

        try {
            mApiService = RetrofitInstance.getRetroClient(AirtimeActivity.this).create(APIService.class);

            responseBodyCall = mApiService.buyAirtime(sender, str_provider, str_amount, str_phone, str_phone);

            responseBodyCall.enqueue(new Callback<BillPayment>() {
                @Override
                public void onResponse(Call<BillPayment> call, Response<BillPayment> response) {

                    Timber.tag("onResponse").d("onResponse%s", response.body());
                    if (response.body() != null) {

                    /*   JSONObject result = null;
                       JSONObject errors = null; //your json value as parameter
                       try {
                           result = new JSONObject(response.body().toString());
                           String header_status = result.getString("header_status");
                           if (!header_status.isEmpty() && header_status.equalsIgnoreCase("200")) {

                           } else {
                               String error = result.getString("error");
                               errors = new JSONObject(error);
                               String inner_error = errors.getString("text");
                               Toast.makeText(AirtimeActivity.this, inner_error, Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                           Toast.makeText(AirtimeActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                       }*/
                        String message = "Please enter your pin to complete transaction";
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Transaction started");
                        sweetAlertDialog.setContentText(message);
                        sweetAlertDialog.setConfirmText("Okay");
                        sweetAlertDialog.setConfirmClickListener(sDialog -> {
                            sDialog.cancel();
                            sweetAlertDialog.cancel();
                            startActivity(new Intent(AirtimeActivity.this, AirtimeActivity.class)
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
                            startActivity(new Intent(AirtimeActivity.this, AirtimeActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        });
                    }
                }


                @Override
                public void onFailure(Call<BillPayment> call, Throwable t) {
                    call.cancel();
                    Timber.tag("onFailure").d("onFailure%s", t.toString());
                    Toast.makeText(AirtimeActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Error");
                    sweetAlertDialog.setContentText(t.getMessage());
                    sweetAlertDialog.setConfirmText("Okay");
                    sweetAlertDialog.setConfirmClickListener(sDialog -> {
                        sDialog.cancel();
                        sweetAlertDialog.cancel();
                        startActivity(new Intent(AirtimeActivity.this, AirtimeActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Timber.tag("Exception").d("Exception%s", e.getMessage());
        }
    }

    private void comingSoon() {

        dialog = new Dialog(AirtimeActivity.this);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}