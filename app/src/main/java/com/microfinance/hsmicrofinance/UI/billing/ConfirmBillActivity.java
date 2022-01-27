package com.microfinance.hsmicrofinance.UI.billing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.databinding.ActivityConfirmBillBinding;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmBillActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityConfirmBillBinding activityConfirmBillBinding;
    private Toolbar toolbar;
    private String bill;
    private TextView tvTitle;
    private TextInputEditText numberTF, nameTF, billTF, dateTF;
    private String str_number, str_name, str_bill, str_date;
    private MaterialButton cirButton;
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfirmBillBinding = ActivityConfirmBillBinding.inflate(getLayoutInflater());
        setContentView(activityConfirmBillBinding.getRoot());
        toolbar = activityConfirmBillBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }
    private void initViews() {
        numberTF = activityConfirmBillBinding.numberTF;
        nameTF = activityConfirmBillBinding.nameTF;
        billTF = activityConfirmBillBinding.billTF;
        dateTF = activityConfirmBillBinding.dateTF;
        tvTitle = activityConfirmBillBinding.tvTitle;
        cirButton = activityConfirmBillBinding.cirButton;

        tvTitle.setText(bill);
        numberTF.setText(str_number);
        cirButton.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v == cirButton) {
            str_number = numberTF.getText().toString();
            str_name = nameTF.getText().toString();
            str_bill = billTF.getText().toString();
            str_date = dateTF.getText().toString();
            if (str_number.isEmpty()) {
                numberTF.setError("Enter Account Number");
                numberTF.requestFocus();
                return;
            }
            if (str_name.isEmpty()) {
                nameTF.setError("Enter Account Name");
                nameTF.requestFocus();
                return;
            }
            if (str_bill.isEmpty()) {
                billTF.setError("Enter Amount ");
                billTF.requestFocus();
                return;
            }
            if (str_date.isEmpty()) {
                dateTF.setError("Date");
                dateTF.requestFocus();
                return;
            }
executeBill(str_number, str_name, str_bill, str_date);

        }
    }


    private void executeBill(String str_number, String str_name, String str_bill, String str_date) {

        BillPayment acc = new BillPayment( str_number, str_number,"kplc_prepaid");
        mApiService = RetrofitInstance.getRetroClient(ConfirmBillActivity.this).create(APIService.class);
        Call<ResponseBody> call1 = mApiService.validateAccount(acc);

        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() != null) {

                    JSONObject result = null;
                    JSONObject errors = null; //your json value as parameter
                    try {
                        result = new JSONObject(response.body().string());

                        String header_status = result.getString("header_status");
                        if (!header_status.isEmpty() && header_status.equalsIgnoreCase("200")) {

                        } else {
                            String error = result.getString("error");
                            errors = new JSONObject(error);
                            String inner_error = errors.getString("text");
                            Toast.makeText(ConfirmBillActivity.this, inner_error, Toast.LENGTH_SHORT).show();

                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ConfirmBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ConfirmBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();
                Toast.makeText(ConfirmBillActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}