package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit;

import android.graphics.Color;
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

import com.microfinance.hsmicrofinance.Network.entity.Status;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentDepositHistorySingleItemBinding;


public class DepositHistorySingleItem extends Fragment {
    FragmentDepositHistorySingleItemBinding mBinding;
    private NavController mNavController;

    public DepositHistorySingleItem() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentDepositHistorySingleItemBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("History Detail");
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.navigate(R.id.action_depositHistorySingleItem_to_basicDepositHistory));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
//        args.putDouble("amount", depositshistory.getAmount());
//        args.putString("trxid", depositshistory.getTrx());
//        args.putInt("status", depositshistory.getStatus());
//        args.putString("date", depositshistory.getCreatedAt());
//        args.putDouble("charge",depositshistory.getCharge());
//        args.putDouble("rate",depositshistory.getGetway().getRate());

        try {
            int status = getArguments().getInt("status");
            mBinding.trdID.setText(getArguments().getString("trxid"));
            mBinding.tvFee.setText(String.valueOf(getArguments().getDouble("charge")));
            mBinding.tvAmount.setText(String.valueOf(getArguments().getDouble("amount")));
            mBinding.tvstatus.setText(checkStatus(status).getStatus());
            mBinding.tvstatus.setTextColor(Color.parseColor(checkStatus(status).getColor()));
            mBinding.tvRate.setText(String.valueOf(getArguments().getDouble("rate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Status checkStatus(int StatusCode) {
        Status status;
        switch (StatusCode) {
            case 0:
                status = new Status("Inactive", "#E0223C");
                break;
            case 1:
                status = new Status("Success", "#297545");
                break;
            case 2:
                status = new Status("Pending", "#0dcaf0");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StatusCode);
        }
        return status;
    }

}