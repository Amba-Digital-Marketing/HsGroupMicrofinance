package com.microfinance.hsmicrofinance.UI.Fragments.Transactions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.databinding.FragmentBasicTransactionHistoryBinding;


public class BasicTransactionHistory extends Fragment {

    FragmentBasicTransactionHistoryBinding mFragmentBasicTransactionHistoryBinding;

  public BasicTransactionHistory(){

  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentBasicTransactionHistoryBinding = FragmentBasicTransactionHistoryBinding.inflate(inflater,container,false);

        return mFragmentBasicTransactionHistoryBinding.getRoot();
    }
}