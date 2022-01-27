package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentPayForFriendsOTPBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.PayForFriendsViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PayForFriendsOTPFragment extends Fragment {

    FragmentPayForFriendsOTPBinding binding;
    PayForFriendsViewModel viewModel;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPayForFriendsOTPBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(getActivity()).get(PayForFriendsViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.progrebar.setVisibility(View.INVISIBLE);
        binding.btnProceed.setOnClickListener( v ->{
            getUserInput();
        });
    }

    private void getUserInput(){
        binding.progrebar.setVisibility(View.VISIBLE);
        String otp = binding.etOTP.getText().toString().trim();

        if(otp != ""){
            viewModel.sendTransferOTP(getContext(),otp);
            viewModel.mconfirmotpLiveData.observe(getViewLifecycleOwner(), responsecode ->{
                if(responsecode == null){
                    binding.tvalert.setText("Error Sending OTP");
                }else{
                    if(responsecode >= 200 && responsecode <300){
                        binding.progrebar.setVisibility(View.INVISIBLE);
                        if(responsecode == 200){
                           String message = viewModel.mconfirmotpMutableLiveData.getValue();
                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("")
                                    .setContentText(message)
                                    .setCancelText("Cancel")
                                    .setConfirmText("Okey")
                                    .showCancelButton(true)
                                    .setConfirmClickListener(sDialog -> {
                                        sDialog.dismiss();
                                        navController.navigate(R.id.action_payForFriendsOTPFragment_to_statementGroupTransfer);
                                    })
                                    .show();

                        }else{
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Error")
                                    .setContentText("Cannot not Process the Transaction at This time")
                                    .setCancelText("Cancel")
                                    .setConfirmText("Okey")
                                    .showCancelButton(true)
                                    .setCancelClickListener(sDialog -> {
                                        sDialog.cancel();
                                        navController.navigate(R.id.action_payForFriendsOTPFragment_to_statementGroupTransfer);
                                    })
                                    .setConfirmClickListener(sDialog -> {
                                        sDialog.dismiss();
                                        navController.navigate(R.id.action_payForFriendsOTPFragment_to_statementGroupTransfer);
                                    })
                                    .show();

                            binding.tvalert.setText("Error Sending OTP" + responsecode);
                        }
                    }else{
                        binding.progrebar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                        binding.tvalert.setText("Error Sending OTP");
                    }

                }

            });
        }else{
            binding.tvalert.setText("Fill in OTP first");
        }
    }
}