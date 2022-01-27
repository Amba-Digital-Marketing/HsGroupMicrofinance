package com.microfinance.hsmicrofinance.UI.Fragments.Registration;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import timber.log.Timber;


public class RegSharedPref extends Application {
    static String prefTitle = "RegistrationPref";
    static String prefname = "name";
    static String prefemail = "email";
    static String prefpassword = "password";
    static String prefconfirmPassword = "confirmPassword";


    Context context;
    private  static SharedPreferences regpref;
    private static SharedPreferences.Editor editor;

    public RegSharedPref(Context context) {
        this.context = context;
    }

    public RegSharedPref() {
    }

    public static void saveSessionFirstRegistrationData(String name, String email, String password, String confirmPassword) {
        editor.putString(prefname, name);
        editor.putString(prefemail, email);
        editor.putString(prefpassword, password);
        editor.putString(prefconfirmPassword, confirmPassword);

        editor.commit();
    }

    public static void saveSecondRegistrationData(String name, String email, String phone_number, String kra, String password, String password_confirmation, Bitmap bitmap, String term_condition, String pin) {
        editor.putString(prefname, name);
        editor.putString(prefemail, email);
        editor.putString(prefpassword, password);
        editor.putString(prefconfirmPassword, password);

        editor.commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        regpref = getSharedPreferences(prefTitle,MODE_PRIVATE);
        editor = regpref.edit();
    }

}
