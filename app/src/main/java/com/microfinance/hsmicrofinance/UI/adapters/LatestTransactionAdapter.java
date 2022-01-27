package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.LatestTransactionsBinding;
import com.microfinance.hsmicrofinance.Network.models.TransactionHistory;

import java.util.List;

public class LatestTransactionAdapter extends  RecyclerView.Adapter<LatestTransactionAdapter.TRXHolder>  {
LatestTransactionsBinding mLatestTransactionsBinding;
LayoutInflater mLayoutInflater;
Context mContext;
List<TransactionHistory.TransactionHistoryDetail>mTransactionHistoryDetailList;
LatestTransactionInterface mLatestTransactionInterface;
    private TransactionHistory.TransactionHistoryDetail mTransactionHistoryDetail;


    public LatestTransactionAdapter(Context context, List<TransactionHistory.TransactionHistoryDetail>transactionHistoryDetails, LatestTransactionInterface latestTransactionInterface) {
        this.mContext = context;
        this.mTransactionHistoryDetailList = transactionHistoryDetails;
        this.mLatestTransactionInterface= latestTransactionInterface;
    }

    @NonNull
    @Override
    public TRXHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mLatestTransactionsBinding = LatestTransactionsBinding.inflate(mLayoutInflater,parent,false);
        return new TRXHolder(mLatestTransactionsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TRXHolder holder, int position) {
        mTransactionHistoryDetail = mTransactionHistoryDetailList.get(position);
        switch (mTransactionHistoryDetail.getStatus())
        {
            case 1:
                holder.mLatestTransactionsBinding.tvStatus.setText("Success");
               // holder.mLatestTransactionsBinding.tvStatus.setBackgroundColor(Color.parseColor("#297545"));
                holder.mLatestTransactionsBinding.tvStatus.setTextColor(Color.parseColor("#297545"));
                break;
            default:
                holder.mLatestTransactionsBinding.tvStatus.setText("Pending");
               // holder.mLatestTransactionsBinding.tvStatus.setBackgroundColor(Color.parseColor("#800000"));
                holder.mLatestTransactionsBinding.tvStatus.setTextColor(Color.parseColor("#800000"));

        }
        String date = mTransactionHistoryDetail.getCreatedAt();
        holder.mLatestTransactionsBinding.tvpkgname.setText(mTransactionHistoryDetail.getType());
        holder.mLatestTransactionsBinding.tvdate.setText(date.substring(0,10));
        holder.mLatestTransactionsBinding.tvAmount.setText("Ksh "+ mTransactionHistoryDetail.getAmount());


    }

    @Override
    public int getItemCount() {
        int size =0;
        if(mTransactionHistoryDetailList != null){
            size = mTransactionHistoryDetailList.size();
        }
        return size;
    }
    public TransactionHistory.TransactionHistoryDetail getTransactionHistoryDetail(int id){
        return mTransactionHistoryDetailList.get(id);
    }

    public class TRXHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
LatestTransactionsBinding mLatestTransactionsBinding;
        public TRXHolder(@NonNull LatestTransactionsBinding binding) {
            super(binding.getRoot());
            this.mLatestTransactionsBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
if(mLatestTransactionInterface != null){
    mLatestTransactionInterface.onClickingLatestTrx(v,getBindingAdapterPosition());
}
        }
    }

    public interface LatestTransactionInterface{
        void onClickingLatestTrx(View v, int position);
    }

}
