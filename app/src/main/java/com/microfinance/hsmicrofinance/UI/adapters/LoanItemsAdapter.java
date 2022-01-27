package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.LoanitemsdisplayBinding;
import com.microfinance.hsmicrofinance.Network.models.LoanPlans;

import java.util.List;

public class LoanItemsAdapter extends RecyclerView.Adapter<LoanItemsAdapter.ViewHolder> {

    private List<LoanPlans.Loan> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    LoanitemsdisplayBinding mLoanitemsdisplayBinding;
    Context mContext;

    // data is passed into the constructor
    public LoanItemsAdapter(Context context, List<LoanPlans.Loan> data,ItemClickListener itemClickListener) {
       this.mContext = context;
        this.mData = data;
        this.mClickListener = itemClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        mLoanitemsdisplayBinding = LoanitemsdisplayBinding.inflate(mInflater,parent,false);
        return new ViewHolder(mLoanitemsdisplayBinding);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoanPlans.Loan Loan = mData.get(position);
        holder.mLoanitemsdisplayBinding.tvloantype.setText(Loan.getName());
        holder.mLoanitemsdisplayBinding.tvloanamountupperlimit.setText(String.valueOf(Loan.getMaxAmount()));
        holder.mLoanitemsdisplayBinding.tvloanamountlowerlimit.setText(String.valueOf(Loan.getMinAmount()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        int size = 0;

        if(mData != null){
            size  = mData.size();

        }
        return size;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


LoanitemsdisplayBinding mLoanitemsdisplayBinding;

      public   ViewHolder(LoanitemsdisplayBinding binding) {
            super(binding.getRoot());

            this.mLoanitemsdisplayBinding = binding;
            binding.getRoot().setOnClickListener(this);
            binding.btnrequestloan.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getBindingAdapterPosition());






        }
    }

    // convenience method for getting data at click position
    public LoanPlans.Loan getItem(int id) {
        return mData.get(id);
    }



    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}


