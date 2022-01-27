package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit;

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
import com.microfinance.hsmicrofinance.UI.adapters.EDepositStatementAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentStatementEdepositBinding;
import com.microfinance.hsmicrofinance.Network.models.EdepositStatement;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;

import java.util.Collections;
import java.util.List;


public class StatementEdeposit extends Fragment implements EDepositStatementAdapter.EdepositStatementInterface {

    FragmentStatementEdepositBinding mBinding;
    private List<EdepositStatement.EDepositStatement> mStatementList;
    private EDepositStatementAdapter mAdapter;
    private LoanHistoryViewModels mViewModels;
    private NavController mNavController;

    public StatementEdeposit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStatementEdepositBinding.inflate(inflater, container, false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("E-Deposit History");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_statementEdeposit_to_basicAccount));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mViewModels.fetchEdepositStatement(getContext());
        mViewModels.getMutableLiveDataofDepositStatement().observe(getViewLifecycleOwner(), statement -> {
            mStatementList = statement;
            Collections.reverse(mStatementList);
            try{
                if(mStatementList.isEmpty()){
                    mBinding.page.setVisibility(View.VISIBLE);
                }else {
                    mBinding.page.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            mAdapter = new EDepositStatementAdapter(requireActivity(),mStatementList,this);
            mBinding.edepositRecclerview.setAdapter(mAdapter);
        });
    }



    @Override
    public void onClickingEdepositItem(View view, int position) {

    }
}