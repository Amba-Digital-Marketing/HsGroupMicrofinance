package com.microfinance.hsmicrofinance.UI.Fragments.AccountSettings;

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
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentUpdateSecurityCodeBinding;

public class UpdateSecurityCode extends Fragment {

    FragmentUpdateSecurityCodeBinding mBinding;
    private HomeActivityViewModel mViewModel;
    private int mOtp;
    private String mNewPass;
    private NavController mNavController;

    public UpdateSecurityCode() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentUpdateSecurityCodeBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Update Code");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_updateSecurityCode_to_passwordChange));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
       // Timber.tag("New Pass").d("pass"+ getArguments().getString("newpass"));
        mNavController = Navigation.findNavController(view);
        mViewModel.getChangeMutableLiveData().observe(getViewLifecycleOwner(), dataSent -> {
            try{
                mOtp = dataSent.getVerificationCode();
                mNewPass = dataSent.getNewPassword();
            }catch (Exception e){
                e.printStackTrace();
            }

        });
        mBinding.submitSecurityCodePagebutton.setOnClickListener(v -> submitOtp());

        mBinding.resendSecurityCode.setOnClickListener(v -> mViewModel.resendOtpForaccount(requireActivity()));
    }

    private void submitOtp() {
        mBinding.progrebar.setVisibility(View.VISIBLE);

        if(mBinding.etOTP.getText().toString().isEmpty()){
            mBinding.etOTP.setError("Enter OTP");
            mBinding.etOTP.requestFocus();
        }else if(Integer.parseInt(mBinding.etOTP.getText().toString().trim()) == mOtp) {


            mViewModel.ConfirmAccountChangePassword(getContext(),mOtp,mNewPass);
        }else if(Integer.parseInt(mBinding.etOTP.getText().toString().trim()) != mOtp) {
            mBinding.etOTP.setError("Otp doesn't match");
            mBinding.etOTP.requestFocus();
        }
    }
}