package com.microfinance.hsmicrofinance.UI.billing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.UI.MyTimber;
import com.microfinance.hsmicrofinance.UI.adapters.BillsAdapter;
import com.microfinance.hsmicrofinance.UI.adapters.RecyclerItemClickListener;
import com.microfinance.hsmicrofinance.Network.data.Constants;
import com.microfinance.hsmicrofinance.databinding.ActivityBillingBinding;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {

    public static ActivityBillingBinding activityBillingBinding;

    private RecyclerView recyclerView, recyclerViewTV, recyclerViewPower, recyclerViewWater;
    private RecyclerView.Adapter adapter, adapter1, adapter2, adapter3;
    private RecyclerView.LayoutManager mLayoutManager, mLayoutManager1, mLayoutManager2, mLayoutManager3;
    private ArrayList<BillPayment> airtime, tv, power, water;
    private Toolbar toolbar;
    private TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBillingBinding = ActivityBillingBinding.inflate(getLayoutInflater());
        setContentView(activityBillingBinding.getRoot());
        toolbar =activityBillingBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BillingActivity.this.finishAffinity();
        startActivity(new Intent(BillingActivity.this, HomeActivity.class));
    }
    private void initViews() {
        recyclerView = activityBillingBinding.recyclerView;
        recyclerViewTV = activityBillingBinding.recyclerViewTV;
        recyclerViewPower = activityBillingBinding.recyclerViewPowers;
        recyclerViewWater = activityBillingBinding.recyclerViewWater;

        airtime = Constants.initAirtime();
        tv = Constants.initPayTV();
        power = Constants.initPower();
        water = Constants.initWater();

        mLayoutManager = new GridLayoutManager(BillingActivity.this, 3);
        mLayoutManager1 = new GridLayoutManager(BillingActivity.this, 3);
        mLayoutManager2 = new GridLayoutManager(BillingActivity.this, 3);
        mLayoutManager3 = new GridLayoutManager(BillingActivity.this, 3);

        this.recyclerView.setLayoutManager(mLayoutManager);
        this.recyclerViewPower.setLayoutManager(mLayoutManager1);
        this.recyclerViewTV.setLayoutManager(mLayoutManager2);
        this.recyclerViewWater.setLayoutManager(mLayoutManager3);

        adapter = new BillsAdapter(BillingActivity.this, airtime,false);
        adapter1 = new BillsAdapter(BillingActivity.this, tv,false);
        adapter2 = new BillsAdapter(BillingActivity.this, power,false);
        adapter3 = new BillsAdapter(BillingActivity.this, water,false);

        this.recyclerView.setAdapter(adapter);
        this.recyclerViewTV.setAdapter(adapter1);
        this.recyclerViewPower.setAdapter(adapter2);
        this.recyclerViewWater.setAdapter(adapter3);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(BillingActivity.this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                BillPayment bill = airtime.get(position);
                                MyTimber.setCurrentAirtime(position);
                                MyTimber.setCurrentBill(bill.biller_name);
                                startActivity(new Intent(BillingActivity.this, AirtimeActivity.class));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
        recyclerViewTV.addOnItemTouchListener(
                new RecyclerItemClickListener(BillingActivity.this,
                        recyclerViewTV,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                BillPayment bill = tv.get(position);
                                MyTimber.setCurrentBill(bill.biller_name);
                                startActivity(new Intent(BillingActivity.this, OtherBillActivity.class));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
        recyclerViewPower.addOnItemTouchListener(
                new RecyclerItemClickListener(BillingActivity.this,
                        recyclerViewPower,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                BillPayment bill = power.get(position);
                                MyTimber.setCurrentBill(bill.biller_name);
                                startActivity(new Intent(BillingActivity.this, OtherBillActivity.class));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
        recyclerViewWater.addOnItemTouchListener(
                new RecyclerItemClickListener(BillingActivity.this,
                        recyclerViewWater,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // do whatever
                                BillPayment bill = water.get(position);
                                MyTimber.setCurrentBill(bill.biller_name);
                                startActivity(new Intent(BillingActivity.this, OtherBillActivity.class));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
        );
    }
}