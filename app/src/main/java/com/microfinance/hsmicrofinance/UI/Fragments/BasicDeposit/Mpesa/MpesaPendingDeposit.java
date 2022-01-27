package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Mpesa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.microfinance.hsmicrofinance.UI.adapters.MpesaPendingAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentMpesaPendingDepositBinding;
import com.microfinance.hsmicrofinance.Network.models.MpesaStatus;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import java.util.List;


public class MpesaPendingDeposit extends Fragment {
FragmentMpesaPendingDepositBinding mBinding;
    private EdepositViewModel mViewModel;
    private List<MpesaStatus.MpesaDetail>mList;


    public MpesaPendingDeposit() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= FragmentMpesaPendingDepositBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mViewModel.getMpesaStatus(getContext());
        mViewModel.getListofMpesaStatus().observe(getViewLifecycleOwner(), mpesaDetails -> {
            try {
                mList = mpesaDetails;
                if(!mList.isEmpty()){
                    mBinding.page.setVisibility(View.GONE);
                }
                //todo check if list is empty

                MpesaPendingAdapter mpesaPendingAdapter = new MpesaPendingAdapter(getContext(),mList);
                mBinding.rvStatusItem.setAdapter(mpesaPendingAdapter);

            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
}