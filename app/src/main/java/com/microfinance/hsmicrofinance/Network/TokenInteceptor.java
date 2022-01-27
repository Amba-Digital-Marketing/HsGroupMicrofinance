package com.microfinance.hsmicrofinance.Network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;


import com.microfinance.hsmicrofinance.LocalDatabase.UserDB;
import com.microfinance.hsmicrofinance.LocalDatabase.UserDao;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInteceptor implements Interceptor {

   String token = "";
    Context context;
    private Request request;

    public TokenInteceptor(Context context) {
        this.context = context;
    }
    public TokenInteceptor(String  token) {
        this.token = token;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        if(token == "" || token == null){
          try{
              Handler handler = new Handler(Looper.getMainLooper());

              handler.postDelayed(() ->{
                  UserDao db = UserDB.getDbInstance(context).userDao();
                  this.token =db.getToken(1);
              },1000);

          }catch (Exception e){
              e.printStackTrace();
          }
          request = chain.request().newBuilder().header("Authorization","Bearer "+token).build();
        }else if(token != "" && token != null){
            request = chain.request().newBuilder().header("Authorization","Bearer "+token).build();
        }
        return chain.proceed(request);
    }

}
