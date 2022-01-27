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
import com.microfinance.hsmicrofinance.databinding.FragmentPasswordChangeBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;


public class PasswordChange extends Fragment {
FragmentPasswordChangeBinding mBinding;

    private NavController mNavController;
    private HomeActivityViewModel mViewModel;

    public PasswordChange() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPasswordChangeBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Change Password");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_passwordChange_to_accountSetting));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);



        mBinding.updatePasswordChangepage.setOnClickListener(v->changePassword(view, mBinding));
    }

    public void changePassword(View view,FragmentPasswordChangeBinding binding){
        if( mBinding.currentPassword.getText().toString().isEmpty()){
            mBinding.currentPassword.setError("Provide your current password");
            mBinding.currentPassword.requestFocus();
        }else if( mBinding.newPasswordChange.getText().toString().isEmpty()){
            mBinding.newPasswordChange.setError("Provide your new password");
            mBinding.newPasswordChange.requestFocus();
        }else if( mBinding.confirmPassword.getText().toString().isEmpty()){
            mBinding.confirmPassword.setError("Please confirm your password");
            mBinding.confirmPassword.requestFocus();

        }else if(!mBinding.newPasswordChange.getText().toString().trim().equals(mBinding.confirmPassword.getText().toString().trim())){
            mBinding.confirmPassword.setError("Password not matching");
            mBinding.confirmPassword.requestFocus();
        }else {
            mBinding.progresbar.setVisibility(View.VISIBLE);

            mViewModel.changePassword(requireActivity(),
                    mBinding.currentPassword.getText().toString().trim(),
                    mBinding.newPasswordChange.getText().toString().trim(),
                    mBinding.confirmPassword.getText().toString().trim(),view,binding);



                    mBinding.currentPassword.setText(null);
                    mBinding.newPasswordChange.setText(null);
                    mBinding.confirmPassword.setText(null);
        }
    }
}