package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.databinding.BasicSupportItemsBinding;
import com.microfinance.hsmicrofinance.Network.entity.Status;
import com.microfinance.hsmicrofinance.Network.entity.SupportHistory;

import java.util.List;


public class SupportItemsAdapter extends RecyclerView.Adapter<SupportItemsAdapter.SupportItemsViewHolder> {
    BasicSupportItemsBinding mBasicSupportItemsBinding;
    LayoutInflater mLayoutInflater;
    private SupportItemsAdapter.SupportItemsClickListener mSupportItemsClickListener;
    List<SupportHistory.AllSupportRequestsBasedOnID> mSupportItemsList;
    private SupportHistory.AllSupportRequestsBasedOnID mSupportItem;
    Context mContext;

    public SupportItemsAdapter(Context context, SupportHistory supportHistory, SupportItemsAdapter.SupportItemsClickListener supportItemsClickListener) {
        this.mContext = context;
        this.mSupportItemsList = supportHistory.getAllSupportRequestsBasedOnID();
        this.mSupportItemsClickListener = supportItemsClickListener;
    }

    @NonNull
    @Override
    public SupportItemsAdapter.SupportItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mBasicSupportItemsBinding = BasicSupportItemsBinding.inflate(mLayoutInflater, parent, false);
        return new SupportItemsAdapter.SupportItemsViewHolder(mBasicSupportItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SupportItemsAdapter.SupportItemsViewHolder holder, int position) {
        mSupportItem = mSupportItemsList.get(position);
        String  date = mSupportItem.getCreatedAt();
        Status status = checkStatus(mSupportItem.getStatus());
        holder.mBasicSupportItemsBinding.tvtitle.setText(mSupportItem.getTitle());
        holder.mBasicSupportItemsBinding.tvstatus.setText(status.getStatus());
        holder.mBasicSupportItemsBinding.tvstatus.setTextColor(Color.parseColor(status.getColor()));
        holder.mBasicSupportItemsBinding.tvdate.setText( date.substring(0,10) + " " + date.substring(12,16));

    }


    @Override
    public int getItemCount() {
        int size = 0;
        if(mSupportItemsList != null){
            size = mSupportItemsList.size();
        }
        return size;
    }

    class SupportItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        BasicSupportItemsBinding mBasicSupportItemsBinding;

        SupportItemsViewHolder(BasicSupportItemsBinding binding) {
            super(binding.getRoot());
            this.mBasicSupportItemsBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mSupportItemsClickListener != null){
                mSupportItemsClickListener.onSupportItemsItemClick(v,getBindingAdapterPosition());
            }
        }
    }
    public SupportHistory.AllSupportRequestsBasedOnID  getmSupportItems(int id){
        return mSupportItemsList.get(id);
    }
    public void setSupportItemsClickListener(SupportItemsAdapter.SupportItemsClickListener itemClickListener){
        this.mSupportItemsClickListener = itemClickListener;
    }
    public interface SupportItemsClickListener{
        void onSupportItemsItemClick(View view, int position);
    }

// check and update support  status
    private Status  checkStatus(int StatusCode){
        Status status;
        switch (StatusCode){
            case 0: status = new Status("Inactive","#E0223C");
                break;
            case 1:  status = new Status("Success","297545");
                break;
            case 2: status = new Status("Pending","#0dcaf0");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StatusCode);
        }
        return  status;
    }

}