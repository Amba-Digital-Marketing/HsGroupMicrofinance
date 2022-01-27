package com.microfinance.hsmicrofinance.UI.Fragments.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.microfinance.hsmicrofinance.databinding.FragmentProfileBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import timber.log.Timber;


public class Profile extends Fragment {
    String TAG = "Profile";
    FragmentProfileBinding mFragmentProfileBinding;
    HomeActivityViewModel homeActivityViewModel;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mFragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);

        getUserData();
        updateUI();
    }

    // view model getUser Data
    private void getUserData() {
        homeActivityViewModel.getUserData();
    }

    private void updateUI() {

        homeActivityViewModel.dashBoardDataMutableLiveData.observe(requireActivity(), dashboardData -> {
            Timber.tag(TAG).d("dashboardData :" + dashboardData.getName() + " " + dashboardData.getAccBallance());

            mFragmentProfileBinding.tvname.setText(dashboardData.getName());
            mFragmentProfileBinding.tvemail.setText(dashboardData.getEmail());
            mFragmentProfileBinding.tvKRA.setText(dashboardData.getKRAPin());
            mFragmentProfileBinding.tvuserPhoneNo.setText(dashboardData.getPhone());
            mFragmentProfileBinding.tvAccNo.setText(dashboardData.getAccNo());
            mFragmentProfileBinding.tvBalance.setText("Ksh " + dashboardData.getAccBallance());
        });

    }

}