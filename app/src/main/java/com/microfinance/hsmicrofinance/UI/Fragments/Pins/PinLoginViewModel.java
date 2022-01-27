package com.microfinance.hsmicrofinance.UI.Fragments.Pins;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PinLoginViewModel extends ViewModel {

    MutableLiveData<Integer> pinloginTrialsMLiveData = new MutableLiveData<>();
    LiveData<Integer> pinloginTrialsLiveData;

    public MutableLiveData<Integer> getPinloginTrialsMLiveData() {
        pinloginTrialsMLiveData = (MutableLiveData<Integer>) pinloginTrialsLiveData;
        return pinloginTrialsMLiveData;
    }

    public MutableLiveData setPinloginTrialsMLiveData(Integer pinlogintrials) {
        pinloginTrialsMLiveData.postValue(pinlogintrials);
        setPinloginTrialsLiveData( pinloginTrialsMLiveData);
     return pinloginTrialsMLiveData;
    }

    public LiveData<Integer> getPinloginTrialsLiveData() {
        return pinloginTrialsLiveData;
    }

    public LiveData<Integer> setPinloginTrialsLiveData(MutableLiveData pinloginTrialsMLiveData) {

        this.pinloginTrialsLiveData = pinloginTrialsMLiveData;
        return pinloginTrialsLiveData;
    }
}
