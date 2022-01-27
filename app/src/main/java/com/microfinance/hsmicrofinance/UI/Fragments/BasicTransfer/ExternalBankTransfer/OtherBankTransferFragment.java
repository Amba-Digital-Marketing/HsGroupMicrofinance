package com.microfinance.hsmicrofinance.UI.Fragments.BasicTransfer.ExternalBankTransfer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.BanksCurrencyDetails;
import com.microfinance.hsmicrofinance.Network.entity.Countries;
import com.microfinance.hsmicrofinance.Network.entity.Currencies;
import com.microfinance.hsmicrofinance.Network.entity.OtherBankTransferDetails;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.OtherBankTransferViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentOtherBankTransferBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;

public class OtherBankTransferFragment extends Fragment {
    FragmentOtherBankTransferBinding mBinding;
    String TAG = "FragOtherBankTran";
    HomeActivityViewModel homeActivityViewModel;
    OtherBankTransferViewModel otherBankTransferViewModel;
    NavController navController;
    Countries countries;
    Currencies currencies;
    Banks banks;
    Map<String, Integer> countriesmap;
    int notified = 0;

    private double balance;
    private Map<String, String> currencymap;
    private Map<String, Integer> banksmap;

    public OtherBankTransferFragment(
            Countries countries,
            Currencies currencies,
            Banks banks
    ) {
        this.countries = countries;
        this.currencies = currencies;
        this.banks = banks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        homeActivityViewModel.notifiedMLD.setValue(notified);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentOtherBankTransferBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currencymap = new HashMap();
        banksmap = new HashMap();
        mBinding.progrebar.setVisibility(View.GONE);
        checkUserBalance();
        updateUserOnBalance();


        otherBankTransferViewModel = new ViewModelProvider(requireActivity()).get(OtherBankTransferViewModel.class);
        navController = Navigation.findNavController(view);

        //setting drop down textfields
        setCountriesToTextField(countries);

        setCurrenciesToTextField(currencies);

        //setBanksToTextField(banks);

        mBinding.btnOtherBankTransfer.setOnClickListener(v -> {
            getUserInput();
            getOTPResponse();
            mBinding.progrebar.setVisibility(View.VISIBLE);
        });

        mBinding.etCountry.setOnItemClickListener(
                (parent, view1, position, id) -> {
                    String country = mBinding.etCountry.getText().toString();
                    gettingCountryID(country);
                }
        );


    }

    // observing get OTP response
    private void getOTPResponse() {
        otherBankTransferViewModel.motpresponseLiveDataCode.observe(getViewLifecycleOwner(), code -> {
            //  if (code >= 200 && code < 300) {
            // navController.navigate(R.id.action_transferFundsFragment_to_otherBankTransferOtpFragment);
            //}

            if (code != null) {
                if (code < 200 || code >= 300) {

                    mBinding.progrebar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Failed to send OTP", Toast.LENGTH_SHORT).show();
                } else {
                    mBinding.progrebar.setVisibility(View.INVISIBLE);
                    if (code == 200) {
                        try {
                            navController.navigate(R.id.action_transferFundsFragment_to_otherBankTransferOtpFragment);
                            otherBankTransferViewModel.motpresponseLiveDataCode.postValue(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                mBinding.progrebar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void getUserInput() {
        mBinding.progrebar.setVisibility(View.GONE);
        String country, bank, currency, branch, account_holder;

        String account = mBinding.etAccount.getText().toString().trim();
        String amount = mBinding.etAmount.getText().toString().trim();
        country = mBinding.etCountry.getText().toString().trim();
        bank = mBinding.etBank.getText().toString().trim();
        account_holder = mBinding.etaccountHolder.getText().toString().trim();
        currency = mBinding.etCurrency.getText().toString().trim();
        branch = mBinding.etBranch.getText().toString().trim();

        // if (bank != "" && country != "" && account.toString().length() != 5 && currency != "" && branch != "" && account_holder != "") {
        //     if (bank != null && country != null && account != null && currency != null && branch != null && account_holder != null) {

        if (country != null && !country.equals("")) {
            if (bank != null && !bank.equals("")) {
                if (currency != null && !currency.equals("")) {
                    if (branch != null && !branch.equals("")) {
                        if (account_holder != null && !account_holder.equals("")) {
                            if (account != null && !account.equals("")) {
                                if (amount != null && !amount.equals("")) {
                                    try {

                                        double sendingamount = Double.parseDouble(mBinding.etAmount.getText().toString().trim());

                                        if (sendingamount > 499) {
                                            String myaccount = mBinding.etAccount.getText().toString().trim();
                                            mBinding.progrebar.setVisibility(View.VISIBLE);
                                            int bankid = getSelectedBank(bank);
                                            String currencyid = getSelectedCurrency(currency);

                                            Timber.tag("transferdetails").d("bankid " + bankid + "currency --" + currencyid);
                                            OtherBankTransferDetails otherBankTransferDetails = new OtherBankTransferDetails(country, currencyid, bankid, branch, account_holder, myaccount, sendingamount);
                                            otherBankTransferViewModel.transferToOtherBanks(getContext(), otherBankTransferDetails);

                                        } else {
                                            mBinding.tvamountAlert.setText("Amount to be sent cannot be less than 500");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                        mBinding.tvamountAlert.setText("Input Correct Amount");
                                        mBinding.tvaccountAlert.setText("Input Correct Account");
                                    }
                                } else {
                                    mBinding.tvamountAlert.setText("Amount Field cannot be empty");
                                }
                            } else {
                                mBinding.tvaccountAlert.setText("Input the correct Account Number");
                            }

                        } else {
                            mBinding.tvAccountHolderAlert.setText("Please input the account holder's name");
                        }

                    } else {
                        mBinding.tvBranchAlert.setText("Please input a branch");
                    }

                } else {
                    mBinding.tvCurrencyAlert.setText("You must Select a Currency");
                }
            } else {
                mBinding.tvBankAlert.setText("You must Select a bank");
            }
        } else {
            mBinding.tvCountryAlert.setText("You must Select a country");
        }

    }

    //set currency adapter
    private void setCountriesToTextField(Countries countries) {
        countriesmap = new HashMap<>();
        List<String> countrylist = new ArrayList<>();
        if (countries == null) {
          try {
              homeActivityViewModel.mCountriesDetailsLiveData.observe(getViewLifecycleOwner(), countries1 -> {
                  if (countries1 != null) {
                      countrylist.clear();
                      for (Countries.Country country :
                              countries1.getCountries()) {
                          countrylist.add(country.getTitle());
                          countriesmap.put(country.getTitle(), country.getId());
                      }
                  }


              });
          }catch (Exception e){
              e.printStackTrace();
          }

        } else {
            countrylist.clear();
            for (Countries.Country country :
                    countries.getCountries()) {
                countrylist.add(country.getTitle());
            }
        }
        if (countrylist != null) {
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, countrylist);
            mBinding.etCountry.setAdapter(adapter);
        }
    }

    //set currency adapter
    private void setCurrenciesToTextField(Currencies currencies) {
        List<String> currencylist = new ArrayList<>();

        if (currencies == null) {
            homeActivityViewModel.mCurrenciesDetailsLiveData.observe(getViewLifecycleOwner(), currencies1 -> {
                if (currencies1 != null) {
                    currencylist.clear();
                    for (Currencies.CurrencyDetail currencyitem :
                            currencies1.getCurrencyDetails()) {
                        currencylist.add(currencyitem.getTitle());
                        currencymap.put(currencyitem.getTitle(), currencyitem.getId());
                    }
                }
            });

        } else {
            currencylist.clear();
            for (Currencies.CurrencyDetail currencyitem :
                    currencies.getCurrencyDetails()) {
                currencylist.add(currencyitem.getTitle());
            }
        }

        if (currencylist != null) {
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, currencylist);
            mBinding.etCurrency.setAdapter(adapter);

        } else {

        }

    }

    //set Bank adapter
    private void setBanksToTextField(Banks banks) {
        List<String> bankslist = new ArrayList<>();


        if (banks == null) {
            homeActivityViewModel.mBankDetailsLiveData.observe(getViewLifecycleOwner(), banks1 -> {
                if (banks1 != null) {
                    bankslist.clear();
                    for (Banks.BankDetail bankDetail :
                            banks1.getBankDetails()) {
                        banksmap.put(bankDetail.getName(), Integer.valueOf(bankDetail.getId()));
                        bankslist.add(bankDetail.getName());
                    }
                    Log.d(TAG, "setBanksToTextField: " + bankslist);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, bankslist);
                    mBinding.etBank.setAdapter(adapter);

                }
            });

        } else {
            bankslist.clear();
            for (Banks.BankDetail bankDetail :
                    banks.getBankDetails()) {
                bankslist.add(bankDetail.getName());
            }
            Log.d(TAG, "setBanksToTextField: " + bankslist);

        }

        if (bankslist != null) {
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, bankslist);
            //fragmentOtherBankTransferBinding.etBank.setAdapter(adapter);
            //@TODO add uncomment this to setAdapter to banks text field
        }

    }


    //get country id so as to set bank id
    private int gettingCountryID(String country) {
        int termid = countriesmap.get(country);
        setBanksToTextField(termid);
        return termid;
    }

    private void setBanksToTextField(int id) {

        otherBankTransferViewModel.getOtherBankDetailsFromCountryId(getContext(), String.valueOf(id));
        mBinding.progrebar.setVisibility(View.VISIBLE);
        otherBankTransferViewModel.mbanksMutableLiveData.observe(getViewLifecycleOwner(), data -> {
            List<String> bankslist = new ArrayList<>();
            for (BanksCurrencyDetails.BanksCountry bankDetail :
                    data.banksCountry) {
                bankslist.add(bankDetail.name);
                banksmap.put(bankDetail.name, bankDetail.id);
            }

            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, bankslist);

            mBinding.etBank.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            mBinding.progrebar.setVisibility(View.GONE);

        });
    }

    private void checkUserBalance() {
        homeActivityViewModel.dashBoardDataMutableLiveData.observe(getViewLifecycleOwner(), dashboardData -> {
            this.balance = Double.parseDouble(dashboardData.getAccBallance());
        });
    }


    private void updateUserOnBalance() {
        notified = homeActivityViewModel.notifiedMLD.getValue();
        if (notified != 0) {
            homeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
                if (dashboardData != null) {
                    this.balance = Double.parseDouble(dashboardData.getAccBallance());
                    if (balance < 500) {
                        this.notified = -1;
                        notifyUserOnAccouBalance();
                        this.notified = 0;
                        homeActivityViewModel.setNotifiedMLD(notified);
                    }
                }
            });
        }

    }

    private void notifyUserOnAccouBalance() {

        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Sorry")
                .setContentText("Your Account balance is below the minimum 500 transfer amount")
                .setNeutralButtonTextColor(Color.parseColor("#297545"))
                .show();

        mBinding.etAccount.setEnabled(false);
        mBinding.etAmount.setEnabled(false);
        mBinding.etCountry.setEnabled(false);
        mBinding.etBank.setEnabled(false);
        mBinding.etaccountHolder.setEnabled(false);
        mBinding.etCurrency.setEnabled(false);
        mBinding.etBranch.setEnabled(false);
        mBinding.btnOtherBankTransfer.setEnabled(false);
        this.notified = 0;
    }

    private int getSelectedBank(String bankname) {
        int bankid = banksmap.get(bankname);
        return bankid;
    }

    private String getSelectedCurrency(String currencyname) {
        String currecyid = currencymap.get(currencyname);
        return currecyid;
    }


}
