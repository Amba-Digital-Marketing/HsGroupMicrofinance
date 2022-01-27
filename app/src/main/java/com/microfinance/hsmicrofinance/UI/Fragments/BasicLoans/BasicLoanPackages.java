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
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.LoanItemsAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicLoanPackagesBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanPlans;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanPackageViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class BasicLoanPackages extends Fragment implements LoanItemsAdapter.ItemClickListener {
    private static final String TAG = "BasicLoanPackages";

    LoanItemsAdapter adapter;

    FragmentBasicLoanPackagesBinding mBinding;
    private List<LoanPlans.Loan>mLoanPlans = new ArrayList<>();
    private NavController mNavController;
    private LoanPackageViewModel mLoanPackageViewModel;
    private RecyclerView mRecyclerView;


    public BasicLoanPackages() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicLoanPackagesBinding.inflate(inflater, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Loan Plans");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicLoanPackages_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mRecyclerView = mBinding.rvLoanPackages;

        mBinding.progrebar.setVisibility(View.VISIBLE);

        mLoanPackageViewModel = new ViewModelProvider(requireActivity()).get(LoanPackageViewModel.class);

            mLoanPackageViewModel.getPackagesObserver().observe(getViewLifecycleOwner(), loans -> {
                mLoanPlans = loans;
                if(mLoanPlans != null){
                    Timber.tag("Loans").d("all loans%s", mLoanPlans.size());
                }
                Timber.tag("Loans").d("all loans to String%s", mLoanPlans.toString());
                adapter = new LoanItemsAdapter(getContext(), mLoanPlans, this);

                mRecyclerView.setAdapter(adapter);
                mBinding.progrebar.setVisibility(View.GONE);

            });
            mLoanPackageViewModel.makeAPIcall(getContext());




    }


    @Override
    public void onItemClick(View view, int position) {

        LoanPlans.Loan loansAvailable = adapter.getItem(position);
        Timber.tag("LoansAvailable").d("onItemClick: %s", loansAvailable.toString());

        Bundle args = new Bundle();
        args.putString("type", loansAvailable.getName());
        args.putInt("lower", loansAvailable.getMinAmount());
        args.putInt("upper", loansAvailable.getMaxAmount());
        args.putInt("id",loansAvailable.getId());



        mNavController.navigate(R.id.action_basicLoanPackages_to_requestLoan, args);

    }


}



