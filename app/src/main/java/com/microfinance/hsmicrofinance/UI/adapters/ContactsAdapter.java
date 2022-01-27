package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.ContactItemBinding;
import com.microfinance.hsmicrofinance.Network.models.PhoneNumbers;

import java.util.List;

public class ContactsAdapter extends  RecyclerView.Adapter<ContactsAdapter.Contact> {
    ContactItemBinding mContactItemBinding;
    Context mContext;
    List<PhoneNumbers>mPhoneNumbersList;
    LayoutInflater mLayoutInflater;
    ContactInterface mContactInterface;
    private PhoneNumbers mPhoneNumbers;

    public ContactsAdapter(Context context, List<PhoneNumbers> phoneNumbersList, ContactInterface contactInterface) {
        this.mContext = context;
        this.mPhoneNumbersList = phoneNumbersList;
        this.mContactInterface = contactInterface;
    }

    @NonNull
    @Override
    public Contact onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mContactItemBinding= ContactItemBinding.inflate(mLayoutInflater,parent,false);
        return new Contact(mContactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Contact holder, int position) {
        mPhoneNumbers = mPhoneNumbersList.get(position);
        holder.mContactItemBinding.tvName.setText(mPhoneNumbers.getName());
        holder.mContactItemBinding.tvPhone.setText(mPhoneNumbers.getPhone());
        holder.mContactItemBinding.tvStatus.setText("Invite");

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(mPhoneNumbersList != null){
            size = mPhoneNumbersList.size();
        }
        return size;
    }
    public PhoneNumbers getPhoneNumbers(int id){
        return mPhoneNumbersList.get(id);
    }
    class Contact extends RecyclerView.ViewHolder implements View.OnClickListener {
ContactItemBinding mContactItemBinding;
        public Contact(ContactItemBinding binding) {
            super(binding.getRoot());
            this.mContactItemBinding= binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mContactInterface.onClickContact(v, getBindingAdapterPosition());
        }
    }
    public interface ContactInterface{
        void onClickContact(View view, int position);
    }
}
