package com.example.parisport.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parisport.R;
import com.example.parisport.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Intent intent = this.getActivity().getIntent();

        if(intent != null){

            String lieuMatch = intent.getStringExtra("lieuMatch");

            binding.detailsLieuMatch.setText(lieuMatch);

        }

        return view;
    }
}