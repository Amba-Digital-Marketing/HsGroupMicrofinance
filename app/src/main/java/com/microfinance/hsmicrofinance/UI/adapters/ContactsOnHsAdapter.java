package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.Network.models.HSContacts;
import com.microfinance.hsmicrofinance.databinding.ContactItemBinding;

import java.util.List;

public class ContactsOnHsAdapter extends RecyclerView.Adapter<ContactsOnHsAdapter.OnHsContact> {
    ContactItemBinding mContactItemBinding;
    List<HSContacts> mUserDetailList;
    LayoutInflater mLayoutInflater;
    Context context;
    onHsInterface onHsInterface;

    public ContactsOnHsAdapter(Context context, List<HSContacts> userDetails, onHsInterface onHsInterface) {
        this.mUserDetailList = userDetails;
        this.context=context;
        this.onHsInterface=onHsInterface;
    }

    @NonNull
    @Override
    public OnHsContact onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mContactItemBinding= ContactItemBinding.inflate(mLayoutInflater,parent,false);
        return new OnHsContact(mContactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OnHsContact holder, int position) {
        HSContacts userDetail = mUserDetailList.get(position);
       holder.mContactItemBinding.tvPhone.setText(userDetail.getPhoneno());
        holder.mContactItemBinding.tvName.setText(userDetail.getName());
        holder.mContactItemBinding.tvStatus.setText("On Hs");

    }

    @Override
    public int getItemCount() {
        int size =0;
        if(mUserDetailList !=null){
            size = mUserDetailList.size();
        }
        return size;
    }
public HSContacts getUserDetail(int id){
        return mUserDetailList.get(id);
}
    class OnHsContact extends RecyclerView.ViewHolder implements View.OnClickListener {
        ContactItemBinding mContactItemBinding;
        public OnHsContact(ContactItemBinding mContactItemBinding) {
            super(mContactItemBinding.getRoot());
            this.mContactItemBinding=mContactItemBinding;
            mContactItemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHsInterface.onclickContactOnHs(view,getBindingAdapterPosition());
        }
    }
    public  interface onHsInterface{
        void onclickContactOnHs(View v, int position);
    }
}
