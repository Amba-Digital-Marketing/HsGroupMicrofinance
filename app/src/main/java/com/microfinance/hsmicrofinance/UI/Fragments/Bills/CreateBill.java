package com.microfinance.hsmicrofinance.UI.Fragments.Bills;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.microfinance.hsmicrofinance.databinding.FragmentCreateBillBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.BillsViewModel;


public class CreateBill extends Fragment {

  FragmentCreateBillBinding mFragmentCreateBillBinding;
    private NavController mNavController;
    private BillsViewModel mBillsViewModel;

    public CreateBill() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentCreateBillBinding = FragmentCreateBillBinding.inflate(inflater,container,false);


        return mFragmentCreateBillBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mFragmentCreateBillBinding.progrebar.setVisibility(View.GONE);

        mBillsViewModel = new ViewModelProvider(requireActivity()).get(BillsViewModel.class);
        mFragmentCreateBillBinding.submitBtn.setOnClickListener(v -> createBill());


    }

    private void createBill() {
        if(mFragmentCreateBillBinding.etEmail.getText().toString().isEmpty()){
            mFragmentCreateBillBinding.etEmail.setError("email required");
            mFragmentCreateBillBinding.etEmail.requestFocus();
        }else  if(mFragmentCreateBillBinding.etTitle.getText().toString().isEmpty()){
            mFragmentCreateBillBinding.etTitle.setError("title required");
            mFragmentCreateBillBinding.etTitle.requestFocus();
        }else  if(mFragmentCreateBillBinding.etDescription.getText().toString().isEmpty()){
            mFragmentCreateBillBinding.etDescription.setError("description required");
            mFragmentCreateBillBinding.etDescription.requestFocus();
        }
        else  if(mFragmentCreateBillBinding.etAmount.getText().toString().isEmpty()){
            mFragmentCreateBillBinding.etAmount.setError("amount required");
            mFragmentCreateBillBinding.etAmount.requestFocus();
        }else {
            mFragmentCreateBillBinding.progrebar.setVisibility(View.VISIBLE);
            mBillsViewModel.apiForCreatingAbill(getContext(),
                    mFragmentCreateBillBinding.etEmail.getText().toString().trim(),
                    mFragmentCreateBillBinding.etTitle.getText().toString().trim(),
                    mFragmentCreateBillBinding.etDescription.getText().toString().trim(),
                    mFragmentCreateBillBinding.etAmount.getText().toString().trim());

                    mFragmentCreateBillBinding.etEmail.setText(null);
                    mFragmentCreateBillBinding.etTitle.setText(null);
                    mFragmentCreateBillBinding.etDescription.setText(null);
                    mFragmentCreateBillBinding.etAmount.setText(null);
                    mFragmentCreateBillBinding.progrebar.setVisibility(View.GONE);
        }

    }
}