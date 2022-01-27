package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Credit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentCreditDebitPaymentBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import timber.log.Timber;


public class CreditDebitPayment extends Fragment {

    FragmentCreditDebitPaymentBinding mBinding;
    private NavController mNavController;
    private EdepositViewModel mViewModel;
    private int mId;

    public CreditDebitPayment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentCreditDebitPaymentBinding.inflate(inflater,container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mId = getArguments().getInt("id");
        Timber.tag("CreditID").d("id------%s", mId);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mBinding.submitBtn.setOnClickListener(v->doPayment());
    }

    private void doPayment() {
        if(mBinding.etAmount.getText().toString().isEmpty()){
            mBinding.etAmount.setError("Please provide amount");
            mBinding.etAmount.requestFocus();

        }else if(Integer.parseInt(mBinding.etAmount.getText().toString().trim()) < 5){
            mBinding.etAmount.setError("Cannot be less than ksh 500");
            mBinding.etAmount.requestFocus();
        }else if(Integer.parseInt(mBinding.etAmount.getText().toString().trim())>1000){
            mBinding.etAmount.setError("Cannot be more than ksh 100000");
            mBinding.etAmount.requestFocus();
        }else {
            //TODO remove int id
           // int id =2;
            mViewModel.callForCreditDebitDeposit(requireActivity(), mBinding.etAmount.getText().toString().trim(),mId);
            mBinding.etAmount.setText(null);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> mNavController.navigate(R.id.action_creditDebitPayment_to_creditDebitPaymentSubmit),2000);
        }


    }
}