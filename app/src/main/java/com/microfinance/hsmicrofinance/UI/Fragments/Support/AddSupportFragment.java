package com.microfinance.hsmicrofinance.UI.Fragments.Support;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.FragmentAddSupportBinding;
import com.microfinance.hsmicrofinance.UI.viewmodels.CreateSupportViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddSupportFragment extends Fragment {

    FragmentAddSupportBinding fragmentAddSupportBinding;
    CreateSupportViewModel createSupportViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAddSupportBinding = FragmentAddSupportBinding.inflate(inflater,container,false);
        createSupportViewModel = new  ViewModelProvider(this).get(CreateSupportViewModel.class);

        return fragmentAddSupportBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        fragmentAddSupportBinding.btnsubmitSupport.setOnClickListener(v ->{
            getuserInput(view);
        });



    }
    private void getuserInput(View view){
       String title =   fragmentAddSupportBinding.etTitle.getText().toString().trim();
        String comment =fragmentAddSupportBinding.etcomment.getText().toString().trim();
        if(title != null && title != ""){
            if(comment != null && comment != ""){
                createSupportViewModel.createSupport(getContext(),view,title,comment);
            }else{
                Toast.makeText(getContext(), "Input a Message", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(getContext(), "Input a Title", Toast.LENGTH_LONG).show();
        }

    }



}