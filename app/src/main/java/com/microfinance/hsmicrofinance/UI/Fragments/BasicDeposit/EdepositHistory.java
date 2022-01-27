package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.DepositHistoryAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentEdepositHistoryBinding;
import com.microfinance.hsmicrofinance.Network.models.DepositHistory;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;

import java.util.Collections;
import java.util.List;


public class EdepositHistory extends Fragment implements DepositHistoryAdapter.ItemClickListener {

    private NavController mNavController;
    private LoanHistoryViewModels mViewModels;
    private List<DepositHistory.Deposit> mDepositList;
    private DepositHistoryAdapter mAdapter;
FragmentEdepositHistoryBinding mBinding;

    public EdepositHistory() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentEdepositHistoryBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController= Navigation.findNavController(view);
        mBinding.progrebar.setVisibility(View.VISIBLE);
        mViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mViewModels.fetchAllDepositHistory(getContext());
        mViewModels.getMutableLiveDataofDepositHistory().observe(getViewLifecycleOwner(), deposits -> {
            mDepositList = deposits;
            if(!mDepositList.isEmpty()){
                mBinding.page.setVisibility(View.GONE);
            }
            //todo check if list is empty

            Collections.reverse(mDepositList);
            mAdapter = new DepositHistoryAdapter(requireActivity(),mDepositList,this);
            mBinding.rvdeposithistory.setAdapter(mAdapter);
            mBinding.progrebar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        DepositHistory.Deposit depositshistory = mAdapter.getItem(position);


        Bundle args = new Bundle();
        args.putDouble("amount", depositshistory.getAmount());
        args.putString("trxid", depositshistory.getTrx());
        args.putInt("status", depositshistory.getStatus());
        args.putString("date", depositshistory.getCreatedAt());
        args.putDouble("charge",depositshistory.getCharge());
        args.putDouble("rate",depositshistory.getGetway().getRate());

        mNavController.navigate(R.id.action_basicDepositHistory_to_depositHistorySingleItem, args);
    }
}