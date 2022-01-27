package com.microfinance.hsmicrofinance.UI.Fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentAccountStatementsBinding;


public class AccountStatements extends Fragment {

    FragmentAccountStatementsBinding mFragmentAccountStatementsBinding;
    private NavController mNavController;

    public AccountStatements() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentAccountStatementsBinding = FragmentAccountStatementsBinding.inflate(inflater,container,false);
        return mFragmentAccountStatementsBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mFragmentAccountStatementsBinding.edeposit.setOnClickListener(v->mNavController.navigate(R.id.action_basicAccount_to_statementEdeposit));
        mFragmentAccountStatementsBinding.banktransfer.setOnClickListener(v->mNavController.navigate(R.id.action_basicAccount_to_statementBankTransfer));
        mFragmentAccountStatementsBinding.grouptransfer.setOnClickListener(v->mNavController.navigate(R.id.action_basicAccount_to_statementGroupTransfer));
        mFragmentAccountStatementsBinding.mybills.setOnClickListener(v->mNavController.navigate(R.id.action_basicAccount_to_statementBills));
    }
}