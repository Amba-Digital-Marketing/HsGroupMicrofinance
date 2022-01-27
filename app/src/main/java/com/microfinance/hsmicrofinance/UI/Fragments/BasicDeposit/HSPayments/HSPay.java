package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.HSPayments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentHSPayBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import timber.log.Timber;


public class HSPay extends Fragment {

  FragmentHSPayBinding mBinding;
    private String[] mCurrency;
    private ArrayAdapter mArrayAdapter;

    private EdepositViewModel mEdepositViewModel;
    private int mId;
    private NavController mNavController;
    private Bundle mArgs;

    public HSPay() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= FragmentHSPayBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("H&S Payment");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_HSPay_to_basicEDeposit));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
       try{

           mId = getArguments().getInt("id");

       }catch (Exception e){
           e.printStackTrace();
       }
        mCurrency = getResources().getStringArray(R.array.currency_type);
        mArrayAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, mCurrency);
        mBinding.autoCompleteCurrency.setAdapter(mArrayAdapter);
        mEdepositViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mBinding.submit.setOnClickListener(v->validate(view));
    }

    private void validate(View view) {
       // navController.navigate(R.id.action_HSPay_to_HSPaymentsSubmit)
        if(mBinding.mobileMoneyDeposit.getText().toString().isEmpty()){
            mBinding.mobileMoneyDeposit.setError("Enter amount");
            mBinding.mobileMoneyDeposit.requestFocus();
        }else if(Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim()) < 5){
            mBinding.mobileMoneyDeposit.setError("Cannot be less then 5");
            mBinding.mobileMoneyDeposit.requestFocus();
        }else  if(Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim())>10000){
            mBinding.mobileMoneyDeposit.setError("Cannot be greater than 10000");
            mBinding.mobileMoneyDeposit.requestFocus();
        }else {
            mBinding.progrebar.setVisibility(View.VISIBLE);
            mEdepositViewModel.checkManualDeposit(getContext(),mId,Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim()),view);

        }
       // mBinding.progrebar.setVisibility(View.GONE);
    }
}