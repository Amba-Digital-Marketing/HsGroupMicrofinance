package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.databinding.MpesaStatusItemBinding;
import com.microfinance.hsmicrofinance.Network.models.MpesaStatus;

import java.util.List;

public class MpesaPendingAdapter extends RecyclerView.Adapter<MpesaPendingAdapter.MpesaHolder> {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<MpesaStatus.MpesaDetail> mMpesaPaymentDetails;
    MpesaStatusItemBinding mBinding;
    private MpesaStatus.MpesaDetail mDetail;

    public MpesaPendingAdapter(Context context, List<MpesaStatus.MpesaDetail>list) {
        this.mContext=context;
        this.mMpesaPaymentDetails= list;
    }

    @NonNull
    @Override
    public MpesaPendingAdapter.MpesaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mBinding = MpesaStatusItemBinding.inflate(mLayoutInflater,parent,false);
        return new MpesaPendingAdapter.MpesaHolder(mBinding) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MpesaPendingAdapter.MpesaHolder holder, int position) {
        mDetail = mMpesaPaymentDetails.get(position);
        holder.mBinding.tvTrxId.setText(mDetail.getTransactionId());
        holder.mBinding.tvDate.setText(mDetail.getCreatedAt().substring(0,10));
        holder.mBinding.tvStatus.setText("Pending");


    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(mMpesaPaymentDetails != null){
            size= mMpesaPaymentDetails.size();
        }
        return  size;

    }

    class MpesaHolder extends RecyclerView.ViewHolder{
        MpesaStatusItemBinding mBinding;
        public MpesaHolder(MpesaStatusItemBinding binding) {
            super(binding.getRoot());
            this.mBinding= binding;
        }
    }
}
