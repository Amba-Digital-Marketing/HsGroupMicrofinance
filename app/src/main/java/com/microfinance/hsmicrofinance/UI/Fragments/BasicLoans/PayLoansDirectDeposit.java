package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.Network.data.LoansDescription;
import com.microfinance.hsmicrofinance.UI.viewmodels.PayLoansViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPayLoansDirectDepositBinding;


public class PayLoansDirectDeposit extends Fragment {
    FragmentPayLoansDirectDepositBinding mFragmentPayLoansDirectDepositBinding;
LoansDescription loan;
String loanStatus ="";
PayLoansViewModel viewModel;
    public PayLoansDirectDeposit(LoansDescription loanDesc) {
        this.loan = loanDesc;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(PayLoansViewModel.class);
        mFragmentPayLoansDirectDepositBinding = FragmentPayLoansDirectDepositBinding.inflate(inflater,container,false);
        return mFragmentPayLoansDirectDepositBinding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        double total = loan.getAmount() + loan.getCharge();


        mFragmentPayLoansDirectDepositBinding.days.setText(String.valueOf(loan.getDays()));
        mFragmentPayLoansDirectDepositBinding.chargeText.setText(String.valueOf(loan.getCharge()));
        mFragmentPayLoansDirectDepositBinding.total.setText(String.valueOf(total));
        mFragmentPayLoansDirectDepositBinding.amount.setText(String.valueOf(loan.getAmount()));
        switch (loan.getStatus()) {
            case 0:
                loanStatus ="Rejected";

                break;
            case 1:
                loanStatus ="Success";

                break;
            case 2:
                loanStatus ="Pending";

                break;
        }
        mFragmentPayLoansDirectDepositBinding.status.setText("loanStatus");

        if(!loanStatus.equals("Success")){
            mFragmentPayLoansDirectDepositBinding.btnpayloan.setVisibility(View.INVISIBLE);
        }

        mFragmentPayLoansDirectDepositBinding.btnpayloan.setOnClickListener(v ->payLoan(loan.getLoanId()));
    }
    private void payLoan(int loanID) {
       /* String amountInput = mFragmentPayLoansDirectDepositBinding.payloanAmount.getText().toString().trim();
        String account = mFragmentPayLoansDirectDepositBinding.payloanAccountNo.getText().toString().trim();

*/
         try {

             viewModel.payLoans(getContext(),loanID);

         }catch (Exception e){
             e.printStackTrace();

             Toast.makeText(getContext(), "Check your internet Connections", Toast.LENGTH_LONG).show();
         }


    }

}