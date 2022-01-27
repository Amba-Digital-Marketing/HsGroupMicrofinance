package com.microfinance.hsmicrofinance.Network.entity;

import android.graphics.Bitmap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserRegistrationDetails {
  String name ="";
    String email ="";
    String phone_number ="";
    String kra ="";
    String password ="";
    String password_confirmation ="";
    MultipartBody.Part myfilePart;
    RequestBody photoBody;
    String term_condition ="";
    String pin  ="";
    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public UserRegistrationDetails(String name, String email, String phone_number, String kra, String password, String password_confirmation, Bitmap bitmap, String term_condition, String pin) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.kra = kra;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.term_condition = term_condition;
        //this.myfilePart = myfilePart;
       // this.photoBody = photoBody;
        this.pin = pin;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getKra() {
        return kra;
    }

    public void setKra(String kra) {
        this.kra = kra;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public MultipartBody.Part getMyfilePart() {
        return myfilePart;
    }

    public void setMyfilePart(MultipartBody.Part myfilePart) {
        this.myfilePart = myfilePart;
    }

    public RequestBody getPhotoBody() {
        return photoBody;
    }

    public void setPhotoBody(RequestBody photoBody) {
        this.photoBody = photoBody;
    }

    public String getTerm_condition() {
        return term_condition;
    }

    public void setTerm_condition(String term_condition) {
        this.term_condition = term_condition;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
