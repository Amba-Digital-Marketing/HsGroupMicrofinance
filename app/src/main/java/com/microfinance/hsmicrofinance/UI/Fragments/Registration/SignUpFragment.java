package com.microfinance.hsmicrofinance.UI.Fragments.Registration;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.microfinance.hsmicrofinance.UI.Fragments.Login.LoginFragment;
import com.microfinance.hsmicrofinance.UI.Fragments.Pins.PinSetterFragment;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentSignUpBinding;
import com.microfinance.hsmicrofinance.Network.entity.UserRegistrationDetails;
import com.microfinance.hsmicrofinance.UI.viewmodels.SignUpViewModel;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;


public class SignUpFragment extends Fragment {
    SignUpViewModel signUpViewModel;
    FragmentSignUpBinding fragmentSignUpBinding;
    private final int REQUEST_CODE_PERMISSION = 1;
    private static final int RESULT_CODE_FILECHOOSER = 201;

    private Bitmap bitmap;

    private static final String TAG = "SignUpFragment";

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return fragmentSignUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);

        fragmentSignUpBinding.chooseFile.setOnClickListener(view1 -> askPermissionAndBrowseFile()
        );
        fragmentSignUpBinding.tvagreeTermsConditions.setOnClickListener(v -> setFragment(new TermsAndConditionsFragment()));
        fragmentSignUpBinding.tvTermsAndConditions.setOnClickListener(v -> setFragment(new TermsAndConditionsFragment()));

        fragmentSignUpBinding.RegisterBtn.setOnClickListener(view1 -> {

            getUserInput();
        });


        //sets up login activity
        fragmentSignUpBinding.loginText.setOnClickListener(v ->{
            setFragment(new LoginFragment());
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
        fragmentSignUpBinding.tvFilechoosen.setText(new File(uri.getPath()).getName());
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

        // Query the details of the picture
       // queryUriDetail(uri);

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


        String name,email,phone_number,kra,password,confirmPassword, term_condition = null;

        name = fragmentSignUpBinding.signupFullname.getText().toString();
        email = fragmentSignUpBinding.signupEmail.getText().toString();
        phone_number = fragmentSignUpBinding.signupPhone.getText().toString();
        kra = fragmentSignUpBinding.signupID.getText().toString();
        password = fragmentSignUpBinding.signupPassword.getText().toString();
        confirmPassword =fragmentSignUpBinding.signupRenterpassword.getText().toString();

        if(name.trim()!= "" && name.trim().length() >2){
            if(email.trim()!= "" && email.trim().length() >6 ){
                email = email.trim().toLowerCase();
                if(phone_number.trim()!= "" && phone_number.trim().length() >= 10){
                    if(kra!=null && kra.trim()!= "" && kra.trim().length() >6){
                        if( password.trim()!= "" && password.trim().length() >6){
                            if(password.equals(confirmPassword)){
                                if(bitmap != null) {
                                   if(fragmentSignUpBinding.agreeTermsCheckbox.isChecked()){
                                       userRegistrationDetails = new UserRegistrationDetails(name, email, phone_number, kra, password, password,bitmap, "true", "1234");

                                       setFragment(new PinSetterFragment(userRegistrationDetails));
                                       Log.d(TAG, "getUserInput: " + userRegistrationDetails.toString());
                                   }else {
                                       Toast.makeText(getContext(),"Accept Terms and Conditions First",Toast.LENGTH_LONG).show();
                                   }

                                }else {
                                    Toast.makeText(getContext(),"Select Image",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getContext(),"Passwords do not match",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getContext(),"Passwords is Too Short",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(getContext(),"Input National ID First",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(),"Input Correct Phone Number",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getContext(),"Enter correct Email",Toast.LENGTH_LONG).show();
                fragmentSignUpBinding.signupEmail.setHint("Enter correct Email");
            }

        }else{
            fragmentSignUpBinding.signupFullname.setHint("Enter correct Name");
        }


        return  userRegistrationDetails;
    }


    public void setFragment(Fragment fragment){

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .addToBackStack(null)
                .commit();

    }
}


