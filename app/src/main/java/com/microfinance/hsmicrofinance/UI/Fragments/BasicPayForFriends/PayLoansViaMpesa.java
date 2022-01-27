package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.microfinance.hsmicrofinance.databinding.FragmentPayLoansViaMpesaBinding;


public class PayLoansViaMpesa extends Fragment {
    private static final String TAG = "PayFriendsMpesa";

    private static final int SELECT_PHONE_NUMBER = 1;


    FragmentPayLoansViaMpesaBinding mFragmentPayLoansViaMpesaBinding;
    int loan;
    public PayLoansViaMpesa(int loan) {
        this.loan = loan;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentPayLoansViaMpesaBinding = FragmentPayLoansViaMpesaBinding.inflate(inflater,container,false);
        return mFragmentPayLoansViaMpesaBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try{
            switch (loan){
                case 1:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Logbook Loan");

                    break;
                case 2:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Business Loan");
                    break;
                case 3:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Kilimo Loans");
                    break;
                case 4:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Title Deed Loans");
                    break;
                case 5:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Personal Loans");
                    break;
                case 6:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Chama Loans");
                    break;
                case 7:
                    mFragmentPayLoansViaMpesaBinding.tvuserloantype.setText("Bima Loans");
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}