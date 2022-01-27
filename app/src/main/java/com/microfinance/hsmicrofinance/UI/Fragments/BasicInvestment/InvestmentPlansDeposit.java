package com.microfinance.hsmicrofinance.UI.Fragments.BasicInvestment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.Network.APIService;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.InvestmentPlanDepositBinding;
import com.microfinance.hsmicrofinance.Network.models.InvestmentPlanDepositModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.InvestmentPackagesViewModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;


public class InvestmentPlansDeposit extends Fragment {
    private static final String TAG = "InvestPLANS";
    InvestmentPlanDepositBinding mBinding;
    private int mId;
    private String mTitle;
    private int mMin;
    private int mMax;
    private int mDuration;
    private int mPercent;
    private int mStatus;
    private APIService mApiService;
    private Call<InvestmentPlanDepositModel> mCall;
    private InvestmentPackagesViewModel mInvestmentPackagesViewModel;
    private HomeActivityViewModel mViewModel;
    private String mBalance;
    private NavController mNavController;


    public InvestmentPlansDeposit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = InvestmentPlanDepositBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        //mInvestmentPlanDepositBinding.progrebar.setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        mViewModel.dashBoardDataMutableLiveData.observe(getViewLifecycleOwner(), dashboardData -> mBalance = dashboardData.getAccBallance());

        mInvestmentPackagesViewModel = new ViewModelProvider(requireActivity()).get(InvestmentPackagesViewModel.class);
        mId = getArguments().getInt("id");
        mTitle = getArguments().getString("title");
        mMin = getArguments().getInt("min");
        mMax = getArguments().getInt("max");
        mDuration = getArguments().getInt("duration");
        mPercent = getArguments().getInt("percent");
        mStatus = getArguments().getInt("status");

        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle(mTitle);
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_investmentPlansDeposit_to_basicInvestmentPackages));

        switch (mId) {
            case 1:
                Picasso.get().load(R.drawable.standard).into(mBinding.ivInvestIcon);
                mBinding.materialCardView.setCardBackgroundColor(Color.parseColor("#1d2453"));
                break;
            case 2:
                Picasso.get().load(R.drawable.gold_ingots).into(mBinding.ivInvestIcon);
                mBinding.materialCardView.setCardBackgroundColor(Color.parseColor("#b8860b"));

                break;
            case 3:
                Picasso.get().load(R.drawable.platinum).into(mBinding.ivInvestIcon);
                mBinding.materialCardView.setCardBackgroundColor(Color.parseColor("#00ced1"));

                break;
            default:
                Picasso.get().load(R.drawable.deposit64).into(mBinding.ivInvestIcon);


        }
        mBinding.tvpkgname.setText(mTitle);
        mBinding.tvloanRange.setText("Ksh " + mMin + " to Ksh " + mMax);


        mBinding.btnIvest.setOnClickListener(v -> deposit(view, mBinding));

    }

    private void deposit(View view,InvestmentPlanDepositBinding binding) {
        if (mBinding.etAmount.getText().toString().isEmpty()) {
            mBinding.etAmount.setError("Please enter amount");
            mBinding.etAmount.requestFocus();
        } else if (Double.parseDouble(mBinding.etAmount.getText().toString().trim()) < mMin) {
            mBinding.etAmount.setError("Cannot be less than " + mMin);
            mBinding.etAmount.requestFocus();
        } else if (Double.parseDouble(mBinding.etAmount.getText().toString().trim()) > mMax) {
            mBinding.etAmount.setError("Cannot be more than " + mMax);
            mBinding.etAmount.requestFocus();
        }else if(Double.parseDouble(mBalance) < Double.parseDouble(mBinding.etAmount.getText().toString().trim())){
            mBinding.etAmount.setError("Insufficient account balance");
            mBinding.etAmount.requestFocus();
        }
        else {
            mBinding.progrebar.setVisibility(View.VISIBLE);
            mInvestmentPackagesViewModel.apiCallForInvestPlanDeposit(getContext(),mId, mBinding.etAmount.getText().toString().trim(),view,binding);
           // handleDeposit(mInvestmentPlanDepositBinding.etAmount.getText().toString());
           // mInvestmentPlanDepositBinding.etAmount.setText(null);
           //mInvestmentPlanDepositBinding.progrebar.setVisibility(View.GONE);

        }

    }


}