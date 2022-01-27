package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.StatementEdepositItemBinding;
import com.microfinance.hsmicrofinance.Network.models.EdepositStatement;

import java.util.List;

public class EDepositStatementAdapter extends RecyclerView.Adapter<EDepositStatementAdapter.DepoHolder> {
    List<EdepositStatement.EDepositStatement> mEDepositStatementList;
    StatementEdepositItemBinding mStatementEdepositBinding;
    LayoutInflater mLayoutInflater;
    Context mContext;
    EDepositStatementAdapter.EdepositStatementInterface mEdepositStatementInterface;
    private EdepositStatement.EDepositStatement mEDepositStatement;

    public EDepositStatementAdapter(Context context, List<EdepositStatement.EDepositStatement>depositStatementList, EDepositStatementAdapter.EdepositStatementInterface edepositStatementInterface) {
        this.mEDepositStatementList = depositStatementList;
        this.mContext = context;
        this.mEdepositStatementInterface = edepositStatementInterface;
    }

    @NonNull
    @Override
    public EDepositStatementAdapter.DepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mStatementEdepositBinding = StatementEdepositItemBinding.inflate(mLayoutInflater,parent,false);
        return new DepoHolder(mStatementEdepositBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EDepositStatementAdapter.DepoHolder holder, int position) {
        mEDepositStatement = mEDepositStatementList.get(position);
        holder.mStatementEdepositBinding.tvtype.setText(mEDepositStatement.getType());
        holder.mStatementEdepositBinding.amount.setText("Ksh "+ mEDepositStatement.getAmount());
        switch (mEDepositStatement.getStatus()){
            case 1:
                holder.mStatementEdepositBinding.tvstatus.setText("Success");
                holder.mStatementEdepositBinding.tvstatus.setTextColor(Color.parseColor("#297545"));
                break;
            case 2:
                holder.mStatementEdepositBinding.tvstatus.setText("Rejected");
                holder.mStatementEdepositBinding.tvstatus.setTextColor(Color.parseColor("#800000"));
                break;
        }


    }

    @Override
    public int getItemCount() {
        int size =0;
        if(mEDepositStatementList != null){
            size =mEDepositStatementList.size();
        }
        return size;
    }

    class DepoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        StatementEdepositItemBinding mStatementEdepositBinding;
        public DepoHolder(@NonNull StatementEdepositItemBinding binding) {
            super(binding.getRoot());
            this.mStatementEdepositBinding=binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mEdepositStatementInterface != null) {
                mEdepositStatementInterface.onClickingEdepositItem(v, getBindingAdapterPosition());
            }
        }
    }
    public EdepositStatement.EDepositStatement getEdeposit(int id){
        return mEDepositStatementList.get(id);
    }
    public interface EdepositStatementInterface{
        void onClickingEdepositItem(View view, int position);
    }
}
