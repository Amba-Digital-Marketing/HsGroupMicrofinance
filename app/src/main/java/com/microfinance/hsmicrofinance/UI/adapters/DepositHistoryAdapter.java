package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.DepositHistoryItemsBinding;
import com.microfinance.hsmicrofinance.Network.models.DepositHistory;

import java.util.List;

public class DepositHistoryAdapter extends RecyclerView.Adapter<DepositHistoryAdapter.ViewHolder> {

private List<DepositHistory.Deposit> mData;
private LayoutInflater mInflater;
DepositHistoryItemsBinding mDepositHistoryItemsBinding;
private ItemClickListener mClickListener;
        Context mContext;
    private DepositHistory.Deposit mDepositsHistory;

    // data is passed into the constructor
public DepositHistoryAdapter(Context context, List<DepositHistory.Deposit> data, ItemClickListener itemClickListener) {
        this.mContext = context;
        this.mData = data;
        this.mClickListener = itemClickListener;
        }

// inflates the row layout from xml when needed
@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        mDepositHistoryItemsBinding = DepositHistoryItemsBinding.inflate(mInflater,parent,false);
        return new ViewHolder(mDepositHistoryItemsBinding);
        }

// binds the data to the TextView in each row
@Override
public void onBindViewHolder(ViewHolder holder, int position) {
    mDepositsHistory = mData.get(position);
    String date = mDepositsHistory.getCreatedAt();
    holder.mDepositHistoryItemsBinding.tvGateway.setText(mDepositsHistory.getGetway().getName());
    holder.mDepositHistoryItemsBinding.tvAmount.setText("Ksh "+ mDepositsHistory.getAmount());
    holder.mDepositHistoryItemsBinding.tvdate.setText("on "+ date.substring(0,10));

    switch (mDepositsHistory.getStatus()){
        case  0:
            holder.mDepositHistoryItemsBinding.tvStatus.setText("Rejected");
            holder.mDepositHistoryItemsBinding.tvStatus.setTextColor(Color.parseColor("#800000"));
            break;
        case  1:
            holder.mDepositHistoryItemsBinding.tvStatus.setText("Success");
            holder.mDepositHistoryItemsBinding.tvStatus.setTextColor(Color.parseColor("#297545"));
            break;
        case  2:
            holder.mDepositHistoryItemsBinding.tvStatus.setText("Pending");
            holder.mDepositHistoryItemsBinding.tvStatus.setTextColor(Color.parseColor("#800000"));
            break;
    }


        }

// total number of rows
@Override
public int getItemCount() {
    int size = 0;
    if(mData != null){
        size = mData.size();
    }
       return size;
        }


// stores and recycles views as they are scrolled off screen
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

DepositHistoryItemsBinding mDepositHistoryItemsBinding;

    ViewHolder(DepositHistoryItemsBinding binding) {
        super(binding.getRoot());
        this.mDepositHistoryItemsBinding = binding;
        binding.getRoot().setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) mClickListener.onItemClick(view, getBindingAdapterPosition());
    }
}

    // convenience method for getting data at click position
    public DepositHistory.Deposit getItem(int id) {
        return mData.get(id);
    }

// parent activity will implement this method to respond to click events
public interface ItemClickListener {
    void onItemClick(View view, int position);
}
}


