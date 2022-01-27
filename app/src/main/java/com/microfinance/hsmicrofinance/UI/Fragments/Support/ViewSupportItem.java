package com.microfinance.hsmicrofinance.UI.Fragments.Support;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.microfinance.hsmicrofinance.UI.adapters.SingleSupportItemAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentViewSupportItemBinding;
import com.microfinance.hsmicrofinance.Network.entity.SingleSupportItem;
import com.microfinance.hsmicrofinance.Network.entity.Status;
import com.microfinance.hsmicrofinance.UI.viewmodels.BasicSupportViewModel;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ViewSupportItem extends Fragment implements SingleSupportItemAdapter.SupportIteminterface {
    FragmentViewSupportItemBinding mFragmentViewSupportItemBinding;
  SingleSupportItemAdapter  supportitemadapter;
  BasicSupportViewModel basicSupportViewModel;
  SingleSupportItem singleSupportItem;
    private int supportId;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentViewSupportItemBinding = FragmentViewSupportItemBinding.inflate(inflater,container,false);

        return mFragmentViewSupportItemBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.supportId = getArguments().getInt("SUPPORTID");

        basicSupportViewModel = new  ViewModelProvider(this).get(BasicSupportViewModel.class);


        basicSupportViewModel.getSingleSupportItem( getContext(), supportId);

        // observing basic support view model for supportItem
        observeBasicSupportViewModel();



        mFragmentViewSupportItemBinding.btnsubmitSupport.setOnClickListener(v -> {
            addSupportComment();

        });

    }

    private SingleSupportItem observeBasicSupportViewModel() {
        basicSupportViewModel.mSingleSupportItemLiveData.observe(getViewLifecycleOwner(),singleSupportItem -> {
            if(singleSupportItem !=  null){
                this.singleSupportItem = singleSupportItem;
                Status status =checkStatus(singleSupportItem.getSingleSupportItemBasedOnID().getStatus());
                if (status.getStatus().equals("Inactive")) {
                    mFragmentViewSupportItemBinding.btnsubmitSupport.setVisibility(View.INVISIBLE);
                    mFragmentViewSupportItemBinding.tilcomment.setVisibility(View.INVISIBLE);
                }

               mFragmentViewSupportItemBinding.tvticketnoview.setText(String.valueOf(singleSupportItem.getSingleSupportItemBasedOnID().getTicketNo()));
              mFragmentViewSupportItemBinding.tvStatusview.setText(status.getStatus());
            mFragmentViewSupportItemBinding.tvStatusview.setTextColor(Color.parseColor(status.getColor()));
            mFragmentViewSupportItemBinding.tvtitleview.setText(String.valueOf(singleSupportItem.getSingleSupportItemBasedOnID().getTitle()));
                supportitemadapter = new SingleSupportItemAdapter(getContext(),singleSupportItem,this);
                mFragmentViewSupportItemBinding.rvsupportmessages.setAdapter(supportitemadapter);

                try{
                    mFragmentViewSupportItemBinding.rvsupportmessages.smoothScrollToPosition(supportitemadapter.getItemCount()-1);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
            }
        });
        return  singleSupportItem;
    }

    private void addSupportComment() {
       String supportcomment = mFragmentViewSupportItemBinding.etsupportcomment.getText().toString().trim();
       if(!supportcomment.equals("") && supportcomment != null){
           int id = singleSupportItem.getSingleSupportItemBasedOnID().getMeta().get(0).getSupportId();
           basicSupportViewModel.addSupportComment(getContext(),id,supportcomment);

           // observing for newchanges
           observeBasicSupportViewModelResponseCode();
       }
    }

    private void observeBasicSupportViewModelResponseCode(){
        basicSupportViewModel.mAddCommentCodeLiveData.observe(getViewLifecycleOwner(),code -> {
            if(code !=  null && code == 200){
                basicSupportViewModel.getSingleSupportItem( getContext(),supportId);
                observeBasicSupportViewModel();
                mFragmentViewSupportItemBinding.etsupportcomment.setText("");
            }else{
                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Sorry!!")
                        .setContentText("We could not process your request now\n" +
                                "check your internet connection and try again")
                        .setNeutralButtonTextColor(Color.parseColor("#297545"))
                        .show();
            }
        });

    }


    @Override
    public void onClickSupportItem(View view, int position) throws IOException {

    }

    // check and update support  status
    private Status checkStatus(int StatusCode){
        Status status;
        switch (StatusCode){
            case 0: status = new Status("Inactive","#E0223C");
                break;
            case 1:  status = new Status("Success","297545");
                break;
            case 2: status = new Status("Pending","#0dcaf0");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StatusCode);
        }
        return  status;
    }
}