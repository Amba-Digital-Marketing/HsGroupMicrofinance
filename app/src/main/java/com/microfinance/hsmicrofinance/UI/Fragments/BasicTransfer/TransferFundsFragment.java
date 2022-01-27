package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer;

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
import com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends.PayFriendsViaMpesa;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends.PayfriendsViaBank;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.ExternalBankTransfer.OtherBankTransferFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.InternalBankTransfer.OwnBankTransferFragment;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;

import com.microfinance.hsmicrofinance.databinding.FragmentTransferFundsBinding;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.Countries;
import com.microfinance.hsmicrofinance.Network.entity.Currencies;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import timber.log.Timber;


public class TransferFundsFragment extends Fragment {
//hosts otherbanktansfer fragment and ownbanktransferFragment
  FragmentTransferFundsBinding mBinding;

    HomeActivityViewModel homeActivityViewModel;
    private ViewPagerAdapter mViewPagerAdapter;

    private Currencies currencies;
    private Countries countries;
    private Banks banks;
    private NavController mNavController;
    String phone;
    String phoneOnHs;

    public TransferFundsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentTransferFundsBinding.inflate(inflater, container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Transfer Money");
        mBinding.toolbar.setNavigationOnClickListener(v->{mNavController.navigate(R.id.action_transferFundsFragment_to_basicDashboard);});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeActivityViewModel = new ViewModelProvider(getActivity()).get(HomeActivityViewModel.class);
        mNavController = Navigation.findNavController(view);

        if (getArguments() != null) {
            this.phone = getArguments().getString("phone");
            this.phoneOnHs = getArguments().getString("onHsPhone");
            Timber.tag("num").d("Number::%s", phone);
        }

        //gettingng currencies to display
        //getting countries
        this.countries = getCountry();
       this.currencies = getCurrency();
        //getting banks to display
        this.banks =  getBanks();

        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        if(phone != null || phoneOnHs != null){
            mViewPagerAdapter.addFragment(new OwnBankTransferFragment(phone, phoneOnHs), "Internal Transfer");
        }else{
            mViewPagerAdapter.addFragment(new OwnBankTransferFragment(), "Internal Transfer");

        }
        mViewPagerAdapter.addFragment(new OtherBankTransferFragment(countries,currencies,banks),"Other Banks");
        mBinding.vpTransferFunds.setAdapter(mViewPagerAdapter);
        mBinding.tlTransferFunds.setupWithViewPager(mBinding.vpTransferFunds);
    }

    //getCountries
    private Countries getCountry(){
            homeActivityViewModel.getCountriesDetails(getContext());
            homeActivityViewModel.mCountriesDetailsLiveData.observe(getViewLifecycleOwner(),countries1 -> {
                this.countries = countries1;
            });

        return  this.countries;
    }

    private Currencies getCurrency(){
            homeActivityViewModel.getCurrenciesDetails(getContext());
            homeActivityViewModel.mCurrenciesDetailsLiveData.observe(getViewLifecycleOwner(),currencies1 -> {
                this.currencies = currencies1;
            });


        return  this.currencies;
    }

    private Banks getBanks(){

            homeActivityViewModel.getBankDetails(getContext());
            homeActivityViewModel.mBankDetailsLiveData.observe(getViewLifecycleOwner(), banks1 ->{
                if(banks1 != null){
                    this.banks = banks1;
                }

            });


        return this.banks;
    }

}