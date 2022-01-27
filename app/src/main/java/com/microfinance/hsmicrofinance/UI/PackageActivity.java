package com.microfinance.hsmicrofinance.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.SliderAdapter;
import com.microfinance.hsmicrofinance.databinding.ActivityPackageBinding;
import com.microfinance.hsmicrofinance.Network.entity.SliderData;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class  PackageActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPackageBinding mActivityPackageBinding;
    int []images ={R.drawable.mobilemoney,R.drawable.logo};
    private SliderAdapter mSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityPackageBinding = ActivityPackageBinding.inflate(getLayoutInflater());
        setContentView(mActivityPackageBinding.getRoot());
        mActivityPackageBinding.btnBasic.setOnClickListener(this);

        ArrayList<SliderData>images = new ArrayList<>();
        images.add(new SliderData(R.drawable.mobilemoney));
        images.add(new SliderData(R.drawable.logo));
        SliderAdapter adapter = new SliderAdapter(this,images);
        mActivityPackageBinding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_RTL);
        mActivityPackageBinding.slider.setSliderAdapter(adapter);
        mActivityPackageBinding.slider.setScrollTimeInSec(2);
        mActivityPackageBinding.slider.setAutoCycle(true);
        mActivityPackageBinding.slider.startAutoCycle();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_basic:
                loadBasicPackageHome();
                break;
        }

    }

    private void loadBasicPackageHome() {
        Intent intent = new Intent(PackageActivity.this,HomeActivity.class);
        overridePendingTransition(0,0);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}