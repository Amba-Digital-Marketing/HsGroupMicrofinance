package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.microfinance.hsmicrofinance.UI.adapters.MpesaDepositAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentMpesaDepositHistoryBinding;
import com.microfinance.hsmicrofinance.Network.models.MpesaDepositCallBack;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import java.util.List;

import timber.log.Timber;


public class MpesaDepositHistory extends Fragment {

 FragmentMpesaDepositHistoryBinding mBinding;
    private EdepositViewModel mViewModel;
    List<MpesaDepositCallBack.MpesaPaymentDetail>mList;
    private MpesaDepositAdapter mAdapter;

    public MpesaDepositHistory() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMpesaDepositHistoryBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mViewModel.getMpesadeposithistory(requireActivity());
        mViewModel.getListOfMpesaDetailsObserver().observe(getViewLifecycleOwner(), list -> {
            try{
                mList=list;
                if(!mList.isEmpty()){
                    mBinding.page.setVisibility(View.GONE);
                }
                //todo check if list is empty

                mAdapter = new MpesaDepositAdapter(getContext(),mList);
                mBinding.rvmpesa.setAdapter(mAdapter);
                Timber.tag("det").d("list~~~~~~"+mList.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}