package com.microfinance.hsmicrofinance.UI.Fragments.BasicPayForFriends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.HSContacts;
import com.microfinance.hsmicrofinance.Network.models.PhoneNumbers;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.ContactsAdapter;
import com.microfinance.hsmicrofinance.UI.adapters.ContactsOnHsAdapter;
import com.microfinance.hsmicrofinance.UI.viewmodels.ContactViewModel;
import com.microfinance.hsmicrofinance.databinding.FragmentContactsBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class Contacts extends Fragment implements ContactsAdapter.ContactInterface, ContactsOnHsAdapter.onHsInterface {
    FragmentContactsBinding mFragmentContactsBinding;
    List<PhoneNumbers> mNumbersList;
    List<AllUsers.UserDetail> mUserDetailList;
    private ContactsAdapter mAdapter;
    private ContactViewModel mContactViewModel;
    private List<PhoneNumbers> mOnHSlist = new ArrayList<>();
    private NavController navController;
    private ContactsOnHsAdapter onHsAdapter;
    private String fragmentToLoad;

    public Contacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentContactsBinding = FragmentContactsBinding.inflate(inflater, container, false);
        return mFragmentContactsBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        try {
            if(getArguments()!= null){
                this.fragmentToLoad = getArguments().getString("FRAGMENT");

            }

            mContactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
            mContactViewModel.getPhoneNumbers().observe(getViewLifecycleOwner(), list -> {
                mNumbersList = list;

                mAdapter = new ContactsAdapter(requireActivity(), mNumbersList, this);
                mFragmentContactsBinding.rvContacts.setAdapter(mAdapter);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mContactViewModel.getUsersLiveData().observe(getViewLifecycleOwner(), userDetails -> {

                if (userDetails == null) {
                    return;
                }
                mUserDetailList = userDetails;
                searchList = compareContacts(mUserDetailList, mNumbersList);

                onHsAdapter = new ContactsOnHsAdapter(requireActivity(), searchList, Contacts.this::onclickContactOnHs);

                mFragmentContactsBinding.rvMembers.setAdapter(onHsAdapter);
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // compare phone contacts
    List<HSContacts> searchList = new ArrayList<>();

    private List compareContacts(List<AllUsers.UserDetail> hsContacts, List<PhoneNumbers> contacts) {

        if (hsContacts != null) {
            Timber.tag("compareContacts").d("user=========%s ", contacts.size());
            //Timber.tag("compareContacts").d("con=^^^^^%s ", hsContacts.toString());
            for (AllUsers.UserDetail contact : hsContacts
            ) {
                Timber.tag("compareContacts").d("contact=^^^^^%s ", contact.getPhone().trim());
                compareHsContact(contact.getPhone(),contact.getAccountNumber(), contacts);
            }

        }


        return searchList;
    }

    private List compareHsContact(String contact,String account, List<PhoneNumbers> phonecontacts) {
        String onHsContact;
        for (PhoneNumbers phonecontact : phonecontacts
        ) {
            if (phonecontact.getPhone().trim().equals(contact) || phonecontact.getPhone().substring(1).trim().equals(contact.substring(1))) {
                searchList.add(new HSContacts(phonecontact.getName(), phonecontact.getPhone(),account));

            }
        }
        Timber.tag("FromPhone").d("searchList=^^^^^%s ", searchList.toString());
        return searchList;
    }


    @Override
    public void onClickContact(View view, int position) {
        PhoneNumbers phoneNumbers = mAdapter.getPhoneNumbers(position);
        Bundle args = new Bundle();
        Timber.d(String.valueOf(phoneNumbers));
        args.putString("phone", phoneNumbers.getPhone());
        if(this.fragmentToLoad.equals("PAYFORFRIENDS")) {
            navController.navigate(R.id.action_contacts_to_payFriendsViaMpesa, args);
        }else if(this.fragmentToLoad.equals("BANKTRANSFER")){
            navController.navigate(R.id.action_contacts_to_transferFundsFragment, args);
        }

    }

    @Override
    public void onclickContactOnHs(View v, int position) {
        HSContacts userDetail = onHsAdapter.getUserDetail(position);
        Bundle args = new Bundle();
        args.putString("onHsPhone", userDetail.getAccountno());

        if(this.fragmentToLoad.equals("PAYFORFRIENDS")) {
            navController.navigate(R.id.action_contacts_to_payFriendsViaMpesa, args);
        }else if(this.fragmentToLoad.equals("BANKTRANSFER")){
            navController.navigate(R.id.action_contacts_to_transferFundsFragment, args);
        }
    }
}