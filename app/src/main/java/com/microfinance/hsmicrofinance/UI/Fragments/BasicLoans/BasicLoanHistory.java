package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.LoanHistoryAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicLoanHistoryBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BasicLoanHistory extends Fragment implements LoanHistoryAdapter.LoanItemClickListener {
    private static final String TAG = "LoanHistory";
   FragmentBasicLoanHistoryBinding mBinding;
    private LoanHistoryAdapter mLoanHistoryAdapter;
    private LoanHistories.LoanHistory mLoanHistory;
    private NavController mNavController;
    private List<LoanHistories.LoanHistory> mLoanHistoryList = new ArrayList<>();
    private LoanHistoryViewModels mLoanHistoryViewModels;

    public BasicLoanHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicLoanHistoryBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Loan History");
        mBinding.toolbar.setNavigationOnClickListener(v->{mNavController.navigate(R.id.action_basicLoanHistory_to_basicDashboard);});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mBinding.progrebar.setVisibility(View.VISIBLE);
        mLoanHistoryViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mLoanHistoryViewModels.getLoanHistoryObserver().observe(getViewLifecycleOwner(), loanHistories -> {
            mLoanHistoryList = loanHistories;
            //todo check if list is empty
            if(!mLoanHistoryList.isEmpty()){
                mBinding.page.setVisibility(View.GONE);
            }
            Collections.reverse(mLoanHistoryList);
            mLoanHistoryAdapter = new LoanHistoryAdapter(requireActivity(), mLoanHistoryList,this);
            mBinding.loanHistoryRecycler.setAdapter(mLoanHistoryAdapter);
            mBinding.progrebar.setVisibility(View.GONE);

        });
        mLoanHistoryViewModels.makeAPIcall(getContext());
       // setRecyclerView();
    }



    @Override
    public void onLoanItemClick(View view, int position) {
        mLoanHistory = new LoanHistories.LoanHistory();
        mLoanHistory = mLoanHistoryAdapter.getLoanHistory(position);
        Bundle args = new Bundle();
        args.putInt("id",mLoanHistory.getId());
        args.putInt("userId",mLoanHistory.getUserId());
        args.putInt("days",mLoanHistory.getDays());
        args.putInt("charge", mLoanHistory.getCharge());
        args.putInt("loanId", mLoanHistory.getLoanplanId());
        args.putInt("amount", mLoanHistory.getAmount());
        args.putInt("status",mLoanHistory.getStatus());


        mNavController.navigate(R.id.action_basicLoanHistory_to_loanHistorySingleItem,args);

    }
}