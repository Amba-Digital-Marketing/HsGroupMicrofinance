package com.microfinance.hsmicrofinance.PermissionsHelper;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;


public class StorageResultAccess {
    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    public  void startPermissionrequest(Fragment fr, StorageResultAccessInterface fs, Intent intent){
                ActivityResultLauncher<Intent> activityLauncher =
                        fr.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), fs::onResult);
        activityLauncher.launch(intent);
    }
}
