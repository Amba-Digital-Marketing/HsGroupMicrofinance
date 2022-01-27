package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.databinding.FragmentSingleStatementBillsBinding;


public class SingleStatementBills extends Fragment {

    FragmentSingleStatementBillsBinding mBinding;

    public SingleStatementBills() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSingleStatementBillsBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Bill Detail");
        mBinding.toolbar.setNavigationOnClickListener(v->{});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String date = getArguments().getString("date");
        mBinding.tvSender.setText(getArguments().getString("sender"));
        mBinding.tvReciever.setText(getArguments().getString("reciever"));
        mBinding.tvTitle.setText(getArguments().getString("title"));
        mBinding.tvAmount.setText(String.valueOf(getArguments().getDouble("amount")));
        mBinding.tvDescription.setText(getArguments().getString("description"));
        mBinding.tvdate.setText(date.substring(0,10));
        switch (getArguments().getInt("status")){
            case 1:
                mBinding.tvStatus.setText("Success");
                break;
            case 2:
                mBinding.tvStatus.setText("Pending");
                break;
        }


    }
}