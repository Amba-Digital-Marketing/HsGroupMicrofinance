package com.microfinance.hsmicrofinance.UI.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.billing.BillingActivity;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicDashboardBinding;

import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;


public class BasicDashboard extends Fragment {

    private static final String TAG = "BasicDashboard";
    protected int notified = -1;
    String username = "";
    FragmentBasicDashboardBinding mFragmentBasicDashboardBinding;
    private NavController mNavController;

    HomeActivityViewModel homeActivityViewModel;
    private double balance;
    UserDao db;
    private LoanHistoryViewModels mViewModels;
    private String mId;
    private Double mRefreshBalance;
    private String mUsername;
    private boolean show = false;

    public BasicDashboard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = UserDB.getDbInstance(getContext()).userDao();
        homeActivityViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        homeActivityViewModel.notifiedMLD.setValue(notified);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeActivityViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        getUserData();
        // Inflate the layout for this fragment
        mFragmentBasicDashboardBinding = FragmentBasicDashboardBinding.inflate(inflater, container, false);

        return mFragmentBasicDashboardBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);

        mFragmentBasicDashboardBinding.tvAccountBalance.setOnClickListener(v -> {
            show = !show;
            updateUI();
        });
        getUserData();
        updateUI();


        try {
            mViewModels.fetchAllUsers(requireActivity());
            selectUsersandCompareId();
            // selectUserFromLocalStorage();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        mFragmentBasicDashboardBinding.bills.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_billsTabs));


        mFragmentBasicDashboardBinding.depositHistory.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicDepositHistory));
        mFragmentBasicDashboardBinding.deposit.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicEDeposit));


    }

    // view model getUser Data
    private void getUserData() {
        homeActivityViewModel.getUserData();
    }

    private void updateUI() {
        if (homeActivityViewModel.notifiedMLD.getValue() != 0) {
            homeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
                if (dashboardData != null) {
                    this.balance = Double.parseDouble(dashboardData.getAccBallance());
                    Timber.tag(TAG).d("dashboardData :" + dashboardData.getAccNo() + " " + dashboardData.getAccBallance());
                    mFragmentBasicDashboardBinding.tvAccNo.setText(dashboardData.getAccNo());
                    if (show) {
                        mFragmentBasicDashboardBinding.tvAccountBalance.setText("Ksh " + dashboardData.getAccBallance());
                    } else {
                        mFragmentBasicDashboardBinding.tvAccountBalance.setText("Click to Show");
                    }
                    mId = dashboardData.getId();
                    username = dashboardData.getName();
                    if (Double.parseDouble(dashboardData.getAccBallance()) < 500) {
                        this.notified = -1;
                        notifyUserOnAccouBalance(Double.parseDouble(dashboardData.getAccBallance()));
                        this.notified = 0;
                        homeActivityViewModel.setNotifiedMLD(notified);
                    }

                    setDahboardsNavigations(balance);
                    disableDashboardUI(balance);
                }
            });
        }

    }

    private void notifyUserOnAccouBalance(double balance) {
        if (balance < 500 && this.notified == -1) {
            new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Hello " + username)
                    .setContentText("Your Account balance is less than 500 Shillings\n" +
                            "Kindly top up to enjoy all the member benefits")
                    .setNeutralButtonTextColor(Color.parseColor("#297545"))
                    .setConfirmClickListener(alert ->
                            {
                                mNavController.navigate(R.id.action_basicDashboard_to_basicEDeposit);
                                alert.dismiss();
                            }
                    )
                    .show();
            this.notified = 0;
        }
    }

    public void selectUserFromLocalStorage() {

        Runnable loginActivityRunnable = () -> {


            db.updateAccBallance(mRefreshBalance, mUsername, 1);

        };

        new Thread(loginActivityRunnable).start();
    }


    //balance notification data

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void selectUsersandCompareId() {

        mViewModels.mMutableLiveDataofAllUsers.observe(getViewLifecycleOwner(), userDetails -> {

            try {
                if (userDetails != null) {
                    userDetails.stream().forEach(userDetail -> {
                        if (userDetail.getId() == Integer.parseInt(mId)) {
                            mRefreshBalance = userDetail.getBalance();
                            mUsername = userDetail.getName();


                            Runnable loginActivityRunnable = () -> {
                                if (mRefreshBalance != null) {

                                    db.updateAccBallance(mRefreshBalance, mUsername, 1);


                                    Timber.tag("balanca#").d("bal ^^" + mRefreshBalance);
                                    Timber.tag("Name#").d("Name **" + mUsername);

                                }
                            };

                            new Thread(loginActivityRunnable).start();
                        }
                    });
                    Timber.tag("refresh").d("Bal " + mRefreshBalance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    // disabling ui components
    private void disableDashboardUI(double balance) {
        if (balance < 500) {
            mFragmentBasicDashboardBinding.latestTransaction.setEnabled(false);
            mFragmentBasicDashboardBinding.payForFriends.setEnabled(false);
            mFragmentBasicDashboardBinding.payLoans.setEnabled(false);
            mFragmentBasicDashboardBinding.withdraw.setEnabled(false);
            mFragmentBasicDashboardBinding.investmentHistory.setEnabled(false);
            mFragmentBasicDashboardBinding.loanHistory.setEnabled(false);

            mFragmentBasicDashboardBinding.latestTransaction.setEnabled(false);
            mFragmentBasicDashboardBinding.payForFriends.setEnabled(false);
            mFragmentBasicDashboardBinding.payLoans.setEnabled(false);
            mFragmentBasicDashboardBinding.withdraw.setEnabled(false);
            mFragmentBasicDashboardBinding.investmentHistory.setEnabled(false);
            mFragmentBasicDashboardBinding.loanHistory.setEnabled(false);


            mFragmentBasicDashboardBinding.tvcontactphone.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvcontactphone2.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvcontactemail.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvcontactemail2.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvcontactlocation.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvcontactlocation2.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvpayforfriends.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvpayloans.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvwithdraw.setTextColor(Color.parseColor("#64737275"));
            mFragmentBasicDashboardBinding.tvbills.setTextColor(Color.parseColor("#64737275"));


            mFragmentBasicDashboardBinding.tvcontactphone.setEnabled(false);
            mFragmentBasicDashboardBinding.tvcontactphone2.setEnabled(false);
            mFragmentBasicDashboardBinding.tvcontactemail.setEnabled(false);
            mFragmentBasicDashboardBinding.tvcontactemail2.setEnabled(false);
            mFragmentBasicDashboardBinding.tvcontactlocation.setEnabled(false);
            mFragmentBasicDashboardBinding.tvcontactlocation2.setEnabled(false);
            mFragmentBasicDashboardBinding.tvbills.setEnabled(false);
            mFragmentBasicDashboardBinding.tvpayforfriends.setEnabled(false);
            mFragmentBasicDashboardBinding.tvpayloans.setEnabled(false);
            mFragmentBasicDashboardBinding.tvwithdraw.setEnabled(false);
            mFragmentBasicDashboardBinding.imageView.setEnabled(false);
            mFragmentBasicDashboardBinding.im1.setEnabled(false);
            mFragmentBasicDashboardBinding.im.setEnabled(false);
            mFragmentBasicDashboardBinding.ivbills.setEnabled(false);
            mFragmentBasicDashboardBinding.contactphone.setEnabled(false);
            mFragmentBasicDashboardBinding.contactemail.setEnabled(false);
            mFragmentBasicDashboardBinding.contactlocation.setEnabled(false);
            mFragmentBasicDashboardBinding.bills.setEnabled(false);


        }
    }

    // setting dashboard navigations

    private void setDahboardsNavigations(double balance) {
        if (balance >= 500) {

            try {
                mFragmentBasicDashboardBinding.latestTransaction.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicLatestTransactions2));
//            mFragmentBasicDashboardBinding.payForFriends.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicPayfriends));

                mFragmentBasicDashboardBinding.payForFriends.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_payFriendsViaMpesa));

                mFragmentBasicDashboardBinding.payLoans.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_myLoans));
                mFragmentBasicDashboardBinding.withdraw.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicWithdrawMoney));
                mFragmentBasicDashboardBinding.investmentHistory.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicInvestmentHistory));
                mFragmentBasicDashboardBinding.loanHistory.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_basicLoanHistory));
//        mFragmentBasicDashboardBinding.bills.setOnClickListener(v -> mNavController.navigate(R.id.action_basicDashboard_to_billsTabs));
                mFragmentBasicDashboardBinding.bills.setOnClickListener(v -> {
                    startActivity(new Intent(getActivity(), BillingActivity.class));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}