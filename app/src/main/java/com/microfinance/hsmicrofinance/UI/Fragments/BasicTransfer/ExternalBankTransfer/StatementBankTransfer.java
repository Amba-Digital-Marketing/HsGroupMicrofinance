package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.ExternalBankTransfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.JsonObject;
import com.microfinance.hsmicrofinance.Network.entity.BankTransferHistory;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.OtherBankTranferAdapter;

import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentStatementBankTransferBinding;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.UI.viewmodels.StatementOtherBankTransferViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class StatementBankTransfer extends Fragment implements OtherBankTranferAdapter.OtherBankinterface {


FragmentStatementBankTransferBinding mBinding;

    StatementOtherBankTransferViewModel otherBankTransferViewModel;
    List<BankTransferHistory.OtherBankTransaction>  mOtherBankTransfers;
    private OtherBankTranferAdapter mAdapter;
    private List<Banks.BankDetail> mBankDetailList;
    private NavController mNavController;
    private HomeActivityViewModel homeActivityViewModel;


    public StatementBankTransfer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStatementBankTransferBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Bank Transfer");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_statementBankTransfer_to_basicAccount));
        mOtherBankTransfers =new ArrayList<>();
        homeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        homeActivityViewModel.getCurrenciesDetails(getActivity());
        homeActivityViewModel.getCountriesDetails(getActivity());
        homeActivityViewModel.getBankDetails(getActivity());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        otherBankTransferViewModel = new ViewModelProvider(requireActivity()).get(StatementOtherBankTransferViewModel.class);
        setRecylerView();

    }
    private void setRecylerView() {
        otherBankTransferViewModel.getTransferHistory(getContext());
        otherBankTransferViewModel.mLiveData.observe(getViewLifecycleOwner(), bankTransferHistory -> {
            if(bankTransferHistory != null){
                otherBankTransferViewModel.getBankDetails(getContext());
                otherBankTransferViewModel.mBankDetailsLiveData.observe(getViewLifecycleOwner(), banks ->{
                    mBankDetailList = banks.getBankDetails();

                    Timber.tag("banks").d("bank list"+bankTransferHistory.getOtherBankTransactions().toString());
                    if(bankTransferHistory.getOtherBankTransactions().isEmpty()){
                        mBinding.page.setVisibility(View.VISIBLE);
                    }else{
                        mBinding.page.setVisibility(View.GONE);
                        mOtherBankTransfers = bankTransferHistory.getOtherBankTransactions();
                    }
                    mAdapter = new OtherBankTranferAdapter(requireActivity(), bankTransferHistory, mBankDetailList,this);
                    mBinding.otherRecyclerview.setAdapter(mAdapter);
                });

               // mFragmentStatementBankTransferBinding.otherRecyclerview.setAdapter(mAdapter);
            }else{
                Toast.makeText(getContext(), "Null", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClickOtherBank(View view, int position) {
        BankTransferHistory.OtherBankTransaction  bankTransaction = mOtherBankTransfers.get(position);
        Bundle args = new Bundle();
        args.putParcelable("bankTransaction", bankTransaction);
        mNavController.navigate(R.id.action_statementBankTransfer_to_singleOtherBankTransaction,args);



    }
}