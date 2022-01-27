package com.microfinance.hsmicrofinance.PermissionsHelper;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.jar.Manifest;

public class PermissionsHelperFragment {
    public  void startPermissionrequest(Fragment fr, FragmentPermissionInterface fs, String manifest){
         ActivityResultLauncher<String> requestPermissionLauncher =
                 fr.registerForActivityResult(new ActivityResultContracts.RequestPermission(), fs::onGranted);

        requestPermissionLauncher.launch(
              manifest
                );
    }
}
