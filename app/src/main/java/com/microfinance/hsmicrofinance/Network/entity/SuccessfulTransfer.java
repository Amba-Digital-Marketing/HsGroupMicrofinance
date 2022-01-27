package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuccessfulTransfer {

    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("Transfer Details")
    @Expose
    public TransferDetails transferDetails;

    public class TransferDetails {

        @SerializedName("user_id")
        @Expose
        public String  user_id;

        @SerializedName("info")
        @Expose
        public String  info;

        /*  "": 75,
        "trxid": "TfoO8DSy1NJHLb05",
        "amount": "520.0",
        "balance": 7951,
        "fee": "0.0",
        "status": 1,
        "": "H&S Group Transfer from Acc Holder: Japheth Kiprotich To Acc Holder: Dan Sang",
        "type": "H&S Group Transfer",
        "updated_at": "2021-11-11T08:20:28.000000Z",
        "created_at": "2021-11-11T08:20:28.000000Z",
        "id": 276*/
    }
}
