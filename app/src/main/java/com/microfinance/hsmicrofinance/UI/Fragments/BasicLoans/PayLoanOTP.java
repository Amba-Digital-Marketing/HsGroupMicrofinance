package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentPayLoanOTPBinding;


public class PayLoanOTP extends Fragment {

   FragmentPayLoanOTPBinding mBinding;

    public PayLoanOTP() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPayLoanOTPBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }
}