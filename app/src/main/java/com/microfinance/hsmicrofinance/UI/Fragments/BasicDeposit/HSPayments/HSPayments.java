package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.HSPayments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentHSPaymentsBinding;


public class HSPayments extends Fragment {

    FragmentHSPaymentsBinding mBinding;
    int id;

    public HSPayments(int id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHSPaymentsBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        Bundle args = new Bundle();
        args.putInt("id", id);
        mBinding.hsPaymentBtn.setOnClickListener(v -> navController.navigate(R.id.action_basicEDeposit_to_HSPay, args));
    }
}