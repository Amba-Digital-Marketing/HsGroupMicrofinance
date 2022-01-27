package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.BillHistoryItemBinding;
import com.microfinance.hsmicrofinance.Network.models.BillStatement;

import java.util.List;

public class BillHistoryAdapter extends RecyclerView.Adapter<BillHistoryAdapter.HistoryHolder> {
    BillHistoryItemBinding mBillHistoryItemBinding;
    LayoutInflater mLayoutInflater;
    Context mContext;
    BillHistoryInterface mBillHistoryInterface;
    List<BillStatement.BillStatementDetail>mBillStatementDetailList;
    private BillStatement.BillStatementDetail mBillStatementDetail;

    public BillHistoryAdapter(Context context, List<BillStatement.BillStatementDetail>billStatementDetailList,BillHistoryInterface billHistoryInterface) {
        this.mContext = context;
        this.mBillStatementDetailList= billStatementDetailList;
        this.mBillHistoryInterface = billHistoryInterface;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mBillHistoryItemBinding = BillHistoryItemBinding.inflate(mLayoutInflater,parent,false);
        return new HistoryHolder(mBillHistoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        mBillStatementDetail = mBillStatementDetailList.get(position);
        holder.mBillHistoryItemBinding.tvReciever.setText("To " +mBillStatementDetail.getReceiver().getName());
        holder.mBillHistoryItemBinding.tvtitle.setText(mBillStatementDetail.getTitle());
        holder.mBillHistoryItemBinding.tvAmount.setText("Ksh "+ mBillStatementDetail.getAmount());
        holder.mBillHistoryItemBinding.tvSender.setText("Sent by "+mBillStatementDetail.getSender().getName());
        switch (mBillStatementDetail.getStatus()){
            case 1:
                holder.mBillHistoryItemBinding.tvstatus.setText("Success");
                holder.mBillHistoryItemBinding.tvstatus.setTextColor(Color.parseColor("#297545"));
                break;
            case 2:
                holder.mBillHistoryItemBinding.tvstatus.setText("Pending");
                holder.mBillHistoryItemBinding.tvstatus.setTextColor(Color.parseColor("#800000"));
                break;

        }

    }

    @Override
    public int getItemCount() {
        int sixe = 0;
        if(mBillStatementDetailList != null){
            sixe = mBillStatementDetailList.size();
        }
        return sixe;
    }
    public BillStatement.BillStatementDetail getBillStatementDetail(int id){
        return mBillStatementDetailList.get(id);
    }

    public class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
BillHistoryItemBinding mBillHistoryItemBinding;
        public HistoryHolder(@NonNull BillHistoryItemBinding binding) {
            super(binding.getRoot());
            this.mBillHistoryItemBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mBillHistoryInterface != null)
                mBillHistoryInterface.onclickHistory(v,getBindingAdapterPosition());
        }
    }

    public interface BillHistoryInterface{
        void onclickHistory(View view, int position);
    }
}
