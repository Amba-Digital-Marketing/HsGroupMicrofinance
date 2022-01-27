package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.entity.InternalTransferResponse;
import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.DashboardData;
import com.microfinance.hsmicrofinance.Network.models.PhoneNumbers;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.viewmodels.ContactViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.HomeActivityViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.InternalTransferViewModel;
import com.microfinance.hsmicrofinance.UI.viewmodels.LoanHistoryViewModels;
import com.microfinance.hsmicrofinance.UI.viewmodels.PayForFriendsViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentPayFriendsViaMpesaBinding;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class PayFriendsViaMpesa extends Fragment {
    private static final String TAG = "PayFriendsMpesa";

    private static final int PHONE_NUMBER_PERMISSION = 101;
    private static final int SELECT_PHONE_NUMBER = 102;


    FragmentPayFriendsViaMpesaBinding mFragmentPayFriendsViaMpesaBinding;
    public static List<PhoneNumbers> mContacts;
    public List<AllUsers.UserDetail> mUserDetailList;
    private NavController mNavController;
    private ContactViewModel mContactViewModel;
    private LoanHistoryViewModels mHistoryViewModels;

    InternalTransferViewModel viewModel;
    private HomeActivityViewModel model;

    int notified = 0;


    String phone;
    String phoneOnHs;
    private HomeActivityViewModel mModel;
    private String mPhone;
    private EdepositViewModel mEdepositViewModel;
    private PayForFriendsViewModel mPayModel;
    private String mAcc_number;
    private InternalTransferViewModel mInteViewModel;
    private HomeActivityViewModel mHomeActivityViewModel;
    private int mUserId;

    public PayFriendsViaMpesa(String phone, String phoneOnHs) {

        // Required empty public constructor
    }

    public PayFriendsViaMpesa() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        viewModel = new ViewModelProvider(getActivity()).get(InternalTransferViewModel.class);
        model.notifiedMLD.setValue(notified);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentPayFriendsViaMpesaBinding = FragmentPayFriendsViaMpesaBinding.inflate(inflater, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mFragmentPayFriendsViaMpesaBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFragmentPayFriendsViaMpesaBinding.toolbar.setTitle("Pay Beneficiary");
        mFragmentPayFriendsViaMpesaBinding.toolbar.setNavigationOnClickListener(v -> {
            mNavController.navigate(R.id.action_payFriendsViaMpesa_to_basicDashboard);
        });

        return mFragmentPayFriendsViaMpesaBinding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            this.phone = getArguments().getString("phone");
            this.phoneOnHs = getArguments().getString("onHsPhone");
            Timber.tag("num").d("Number::%s", phone);
        }


        mNavController = Navigation.findNavController(view);
        mHomeActivityViewModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        mHistoryViewModels = new ViewModelProvider(requireActivity()).get(LoanHistoryViewModels.class);
        mEdepositViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);
        mPayModel = new ViewModelProvider(requireActivity()).get(PayForFriendsViewModel.class);

        mInteViewModel = new ViewModelProvider(requireActivity()).get(InternalTransferViewModel.class);
        mHistoryViewModels.fetchAllUsers(requireActivity());


        mHistoryViewModels.getMutableLiveDataofAllUsers().observe(getViewLifecycleOwner(), userDetails -> {

            mUserDetailList = userDetails;
            if (phone != null) {
                mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.setText(phone
                );
            }
            if (phoneOnHs != null) {

                mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.setText(phoneOnHs);
            }

        });
        mFragmentPayFriendsViaMpesaBinding.payfriendChooseBtn.setOnClickListener(v -> {
            // pickPhoneNumber();
            try {
                askPermissionAndBrowseFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


//        if(getArguments().getString("phone") != null){
//            Timber.tag("phoneArgs").d("args*******%s", getArguments().getString("phone"));
//        }

        mModel = new ViewModelProvider(requireActivity()).get(HomeActivityViewModel.class);
        mModel.dashBoardDataMutableLiveData.observe(getViewLifecycleOwner(), new Observer<DashboardData>() {
            @Override
            public void onChanged(DashboardData dashboardData) {
                mPhone = dashboardData.getPhone();
                mUserId = Integer.parseInt(dashboardData.getId());
                int id = dashboardData.getUid();
                Timber.tag("phone").d("####" + mPhone);
                Timber.tag("phone").d("@@@" + mUserId);
                Timber.tag("phone").d("####***" + id);
                mEdepositViewModel.deletePending(requireActivity(),mUserId);
            }
        });
        try{


        }catch (Exception e){
            e.printStackTrace();
        }

        mFragmentPayFriendsViaMpesaBinding.payfriendSubmitBtn.setOnClickListener(view1 -> doPayForFriend(view,mFragmentPayFriendsViaMpesaBinding));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void doPayForFriend(View view,FragmentPayFriendsViaMpesaBinding binding) {
        String fPhone = mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.getText().toString().trim();
        if (mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.getText().toString().isEmpty()) {
            mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.setError("Enter Account number");
            mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.requestFocus();
        } else if (mFragmentPayFriendsViaMpesaBinding.enterAmount.getText().toString().isEmpty()) {
            mFragmentPayFriendsViaMpesaBinding.enterAmount.setError("Enter Amount");
            mFragmentPayFriendsViaMpesaBinding.enterAmount.requestFocus();
        }else if(fPhone.length()<10){
            mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.setError("Enter 10 digits");
            mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.requestFocus();
        } else {
            String phone="";
            if (mPhone.length() == 13) {
                phone = "0" + mPhone.substring(4, 13);
            } else if (mPhone.length() == 12) {
                phone = "0" + mPhone.substring(3, 12);
            } else if (mPhone.length() == 10) {
                phone = mPhone;
            } else {
                Timber.tag("phone_number").d("#### " + phone);
            }

            try {
                 viewModel.transferToHSAccountBanks(getContext(), mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.getText().toString().trim(), Double.parseDouble(mFragmentPayFriendsViaMpesaBinding.enterAmount.getText().toString().trim()));
                viewModel.mOtherBankTransferMutableLiveData.observe(getViewLifecycleOwner(), internalTransferResponse ->{
                    if(internalTransferResponse != null){
                        if(internalTransferResponse.transferInformation != null){
                            mNavController.navigate(R.id.action_payFriendsViaMpesa_to_internalTransferOtpFragment);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            Timber.tag("phone_number").d("##%%% " + fPhone);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void doInternaltransfer() {
        String testPhone = mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.getText().toString().trim();
        String phone = "";

        if (testPhone.length() == 13)
            phone = testPhone;
        if (testPhone.length() == 12)
            phone = "+" + testPhone;
        if (testPhone.length()==10)
            phone= "+254"+testPhone.substring(1,10);
        String finalPhone = phone;
        mUserDetailList.stream().forEach(userDetail -> {
                if (userDetail.getPhone().equals(finalPhone) ){
                    mAcc_number = userDetail.getAccountNumber();
                    Timber.tag("acc_number").d("account@@@  "+ mAcc_number);
                }
            });

            try{
                mInteViewModel.transferToHSAccountBanks(requireActivity(),mAcc_number,Double.parseDouble(mFragmentPayFriendsViaMpesaBinding.enterAmount.getText().toString()));

            }catch (Exception e){
                e.printStackTrace();
            }

    }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void askPermissionAndBrowseFile () {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                // Check if we have Call permission
                int permisson = ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.READ_CONTACTS);

                if (permisson != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions(
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PHONE_NUMBER_PERMISSION
                    );
                    return;
                }
            }
            try {
                this.addContacts();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // When you have the request results
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onRequestPermissionsResult ( int requestCode,
        String permissions[], int[] grantResults){

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            //
            switch (requestCode) {
                case PHONE_NUMBER_PERMISSION: {

                    // Note: If request is cancelled, the result arrays are empty.
                    // Permissions granted (CALL_PHONE).
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        Log.i(TAG, "Permission granted!");
                        Toast.makeText(getContext(), "Permission granted!", Toast.LENGTH_SHORT).show();

                        this.addContacts();
                    }
                    // Cancelled or denied.
                    else {
                        Log.i(TAG, "Permission denied!");
                        Toast.makeText(getContext(), "Permission denied!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void addContacts () {
            mContacts = new ArrayList<>();

            //to store name-number pair
            JSONObject obj = new JSONObject();

            try {

                Cursor phones = requireActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                while (phones.moveToNext()) {
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //obj.put(name, phoneNumber);
                    PhoneNumbers phoneNumbers = new PhoneNumbers(name, phoneNumber,phoneNumber);
                    mContacts.add(phoneNumbers);

                }
                phones.close();
                Timber.tag("CONTACTS").d("CONTACTS ----------%s", mContacts.toString());
                Timber.tag("CONTACTS").d("CONTACTS ----------%s", mContacts.size());

                /*
                 **
                 *** //TODO SORT LIST OF CONTACTS IN ASCENDING ORDER
                 *
                 */
                Collections.sort(mContacts, (o1, o2) -> (int) (o1.getName().compareTo(o2.getName())));

                Timber.tag("SortedContacts").d("Name A--Z:%s ", mContacts);


                mContactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
                mContactViewModel.setUsersLiveData(mUserDetailList);
                mContactViewModel.setPhoneNumbers(mContacts);
                Bundle bundle = new Bundle();
                bundle.putString("FRAGMENT","PAYFORFRIENDS");
                mNavController.navigate(R.id.action_payFriendsViaMpesa_to_contacts,bundle);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case SELECT_PHONE_NUMBER:
                        contactPicked(data);
                        break;
                }
            } else {
                Toast.makeText(getContext(), "Failed To pick contact ", Toast.LENGTH_SHORT).show();
            }
        }

        private void contactPicked (Intent data){
            Cursor cursor;
            try {
                String phoneNo;
                Uri uri = data.getData();
                cursor = getContext().getContentResolver().query(uri, null, null, null, null);
                cursor.moveToFirst();
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                phoneNo = cursor.getString(phoneIndex);
                mFragmentPayFriendsViaMpesaBinding.choosePhoneNumber.setText(accountNumberLookup(phoneNo));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private String accountNumberLookup(String testPhone) {


        String phone = "";

        if (testPhone.length() == 13)
            phone = testPhone;
        if (testPhone.length() == 12)
            phone = "+" + testPhone;
        if (testPhone.length() == 10)
            phone = "+254" + testPhone.substring(1, 10);
        String finalPhone = phone;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mUserDetailList.stream().forEach(userDetail -> {
                if (userDetail.getPhone().equals(finalPhone)) {
                    mAcc_number = userDetail.getAccountNumber();
                    Timber.tag("acc_number").d("account@@@  " + mAcc_number);


                }
            });

        }

        return mAcc_number;
    }





}
