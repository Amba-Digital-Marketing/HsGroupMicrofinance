package com.microfinance.hsmicrofinance.UI.Fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicAccountBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;


public class BasicAccount extends Fragment {

   FragmentBasicAccountBinding mBinding;
    HomeActivityViewModel homeActivityViewModel;
    private ViewPagerAdapter mViewPagerAdapter;
    private NavController mNavController;

    public BasicAccount() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding =FragmentBasicAccountBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Account");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicAccount_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.addFragment(new AccountStatements(),"Statement");
        mViewPagerAdapter.addFragment(new Profile(),"Profile");
        mBinding.accountViewPager.setAdapter(mViewPagerAdapter);
        mBinding.accountTablayout.setupWithViewPager(mBinding.accountViewPager);

        homeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
    }


}