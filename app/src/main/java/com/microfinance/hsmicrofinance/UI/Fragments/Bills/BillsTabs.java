package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBillsTabsBinding;


public class BillsTabs extends Fragment {
FragmentBillsTabsBinding mFragmentBillsTabsBinding;
    private NavController mNavController;
    private ViewPagerAdapter mViewPagerAdapter;

    public BillsTabs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentBillsTabsBinding = FragmentBillsTabsBinding.inflate(inflater,container,false);
        return mFragmentBillsTabsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.addFragment(new CreateBill(),"Create Bill");
        mViewPagerAdapter.addFragment(new BillHistory(),"Bill History");
        mViewPagerAdapter.addFragment(new BillsRecieved(),"Bills Received");
        mFragmentBillsTabsBinding.billsViewPager.setAdapter(mViewPagerAdapter);
        mFragmentBillsTabsBinding.billsTablayout.setupWithViewPager(mFragmentBillsTabsBinding.billsViewPager);
    }
}