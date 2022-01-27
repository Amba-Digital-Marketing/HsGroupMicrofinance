package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Credit;

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


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentCreditDebitPaymentSubmitBinding;
import com.microfinance.hsmicrofinance.Network.models.CreditTransferDeposit;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import timber.log.Timber;


public class CreditDebitPaymentSubmit extends Fragment {
    public static final String CODE="pk_live_51JG3k5FtucG3Zywq82OxOkvZRKZ28WMFvTpapyDDw5v1br9Yi6N0SFoo5NCHsucRLrJao4qHj1Vw3fIWmqyUlXdr00GPW4TTQc";

   FragmentCreditDebitPaymentSubmitBinding mBinding;
    private EdepositViewModel mViewModel;
    private CreditTransferDeposit.PaymentDetails mDetails;
    private NavController mNavController;


    public CreditDebitPaymentSubmit() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentCreditDebitPaymentSubmitBinding.inflate(inflater,container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mViewModel.getPaymentDetailsObserver().observe(getViewLifecycleOwner(), paymentDetails -> {
            mDetails = paymentDetails;
            Timber.tag("det").d(mDetails.toString());
            try{

                mBinding.tvAmount.setText(String.valueOf(mDetails.getAmount()));
                mBinding.tvCharge.setText(String.valueOf(mDetails.getCharge()));
                mBinding.tvTotal.setText(String.valueOf(mDetails.getPayable()));
                mBinding.tvUsdAmount.setText(String.valueOf(mDetails.getUsdAmount()));
                mBinding.tvType.setText(mDetails.getType());


            }catch (Exception e){
                e.printStackTrace();
            }
        });

        mBinding.submit.setOnClickListener(v->submitDeposit());
    }

    private void submitDeposit() {
        double amount =Double.parseDouble(mBinding.tvUsdAmount.getText().toString().trim());
        try {
            mViewModel.callForCreditDebitSubmission(requireActivity(),
                    amount,
                    CODE,
                    1,
                    2);
            mNavController.navigate(R.id.action_creditDebitPaymentSubmit_to_basicDashboard);

            Timber.tag("paydata").d("data+++"+ CODE+" ");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}