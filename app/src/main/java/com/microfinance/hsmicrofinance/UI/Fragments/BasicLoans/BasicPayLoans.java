package com.microfinance.hsmicrofinance.UI.Fragments.BasicLoans;

import android.os.Bundle;
import android.util.Log;
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

import com.microfinance.hsmicrofinance.Network.data.LoansDescription;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.ViewPagerAdapter;
import com.microfinance.hsmicrofinance.UI.viewmodels.PayLoansViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicPayLoansBinding;


public class BasicPayLoans extends Fragment {
    private static final String TAG = "Payloans";
    String loanStatus ="";
   FragmentBasicPayLoansBinding mBinding;
    private ViewPagerAdapter mViewPagerAdapter;
    private PayLoansViewModel mViewModel;
    private NavController mNavController;

    public BasicPayLoans() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicPayLoansBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Loan Detail");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicPayLoans_to_myLoans));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: "+ "paylaon");

        mViewModel = new ViewModelProvider(requireActivity()).get(PayLoansViewModel.class);
        mNavController = Navigation.findNavController(view);

        double total = (double) getArguments().getInt("amount")+getArguments().getInt("charge");

        mBinding.days.setText(String.valueOf(getArguments().getInt("days")));
        mBinding.chargeText.setText(String.valueOf(getArguments().getInt("charge")));
        mBinding.total.setText(String.valueOf(total));
        mBinding.amount.setText(String.valueOf(getArguments().getInt("amount")));
        switch (getArguments().getInt("status")) {
            case 0:
                loanStatus ="Rejected";

                break;
            case 1:
                loanStatus ="Success";

                break;
            case 2:
                loanStatus ="Pending";

                break;
        }
        mBinding.status.setText("loanStatus");

        if(!loanStatus.equals("Success")){
            mBinding.btnpayloan.setVisibility(View.INVISIBLE);
        }

        mBinding.btnpayloan.setOnClickListener(v ->payLoan(getArguments().getInt("id")));




//        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
//        mViewPagerAdapter.addFragment(new PayLoansDirectDeposit(setLoanDescriptionItem()),"Direct Deposit");
       // mViewPagerAdapter.addFragment(new PayLoansViaMpesa(laon),"M~Pesa");
//        mFragmentBasicPayLoansBinding.payloansViewPager.setAdapter(mViewPagerAdapter);
//        mFragmentBasicPayLoansBinding.payLoansTablayout.setupWithViewPager(mFragmentBasicPayLoansBinding.payloansViewPager);
    }

    private void payLoan(int loanID) {
       /* String amountInput = mFragmentPayLoansDirectDepositBinding.payloanAmount.getText().toString().trim();
        String account = mFragmentPayLoansDirectDepositBinding.payloanAccountNo.getText().toString().trim();

*/
        try {

            mViewModel.payLoans(getContext(),loanID);

        }catch (Exception e){
            e.printStackTrace();

            Toast.makeText(getContext(), "Check your internet Connections", Toast.LENGTH_LONG).show();
        }


    }
private LoansDescription setLoanDescriptionItem(){
        int id =getArguments().getInt("id");
    int userId= getArguments().getInt("userId");
    int days= getArguments().getInt("days");
    int charge=getArguments().getInt("charge");
    int loanId= getArguments().getInt("loanId");
    double amount =getArguments().getInt("amount");
  int status =getArguments().getInt("status");


    LoansDescription loansDescription = new LoansDescription(id,userId,days,charge,loanId,amount, status);
            return loansDescription;
}

}