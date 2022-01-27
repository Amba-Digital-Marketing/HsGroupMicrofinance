package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.Mpesa;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.microfinance.hsmicrofinance.Network.models.MpesaResponseBody;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.billing.AirtimeActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentMobileMoneyPaymentBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import timber.log.Timber;


public class MobileMoneyPayment extends Fragment {
    FragmentMobileMoneyPaymentBinding mBinding;
    private HomeActivityViewModel mViewModel;
    private String[] mCurrency;
    private ArrayAdapter mArrayAdapter;
    private String mPhone;
    private EdepositViewModel mEdepositViewModel;
    private NavController mNavController;
    private Dialog dialog;
    private LottieAnimationView lottieAnimationView;
    private TextView tvNo, tvYes;

    public MobileMoneyPayment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentMobileMoneyPaymentBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("M~pesa");
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.navigate(R.id.action_mobileMoneyPayment_to_basicEDeposit));

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        mCurrency = getResources().getStringArray(R.array.currency_type);
        mArrayAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, mCurrency);

        mEdepositViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mViewModel.dashBoardDataMutableLiveData.observe(getViewLifecycleOwner(), dashboardData -> {
            mPhone = dashboardData.getPhone();
            int id = Integer.parseInt(dashboardData.getId());
            Timber.tag("phone").d("number%s", mPhone);
            mEdepositViewModel.deletePending(requireActivity(), id);
        });
        mBinding.submit.setOnClickListener(v -> {
            if (mBinding.mobileMoneyDeposit.getText().toString().isEmpty()) {
                mBinding.mobileMoneyDeposit.setError("Please enter amount");
                mBinding.mobileMoneyDeposit.requestFocus();
            } else if (Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim()) < 500) {
                mBinding.mobileMoneyDeposit.setError("Cannot be less than 500");
                mBinding.mobileMoneyDeposit.requestFocus();
            } else if (Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim()) > 100000) {
                mBinding.mobileMoneyDeposit.setError("Cannot be more than 100000");
                mBinding.mobileMoneyDeposit.requestFocus();
            } else {
//                mBinding.progrebar.setVisibility(View.VISIBLE);
                doMpesadeposit(view, mBinding);


            }
        });

    }

    private void doMpesadeposit(View view, FragmentMobileMoneyPaymentBinding binding) {
        String phone = "";
        if (mPhone.length() == 13)
            phone = "0" + mPhone.substring(4, 13);
        if (mPhone.length() == 12)
            phone = "0" + mPhone.substring(3, 12);
        if (mPhone.length() == 10)
            phone = mPhone;
        comingSoon();
//        mEdepositViewModel.mpesaDeposit(getContext(), Integer.parseInt(mBinding.mobileMoneyDeposit.getText().toString().trim()), phone, view, binding);

        Timber.tag("phone").d("number  ^^" + phone);
    }

    private void comingSoon() {

        dialog = new Dialog(getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_comming_soon);

        lottieAnimationView = dialog.findViewById(R.id.lottie);
        tvNo = dialog.findViewById(R.id.tvNo);
        tvYes = dialog.findViewById(R.id.tvYes);
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

}