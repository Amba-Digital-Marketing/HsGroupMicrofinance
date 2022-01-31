package com.microfinance.hsmicrofinance.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String BASE_URL = "https://whispering-inlet-50206.herokuapp.com/";
    public static String HS_BASE_URL = "https://www.member.hsgroup.tech/api/";

    private static Retrofit sRetrofit;
    private static HttpLoggingInterceptor sHttpLoggingInterceptor;
    private static OkHttpClient sOkHttpClient;
    private static TokenInteceptor sInteceptor;


    public static Retrofit getRetroClient(Context context){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        sHttpLoggingInterceptor = new HttpLoggingInterceptor();
        sHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        sInteceptor = new TokenInteceptor(context);
        sOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(sHttpLoggingInterceptor)
                .addInterceptor(sInteceptor)
                .build();

        if(sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(HS_BASE_URL)
                    //.addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                   .client(getUnsafeOkHttpClient().build())
                    //.client(sOkHttpClient)

                    .build();
        }
        else {
            //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            System.out.println("Empty Empty");
        }


        return sRetrofit;


}


//here
   private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //here

    public static Retrofit getRetroClientWithToken(String token){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        sHttpLoggingInterceptor = new HttpLoggingInterceptor();
        sHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        sInteceptor = new TokenInteceptor(token);

        sOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(sHttpLoggingInterceptor)
                .addInterceptor(sInteceptor)
                .build();

            if(sRetrofit == null){
                sRetrofit = new Retrofit.Builder()
                        .baseUrl(HS_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(sOkHttpClient)
                        .build();
            }
            else {
                //Toast.makeText(sInteceptor.context, "empty token", Toast.LENGTH_SHORT).show();
                System.out.println("Empty Retrofit with token");

            }



        return sRetrofit;


    }





}
