package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.InvestmentPackageItemBinding;
import com.microfinance.hsmicrofinance.Network.models.InvestPlans;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InvestmentPackagesAdapter extends RecyclerView.Adapter<InvestmentPackagesAdapter.InvestHolder> {
    InvestmentPackageItemBinding mInvestmentPackageItemBinding;
    LayoutInflater mLayoutInflater;
    Context mContext;
    List<InvestPlans.InvestmentPackage>mInvestmentPackages;
    InvestPackageInterface mInvestPackageInterface;
    private InvestPlans.InvestmentPackage mInvPkg;

    public InvestmentPackagesAdapter(Context context, List<InvestPlans.InvestmentPackage>investmentPackages,InvestPackageInterface investPackageInterface) {
        this.mContext= context;
        this.mInvestmentPackages= investmentPackages;
        this.mInvestPackageInterface = investPackageInterface;
    }

    @NonNull
    @Override
    public InvestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mInvestmentPackageItemBinding = InvestmentPackageItemBinding.inflate(mLayoutInflater,parent,false);
        return new InvestHolder(mInvestmentPackageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestHolder holder, int position) {
        mInvPkg = mInvestmentPackages.get(position);
        holder.mInvestmentPackageItemBinding.txtPackageName.setText(mInvPkg.getTitle());
        if(mInvPkg.getTitle().equals("Standard")){
            Picasso.get().load(R.drawable.standard).into(holder.mInvestmentPackageItemBinding.ivInvestIcon);
        }else if(mInvPkg.getTitle().equals("Gold")){
            Picasso.get().load(R.drawable.gold_ingots).into(holder.mInvestmentPackageItemBinding.ivInvestIcon);
        }else if(mInvPkg.getTitle().equals("Platinum")){
            Picasso.get().load(R.drawable.platinum).into(holder.mInvestmentPackageItemBinding.ivInvestIcon);
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(mInvestmentPackages != null){
            size=mInvestmentPackages.size();
        }
        return size;
    }

    public class InvestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        InvestmentPackageItemBinding mInvestmentPackageItemBinding;
        public InvestHolder(@NonNull InvestmentPackageItemBinding binding) {
            super(binding.getRoot());
            this.mInvestmentPackageItemBinding = binding;
            mInvestmentPackageItemBinding.ivDeposit.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mInvestPackageInterface != null){
                mInvestPackageInterface.onClickInvestNow(v, getBindingAdapterPosition());
            }

        }
    }
    public InvestPlans.InvestmentPackage getPackage(int id){
        return mInvestmentPackages.get(id);
    }
    public interface InvestPackageInterface{
        void onClickInvestNow(View view, int position);
    }
}
