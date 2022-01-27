package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.ExternalBankTransfer;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.microfinance.hsmicrofinance.Network.entity.BankTransferHistory;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.Countries;
import com.microfinance.hsmicrofinance.Network.entity.Currencies;
import com.microfinance.hsmicrofinance.Network.entity.Status;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentSingleOtherBankTransactionBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class SingleOtherBankTransaction extends Fragment {
    FragmentSingleOtherBankTransactionBinding mBinding;
    private NavController mNavController;
    private String currency;

    public SingleOtherBankTransaction() {
        // Required empty public constructor
    }

    private BankTransferHistory.OtherBankTransaction bankTransaction;
    private JSONObject result = null;
    private HomeActivityViewModel homeActivityViewModel;
    private List<Banks.BankDetail> banks;
    private List<Countries.Currency> currencyDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSingleOtherBankTransactionBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Bank Transfer");
        mBinding.toolbar.setNavigationOnClickListener(v -> mNavController.navigate(R.id.action_singleOtherBankTransaction_to_statementBankTransfer));
        homeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
     //   homeActivityViewModel.getCountriesDetails(getContext());
        loadData();
        return mBinding.getRoot();
    }


    private void loadData() {
        homeActivityViewModel.getUserData();
    }

    private Status checkStatus(int StatusCode) {
        Status status;
        switch (StatusCode) {
            case 0:
                status = new Status("Inactive", "#E0223C");
                break;
            case 1:
                status = new Status("Success", "#297545");
                break;
            case 2:
                status = new Status("Pending", "#0dcaf0");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StatusCode);
        }
        return status;
    }

    private String loadCurrencies(String c) {
        currencyDetails = new ArrayList<>();
        homeActivityViewModel.mCountriesDetailsLiveData.observe(getViewLifecycleOwner(), countries -> {
            if (countries != null) {
                currencyDetails = countries.getCurrencies();
                currency = getCurrencies(currencyDetails, c);
                mBinding.tvcurrencyview.setText(currency);
               // Toast.makeText(getContext(), "found", Toast.LENGTH_LONG).show();

            }else{
               // Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();'
                currency = getString(R.string.itemloading);
            }

        });

        return currency;
    }

    private String getCurrencies(List<Countries.Currency> currencyDetailList, String c) {
        String currency_title = "";
        for (Countries.Currency currencyDetail :
                currencyDetailList) {
            if (String.valueOf(currencyDetail.getId()).equals(c)) {
                currency_title = currencyDetail.getTitle();
               // Toast.makeText(getContext(), currency_title, Toast.LENGTH_LONG).show();
            }else{

                //currency_title =getString(R.string.itemloading);
              //  Toast.makeText(getContext(), c, Toast.LENGTH_LONG).show();
            }

        }
        return currency_title;
    }

    private String getBankFromId(String id) {
        banks = new ArrayList<>();
        homeActivityViewModel.mBankDetailsLiveData.observe(requireActivity(), bankList -> {
            if (bankList != null) {
                banks = bankList.getBankDetails();
            }
        });
        return getBankName(banks, id);
    }

    private String getUserFromId(String id) {
        AtomicReference<String> name = new AtomicReference<>("");
        try {
            homeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
                if (dashboardData != null) {
                    name.set(dashboardData.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name.get();
    }

    private String getBankName(List<Banks.BankDetail> bankslist, String bankid) {
        String bankname = "";
        for (Banks.BankDetail bank :
                bankslist) {
            if (bank.getId().equals(bankid)) {
                bankname = bank.getName();
            }

        }
        return bankname;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//getting passed bundle data from clicked item other bank transfer
        mNavController = Navigation.findNavController(view);
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                bankTransaction = bundle.getParcelable("bankTransaction");

                String id = bankTransaction.getTransactionId();
                String userid = bankTransaction.getUserId();

                mBinding.tvusernameview.setText(getUserFromId(userid));
                String termid = bankTransaction.getTermId();

                String bankid = bankTransaction.getBankId();
                String transactionid = bankTransaction.getTransactionId();
                String amount = concatDouble(Double.parseDouble(bankTransaction.getAmount()));
                String currencyRate = bankTransaction.getCurrencyRate();
                Status status = checkStatus(Integer.parseInt(bankTransaction.getStatus()));
                String createdAt = bankTransaction.getCreatedAt().substring(0,10) +" " + bankTransaction.getCreatedAt().substring(11,16);
                String updatedAt = bankTransaction.getUpdatedAt();
                String currency = bankTransaction.getTermId();
                String account = "";
                String accountholdername = "=";
                String branch = "";
                String info = bankTransaction.getInfo();
                try {
                    result = new JSONObject(info);
                    account = result.getString("account_number");
                    accountholdername = result.getString("account_holder_name");
                    branch = result.getString("branch");

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                mBinding.tvtransactionTypeView.setText(R.string.banktransfer);
                mBinding.tvcurrencyview.setText(loadCurrencies(currency));
                mBinding.tvcurrencyrateview.setText(concatDouble(Double.parseDouble(bankTransaction.getCurrencyRate())));

                mBinding.tvstatusview.setText(status.getStatus());
                mBinding.tvstatusview.setTextColor(Color.parseColor(status.getColor()));

                mBinding.tvbankNameview.setText(getBankFromId(bankid));
                mBinding.tvAccountHoldernameview.setText(accountholdername);
                mBinding.tvAccountNumberview.setText(account);
                mBinding.tvAmountview.setText(String.valueOf(amount));
                mBinding.tvBranchview.setText(branch);
                mBinding.tvTransactionDateview.setText(createdAt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String concatDouble(double mydouble){

        DecimalFormat df = new DecimalFormat("#.00");
        String finaldouble = df.format(mydouble);
        return finaldouble;
    }

}