package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.microfinance.hsmicrofinance.UI.adapters.BillHistoryAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBillHistoryBinding;
import com.microfinance.hsmicrofinance.Network.models.BillStatement;
import com.microfinance.hsmicrofinance.UI.viewmodels.BillsViewModel;

import java.util.List;


public class BillHistory extends Fragment implements BillHistoryAdapter.BillHistoryInterface {

 private List<BillStatement.BillStatementDetail>mBillStatementList ;
 FragmentBillHistoryBinding mFragmentBillHistoryBinding ;
    private BillsViewModel mBillsViewModel;
    private BillHistoryAdapter mBillHistoryAdapter;

    public BillHistory() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentBillHistoryBinding = FragmentBillHistoryBinding.inflate(inflater,container,false);
//        BillStatement billStatement = new BillStatement();
//        mBillStatementList = new ArrayList<>();
//        mBillStatementList.add(billStatement);
//        if(mBillStatementList.size()>0){
//            return inflater.inflate(R.layout.fragment_bill_history, container, false);
//        }else
            return mFragmentBillHistoryBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBillsViewModel = new ViewModelProvider(requireActivity()).get(BillsViewModel.class);
        mBillsViewModel.getObserverofBillStatement().observe(getViewLifecycleOwner(), billStatementDetailList -> {
            mBillStatementList = billStatementDetailList;
            mBillHistoryAdapter = new BillHistoryAdapter(requireActivity(),mBillStatementList,this);
            mFragmentBillHistoryBinding.billhistoryrecyler.setAdapter(mBillHistoryAdapter);

        });
        mBillsViewModel.apiForAllBillHistory(getContext());
    }

    @Override
    public void onclickHistory(View view, int position) {

    }
}