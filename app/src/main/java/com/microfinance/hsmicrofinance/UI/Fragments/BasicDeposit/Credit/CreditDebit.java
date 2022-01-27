package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Credit;

import android.content.Intent;
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


import com.microfinance.hsmicrofinance.UI.billing.WebviewActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentCreditDebitBinding;


public class CreditDebit extends Fragment {
    FragmentCreditDebitBinding mBinding;
    private NavController mNavController;

    int id;

    public CreditDebit(int id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentCreditDebitBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        // Timber.tag("ID").d("id------%s",id);
        Bundle args = new Bundle();
        args.putInt("id", id);
        mBinding.creditDepitBtn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), WebviewActivity.class));
        }/*mNavController.navigate(R.id.action_basicEDeposit_to_creditDebitPayment, args)*/);
    }
}