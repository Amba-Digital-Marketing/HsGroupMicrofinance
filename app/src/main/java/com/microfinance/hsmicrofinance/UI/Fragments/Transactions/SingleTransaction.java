package com.microfinance.hsmicrofinance.UI.Fragments.Transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentSingleTransactionBinding;


public class SingleTransaction extends Fragment {

    FragmentSingleTransactionBinding mBinding;
    private NavController mNavController;


    public SingleTransaction() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSingleTransactionBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Transaction Detail");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_singleTransaction_to_basicLatestTransactions2));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        String date = getArguments().getString("date");
        mBinding.tvTrxId.setText(getArguments().getString("trxId"));
        mBinding.tvAmount.setText(String.valueOf(getArguments().getDouble("amount")));
        mBinding.tvBalance.setText(String.valueOf(getArguments().getDouble("balance")));
        mBinding.tvFee.setText(String.valueOf(getArguments().getDouble("fee")));
        mBinding.tvstatus.setText(String.valueOf(getArguments().getInt("status")));
        mBinding.tvType.setText(getArguments().getString("type"));
        mBinding.tvInfo.setText(getArguments().getString("info"));
        mBinding.tvdate.setText(date.substring(0,10));

    }
}