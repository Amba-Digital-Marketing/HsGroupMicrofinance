package com.microfinance.hsmicrofinance.UI.Fragments.BasicWithdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentTranferOTPconfirmBinding;
import com.microfinance.hsmicrofinance.Network.models.WithrawOTPRequest;
import com.microfinance.hsmicrofinance.UI.viewmodels.WithdrawViewModel;

import timber.log.Timber;


public class TranferOTPconfirm extends Fragment {

    FragmentTranferOTPconfirmBinding mBinding;
    private WithdrawViewModel mViewModel;
    private WithrawOTPRequest.Details mDetails;
    private String mCode;
    private NavController mNavController;

    public TranferOTPconfirm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentTranferOTPconfirmBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Confirm OTP");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_tranferOTPconfirm_to_confirmTransfer));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(WithdrawViewModel.class);
        mViewModel.getOTPObserver().observe(getViewLifecycleOwner(), details -> {
            mDetails = details;
            //this.mCode = mDetails.getVerificationCode().toString().trim();
            this.mCode=mDetails.verificationCode.trim();
            Timber.tag("OTP1").d("otp1%s " +" "+mCode);

        });
        mBinding.submit.setOnClickListener(view1 -> validate(view));
    }

    private void validate(View view) {
        if (mBinding.etOtp.getText().toString().trim().isEmpty()){
            mBinding.etOtp.setError("OTP required");
            mBinding.etOtp.requestFocus();

        }else if(!mBinding.etOtp.getText().toString().trim().equals(mCode)){
            mBinding.etOtp.setError("Wrong OTP");
            mBinding.etOtp.requestFocus();
        }else if(mBinding.etOtp.getText().toString().trim().equals(mCode)){
            Timber.tag("OTP2").d("otp2%s "+" "+" "+mCode);
            mBinding.progrebar.setVisibility(View.VISIBLE);
        mViewModel.confrimOTp(requireActivity(), mBinding.etOtp.getText().toString().trim(),view);
       }
    }
}