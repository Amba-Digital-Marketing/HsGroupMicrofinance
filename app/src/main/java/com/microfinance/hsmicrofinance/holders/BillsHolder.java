package com.microfinance.hsmicrofinance.holders;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.microfinance.hsmicrofinance.databinding.ItemBillBinding;


public class BillsHolder extends RecyclerView.ViewHolder {
    private ItemBillBinding view;
    public final TextView name;
    public final ImageView image;
    public final CardView cardView;

    public BillsHolder(ItemBillBinding view) {
        super(view.getRoot());
        this.view = view;
        name = view.name;
        image = view.image;
        cardView = view.cardView;
    }
}
