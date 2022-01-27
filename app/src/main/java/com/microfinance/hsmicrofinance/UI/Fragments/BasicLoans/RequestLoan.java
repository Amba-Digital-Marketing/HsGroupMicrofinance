package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

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

import com.microfinance.hsmicrofinance.Network.APIService;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentRequestLoanBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanRequest;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanPackageViewModel;

import retrofit2.Call;

public class RequestLoan extends Fragment {


    FragmentRequestLoanBinding mBinding;
    private int mId;
    private int mMin;
    private int mMax;
    private APIService mApiService;
    private Call<LoanRequest> mLoanRequestCall;
    private LoanPackageViewModel mLoanPackageViewModel;
    private NavController mNavController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentRequestLoanBinding.inflate(inflater, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Request Loan");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_requestLoan_to_basicLoanPackages));
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        mLoanPackageViewModel = new ViewModelProvider(requireActivity()).get(LoanPackageViewModel.class);
        mBinding.progrebar.setVisibility(View.INVISIBLE);
        mId = getArguments().getInt("id");
        mMin = getArguments().getInt("lower");
        mMax = getArguments().getInt("upper");

            mBinding.tvLoanType.setText(getArguments().getString("type"));
            mBinding.tvloanRange.setText("Ksh "+mMin +" to Ksh " +mMax );
            mBinding.btnRequestLoan.setOnClickListener(v -> requestLoan(view));



    }

    private void requestLoan(View view) {
        //mFragmentRequestLoanBinding.progrebar.setVisibility(View.INVISIBLE);

        if(mBinding.amount.getText().toString().isEmpty()){
            mBinding.amount.setError("Please enter amount");
            mBinding.amount.requestFocus();
        }else if (Integer.parseInt(mBinding.amount.getText().toString().trim()) < mMin) {
            mBinding.amount.setError("Cannot be less than " + mMin);
            mBinding.amount.requestFocus();
        } else if (Integer.parseInt(mBinding.amount.getText().toString().trim()) > mMax) {
            mBinding.amount.setError("Cannot be more than " + mMax);
            mBinding.amount.requestFocus();
        }else{

            mBinding.progrebar.setVisibility(View.VISIBLE);
            mLoanPackageViewModel.apiCallForLoanRequest(getContext(),mId, mBinding.amount.getText().toString().trim(),view);
            mBinding.amount.setText(null);
            //mFragmentRequestLoanBinding.progrebar.setVisibility(View.GONE);


        }
    }


}