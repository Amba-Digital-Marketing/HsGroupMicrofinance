package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.PayLoansAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentMyLoansBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;


public class MyLoans extends Fragment implements PayLoansAdapter.PayLoanListener, PayLoansAdapter.LoanItemListener {
FragmentMyLoansBinding mBinding;
List<LoanHistories.LoanHistory>mHistoryList;
    private LoanHistoryViewModels mViewModels;
    private PayLoansAdapter mAdapter;
    private NavController mNavController;

    public MyLoans() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMyLoansBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("My Loans");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_myLoans_to_basicDashboard));
        return mBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mBinding.progrebar.setVisibility(View.VISIBLE);
        mViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mViewModels.makeAPIcall(requireActivity());
        mViewModels.getLoanHistoryObserver().observe(getViewLifecycleOwner(), loanHistories -> {
            mHistoryList = loanHistories;
            Collections.reverse(mHistoryList);
            try{
                List<LoanHistories.LoanHistory>myLoans = new ArrayList<>();
                mHistoryList.stream().forEach(loanHistory -> {
                    if(loanHistory.getStatus() == 1){
                        myLoans.add(loanHistory);
                    }
                });
                mAdapter = new PayLoansAdapter(getContext(),myLoans, this,this);
                mBinding.rvUserLoans.setAdapter(mAdapter);
                mBinding.progrebar.setVisibility(View.GONE);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    @Override
    public void onLoanPayClick(View view, int position) {
       try {
           LoanHistories.LoanHistory loanHistory = mAdapter.getLoanHistory(position);
           Bundle args = new Bundle();
           args.putInt("id",loanHistory.getId());
           args.putInt("userId",loanHistory.getUserId());
           args.putInt("days",loanHistory.getDays());
           args.putInt("charge", loanHistory.getCharge());
           args.putInt("loanId", loanHistory.getLoanplanId());
           args.putInt("amount", loanHistory.getAmount());
           args.putInt("status",loanHistory.getStatus());


           args.putInt("PUT_LOAN_TYPE",loanHistory.getLoanplanId());
           Timber.tag("LoanPay").d("pay========%s", loanHistory.toString());
           mNavController.navigate(R.id.action_myLoans_to_basicPayLoans,args);
       }catch (Exception e){
           e.printStackTrace();;
       }
    }

    @Override
    public void onLoanItemClick(View view, int position) {

        LoanHistories.LoanHistory loanHistory = mAdapter.getLoanHistory(position);

        Bundle args = new Bundle();
        args.putInt("id",loanHistory.getId());
        args.putInt("userId",loanHistory.getUserId());
        args.putInt("days",loanHistory.getDays());
        args.putInt("charge", loanHistory.getCharge());
        args.putInt("loanId", loanHistory.getLoanplanId());
        args.putInt("amount", loanHistory.getAmount());
        args.putInt("status",loanHistory.getStatus());

        Timber.tag("LoanPay").d("pay+++++ id==%s", loanHistory.getLoanplanId());
    }
}