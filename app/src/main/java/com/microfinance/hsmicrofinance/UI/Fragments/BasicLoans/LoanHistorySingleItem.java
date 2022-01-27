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


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentLoanHistorySingleItemBinding;
import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;

import java.util.ArrayList;
import java.util.List;


public class LoanHistorySingleItem extends Fragment {
    private static final String TAG = "LoanSingleItem";
    List<AllUsers.UserDetail> mUserDetails = new ArrayList<>();
    FragmentLoanHistorySingleItemBinding mBinding;
    private LoanHistoryViewModels mLoanHistoryViewModels;
    private NavController mNavController;

    public LoanHistorySingleItem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentLoanHistorySingleItemBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Loan Detail");
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.navigate(R.id.action_loanHistorySingleItem_to_basicLoanHistory));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        mLoanHistoryViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mLoanHistoryViewModels.getMutableLiveDataofAllUsers().observe(getViewLifecycleOwner(), userDetails -> {
            mUserDetails = userDetails;
            try {
                for (int i = 0; i < mUserDetails.size(); i++) {
                    if (getArguments().getInt("userId") == mUserDetails.get(i).getId()) {
                        mBinding.name.setText(mUserDetails.get(i).getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        mLoanHistoryViewModels.fetchAllUsers(requireActivity());


        try {
            int total = getArguments().getInt("amount") + getArguments().getInt("charge");


            switch (getArguments().getInt("loanId")) {
                case 1:
                    mBinding.pkg.setText("Logbook Loan");
                    break;
                case 2:
                    mBinding.pkg.setText("Business Loan");
                    break;
                case 3:
                    mBinding.pkg.setText("Kilimo Loans");
                    break;
                case 4:
                    mBinding.pkg.setText("Title Deed Loans");
                    break;
                case 5:
                    mBinding.pkg.setText("Personal Loans");
                    break;
                case 6:
                    mBinding.pkg.setText("Chama Loans");
                    break;
                case 7:
                    mBinding.pkg.setText("Bima Loans");
                    break;

            }

            mBinding.days.setText(String.valueOf(getArguments().getInt("days")));
            mBinding.chargeText.setText(String.valueOf(getArguments().getInt("charge")));
            mBinding.total.setText(String.valueOf(total));
            mBinding.amount.setText(String.valueOf(getArguments().getInt("amount")));
            switch (getArguments().getInt("status")) {
                case 0:
                    mBinding.status.setText("Rejected");
                    break;
                case 1:
                    mBinding.status.setText("Success");
                    break;
                case 2:
                    mBinding.status.setText("Pending");
                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}