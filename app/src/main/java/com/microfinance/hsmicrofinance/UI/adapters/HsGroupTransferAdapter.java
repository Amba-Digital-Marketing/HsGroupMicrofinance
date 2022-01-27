package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.StatementGroupItemBinding;
import com.microfinance.hsmicrofinance.Network.entity.HSgroupTransfer;

import java.util.List;

public class HsGroupTransferAdapter extends RecyclerView.Adapter<HsGroupTransferAdapter.GroupHolder> {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<HSgroupTransfer>mHSgroupTransfers;
    HSGroupinterface mHSGroupinterface;
    StatementGroupItemBinding mStatementGroupItemBinding;
    private HSgroupTransfer mHSgroupTransfer;

    public HsGroupTransferAdapter(Context context,List<HSgroupTransfer>hSgroupTransfers, HSGroupinterface hsGroupinterface) {
        this.mContext = context;
        this.mHSgroupTransfers = hSgroupTransfers;
        this.mHSGroupinterface = hsGroupinterface;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mStatementGroupItemBinding = StatementGroupItemBinding.inflate(mLayoutInflater,parent,false);
        return new GroupHolder(mStatementGroupItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {

        mHSgroupTransfer = mHSgroupTransfers.get(position);
       // holder.mStatementGroupItemBinding.accNo.setText(String.valueOf(mHSgroupTransfer.getAccNo()));
       // holder.mStatementGroupItemBinding.amount.setText(String.valueOf(mHSgroupTransfer.getAmount()));
       // holder.mStatementGroupItemBinding.date.setText(mHSgroupTransfer.getDate());
    }

    @Override
    public int getItemCount() {
        return mHSgroupTransfers.size();
    }

    class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
StatementGroupItemBinding mStatementGroupItemBinding;
        public GroupHolder(@NonNull StatementGroupItemBinding binding) {
            super(binding.getRoot());
            this.mStatementGroupItemBinding= binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mHSGroupinterface != null){
                mHSGroupinterface.onClickHSTransfer(v,getBindingAdapterPosition());
            }
        }
    }
    public HSgroupTransfer getGroupTrasfer(int id){
        return mHSgroupTransfers.get(id);
    }
    public interface HSGroupinterface{
        void onClickHSTransfer(View view,int position);
    }
}
