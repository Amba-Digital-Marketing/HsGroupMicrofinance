package com.microfinance.hsmicrofinance.UI.billing;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.microfinance.hsmicrofinance.databinding.ActivityStripeBinding;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.model.Token;

public class StripeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityStripeBinding activityStripeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStripeBinding = ActivityStripeBinding.inflate(getLayoutInflater());
        setContentView(activityStripeBinding.getRoot());
        setSupportActionBar(activityStripeBinding.toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {

        activityStripeBinding.cirButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == activityStripeBinding.cirButton) {

            PaymentMethodCreateParams params = activityStripeBinding.cardMultiLineWidget.getPaymentMethodCreateParams();

            if (params != null) {

                Stripe stripe = new Stripe(StripeActivity.this, "pk_live_51JG3k5FtucG3Zywq82OxOkvZRKZ28WMFvTpapyDDw5v1br9Yi6N0SFoo5NCHsucRLrJao4qHj1Vw3fIWmqyUlXdr00GPW4TTQc");
                stripe.createCardToken(activityStripeBinding.cardMultiLineWidget.getCardParams(), "", "", new ApiResultCallback<Token>() {
                    @Override
                    public void onSuccess(@NonNull Token token) {
                        Log.e("StripeActivity","onSuccess : " + token.getId());
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        Log.e("StripeActivity","onError : " + e.getMessage());
                    }
                });
                Toast.makeText(StripeActivity.this, "Validating card....", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(StripeActivity.this, "Check all data fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
}