package com.microfinance.hsmicrofinance.UI.Fragments.BasicInvestment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentSingleInvestmentHistoryBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.InvestmentHistoryViewModel;


public class SingleInvestmentHistory extends Fragment {


    FragmentSingleInvestmentHistoryBinding mBinding;
    private InvestmentHistoryViewModel mInvestmentHistoryViewModel;
    private NavController mNavController;

    public SingleInvestmentHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSingleInvestmentHistoryBinding.inflate(inflater, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Invest Plan Detail");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_singleInvestmentHistory_to_basicInvestmentHistory));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        switch (getArguments().getInt("planId")) {
            case 1:
                mBinding.tvpkgname.setText("Standard");
                break;
            case 2:
                mBinding.tvpkgname.setText("Gold");
                break;
            case 3:
                mBinding.tvpkgname.setText("Platinum");
                break;

        }
        switch (getArguments().getInt("status")) {
            case 1:
                mBinding.tvStatus.setText("Success");
                break;
            case 2:
                mBinding.tvStatus.setText("Waiting");
                break;

        }
        String date = getArguments().getString("sdate");

        mBinding.tvAmount.setText(String.valueOf(getArguments().getInt("amount")));
        mBinding.tvpercent.setText(String.valueOf(getArguments().getInt("percent")));
        mBinding.tvRdate.setText(getArguments().getString("rdate"));
        mBinding.tvStartdate.setText(date.substring(0, 10));
        mBinding.tvtotal.setText(String.valueOf(getArguments().getDouble("total")));
    }
}