package com.microfinance.hsmicrofinance.UI.Fragments.AccountSettings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicSettingsBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.AccountSettingsViewModel;


public class BasicSettings extends Fragment {

   FragmentBasicSettingsBinding mBinding;
    private NavController mNavController;
    private AccountSettingsViewModel mViewModel;
    public final int OK = 200;
    public final int SERVER = 200;

    public BasicSettings() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicSettingsBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Settings");
        mBinding.toolbar.setNavigationOnClickListener(v->{mNavController.navigate(R.id.action_basicSettings_to_basicDashboard);});
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(AccountSettingsViewModel.class);

        mBinding.submitbutton.setOnClickListener(v->{
            if(mBinding.etPassword.getText().toString().isEmpty()){
                mBinding.etPassword.setError("Please provide your password");
                mBinding.etPassword.requestFocus();
            }else{
                mBinding.progrebar.setVisibility(View.VISIBLE);
                mViewModel.confirmAccountSettings(requireActivity(), mBinding.etPassword.getText().toString().trim());
                mViewModel.getAccountSettingConfirmationMutableLiveData().observe(getViewLifecycleOwner(), integer -> {
                    try{
                        if(OK == integer){
                            mNavController.navigate(R.id.action_basicSettings_to_accountSetting);
                            mBinding.etPassword.setText(null);
                            mBinding.progrebar.setVisibility(View.GONE);
                        }else {
                            mBinding.etPassword.setError("Wrong password,Try again");
                            mBinding.etPassword.requestFocus();
                            mBinding.progrebar.setVisibility(View.GONE);
                        }



                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
            }
        });

    }
}