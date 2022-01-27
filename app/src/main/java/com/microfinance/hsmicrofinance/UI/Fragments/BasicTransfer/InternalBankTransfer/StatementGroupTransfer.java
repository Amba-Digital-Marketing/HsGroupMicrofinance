package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.InternalBankTransfer;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.HSGroupAdapter;
import com.microfinance.hsmicrofinance.UI.adapters.HsGroupTransferAdapter;

import com.microfinance.hsmicrofinance.databinding.FragmentStatementGroupTransferBinding;
import com.microfinance.hsmicrofinance.Network.entity.HSgroupTransfer;
import com.microfinance.hsmicrofinance.Network.models.TransactionHistory;
import com.microfinance.hsmicrofinance.UI.viewmodels.LatestTransactionViewModel;

import java.util.List;
import java.util.stream.Collectors;

import timber.log.Timber;


public class StatementGroupTransfer extends Fragment implements HsGroupTransferAdapter.HSGroupinterface {

    FragmentStatementGroupTransferBinding mBinding;
    private List<HSgroupTransfer> mHSgroupTransfers;
    private HsGroupTransferAdapter mAdapter;
    private LatestTransactionViewModel mViewModel;
    List<TransactionHistory.TransactionHistoryDetail>mList;
    private NavController mNavController;

    public StatementGroupTransfer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentStatementGroupTransferBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Group Transfer");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_statementGroupTransfer_to_basicAccount));
        return mBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        setRecyclerView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setRecyclerView() {
        mViewModel = new ViewModelProvider(requireActivity()).get(LatestTransactionViewModel.class);
        mViewModel.makeAPIcall(requireActivity());
        mViewModel.getLatestTransactionObserver().observe(getViewLifecycleOwner(), transactionHistoryDetails -> {
            mList = transactionHistoryDetails.stream().filter(transactionHistoryDetail -> transactionHistoryDetail.getType().equals("H&S Group Transfer")).collect(Collectors.toList());
            Timber.tag("HSList").d("details%s", mList.toString());
            try{
                if(mList.isEmpty()){
                   // mFragmentStatementGroupTransferBinding.progrebar.setVisibility(View.VISIBLE);
                    mBinding.page.setVisibility(View.VISIBLE);
                }else {
                    mBinding.progrebar.setVisibility(View.GONE);
                    mBinding.page.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            HSGroupAdapter hsadapter = new HSGroupAdapter(requireActivity(),mList,this::onClickHSTransfer);
            mBinding.groupItemRecycler.setAdapter(hsadapter);

        });


    }

    @Override
    public void onClickHSTransfer(View view, int position) {

    }
}