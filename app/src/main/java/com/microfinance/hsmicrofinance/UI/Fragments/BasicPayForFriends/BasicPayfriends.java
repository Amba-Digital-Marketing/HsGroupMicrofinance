package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.InternalBankTransfer.OwnBankTransferFragment;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicPayfriendsBinding;

import timber.log.Timber;


public class BasicPayfriends extends Fragment {

    FragmentBasicPayfriendsBinding mBinding;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private String phone;
    private String phoneOnHs;
    private NavController mNavController;

    public BasicPayfriends() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentBasicPayfriendsBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Pay Beneficiary");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicPayfriends_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        if(getArguments() != null){
            phone = getArguments().getString("phone");
            phoneOnHs = getArguments().getString("onHsPhone");
            Timber.tag("num").d("Number::%s", phone);
        }

        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
       mViewPagerAdapter.addFragment(new PayFriendsViaMpesa(phone,phoneOnHs), "M~Pesa");
        mViewPagerAdapter.addFragment(new OwnBankTransferFragment(),"Bank");

        mBinding.payfriendsViewPager.setAdapter(mViewPagerAdapter);
        mBinding.payFriendsTablayout.setupWithViewPager(mBinding.payfriendsViewPager);
    }


}