package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.ItemBillBinding;
import com.microfinance.hsmicrofinance.holders.BillsHolder;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;

import java.util.ArrayList;
import java.util.List;

public class BillsAdapter extends RecyclerView.Adapter<BillsHolder> {
    private ItemBillBinding itemBillBinding;
    private LayoutInflater mLayoutInflater;
    public final ArrayList<BillPayment> allbills;
    private Context context;
    List<CardView> cardViewList = new ArrayList<>();
    int selectedIndex = -1;
    private boolean isSelectable = false;

    public BillsAdapter(Context context, ArrayList<BillPayment> allbills, boolean isSelectable) {
        this.context = context;
        this.allbills = allbills;
        this.isSelectable = isSelectable;
    }

    public BillPayment getItem(int position) {
        return allbills.get(position);
    }

    @NonNull
    @Override
    public BillsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        itemBillBinding = ItemBillBinding.inflate(mLayoutInflater, parent, false);
        return new BillsHolder(itemBillBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BillsHolder holder, final int position) {
        BillPayment bill = allbills.get(position);
        try {
            holder.name.setText(bill.biller_name);
            Glide.with(context)
                    .load(bill.url)
                    .override(100, 200)
                    .fitCenter() // scale to fit entire image within ImageView
                    .error(R.drawable.load)
                    .into(holder.image);
            cardViewList.add(holder.cardView);

            if (isSelectable) {
                if (selectedIndex == position) {
                    holder.itemView.setBackgroundResource(R.drawable.selected);
                } else {
                    holder.itemView.setBackgroundResource(R.color.white);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (allbills != null) {
            return allbills.size();
        } else {
            return 0;
        }
    }

    public void changeBackground(int position) {
        selectedIndex = position;
        notifyDataSetChanged();
    }
}
