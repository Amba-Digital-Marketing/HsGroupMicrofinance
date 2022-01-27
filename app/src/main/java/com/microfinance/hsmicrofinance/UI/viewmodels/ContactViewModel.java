package com.microfinance.hsmicrofinance.UI.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.PhoneNumbers;

import java.util.List;

public class ContactViewModel extends ViewModel {
    MutableLiveData<List<PhoneNumbers>> mPhoneNumbers;
    MutableLiveData<List<AllUsers.UserDetail>>mUsersLiveData;


    public ContactViewModel() {
        mPhoneNumbers = new MutableLiveData<>();
        mUsersLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<PhoneNumbers>> getPhoneNumbers() {
        return mPhoneNumbers;
    }

    public MutableLiveData<List<AllUsers.UserDetail>> getUsersLiveData() {
        return mUsersLiveData;
    }



    public void setPhoneNumbers (List<PhoneNumbers>list){
        mPhoneNumbers.setValue(list);
    }
    public void setUsersLiveData(List<AllUsers.UserDetail>userDetails){
        mUsersLiveData.setValue(userDetails);
    }


}
