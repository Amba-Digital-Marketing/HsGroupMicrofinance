package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.databinding.LoanhistoryitemBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;

import java.util.List;

public class LoanHistoryAdapter extends RecyclerView.Adapter<LoanHistoryAdapter.LoanViewHolder> {
    LoanhistoryitemBinding mLoanhistoryitemBinding;
    LayoutInflater mLayoutInflater;
    private LoanItemClickListener mLoanItemClickListener;
    List<LoanHistories.LoanHistory>mHistoryList;
    private LoanHistories.LoanHistory mLoanHistory;
    Context mContext;

    public LoanHistoryAdapter(Context context, List<LoanHistories.LoanHistory>loanHistories, LoanItemClickListener loanItemClickListener) {
        this.mContext = context;
        this.mHistoryList = loanHistories;
        this.mLoanItemClickListener = loanItemClickListener;
    }

    @NonNull
    @Override
    public LoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mLoanhistoryitemBinding = LoanhistoryitemBinding.inflate(mLayoutInflater, parent, false);
        return new LoanViewHolder(mLoanhistoryitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanViewHolder holder, int position) {
        mLoanHistory = mHistoryList.get(position);
        switch (mLoanHistory.getLoanplanId()){
            case 1:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("LogBook Loan");
                break;
            case 2:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Business Loan");
                break;
            case 3:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Kilimo Loans");
                break;
            case 4:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Title Deed Loans");
                break;
            case 5:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Personal Loans");
                break;
            case 6:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Chama Loans");
                break;
            case 7:
                holder.mLoanhistoryitemBinding.tvpkgname.setText("Bima Loans");
                break;
        }
        holder.mLoanhistoryitemBinding.tvAmount.setText("Ksh "+ mLoanHistory.getAmount());
        switch (mLoanHistory.getStatus()){
            case 0:
                holder.mLoanhistoryitemBinding.tvStatus.setText("Rejected");
                //holder.mLoanhistoryitemBinding.tvStatus.setBackgroundColor(Color.parseColor("#800000"));
                holder.mLoanhistoryitemBinding.tvStatus.setTextColor(Color.parseColor("#800000"));

                break;
            case 1:
                holder.mLoanhistoryitemBinding.tvStatus.setText("Success");
                holder.mLoanhistoryitemBinding.tvStatus.setTextColor(Color.parseColor("#297545"));
                //holder.mLoanhistoryitemBinding.tvStatus.setBackgroundColor(Color.parseColor("#297545"));
                break;
            case 2:
                holder.mLoanhistoryitemBinding.tvStatus.setText("Pending");
                holder.mLoanhistoryitemBinding.tvStatus.setTextColor(Color.parseColor("#800000"));
                //holder.mLoanhistoryitemBinding.tvStatus.setBackgroundColor(Color.parseColor("#800000"));
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
        LoanhistoryitemBinding mLoanhistoryitemBinding;

        LoanViewHolder(LoanhistoryitemBinding binding) {
            super(binding.getRoot());
            this.mLoanhistoryitemBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mLoanItemClickListener != null){
               mLoanItemClickListener.onLoanItemClick(v,getBindingAdapterPosition());
            }
        }
    }
    public LoanHistories.LoanHistory getLoanHistory(int id){
        return mHistoryList.get(id);
    }

    public interface LoanItemClickListener{
        void onLoanItemClick(View view, int position);
    }
}
