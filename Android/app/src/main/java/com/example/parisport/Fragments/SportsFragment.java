package com.example.parisport.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.example.parisport.databinding.FragmentBetBinding;
import com.example.parisport.databinding.FragmentHomeBinding;
import com.example.parisport.databinding.FragmentSportsBinding;

public class SportsFragment extends Fragment {

    private FragmentSportsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSportsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Service.getHistoriqueParisToJson();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}