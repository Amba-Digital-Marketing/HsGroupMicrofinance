package com.microfinance.hsmicrofinance.LocalDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
 public UserEntity(int uid, String id,String name,String email,String KRAPin, String phone,String accNo,String accBallance, String pin, String usertoken,int verificationCode,int verificationStatus) {
  this.uid = uid;
  this.id = id;
  this.name = name;
  this.email = email;
  this.KRAPin = KRAPin;
  this.phone = phone;
  this.accNo = accNo;
  this.accBallance = accBallance;
  this.pin = pin;
  this.usertoken = usertoken;
  this.verificationCode = verificationCode;
  this.verificationStatus = verificationStatus;

 }

 @PrimaryKey
 @ColumnInfo(name = "uid")
 public int uid;

 @ColumnInfo(name = "id")
 public String id;

 @ColumnInfo(name = "name")
 public String name;

 @ColumnInfo(name = "email")
 public String email;

 @ColumnInfo(name = "KRAPin")
 public String KRAPin;

 @ColumnInfo(name = "phone")
 public String phone;

 @ColumnInfo(name = "accNo")
 public String accNo;

 @ColumnInfo(name = "pin")
 public String pin;

 @ColumnInfo(name = "accBallance")
 public String accBallance;

 @ColumnInfo(name = "usertoken")
 public String usertoken;

 @ColumnInfo(name = "verificationCode")
 public int verificationCode;

 @ColumnInfo(name = "verificationStatus")
 public int verificationStatus;
}
