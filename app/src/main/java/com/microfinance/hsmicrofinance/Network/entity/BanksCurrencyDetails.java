package com.microfinance.hsmicrofinance.Network.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BanksCurrencyDetails {

    @SerializedName("Banks Country")
    @Expose
    public List<BanksCountry> banksCountry = null;

    public class BanksCountry {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("name")
        @Expose
        public String name;

    }
}


