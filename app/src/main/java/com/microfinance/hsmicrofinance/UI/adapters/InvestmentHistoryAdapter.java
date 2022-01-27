package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.InvesthistoryitemBinding;
import com.microfinance.hsmicrofinance.Network.models.InvestmentHistory;

import java.util.List;

public class InvestmentHistoryAdapter extends RecyclerView.Adapter<InvestmentHistoryAdapter.HistoryHolder> {
    private Context mContext;
    private List<InvestmentHistory.InvestementHistoryDetail> mInvestmentHistoryList;
    private LayoutInflater mLayoutInflater;
    private InvesthistoryitemBinding mInvesthistoryitemBinding;
    private InvestmentHistoryInterface mInvestmentHistoryInterface;
    InvestmentHistory.InvestementHistoryDetail mInvestmentHistory;

    public InvestmentHistoryAdapter(Context context, List<InvestmentHistory.InvestementHistoryDetail> investmentHistoryList, InvestmentHistoryInterface investmentHistoryInterface) {
        this.mInvestmentHistoryList = investmentHistoryList;
        this.mContext = context;
        this.mInvestmentHistoryInterface = investmentHistoryInterface;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mInvesthistoryitemBinding = InvesthistoryitemBinding.inflate(mLayoutInflater, parent, false);
        return new HistoryHolder(mInvesthistoryitemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        mInvestmentHistory = mInvestmentHistoryList.get(position);

        switch (mInvestmentHistory.getFdrplanId()) {
            case 1:
                holder.mInvesthistoryitemBinding.tvpkgname.setText("Standard");
                holder.mInvesthistoryitemBinding.tvpkgname.setTextColor(Color.parseColor("#1d2453"));
                break;
            case 2:
                holder.mInvesthistoryitemBinding.tvpkgname.setText("Gold");
                holder.mInvesthistoryitemBinding.tvpkgname.setTextColor(Color.parseColor("#b8860b"));
                break;
            case 3:
                holder.mInvesthistoryitemBinding.tvpkgname.setText("Platinum");
                holder.mInvesthistoryitemBinding.tvpkgname.setTextColor(Color.parseColor("#00ced1"));
                break;
        }
        holder.mInvesthistoryitemBinding.tvAmount.setText("Ksh " + mInvestmentHistory.getAmount());
        holder.mInvesthistoryitemBinding.tvReturnDate.setText("return date " + mInvestmentHistory.getReturnDate());

        switch (mInvestmentHistory.getStatus()) {
            case 1:
                holder.mInvesthistoryitemBinding.tvStatus.setText("Success");
                // holder.mInvesthistoryitemBinding.tvStatus.setBackgroundColor(Color.parseColor("#297545"));
                holder.mInvesthistoryitemBinding.tvStatus.setTextColor(Color.parseColor("#297545"));
                break;
            case 2:
                holder.mInvesthistoryitemBinding.tvStatus.setText("Waiting");
                // holder.mInvesthistoryitemBinding.tvStatus.setBackgroundColor(Color.parseColor("#0dcaf0"));
                holder.mInvesthistoryitemBinding.tvStatus.setTextColor(Color.parseColor("#0dcaf0"));
                break;


        }


    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mInvestmentHistoryList != null) {
            size = mInvestmentHistoryList.size();
        }
        return size;
    }

    public InvestmentHistory.InvestementHistoryDetail getInvestmentHistory(int id) {
        return mInvestmentHistoryList.get(id);
    }

    class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        InvesthistoryitemBinding mInvesthistoryitemBinding;

        public HistoryHolder(InvesthistoryitemBinding binding) {
            super(binding.getRoot());
            this.mInvesthistoryitemBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (mInvestmentHistoryInterface != null) {
                mInvestmentHistoryInterface.onClickInvestment(v, getBindingAdapterPosition());
            }
        }
    }

    public interface InvestmentHistoryInterface {
        void onClickInvestment(View v, int position);
    }
}
