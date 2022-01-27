package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.EDeposit;

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
import com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Credit.CreditDebit;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.HSPayments.HSPayments;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Mpesa.MobileMoney;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicEDepositBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import timber.log.Timber;


public class BasicEDeposit extends Fragment {

    private static final String TAG = "BasicEdeposit";


    FragmentBasicEDepositBinding mBinding;
    private ViewPagerAdapter mViewPagerAdapter;
    private EdepositViewModel mEdepositViewModel;
    private int mCreditCardId;
    private int mMobileMoneyId;
    private NavController mNavController;

    public BasicEDeposit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mEdepositViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mEdepositViewModel.getAllDepositGateways(getContext());
        mBinding = FragmentBasicEDepositBinding.inflate(inflater, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("E - Deposit");
        mBinding.toolbar.setNavigationOnClickListener(v->{mNavController.navigate(R.id.action_basicEDeposit_to_basicDashboard);});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mEdepositViewModel.getLiveDataofPaymentgatewaysObserver().observe(getViewLifecycleOwner(), edepostGateways -> {
            mCreditCardId = edepostGateways.get(0).id;
            mMobileMoneyId = edepostGateways.get(1).id;
            Timber.tag("Credit").d("%s", edepostGateways.toString());
            Timber.tag("CreditID").d("id------%s", mCreditCardId);
            mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            mViewPagerAdapter.addFragment(new CreditDebit(mCreditCardId), "Credit Card");
            mViewPagerAdapter.addFragment(new MobileMoney(), "Lipa Na Mpesa");
            mViewPagerAdapter.addFragment(new HSPayments(mMobileMoneyId), "H&S Payments");
            mBinding.depositViewPager.setAdapter(mViewPagerAdapter);
            mBinding.depositTablayout.setupWithViewPager(mBinding.depositViewPager);
        });


    }
}