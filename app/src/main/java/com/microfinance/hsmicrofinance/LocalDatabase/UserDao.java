package com.microfinance.hsmicrofinance.LocalDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

        @Query("SELECT * FROM userentity")
        List<UserEntity> getAll();

        @Query("SELECT * FROM userentity WHERE uid IN (:uid)")
        List<UserEntity> loadAllByIds(int uid);

        @Query("SELECT * FROM userentity WHERE uid IN (:uid)")
        UserEntity loaduserById(int uid);

        @Query("SELECT usertoken FROM userentity WHERE uid =:uid")
         String getToken(int uid);

        @Query("SELECT verificationStatus FROM userentity WHERE uid =:uid")
        Integer getVerificationStatus(int uid);

        @Query("UPDATE userentity SET verificationCode =:verificationCode WHERE uid =:uid")
        void updateVerificationCode(int verificationCode, int uid);

        @Query("SELECT verificationCode FROM userentity WHERE uid =:uid")
        Integer getVerificationCode(int uid);

        @Query("UPDATE userentity SET verificationStatus =:verificationstatus WHERE uid =:uid")
        void updateVerificationStatus(int verificationstatus, int uid);

        @Query("UPDATE userentity SET pin =:pin WHERE uid =:uid")
        void updateId(String pin, int uid);

        @Query("UPDATE userentity SET accBallance =:accBallance, name =:name WHERE uid =:uid")
        void updateAccBallance(double accBallance,String name, int uid);
        @Query("UPDATE userentity SET name =:name WHERE uid=:uid")
        void updateUserName(String name,int uid);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(UserEntity... users);

        @Delete
        void delete(UserEntity user);
    }

