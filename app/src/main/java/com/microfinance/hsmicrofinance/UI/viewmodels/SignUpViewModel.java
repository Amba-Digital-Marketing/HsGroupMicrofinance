package com.microfinance.hsmicrofinance.UI.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.microfinance.hsmicrofinance.Network.APIService;
import com.microfinance.hsmicrofinance.Network.RetrofitInstance;
import com.microfinance.hsmicrofinance.Network.entity.UserRegResponse;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.BaseLogin;
import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.HomeActivity;
import com.microfinance.hsmicrofinance.UI.LoginActivity;
import com.microfinance.hsmicrofinance.databinding.FragmentPinSetterAccountSettingBinding;
import com.microfinance.hsmicrofinance.databinding.FragmentPinlockBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {


    private static final String TAG = "SignUpViewModel";
    private APIService mApiService;
    private Call<UserRegResponse> mCall;
    public MutableLiveData<Integer> mRegCodeLiveData = new MutableLiveData<>();
    MutableLiveData<UserRegResponse> mRegLiveData = new MutableLiveData<>();

    public MutableLiveData<UserRegResponse> getUserObserver() {
        return mRegLiveData;
    }

    public void registerUser(UserRegistrationDetails regDetails, Fragment host, FragmentPinlockBinding fragmentPinSetterBinding) {
        try {
            File file = persistImage(host.getActivity(), regDetails.getBitmap(), "kra");
            RequestBody requestFile = RequestBody.create(file,MediaType.parse("*/*"));
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("kra", "kra", requestFile);
            RequestBody name = RequestBody.create(regDetails.getName(), MediaType.parse("text/plain"));
            RequestBody email = RequestBody.create(regDetails.getEmail(), MediaType.parse("text/plain"));
            RequestBody kra = RequestBody.create(regDetails.getKra(), MediaType.parse("text/plain"));
            RequestBody phoneno = RequestBody.create(regDetails.getPhone_number(), MediaType.parse("text/plain"));
            RequestBody password = RequestBody.create( regDetails.getPassword(), MediaType.parse("text/plain"));
            RequestBody termcond = RequestBody.create(regDetails.getTerm_condition(), MediaType.parse("text/plain"));
            RequestBody pin = RequestBody.create(regDetails.getPin(), MediaType.parse("text/plain"));



            Log.d(TAG, "registerUser: Name" + file.getName() + " +++++++++++++++++++ filePath" + file.getPath() + " +++++RequestBody" + requestFile.contentType().toString());
            mApiService = RetrofitInstance.getRetroClient(host.getContext()).create(APIService.class);
            fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
            mCall = mApiService.registerUser(name, email, phoneno, kra, password, password, requestFile, termcond, pin);
            Log.d(TAG, "Reg Items: " + regDetails.getEmail() + "   " + regDetails.getMyfilePart() + regDetails.getTerm_condition() + regDetails.getPin());

            mCall.enqueue(new Callback<UserRegResponse>() {
                @Override
                public void onResponse(Call<UserRegResponse> call, Response<UserRegResponse> response) {
                    fragmentPinSetterBinding.progrebar.setVisibility(View.GONE);
                    mRegCodeLiveData.postValue(response.code());
                    if (response.code() == 201 && response.isSuccessful()) {
                        if (response.body() != null) {
                            try {
                                mRegLiveData.postValue(response.body());
                                Log.d(TAG, "onResponse: " + response.body());
                                mRegCodeLiveData.postValue(response.code());

                                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(host.getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialog.setTitleText("Success")
                                        .setContentText("You are now a member of our group \n You will be redirected to login")
                                        .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                                        .setConfirmClickListener(v -> v.dismiss())
                                        .setCancelable(false);

                                sweetAlertDialog.show();

                                Drawable drawable = host.getResources().getDrawable(R.drawable.ic_baseline_check_24);
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(() -> {
                                    sweetAlertDialog.dismiss();
//                                    fragmentPinSetterBinding.tvPinLogin.setText("Pin Set Successful");
//                                    fragmentPinSetterBinding.ivpinLock.setImageDrawable(drawable);
//                                    fragmentPinSetterBinding.progrebar.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(host.getContext(), LoginActivity.class);
                                    intent.putExtra("PUT_EMAIL", regDetails.getEmail());
                                    host.startActivity(intent);
                                    host.getActivity().finishAffinity();
                                }, 1000);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            //saveUsertoLocalDB(response.body(),context);
                        }
                    } else {
                        new SweetAlertDialog(host.getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Failed")
                                .setContentText("We are facing a problem registering\n" +
                                        "It will be solved shortly")
                                .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                                .setConfirmClickListener(v -> {
                                    v.dismiss();
                                    //setFragment(new BaseLogin());
                                })
                                .show();
                    }


                }

                @Override
                public void onFailure(Call<UserRegResponse> call, Throwable t) {
                    fragmentPinSetterBinding.progrebar.setVisibility(View.GONE);
                    new SweetAlertDialog(host.getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Failed")
                            .setContentText("We are facing a problem registering\n" +
                                    "It will be solved shortly")
                            .setConfirmButtonBackgroundColor(Color.parseColor("#297545"))
                            .setConfirmClickListener(v -> {
                                v.dismiss();

                                //

                                // setFragment(new BaseLogin());
                            })
                            .show();
                    mRegLiveData = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File persistImage(Context context, Bitmap bitmap, String name) {
        File filesDir = context.getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 15, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e(TAG, "Error writing bitmap", e);
        }
        return imageFile;
    }

    // set Fragment
    private void setFragment(Fragment host, Fragment fragment) {
        host.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

    }

}
