package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentPayfriendsViaBankBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.PayForFriendsViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class PayfriendsViaBank extends Fragment {
    String TAG = "FragPayFriends";
    PayForFriendsViewModel viewModel;
    NavController navController;
 FragmentPayfriendsViaBankBinding mFragmentPayfriendsViaBankBinding;

    public PayfriendsViaBank() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentPayfriendsViaBankBinding = FragmentPayfriendsViaBankBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(getActivity()).get(PayForFriendsViewModel.class);
        return mFragmentPayfriendsViaBankBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
        mFragmentPayfriendsViaBankBinding.payfriendSubmitBtn.setOnClickListener(v->{
                mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.VISIBLE);
                getUserInput();
        });
        navController = Navigation.findNavController(view);
    }




    private void getUserInput() {
        String account;double amount;

        if(!mFragmentPayfriendsViaBankBinding.etammount.getText().toString().trim().equals("")  && !mFragmentPayfriendsViaBankBinding.etaccount.getText().toString().trim().equals("") ){
            try{
                amount =  Double.parseDouble(mFragmentPayfriendsViaBankBinding.etammount.getText().toString());
                account =   mFragmentPayfriendsViaBankBinding.etaccount.getText().toString().trim();
                internalTransfer(account,amount);

                 viewModel.mLiveDataCode.observe(getViewLifecycleOwner(), code ->{
                     if(code == null || code == 200 ) {
                         mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.GONE);
                     }
                   });

                viewModel.mutableLiveDataTransferOTP.observe(getViewLifecycleOwner(), otpResponse ->{
                    if(otpResponse != null){
                        mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
                        if (otpResponse < 200 || otpResponse >= 300) {
                            mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
                        } else {
                            if (otpResponse == 200) {
                                mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);

                                viewModel.mutableLiveDataTransferOTP.postValue(null);
                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Success \n")
                                        .setContentText("OTP has been sent to your Email\n Press Ok to proceed")
                                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                        .setConfirmClickListener(sweetAlertDialog -> {
                                            sweetAlertDialog.dismiss();
                                            navController.navigate(R.id.action_basicPayfriends_to_payForFriendsOTPFragment);

                                        })
                                        .show();
                            } else {
                                mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
                                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Failed To Transfer")
                                        .setContentText("Sorry! Could not proccess the payments at this time!")
                                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                                        .setConfirmClickListener(sweetAlertDialog ->{
                                            sweetAlertDialog.dismiss();
                                        })
                                        .show();
                            }
                        }
                    }else{
                        mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
                    }
                } );


            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            mFragmentPayfriendsViaBankBinding.progrebar.setVisibility(View.INVISIBLE);
            mFragmentPayfriendsViaBankBinding.tvalert.setText("Fill in all values");
        }

    }

    private void internalTransfer(String account, double amount){
        viewModel.transferToHSAccountBanks(getContext(),account,amount);
    }

}