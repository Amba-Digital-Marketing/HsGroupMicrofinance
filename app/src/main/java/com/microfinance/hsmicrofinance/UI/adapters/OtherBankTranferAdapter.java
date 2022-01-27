package com.microfinance.hsmicrofinance.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

;
import com.microfinance.hsmicrofinance.databinding.OtherBankTransferItemBinding;
import com.microfinance.hsmicrofinance.Network.entity.BankTransferHistory;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.Status;

import java.io.IOException;
import java.util.List;

public class OtherBankTranferAdapter extends RecyclerView.Adapter<OtherBankTranferAdapter.OtherTransfer> {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<BankTransferHistory.OtherBankTransaction>  mOtherBankTransfers;
    OtherBankinterface mOtherBankinterface;
    List<Banks.BankDetail> mBankslist;
    OtherBankTransferItemBinding mOtherBankTransferItemBinding;
    private BankTransferHistory.OtherBankTransaction mOtherBankTransfer;

    public OtherBankTranferAdapter(Context context, BankTransferHistory otherBankTransfers,List<Banks.BankDetail> mBankslist,OtherBankinterface otherBankinterface) {
        this.mContext=context;
        this.mOtherBankinterface= otherBankinterface;
        this.mOtherBankTransfers=  otherBankTransfers.getOtherBankTransactions();
        this.mBankslist = mBankslist;
    }

    @NonNull
    @Override
    public OtherTransfer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        mOtherBankTransferItemBinding = OtherBankTransferItemBinding.inflate(mLayoutInflater,parent,false);
        return new OtherTransfer(mOtherBankTransferItemBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull OtherTransfer holder, int position) {
        mOtherBankTransfer = mOtherBankTransfers.get(position);
        String bankName = "";
        String date = mOtherBankTransfer.getUpdatedAt();
        Status status = checkStatus(Integer.parseInt(mOtherBankTransfer.getStatus()));
        bankName = getBankName( mBankslist, Integer.parseInt(mOtherBankTransfer.getBankId()));
        holder.mOtherBankTransferItemBinding.tvbankname.setText(bankName);
        holder.mOtherBankTransferItemBinding.tvAmountView.setText("Ksh "+concatDouble(Double.parseDouble(mOtherBankTransfer.getTransaction().getAmount())));
        holder.mOtherBankTransferItemBinding.status.setText(status.getStatus());
        holder.mOtherBankTransferItemBinding.status.setTextColor(Color.parseColor(status.getColor()));
        holder.mOtherBankTransferItemBinding.tvdateview.setText(date.substring(0,10) + " " + date.substring(12,16));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String concatDouble(double mydouble){


        DecimalFormat df = new DecimalFormat("#.00");
        String finaldouble = df.format(mydouble);
        return finaldouble;
    }

    @Override
    public int getItemCount() {
        return mOtherBankTransfers.size();
    }
  public class OtherTransfer extends RecyclerView.ViewHolder implements View.OnClickListener {
    OtherBankTransferItemBinding mOtherBankTransferItemBinding;
        public OtherTransfer(@NonNull OtherBankTransferItemBinding binding) {
            super(binding.getRoot());
            this.mOtherBankTransferItemBinding = binding;
            mOtherBankTransferItemBinding.cvotherbanktransfer.setOnClickListener(this);
            mOtherBankTransferItemBinding.tvbank.setOnClickListener(this);
            mOtherBankTransferItemBinding.tvAmountView.setOnClickListener(this);
            mOtherBankTransferItemBinding.status.setOnClickListener(this);
            mOtherBankTransferItemBinding.tvdate.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOtherBankinterface !=null){
                try {
                    mOtherBankinterface.onClickOtherBank(v,getBindingAdapterPosition());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public BankTransferHistory.OtherBankTransaction getOthertranfer(int id){
        return mOtherBankTransfers.get(id);
    }
    public interface OtherBankinterface{
        void onClickOtherBank(View view, int position) throws IOException;
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
