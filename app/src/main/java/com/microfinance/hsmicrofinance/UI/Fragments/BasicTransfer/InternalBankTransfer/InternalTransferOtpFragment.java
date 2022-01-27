package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.InternalBankTransfer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentInternalTransferOtpBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.InternalTransferViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class InternalTransferOtpFragment extends Fragment {
    InternalTransferViewModel viewModel;
    NavController navController;
    FragmentInternalTransferOtpBinding transferOtpBinding;

    public InternalTransferOtpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        transferOtpBinding = FragmentInternalTransferOtpBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(InternalTransferViewModel.class);
        ((AppCompatActivity) getActivity()).setSupportActionBar(transferOtpBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        transferOtpBinding.toolbar.setTitle("OTP Confirmation");
        transferOtpBinding.toolbar.setNavigationOnClickListener(v -> {
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Confirmation")
                    .setContentText("Are you sure you want to cancel the transaction?")
                    .setConfirmText("Okay")
                    .setConfirmClickListener(sDialog -> {
                        sDialog.dismiss();
                        navController.navigate(R.id.action_internalTransferOtpFragment_to_basicDashboard);

                    })
                    .setCancelText("No")
                    .setCancelClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();
                    })
                    .show();
        });
        return transferOtpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        transferOtpBinding.progrebar.setVisibility(View.INVISIBLE);
        transferOtpBinding.btnProceed.setOnClickListener(v -> {
            getUserInput();
        });
    }


    private void getUserInput() {
        String otp = transferOtpBinding.etOTP.getText().toString().trim();
        if (otp.isEmpty()) {
            transferOtpBinding.etOTP.setError("Enter OTP Code");
            transferOtpBinding.etOTP.requestFocus();
            return;
        }
        transferOtpBinding.progrebar.setVisibility(View.VISIBLE);
        viewModel.sendTransferOTP(getContext(), otp);

        viewModel.mconfirmotpLiveData.observe(getViewLifecycleOwner(), responsecode -> {
            if (responsecode == null) {
                transferOtpBinding.progrebar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                transferOtpBinding.tvalert.setText("Error Sending OTP");
            } else {
                transferOtpBinding.progrebar.setVisibility(View.INVISIBLE);
                if (responsecode >= 200 && responsecode < 300) {

                    if (responsecode == 200) {
                        viewModel.mconfirmotpMutableLiveData.observe(getViewLifecycleOwner(), res -> {
                            if (res == null) {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                                transferOtpBinding.tvalert.setText("Error Sending OTP");
                            } else {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Success")
                                        .setContentText(viewModel.mconfirmotpMutableLiveData.getValue().transferDetails.info)
                                        .setConfirmText("Okay")
                                        .setConfirmClickListener(sDialog -> {
                                            sDialog.dismiss();
                                            navController.navigate(R.id.action_internalTransferOtpFragment_to_statementGroupTransfer);
                                        })
                                        .show();
                            }
                        });


                    } else {
                        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                                .setTitleText("Failed")
                                .setContentText("Transaction Could not be proccessed")
                                .setConfirmText("Okay")
                                .setConfirmClickListener(sDialog -> {
                                    sDialog.dismiss();
                                    navController.navigate(R.id.action_internalTransferOtpFragment_to_statementGroupTransfer);
                                })
                                .show();
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                        transferOtpBinding.tvalert.setText("Error Sending OTP" + responsecode);
                    }
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    transferOtpBinding.tvalert.setText("Error Sending OTP");
                }

            }

        });

    }
}