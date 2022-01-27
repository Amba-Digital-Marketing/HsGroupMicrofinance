package com.microfinance.hsmicrofinance.UI;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import timber.log.Timber;

public class MyTimber extends Application {

    static SharedPreferences sharedpreferences;
    static SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "Bills";
    public static final String billName = "bill_name";
    public static final String accountNumber = "account_number";
    public static final String airtimePosition = "position";
    public static final String firebaseToken = "token";
    public static final String pinTrials = "pintrials";
    public static final String session_currency_id = "session_currency_id";
    public static final String session_amount = "session_amount";
    public static final String session_account_holder_name = "session_account_holder_name";
    public static final String session_account_no = "session_account_no";
    public static final String session_currency_id1 = "session_currency_id1";
    public static final String session_currency_rate = "session_currency_rate";
    public static final String session_branch = "session_branch";
    public static final String session_bank = "session_bank";
    public static final String session_total_charge = "session_total_charge";

    public static void setCurrentBill(String bill_name) {
        editor.putString(billName, bill_name);
        editor.commit();
    }

    public static String getCurrentBill() {
        return sharedpreferences.getString(billName, "");
    }

    public static String getCurrentAccountNumber() {
        return sharedpreferences.getString(accountNumber, "");
    }

    public static void setCurrentAccountNumber(String account_number) {
        editor.putString(accountNumber, account_number);
        editor.commit();
    }

    public static void setCurrentAirtime(int position) {
        editor.putInt(airtimePosition, position);
        editor.commit();
    }

    public static int getCurrentAirtime() {
        return sharedpreferences.getInt(airtimePosition, 0);
    }

    public static void setFirebaseToken(String token) {
        editor.putString(firebaseToken, token);
        editor.commit();
    }

    public static void setPinTrials(int trials) {
        editor.putInt(pinTrials, trials);
        editor.commit();
    }

    public static int getPinTrials() {
        return sharedpreferences.getInt(pinTrials, 0);
    }

    public static void saveSessionData(String currency_id, String amount, String account_holder_name,
                                       String account_no, String currency_id1, String currency_rate,
                                       String branch, String bank, String total_charge) {
        editor.putString(session_currency_id, currency_id);
        editor.putString(session_amount, amount);
        editor.putString(session_account_holder_name, account_holder_name);
        editor.putString(session_account_no, account_no);
        editor.putString(session_currency_id1, currency_id1);
        editor.putString(session_currency_rate, currency_rate);
        editor.putString(session_branch, branch);
        editor.putString(session_bank, bank);
        editor.putString(session_total_charge, total_charge);
        editor.commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }
}
