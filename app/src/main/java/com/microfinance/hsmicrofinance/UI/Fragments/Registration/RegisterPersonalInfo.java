package com.microfinance.hsmicrofinance.UI.Fragments.Registration;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentRegisterPersonalInfoBinding;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;
import com.microfinance.hsmicrofinance.UI.viewmodels.SignUpViewModel;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

public class RegisterPersonalInfo extends Fragment {
    FragmentRegisterPersonalInfoBinding binding;
    SignUpViewModel signUpViewModel;
    private final int REQUEST_CODE_PERMISSION = 1;
    private static final int RESULT_CODE_FILECHOOSER = 201;

    private Bitmap bitmap;

    private static final String TAG = "RegisterFragment";
    String name,email,password,confirmPassword;

    public RegisterPersonalInfo(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterPersonalInfoBinding.inflate(inflater,container,false);

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.bgHeader);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


        binding.bgHeader.setNavigationOnClickListener(v -> {

          setFragment(new RegisterFragment());

                });

        // Inflate the layout for this fragment

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);

        binding.chooseFile.setOnClickListener(view1 -> askPermissionAndBrowseFile());

        binding.tvagreeTermsConditions.setOnClickListener(v -> setFragment(new TermsAndConditionsFragment()));
        binding.tvTermsAndConditions.setOnClickListener(v -> setFragment(new TermsAndConditionsFragment()));

        binding.RegisterBtn.setOnClickListener(view1 -> {

            getUserInput();
        });

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

                    doBrowseFile();
                }
                // Cancelled or denied.
                else {
                    Toast.makeText(getContext(), "This Permision is required!", Toast.LENGTH_SHORT).show();
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
        binding.tvFilechoosen.setText(new File(uri.getPath()).getName());
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

    private UserRegistrationDetails getUserInput(){
        UserRegistrationDetails userRegistrationDetails = null;


        String phone_number,kra;

        phone_number = binding.signupPhone.getText().toString();
        kra = binding.signupID.getText().toString();

                if(phone_number.trim()!= ""){
                    if(phone_number.trim().length() >= 10) {
                        if (kra != null && kra.trim() != "" && kra.trim().length() > 6) {
                            if (bitmap != null) {
                                if (binding.agreeTermsCheckbox.isChecked()) {
                                    userRegistrationDetails = new UserRegistrationDetails(name, email, phone_number, kra, password, password, bitmap, "true", "1234");

                                    setFragment(new PinSetterFragment(userRegistrationDetails));
                                } else {
                                    binding.tvtermsAlert.setText("Accept the terms and conditions first");
                                }

                            } else {
                                binding.tvidphotoAlert.setText("ID/Passport photo required");
                            }
                        } else {
                            binding.tvidalert.setText("National ID/Passport Number required");
                        }
                    }else{
                        binding.tvPhonealert.setText("Phone Number must have 10 or 12 digits");
                    }
                }else{
                    binding.tvPhonealert.setText("Phone Number is required");

                }




        return  userRegistrationDetails;
    }


    public void setFragment(Fragment fragment){

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();

    }



}