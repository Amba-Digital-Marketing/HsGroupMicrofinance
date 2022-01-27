package com.microfinance.hsmicrofinance.UI.Fragments.AccountSettings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;
import com.microfinance.hsmicrofinance.LocalDatabase.UserEntity;
import com.microfinance.hsmicrofinance.Network.entity.AccountExtraInfoResponse;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.AccountExtraInfoViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentAccountSettingBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;

import java.util.List;

import timber.log.Timber;


public class AccountSetting extends Fragment {



FragmentAccountSettingBinding mBinding;
    AccountExtraInfoViewModel userDataViewModel;
    private NavController mNavController;
    private String[] mVerification;
    private ArrayAdapter mAdapter;
    private HomeActivityViewModel mViewModel;
    private AccountExtraInfoViewModel accountExtraInfoViewModel;
    UserDao db;

    public AccountSetting() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        userDataViewModel =new ViewModelProvider(requireActivity()).get(AccountExtraInfoViewModel.class);
        accountExtraInfoViewModel =new ViewModelProvider(requireActivity()).get(AccountExtraInfoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = UserDB.getDbInstance(getContext()).userDao();
        accountExtraInfoViewModel.getExtraAccountInfo(getContext());
        // Inflate the layout for this fragment
        mBinding = FragmentAccountSettingBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Update Info");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_accountSetting_to_basicSettings));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);
        mVerification = getResources().getStringArray(R.array.verification);
        mAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1,mVerification);
        mBinding.selectVerification.setAdapter(mAdapter);
        mBinding.updateChangeBtn.setOnClickListener(v -> upDateUserDetails(view));
        mBinding.passwordAccountSetting.setOnClickListener(v-> mNavController.navigate(R.id.action_accountSetting_to_passwordChange));
        mBinding.btnChangepin.setOnClickListener(v->selectUserFromLocalStorage());

        getUserData();
        upDateUi();
        getUserExtraDataSettings();

    }

    private void upDateUserDetails(View view) {
        mBinding.progrebar.setVisibility(View.VISIBLE);
        mViewModel.upDateAccountInfo(requireActivity(),
                mBinding.etName.getText().toString().trim(),
                mBinding.etEmail.getText().toString().trim(),
                mBinding.etPhone.getText().toString().trim(),
        mBinding.etStreet.getText().toString().trim(),
        mBinding.etCity.getText().toString().trim(),
        mBinding.etState.getText().toString().trim(),
        mBinding.etzipcode.getText().toString().trim(),
                mBinding.etCountry.getText().toString().trim(),view);


    }

    private void upDateUi() {
        mViewModel.dashBoardDataMutableLiveData.observe(getViewLifecycleOwner(), dashboardData -> {
            Timber.tag("Update").d("info: %s", dashboardData.getName());
            mBinding.etName.setText(dashboardData.getName());
            mBinding.etEmail.setText(dashboardData.getEmail());
            mBinding.etPhone.setText(dashboardData.getPhone());

        });
    }
    private void selectUserFromLocalStorage(){

        Runnable loginActivityRunnable  = () -> {
            UserEntity userEntity = db.loaduserById(1);

            Handler handler = new Handler(Looper.getMainLooper());

            handler.post(() -> {

                        if(userEntity != null){
                                    if (userEntity.pin != null && !userEntity.pin.equals("null")) {
                                        String PIN =userEntity.pin;
                                        Bundle args = new Bundle();
                                        args.putString("pin",PIN);

                                        mNavController.navigate(R.id.action_accountSetting_to_pinLoginAccountSetting, args);
                                    }

                        }
                    }
            );

        };

        new Thread(loginActivityRunnable).start();
    }

    public void getUserData(){
        mViewModel.getUserData();
        accountExtraInfoViewModel.getExtraAccountInfo(getContext());
    }

    private  void updateUIWithUserData(){
        accountExtraInfoViewModel.extraUserInfoLiveData.observe(getViewLifecycleOwner(),data ->{
            List<AccountExtraInfoResponse.UserAccountAdressUpdateDetail> userdata = data.getUserAccountAdressUpdateDetails();
           // userdata.get(0).getCity() =
        });
    }

    private void getUserExtraDataSettings(){
        userDataViewModel.extraUserInfoLiveData.observe(getViewLifecycleOwner(),data->{
            if(data!= null){
             try {
                 String city = data.getUserAccountAdressUpdateDetails().get(0).getCity().toString();
                 String country = data.getUserAccountAdressUpdateDetails().get(0).getCountry().toString();
                 String state = data.getUserAccountAdressUpdateDetails().get(0).getState();
                 String postalCode = String.valueOf(data.getUserAccountAdressUpdateDetails().get(0).getPostalCode());
                 String street = data.getUserAccountAdressUpdateDetails().get(0).getStreet().toString();


                 mBinding.etStreet.setText(city);
                 mBinding.etCity.setText(country);
                 mBinding.etState.setText(state);
                 mBinding.etzipcode.setText(postalCode);
                 mBinding.etCountry.setText(street);
             }catch (Exception e){
                 e.printStackTrace();
             }
            }
        });
    }
}