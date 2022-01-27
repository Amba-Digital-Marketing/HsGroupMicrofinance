package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

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
import com.microfinance.hsmicrofinance.UI.adapters.BillHistoryAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentStatementBillsBinding;
import com.microfinance.hsmicrofinance.Network.models.BillStatement;
import com.microfinance.hsmicrofinance.UI.viewmodels.BillsViewModel;

import java.util.Collections;
import java.util.List;


public class StatementBills extends Fragment implements BillHistoryAdapter.BillHistoryInterface {

FragmentStatementBillsBinding mBinding;
    private List<BillStatement.BillStatementDetail> mStatementList;
    private BillsViewModel mBillsViewModel;
    private BillHistoryAdapter mBillHistoryAdapter;
    private BillStatement.BillStatementDetail mBillStatementDetail;
    private NavController mNavController;

    public StatementBills() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStatementBillsBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Bills");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_statementBills_to_basicAccount));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNavController = Navigation.findNavController(view);
        super.onViewCreated(view, savedInstanceState);
        mBillsViewModel = new ViewModelProvider(requireActivity()).get(BillsViewModel.class);
        mBillsViewModel.getObserverofBillStatement().observe(getViewLifecycleOwner(), billStatementDetailList -> {
            mStatementList = billStatementDetailList;
            Collections.reverse(mStatementList);
            if(mStatementList.isEmpty()){
                mBinding.page.setVisibility(View.VISIBLE);
            }else {
                mBinding.page.setVisibility(View.GONE);
            }
            mBillHistoryAdapter = new BillHistoryAdapter(requireActivity(),mStatementList,this);
            mBinding.billRecyclerview.setAdapter(mBillHistoryAdapter);
        });
        mBillsViewModel.apiForAllBillHistory(getContext());
    }


    @Override
    public void onclickHistory(View view, int position) {
        mBillStatementDetail = mBillHistoryAdapter.getBillStatementDetail(position);
        Bundle args = new Bundle();
        args.putString("sender",mBillStatementDetail.getSender().getName());
        args.putString("reciever", mBillStatementDetail.getReceiver().getName());
        args.putString("title",mBillStatementDetail.getTitle());
        args.putDouble("amount",mBillStatementDetail.getAmount());
        args.putString("description",mBillStatementDetail.getDescription());
        args.putInt("status",mBillStatementDetail.getStatus());
        args.putString("date",mBillStatementDetail.getCreatedAt());

        mNavController.navigate(R.id.action_statementBills_to_singleStatementBills,args);
    }
}