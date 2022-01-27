package com.microfinance.hsmicrofinance.UI.Fragments.BasicWithdraw;

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

import com.microfinance.hsmicrofinance.Network.models.DetailsForWithdraw;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.WithdrawViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentConfirmTransferBinding;


public class ConfirmTransfer extends Fragment {

    FragmentConfirmTransferBinding mBinding;
    private NavController mNavController;
    private WithdrawViewModel mViewModel;
    private DetailsForWithdraw.Details mDetails;
    private HomeActivityViewModel mHomeActivityViewModel;
    private String acc;
    private int notified=-1;
    private int mMyamount;
    private double mAmount;

    public ConfirmTransfer() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        updateUserOnBalance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentConfirmTransferBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Confirm Transfer");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_confirmTransfer_to_basicWithdrawMoney));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(WithdrawViewModel.class);
        mViewModel.getDetailsMutableLiveDataObserver().observe(getViewLifecycleOwner(), details -> {
            mDetails= details;
           // mFragmentConfirmTransferBinding.tvExCharge.setText(String.valueOf(mDetails.amountUsd));
            mBinding.tvTotalCharge.setText("Ksh "+mDetails.fee);
            mBinding.tvAmount.setText("Ksh "+mDetails.amountWithdraw);
            mBinding.tvFinalAmount.setText("Ksh "+mDetails.amountWithdraw);
            mAmount = mDetails.amountWithdraw+mDetails.fee;
            mMyamount = (int) mAmount;


        });
        mBinding.submit.setOnClickListener(v->{
            try {
                mBinding.progrebar.setVisibility(View.VISIBLE);
                mViewModel.getOTPforWithdraw(requireActivity(),mMyamount,acc,view);

            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }
    private void updateUserOnBalance() {
       // if(mHomeActivityViewModel.notifiedMLD.getValue() != 0){
            mHomeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
                if(dashboardData != null) {
                    this.acc = dashboardData.getAccNo();

                }
            });
     //   }

    }
}