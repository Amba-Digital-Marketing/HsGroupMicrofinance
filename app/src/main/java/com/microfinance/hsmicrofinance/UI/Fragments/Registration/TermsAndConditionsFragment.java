package com.microfinance.hsmicrofinance.UI.Fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microfinance.hsmicrofinance.databinding.FragmentTermsAndConditionsBinding;


public class TermsAndConditionsFragment extends Fragment {
FragmentTermsAndConditionsBinding binding;

    public TermsAndConditionsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTermsAndConditionsBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }


}