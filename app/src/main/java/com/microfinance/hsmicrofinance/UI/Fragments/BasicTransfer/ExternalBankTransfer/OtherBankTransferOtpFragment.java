package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.ExternalBankTransfer;

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
import com.microfinance.hsmicrofinance.databinding.FragmentOtherBankTransferOtpBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.OtherBankTransferViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class OtherBankTransferOtpFragment extends Fragment {
        FragmentOtherBankTransferOtpBinding binding;

    OtherBankTransferViewModel viewModel;
    NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(OtherBankTransferViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtherBankTransferOtpBinding.inflate(inflater,container,false);
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
        String otp = binding.etOTP.getText().toString().trim();

        if(!otp.equals("")){
            viewModel.sendTransferOTPToServer(getContext(),otp,viewModel.mOtherBankTransferMutableLiveData.getValue());
            viewModel.mconfirmotpLiveDataCode.observe(getViewLifecycleOwner(), responsecode ->{
                if(responsecode == null){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    binding.tvalert.setText("Error Sending OTP");
                }else{
                    if(responsecode >= 200 && responsecode <300){

                        if(responsecode == 200){
                            viewModel.mconfirmotpMutableLiveData.observe(getViewLifecycleOwner(), data -> {

                                if (data!=null){
                                 SweetAlertDialog dialog=   new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                                           dialog .setTitleText("Success!!")
                                            .setContentText(data.transactionsTable.info)

                                            .setConfirmClickListener(on->{
                                                navController.navigate(R.id.action_otherBankTransferOtpFragment_to_statementBankTransfer);
                                                on.dismiss();
                                            })

                                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                                           dialog .show();
                                }else{
                                   SweetAlertDialog dialog= new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                                           dialog .setTitleText("Request Failed")
                                            .setContentText("Please try again later")
                                            .setNeutralButtonTextColor(Color.parseColor("#297545")).setCancelable(false);
                                            dialog.show();
                                }
                            });


                        }else{
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                            binding.tvalert.setText("Error Sending OTP" + responsecode);
                        }
                    }else{
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