package com.microfinance.hsmicrofinance.UI.Fragments.Support;

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


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.SupportItemsAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicPayfriendsBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicSupportBinding;
import com.microfinance.hsmicrofinance.Network.entity.SupportHistory;
import com.microfinance.hsmicrofinance.UI.viewmodels.BasicSupportViewModel;

import java.util.List;


public class BasicSupport extends Fragment implements SupportItemsAdapter.SupportItemsClickListener {
    private static final String TAG = "BasicSupport";
    private SupportItemsAdapter mSupportItemsAdapter;
    BasicSupportViewModel basicSupportViewModel;
    private SupportHistory mSupportItems;
    List<SupportHistory.AllSupportRequestsBasedOnID> mSupportItemsList;

    FragmentBasicSupportBinding mBinding;
    private NavController navController;

    public BasicSupport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = FragmentBasicSupportBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Support");
        mBinding.toolbar.setNavigationOnClickListener(v->navController.navigate(R.id.action_basicSupport_to_basicDashboard));
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mBinding.btnnewSupport.setOnClickListener(v ->
            navController.navigate(R.id.action_basicSupport_to_addSupportFragment)
        );

        basicSupportViewModel = new ViewModelProvider(this).get(BasicSupportViewModel.class);
        basicSupportViewModel.getSupportItems(getContext());
        basicSupportViewModel.mSupportItemsLiveData.observe(getViewLifecycleOwner(), supportHistory ->{
            if(supportHistory != null){
                setRecyclerView(supportHistory);
            }else{
                Toast.makeText(this.getContext(), "null", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void setRecyclerView(SupportHistory supportHistory) {
        mSupportItemsAdapter = new SupportItemsAdapter(requireActivity(),supportHistory,this);
        mBinding.loanHistoryRecycler.setAdapter(mSupportItemsAdapter);
    }

    @Override
    public void onSupportItemsItemClick(View view, int position) {
        mSupportItems = new SupportHistory();
       mSupportItemsList = mSupportItems.getAllSupportRequestsBasedOnID();
       SupportHistory.AllSupportRequestsBasedOnID  mSupportItem = mSupportItemsAdapter.getmSupportItems(position);

        int supportid = mSupportItem.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("SUPPORTID", supportid);
        navController.navigate(R.id.action_basicSupport_to_viewSupportItem,bundle);
    }


}