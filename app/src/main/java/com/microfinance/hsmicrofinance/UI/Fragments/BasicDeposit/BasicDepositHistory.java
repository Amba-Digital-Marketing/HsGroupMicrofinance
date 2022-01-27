package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Mpesa.MpesaPendingDeposit;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicDepositHistoryBinding;


public class BasicDepositHistory extends Fragment {

    FragmentBasicDepositHistoryBinding mBinding;
    private ViewPagerAdapter mViewPagerAdapter;
    private NavController mNavController;


    public BasicDepositHistory() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicDepositHistoryBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Deposit History");
        mBinding.toolbar.setNavigationOnClickListener(v->{mNavController.navigate(R.id.action_basicDepositHistory_to_basicDashboard);});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.addFragment(new EdepositHistory(),"Deposit History");
        mViewPagerAdapter.addFragment(new MpesaDepositHistory(),"M~Pesa History");
        mViewPagerAdapter.addFragment(new MpesaPendingDeposit(),"Pending Deposit");
        mBinding.payloansViewPager.setAdapter(mViewPagerAdapter);
        mBinding.payLoansTablayout.setupWithViewPager(mBinding.payloansViewPager);
    }
}