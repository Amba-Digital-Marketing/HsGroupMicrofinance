package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Mpesa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentMobileMoneyBinding;


public class MobileMoney extends Fragment {

FragmentMobileMoneyBinding mFragmentMobileMoneyBinding;
    private NavController mNavController;

    public MobileMoney() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentMobileMoneyBinding = FragmentMobileMoneyBinding.inflate(inflater,container,false);
        return mFragmentMobileMoneyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mFragmentMobileMoneyBinding.mobilemoneyBtn.setOnClickListener(v->mNavController.navigate(R.id.action_basicEDeposit_to_mobileMoneyPayment));

    }
}