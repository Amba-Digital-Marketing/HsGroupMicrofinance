package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.microfinance.hsmicrofinance.databinding.FragmentBillsRecievedBinding;


public class BillsRecieved extends Fragment {

    FragmentBillsRecievedBinding mFragmentBillsRecievedBinding;


    public BillsRecieved() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentBillsRecievedBinding = FragmentBillsRecievedBinding.inflate(inflater,container,false);
            return mFragmentBillsRecievedBinding.getRoot();
    }
}