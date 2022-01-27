package com.microfinance.hsmicrofinance.LocalDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDB extends RoomDatabase  {
        public abstract UserDao userDao();

        private static  UserDB INSTANCE;

        public static  UserDB getDbInstance(Context context){
                if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "Db_name")
                                .allowMainThreadQueries()
                                .build();
                }

                return INSTANCE;
        }



}
