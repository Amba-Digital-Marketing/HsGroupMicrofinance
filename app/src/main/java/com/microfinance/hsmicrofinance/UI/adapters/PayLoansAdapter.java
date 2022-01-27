package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.UserLoanItemsBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PayLoansAdapter extends RecyclerView.Adapter<PayLoansAdapter.LoanViewHolder> {
    UserLoanItemsBinding mUserLoansBinding;
    LayoutInflater mLayoutInflater;
    private PayLoanListener mLoanItemClickListener;
    private LoanItemListener mLoanItemListener;
    List<LoanHistories.LoanHistory>mHistoryList;
    private LoanHistories.LoanHistory mLoanHistory;
    Context mContext;

    public PayLoansAdapter(Context context, List<LoanHistories.LoanHistory>loanHistories, PayLoanListener payLoanListener,LoanItemListener loanItemListener) {
        this.mContext = context;
        this.mHistoryList = loanHistories;
        this.mLoanItemClickListener = payLoanListener;
        this.mLoanItemListener = loanItemListener;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mUserLoansBinding = UserLoanItemsBinding.inflate(mLayoutInflater, parent, false);
        return new LoanViewHolder(mUserLoansBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        mLoanHistory = mHistoryList.get(position);
        switch (mLoanHistory.getLoanplanId()){
            case 1:
                holder.mUserLoansBinding.etLoan.setText("LogBook Loan");
                Picasso.get().load(R.drawable.logbookloan).into(mUserLoansBinding.imLoan);
                break;
            case 2:
                holder.mUserLoansBinding.etLoan.setText("Business Loan");
                Picasso.get().load(R.drawable.businessloan).into(mUserLoansBinding.imLoan);
                break;
            case 3:
                holder.mUserLoansBinding.etLoan.setText("Kilimo Loans");
                Picasso.get().load(R.drawable.kilimoloan).into(mUserLoansBinding.imLoan);
                break;
            case 4:
                holder.mUserLoansBinding.etLoan.setText("Title Deed Loans");
                Picasso.get().load(R.drawable.titledeed).into(mUserLoansBinding.imLoan);
                break;
            case 5:
                holder.mUserLoansBinding.etLoan.setText("Personal Loans");
                Picasso.get().load(R.drawable.personloan).into(mUserLoansBinding.imLoan);
                break;
            case 6:
                holder.mUserLoansBinding.etLoan.setText("Chama Loans");
                Picasso.get().load(R.drawable.chamaloan).into(mUserLoansBinding.imLoan);
                break;
            case 7:
                holder.mUserLoansBinding.etLoan.setText("Bima Loans");
                Picasso.get().load(R.drawable.bimaloans).into(mUserLoansBinding.imLoan);
                break;
        }



    }


    @Override
    public int getItemCount() {
        int size = 0;
        if(mHistoryList != null){
            size = mHistoryList.size();
        }
        return size;
    }

    class LoanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        UserLoanItemsBinding mUserLoansBinding;

        LoanViewHolder(UserLoanItemsBinding binding) {
            super(binding.getRoot());
            this.mUserLoansBinding = binding;
            mUserLoansBinding.ltPayLoan.setOnClickListener(this);
            mUserLoansBinding.ltLoan.setOnClickListener(this);
            ///binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == mUserLoansBinding.ltPayLoan){
                mLoanItemClickListener.onLoanPayClick(v,getBindingAdapterPosition());
            }else{
                mLoanItemListener.onLoanItemClick(v,getBindingAdapterPosition());
            }





        }
    }
    public LoanHistories.LoanHistory getLoanHistory(int id){
        return mHistoryList.get(id);
    }

    public interface PayLoanListener{
        void onLoanPayClick(View view, int position);

    }
    public interface LoanItemListener{
        void onLoanItemClick(View view,int position);
    }
}
