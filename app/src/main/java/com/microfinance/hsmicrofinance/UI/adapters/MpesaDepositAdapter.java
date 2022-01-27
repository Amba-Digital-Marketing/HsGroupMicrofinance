package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.databinding.MpesaHistoryItemBinding;
import com.microfinance.hsmicrofinance.Network.models.MpesaDepositCallBack;

import java.util.List;

public class MpesaDepositAdapter extends  RecyclerView.Adapter<MpesaDepositAdapter.MpesaHolder> {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<MpesaDepositCallBack.MpesaPaymentDetail>mMpesaPaymentDetails;
    MpesaHistoryItemBinding mBinding;
    private MpesaDepositCallBack.MpesaPaymentDetail mDetail;

    public MpesaDepositAdapter(Context context, List<MpesaDepositCallBack.MpesaPaymentDetail>list) {
        this.mContext=context;
        this.mMpesaPaymentDetails= list;
    }

    @NonNull
    @Override
    public MpesaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mBinding = MpesaHistoryItemBinding.inflate(mLayoutInflater,parent,false);
        return new MpesaHolder(mBinding) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MpesaHolder holder, int position) {
        mDetail = mMpesaPaymentDetails.get(position);
        holder.mBinding.tvAmount.setText(mDetail.getAmount());
        holder.mBinding.tvDate.setText(mDetail.getTransactionDate().substring(0,4)+"-"+mDetail.getTransactionDate().substring(4,6)+"-"+mDetail.getTransactionDate().substring(6-8));
        holder.mBinding.tvReciept.setText(mDetail.getMpesaReceiptNumber());
        holder.mBinding.tvPhone.setText(mDetail.getPhoneNumber());

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
MpesaHistoryItemBinding mBinding;
        public MpesaHolder(MpesaHistoryItemBinding binding) {
            super(binding.getRoot());
            this.mBinding= binding;
        }
    }
}
