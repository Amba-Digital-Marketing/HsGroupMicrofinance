package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.microfinance.hsmicrofinance.databinding.SupportReceivedmessagesItemsBinding;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.SingleSupportItem;
import com.microfinance.hsmicrofinance.Network.entity.Status;

import java.io.IOException;
import java.util.List;

public class SingleSupportItemAdapter extends RecyclerView.Adapter<SingleSupportItemAdapter.SingleSupportItemHolder> {
    Context mContext;
    LayoutInflater mLayoutInflater;
    SingleSupportItem singleSupportItem;
    SingleSupportItem.SingleSupportItemBasedOnID supportItemBasedOnID;
    List<SingleSupportItem.Metum>  metumsList;
    SingleSupportItem.Metum metum;
    SingleSupportItem.User supportUser;
    SupportReceivedmessagesItemsBinding supportReceivedmessagesItemsBinding;
    SupportIteminterface supportIteminterface;
    String TAG = "SingleSupport";


    public SingleSupportItemAdapter(Context context, SingleSupportItem singleSupportItem,SupportIteminterface supportIteminterface) {
        this.mContext=context;
        this.supportIteminterface = supportIteminterface;
        this.singleSupportItem = singleSupportItem;
        this.supportItemBasedOnID =singleSupportItem.getSingleSupportItemBasedOnID();
        this.metumsList= singleSupportItem.getSingleSupportItemBasedOnID().getMeta();
        this.supportUser = singleSupportItem.getSingleSupportItemBasedOnID().getUser();
        Log.d(TAG, "SingleSupportItemAdapter: " +singleSupportItem.toString());

    }

    @NonNull
    @Override
    public SingleSupportItemAdapter.SingleSupportItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        supportReceivedmessagesItemsBinding = SupportReceivedmessagesItemsBinding.inflate(mLayoutInflater,parent,false);
        return new SingleSupportItemAdapter.SingleSupportItemHolder(supportReceivedmessagesItemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleSupportItemAdapter.SingleSupportItemHolder holder, int position) {


       this.metum = metumsList.get(position);

        if(metum.getType() == 1){
            holder.supportReceivedmessagesItemsBinding.tvsentmessage.setText(metum.getComment());
           holder.supportReceivedmessagesItemsBinding.tvreceivedmessage.setVisibility(View.INVISIBLE);
           holder.supportReceivedmessagesItemsBinding.tvadmin.setVisibility(View.INVISIBLE);
           // holder.supportReceivedmessagesItemsBinding.ivmessageowner.setVisibility(View.INVISIBLE);
            holder.supportReceivedmessagesItemsBinding.tvsentTextOwner.setText(supportItemBasedOnID.getUser().getName());

        }else if(metum.getType() == 0){
           holder.supportReceivedmessagesItemsBinding.tvreceivedmessage.setText(metum.getComment());
            holder.supportReceivedmessagesItemsBinding.tvadmin.setText(String.valueOf("Admin"));
            holder.supportReceivedmessagesItemsBinding.tvsentmessage.setVisibility(View.INVISIBLE);
            holder.supportReceivedmessagesItemsBinding.tvsentTextOwner.setVisibility(View.INVISIBLE);
          //  holder.supportReceivedmessagesItemsBinding.ivsentmessageowner.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(metumsList != null){
            size = metumsList.size();
        }
        return size;
    }

    public class SingleSupportItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SupportReceivedmessagesItemsBinding supportReceivedmessagesItemsBinding;
        public SingleSupportItemHolder(@NonNull SupportReceivedmessagesItemsBinding binding) {
            super(binding.getRoot());
            this.supportReceivedmessagesItemsBinding = binding;
            supportReceivedmessagesItemsBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(supportIteminterface !=null){
                try {
                    supportIteminterface.onClickSupportItem(v,getBindingAdapterPosition());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public SingleSupportItem.Metum getMetum(int id){
        return metumsList.get(id);
    }
    public interface SupportIteminterface{
        void onClickSupportItem(View view, int position) throws IOException;
    }

    private Status checkStatus(int StatusCode){
        Status status;
        switch (StatusCode){
            case 0: status = new Status("Inactive","#E0223C");
                break;
            case 1:  status = new Status("Success","#297545");
                break;
            case 2: status = new Status("Pending","#0dcaf0");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StatusCode);
        }
        return  status;
    }

    private String getBankName(List<Banks.BankDetail> bankslist, int bankid){
        String bankname = "";
        for (Banks.BankDetail bank:
                bankslist) {
            if (bank.getId().equals(String.valueOf(bankid))) {
                bankname = bank.getName();
            }

        }
        return  bankname;
    }

}
