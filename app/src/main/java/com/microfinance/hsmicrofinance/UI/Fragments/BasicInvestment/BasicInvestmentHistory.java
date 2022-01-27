package com.microfinance.hsmicrofinance.UI.Fragments.BasicInvestment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.InvestmentHistoryAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicInvestmentHistoryBinding;
import com.microfinance.hsmicrofinance.Network.models.InvestmentHistory;
import com.microfinance.hsmicrofinance.UI.viewmodels.InvestmentHistoryViewModel;

import java.util.Collections;
import java.util.List;


public class BasicInvestmentHistory extends Fragment implements InvestmentHistoryAdapter.InvestmentHistoryInterface {
    private static final String TAG = "BasicInvestmentHistory";
    FragmentBasicInvestmentHistoryBinding mBinding;
    private RecyclerView mRecyclerView;
    private InvestmentHistoryAdapter mAdapter;
    private NavController mNavController;
    private InvestmentHistoryViewModel mInvestmentHistoryViewModel;
    private ProgressBar mProgressBar;
    private List<InvestmentHistory.InvestementHistoryDetail> mInvestmentHistoryList;

    public BasicInvestmentHistory() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicInvestmentHistoryBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Investment History");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicInvestmentHistory_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = mBinding.investmenthistoryRecycler;

        mNavController = Navigation.findNavController(view);

        mInvestmentHistoryViewModel = new ViewModelProvider(requireActivity()).get(InvestmentHistoryViewModel.class);
       // mProgressBar = new ProgressBar(getContext());
        mBinding.progressbar.setVisibility(View.VISIBLE);
       // mProgressBar.setVisibility(View.VISIBLE);
        mInvestmentHistoryViewModel.getInvestmentHistoryMutableLiveDataObserver().observe(getViewLifecycleOwner(), investementHistoryDetails -> {
            mInvestmentHistoryList = investementHistoryDetails;
            //todo check if list is empty
            if(!mInvestmentHistoryList.isEmpty()){
                mBinding.page.setVisibility(View.GONE);
            }

            Collections.reverse(mInvestmentHistoryList);
            mAdapter = new InvestmentHistoryAdapter(getContext(), mInvestmentHistoryList,this);
            mRecyclerView.setAdapter(mAdapter);
            mBinding.progressbar.setVisibility(View.GONE);
        });
        mInvestmentHistoryViewModel.makeApiCall(getContext());

    }


    @Override
    public void onClickInvestment(View v, int position) {
        InvestmentHistory.InvestementHistoryDetail detail = mAdapter.getInvestmentHistory(position);
        Bundle args = new Bundle();
        args.putInt("id",detail.getId());
        args.putInt("userid",detail.getUserId());
        args.putInt("planId",detail.getFdrplanId());
        args.putInt("amount",detail.getAmount());
        args.putInt("percent",detail.getReturnPercent());
        args.putDouble("total",detail.getReturnTotal());
        args.putInt("status",detail.getStatus());
        args.putString("sdate", detail.getCreatedAt());
        args.putString("rdate",detail.getReturnDate());
        mNavController.navigate(R.id.action_basicInvestmentHistory_to_singleInvestmentHistory,args);

    }
}