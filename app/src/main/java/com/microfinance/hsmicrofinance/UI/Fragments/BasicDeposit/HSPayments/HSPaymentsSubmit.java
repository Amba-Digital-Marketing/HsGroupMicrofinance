package com.microfinance.hsmicrofinance.UI.Fragments.BasicDeposit.HSPayments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentHSPaymentsSubmitBinding;
import com.microfinance.hsmicrofinance.Network.models.UploadDetails;
import com.microfinance.hsmicrofinance.UI.viewmodels.EdepositViewModel;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;


public class HSPaymentsSubmit extends Fragment {
    private static final String TAG = "HSPayment";
    private final int REQUEST_CODE_PERMISSION = 1001;
    private static final int RESULT_CODE_FILECHOOSER = 2001;
    private Bitmap bitmap;

FragmentHSPaymentsSubmitBinding mBinding;
    private EdepositViewModel mViewModel;
    private String mCurrency;
    private String mId;
    private NavController mNavController;

    public HSPaymentsSubmit() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= FragmentHSPaymentsSubmitBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("H&S Payment");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_HSPaymentsSubmit_to_HSPay));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mViewModel = new ViewModelProvider(requireActivity()).get(EdepositViewModel.class);

        try{
            mViewModel.getDataOfManualDepositObserver().observe(getViewLifecycleOwner(), paymentDetails -> {
              if(paymentDetails != null)  {
                    mBinding.tvAmount.setText("Ksh " + paymentDetails.getAmount());
                    mBinding.tvCharge.setText("Ksh " + paymentDetails.getCharge());
                    mBinding.tvAmountToPay.setText("Ksh " + ( paymentDetails.getPayable()));
                    mBinding.tvUsdAmount.setText("Ksh " + paymentDetails.getUsdAmount());
                    mCurrency = paymentDetails.getCurrency();
                    mId = paymentDetails.getId();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        mBinding.chooseFile.setOnClickListener(v->askPermissionAndBrowseFile());
        mBinding.btnSubmit.setOnClickListener(v->doPayment(view));
    }

    private void doPayment(View view) {
        try{
            if(mBinding.comment.getText().toString().isEmpty()){
                mBinding.comment.setError("Provide a comment");
                mBinding.comment.requestFocus();
            }else if(mBinding.tvFilechoosen.getText().toString().isEmpty()){
                mBinding.tvFilechoosen.setText("* image");
                mBinding.tvFilechoosen.setTextColor(Color.parseColor("#800000"));
            }
            else {
                mBinding.progrebar.setVisibility(View.VISIBLE);
                UploadDetails uploadDetails = new UploadDetails(mBinding.tvAmount.getText().toString(), mBinding.comment.getText().toString(), mCurrency, mId, bitmap);
                mViewModel.manulDepositSubmit(getContext(), uploadDetails,view);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void askPermissionAndBrowseFile() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            // Check if we have Call permission
            int permisson = ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION
                );
                return;
            }
        }
        try {
            this.doBrowseFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (CALL_PHONE).
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission granted!");
                    Toast.makeText(getContext(), "Permission granted!", Toast.LENGTH_SHORT).show();

                    doBrowseFile();
                }
                // Cancelled or denied.
                else {
                    Log.i(TAG, "Permission denied!");
                    Toast.makeText(getContext(), "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void doBrowseFile() {
        try {
            Intent chooseFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFileIntent.setType("*/*");
            // Only return URIs that can be opened with ContentResolver
            chooseFileIntent.addCategory(Intent.CATEGORY_OPENABLE);

            chooseFileIntent = Intent.createChooser(chooseFileIntent, "Choose a file");
            startActivityForResult(chooseFileIntent, RESULT_CODE_FILECHOOSER);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case RESULT_CODE_FILECHOOSER:
                try{
                    if (resultCode == RESULT_OK) {
                        if (data != null) {
                            imagePicked( data);
                        }else {
                            Log.w(TAG, "onActivityResult: Error");
                        }
                    }
                    break;
                }catch (Exception e){
                    e.printStackTrace();
                }

        }

    }

    private void imagePicked(Intent data) {
        Uri uri = data.getData();
        mBinding.tvFilechoosen.setText(new File(uri.getPath()).getName());
        InputStream imageInputStream = null;
        try {
            // Open the input stream of Uri
            imageInputStream = requireActivity().getContentResolver().openInputStream(uri);
            // Parsing the input stream into a Bitmap
            this.bitmap = BitmapFactory.decodeStream(imageInputStream);
            // Display Bitmap to ImageView
        } catch ( Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(imageInputStream);
        }

    }
    private void closeStream(Closeable c) {
        try {
            c.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}