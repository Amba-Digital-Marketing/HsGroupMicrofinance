package com.microfinance.hsmicrofinance.UI.billing;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;


import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.UI.adapters.ContactsAdapter;
import com.microfinance.hsmicrofinance.UI.adapters.ContactsOnHsAdapter;
import com.microfinance.hsmicrofinance.databinding.ActivityPhoneBookBinding;
import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.HSContacts;
import com.microfinance.hsmicrofinance.Network.models.PhoneNumbers;
import com.microfinance.hsmicrofinance.UI.viewmodels.ContactViewModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PhoneBookActivity extends AppCompatActivity implements ContactsAdapter.ContactInterface, ContactsOnHsAdapter.onHsInterface {
    ActivityPhoneBookBinding activityBillingBinding;
    private Toolbar toolbar;
    private List<PhoneNumbers> mNumbersList;
    private List<AllUsers.UserDetail> mUserDetailList;
    private ContactsAdapter mAdapter;
    private ContactViewModel mContactViewModel;
    private List<PhoneNumbers> mOnHSlist = new ArrayList<>();
    private NavController navController;
    private ContactsOnHsAdapter onHsAdapter;
    List<HSContacts> searchList = new ArrayList<>();
    private static final String TAG = PhoneBookActivity.class.getSimpleName();
    public static List<PhoneNumbers> mContacts;
    private Call<AllUsers> mAllUsersCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBillingBinding = ActivityPhoneBookBinding.inflate(getLayoutInflater());
        setContentView(activityBillingBinding.getRoot());
        toolbar = activityBillingBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews() {
        loadContacts();
//        loadApiContacts();
        try {
            mContactViewModel = new ViewModelProvider(PhoneBookActivity.this).get(ContactViewModel.class);
            mContactViewModel.getPhoneNumbers().observe(PhoneBookActivity.this, list -> {
                mNumbersList = list;

                mAdapter = new ContactsAdapter(PhoneBookActivity.this, mNumbersList, this);
                activityBillingBinding.rvContacts.setAdapter(mAdapter);

            });
            Timber.tag("compareContacts").d("user=========%s ", mNumbersList.size());
        } catch (Exception e) {
            e.printStackTrace();
            Timber.tag("compareContacts").d("user=========%s ", e.getMessage());

        }

        try {
            mContactViewModel.getUsersLiveData().observe(PhoneBookActivity.this, userDetails -> {

                if (userDetails != null) {

                }
                mUserDetailList = userDetails;
                searchList = compareContacts(mUserDetailList, mNumbersList);

                onHsAdapter = new ContactsOnHsAdapter(PhoneBookActivity.this, searchList, PhoneBookActivity.this);

                activityBillingBinding.rvMembers.setAdapter(onHsAdapter);
            });
            Timber.tag("compareContacts").d("user=========%s ", mNumbersList.size());


        } catch (Exception e) {
            e.printStackTrace();
            Timber.tag("compareContacts").d("user=========%s ", e.getMessage());
        }
    }

    private void loadApiContacts() {

        APIService allUser = RetrofitInstance.getRetroClient(PhoneBookActivity.this).create(APIService.class);
        mAllUsersCall = allUser.getAllUsers();
        mAllUsersCall.enqueue(new Callback<AllUsers>() {
            @Override
            public void onResponse(Call<AllUsers> call, Response<AllUsers> response) {
                AllUsers allUsers = response.body();
                List<AllUsers.UserDetail>userDetailList = allUsers.getUserDetails();
//                mMutableLiveDataofAllUsers.postValue(userDetailList);
//                Timber.tag("onResponse").d("all users=========%s ", userDetailList.size());

            }

            @Override
            public void onFailure(Call<AllUsers> call, Throwable t) {

            }
        });
    }

    private void loadContacts() {
        mContacts = new ArrayList<>();

        //to store name-number pair
        JSONObject obj = new JSONObject();

        try {

            Cursor phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

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

            Collections.sort(mContacts, (o1, o2) -> (int) (o1.getName().compareTo(o2.getName())));

            Timber.tag("SortedContacts").d("Name A--Z:%s", mContacts);


            mContactViewModel = new ViewModelProvider(PhoneBookActivity.this).get(ContactViewModel.class);
            mContactViewModel.setUsersLiveData(mUserDetailList);
            mContactViewModel.setPhoneNumbers(mContacts);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List compareContacts(List<AllUsers.UserDetail> hsContacts, List<PhoneNumbers> contacts) {

        if (hsContacts != null) {
            Timber.tag("compareContacts").d("user=========%s ", contacts.size());
            //Timber.tag("compareContacts").d("con=^^^^^%s ", hsContacts.toString());
            for (AllUsers.UserDetail contact : hsContacts
            ) {
                Timber.tag("compareContacts").d("contact=^^^^^%s ", contact.getPhone().trim());
                compareHsContact(contact.getPhone(), contacts);
            }

        }


        return searchList;
    }

    private List compareHsContact(String contact, List<PhoneNumbers> phonecontacts) {
        String onHsContact;
        for (PhoneNumbers phonecontact : phonecontacts
        ) {
            if (phonecontact.getPhone().trim().equals(contact) || phonecontact.getPhone().substring(1).trim().equals(contact.substring(1))) {
                searchList.add(new HSContacts(phonecontact.getName(), phonecontact.getPhone(),phonecontact.getAccountno()));

            }
        }
        Timber.tag("FromPhone").d("searchList=^^^^^%s ", searchList.toString());
        return searchList;
    }


    @Override
    public void onClickContact(View view, int position) {
        PhoneNumbers phoneNumbers = mAdapter.getPhoneNumbers(position);
        Intent data = new Intent();
        data.putExtra("phone", phoneNumbers.getPhone());
        setResult(RESULT_OK, data);
        PhoneBookActivity.this.finish();
    }

    @Override
    public void onclickContactOnHs(View v, int position) {
        HSContacts userDetail = onHsAdapter.getUserDetail(position);
        Intent data = new Intent();
        data.putExtra("phone", userDetail.getPhoneno());
        setResult(RESULT_OK, data);
        PhoneBookActivity.this.finish();

    }
}