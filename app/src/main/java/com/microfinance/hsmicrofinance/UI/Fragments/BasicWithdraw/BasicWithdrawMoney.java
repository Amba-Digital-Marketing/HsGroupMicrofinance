package com.microfinance.hsmicrofinance.UI.Fragments.BasicWithdraw;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import com.microfinance.hsmicrofinance.Network.models.WithdrawDetail;
import com.microfinance.hsmicrofinance.UI.viewmodels.WithdrawViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicWithdrawMoneyBinding;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;


public class BasicWithdrawMoney extends Fragment {

FragmentBasicWithdrawMoneyBinding mFragmentBasicWithdrawMoneyBinding;

    private NavController mNavController;

    private String[] mCurrency;
    private ArrayAdapter mArrayAdapter;
    private WithdrawViewModel mViewModel;
    List<WithdrawDetail.WithdrawMethodDetail> mWithdrawMethodDetails;
    private int mId;
    private int mTermID;
    private HomeActivityViewModel mHomeActivityViewModel;
    private int notified=-1;
    private Double balance;
    private Double BASICWITHDRAWAMOUNT = 200.0;
    private Double MAXIMUMWITHDRAWAMOUNT =50000.0;

    public BasicWithdrawMoney() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        updateUserOnBalance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentBasicWithdrawMoneyBinding = FragmentBasicWithdrawMoneyBinding.inflate(inflater, container, false);
        return mFragmentBasicWithdrawMoneyBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            ((AppCompatActivity)getActivity()).setSupportActionBar(mFragmentBasicWithdrawMoneyBinding.toolbar);
            //mFragmentBasicWithdrawMoneyBinding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
            mFragmentBasicWithdrawMoneyBinding.toolbar.setTitle("Withdraw");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mFragmentBasicWithdrawMoneyBinding.toolbar.setNavigationOnClickListener(v->{
                mNavController.navigate(R.id.action_basicWithdrawMoney_to_basicDashboard);
                //Toast.makeText(requireActivity(), "Tool Bar Click", Toast.LENGTH_SHORT).show();
            });


        }catch (Exception e){
        e.printStackTrace();
        }
        mNavController = Navigation.findNavController(view);
        mCurrency = getResources().getStringArray(R.array.currency_type);
        mArrayAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, mCurrency);
        mFragmentBasicWithdrawMoneyBinding.autoCompleteCurrency.setAdapter(mArrayAdapter);
        mViewModel = new ViewModelProvider(requireActivity()).get(WithdrawViewModel.class);
        mViewModel.apiForWithdrawdetails(getContext());
       // mHomeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
       // updateUserOnBalance();
        mViewModel.getLiveDataofWithdrawDetailObserver().observe(getViewLifecycleOwner(), withdrawMethodDetails -> {
            mWithdrawMethodDetails = withdrawMethodDetails;
            this.mId = mWithdrawMethodDetails.get(0).id;
            this.mTermID = mWithdrawMethodDetails.get(0).term.get(0).id;
            Timber.tag("WDetails").d("Deatils ++%s", mWithdrawMethodDetails.toString());
            Timber.tag("WDetails").d("ID ++%s", mId);
            Timber.tag("WDetails").d("Term Id %s", withdrawMethodDetails.get(0).term.get(0).id);
        });

                mFragmentBasicWithdrawMoneyBinding.btnWithdraw.setOnClickListener(v-> doWithraw(view));
        //mNavController.navigate(R.id.action_basicWithdrawMoney_to_confirmTransfer)
        mFragmentBasicWithdrawMoneyBinding.btn1.setOnClickListener(v->{
            mViewModel.withdraw(requireActivity(),mId,2000.0,26,view);
        });
        mFragmentBasicWithdrawMoneyBinding.button2.setOnClickListener(v->{
            mViewModel.getOTPforWithdraw(requireActivity(),2000,"8084889432252678",view);
        });
        mFragmentBasicWithdrawMoneyBinding.button3.setOnClickListener(view1 -> {

            mViewModel.confrimOTp(requireActivity(), mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim(),view);

        });
    }

    private void doWithraw(View view) {
        if(mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim().isEmpty()){
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.setError("Please enter amount");
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.requestFocus();
        }else if(Integer.parseInt(mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim()) < BASICWITHDRAWAMOUNT){
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.setError("Cannot be less than 2000");
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.requestFocus();
        }else if(Integer.parseInt(mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim()) > MAXIMUMWITHDRAWAMOUNT){
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.setError("Cannot be more than 50000");
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.requestFocus();
        }
        else if(Double.parseDouble(mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim()) > balance ){
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.setError("You have insufficient balance");
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.requestFocus();
        }
        else if(balance < BASICWITHDRAWAMOUNT){
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.setError("You have insufficient balance");
            mFragmentBasicWithdrawMoneyBinding.withrawAmount.requestFocus();
        }
        else{
            mFragmentBasicWithdrawMoneyBinding.progrebar.setVisibility(View.VISIBLE);
            Double amount = Double.parseDouble(mFragmentBasicWithdrawMoneyBinding.withrawAmount.getText().toString().trim());
            mViewModel.withdraw(requireActivity(),mId,amount,mTermID,view);

          ////  mNavController.navigate(R.id.action_basicWithdrawMoney_to_confirmTransfer);
           // mFragmentBasicWithdrawMoneyBinding.withrawAmount.setText(null);
        }
    }
    private void updateUserOnBalance() {
       // if(mHomeActivityViewModel.notifiedMLD.getValue() != 0){
            mHomeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
                if(dashboardData != null) {
                    this.balance = Double.parseDouble(dashboardData.getAccBallance());
                    if (balance < 500) {
                        this.notified = -1;
                        notifyUserOnAccouBalance(balance);
                        this.notified = 0;
                        mHomeActivityViewModel.setNotifiedMLD( notified);
                    }
                }
            });
      //  }

    }

    private  void notifyUserOnAccouBalance(double balance){
        if (balance< BASICWITHDRAWAMOUNT && this.notified == -1){
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Sorry")
                    .setContentText("Your Account balance is below the minimum 200")
                    .setNeutralButtonTextColor(Color.parseColor("#297545"))
                    .show();
            this.notified = 0;
        }
    }
}