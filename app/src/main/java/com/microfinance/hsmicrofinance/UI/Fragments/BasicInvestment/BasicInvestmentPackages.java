package com.microfinance.hsmicrofinance.UI.Fragments.BasicInvestment;

import android.os.Bundle;
import android.util.Log;
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
import com.microfinance.hsmicrofinance.UI.adapters.InvestmentPackagesAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicInvestmentPackagesBinding;
import com.microfinance.hsmicrofinance.Network.models.InvestPlans;
import com.microfinance.hsmicrofinance.UI.viewmodels.InvestmentPackagesViewModel;

import java.util.ArrayList;
import java.util.List;


public class BasicInvestmentPackages extends Fragment implements InvestmentPackagesAdapter.InvestPackageInterface {

    private static final String TAG = "BasicInvestPackages";
    FragmentBasicInvestmentPackagesBinding mBinding;
    private NavController mNavController;
    List<InvestPlans.InvestmentPackage>mInvestmentPackages = new ArrayList<>();
    private InvestmentPackagesAdapter mInvestmentPackagesAdapter;
    public static InvestmentPackagesViewModel mInvestmentPackagesViewModel;
    private InvestPlans.InvestmentPackage mInvestmentPackage;

    public BasicInvestmentPackages() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicInvestmentPackagesBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Investment Packages");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicInvestmentPackages_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: "+ "created");
        mNavController = Navigation.findNavController(view);
        mBinding.progrebar.setVisibility(View.VISIBLE);

        mInvestmentPackagesViewModel = new ViewModelProvider(requireActivity()).get(InvestmentPackagesViewModel.class);
        mInvestmentPackagesViewModel.getInvestPackagesObserver().observe(getViewLifecycleOwner(), investmentPackages ->  {

                mInvestmentPackages = investmentPackages;
                mInvestmentPackagesAdapter = new InvestmentPackagesAdapter(getContext(),mInvestmentPackages,this);

                mBinding.rvInvestmentPackages.setAdapter(mInvestmentPackagesAdapter);
                mBinding.progrebar.setVisibility(View.GONE);

        });
        mInvestmentPackagesViewModel.makeAPIcall(getContext());


    }

    @Override
    public void onClickInvestNow(View view, int position) {
        mInvestmentPackage = mInvestmentPackagesAdapter.getPackage(position);
        Log.d(TAG, "onClickInvestNow: "+ mInvestmentPackage.toString());
        Bundle args = new Bundle();
        args.putInt("id",mInvestmentPackage.getId());
        args.putString("title",mInvestmentPackage.getTitle());
        args.putInt("min",mInvestmentPackage.getMinAmount());
        args.putInt("max",mInvestmentPackage.getMaxAmount());
        args.putInt("duration",mInvestmentPackage.getDuration());
        args.putInt("percent",mInvestmentPackage.getPercentReturn());
        args.putInt("status",mInvestmentPackage.getStatus());

        mNavController.navigate(R.id.action_basicInvestmentPackages_to_investmentPlansDeposit,args);

    }
}