package com.example.parisport.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parisport.R;
import com.example.parisport.databinding.FragmentDetailsBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    /**
     * @param inflater 0
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Intent intent = this.getActivity().getIntent();

        if(intent != null){

            try{
                String lieuMatch = intent.getStringExtra("lieuMatch");
                Date dateMatch = (Date) intent.getSerializableExtra("dateMatch");
                String equipe1 = intent.getStringExtra("equipe1");
                String equipe2 = intent.getStringExtra("equipe2");
                float cotev1 = intent.getFloatExtra("cotev1", 0.0f);
                float cotev2 = intent.getFloatExtra("cotev2", 0.0f);
                float cotex = intent.getFloatExtra("cotex",0.0f);
                String resultat = intent.getStringExtra("resultat");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                binding.detailsLieuMatch.setText(lieuMatch);
                binding.detailsEquipe1.setText(equipe1);
                binding.detailsEquipe2.setText(equipe2);
                binding.detailsCotev1.setText(String.valueOf(cotev1));
                binding.detailsCotev2.setText(String.valueOf(cotev2));
                binding.detailsCotex.setText(String.valueOf(cotex));
                binding.detailsResultat.setText(resultat);
                // binding.detailsDateMatch.setText(sdf.format(dateMatch));
            }
            catch (Exception e){
                throw e;
            }

        }

        return view;
    }
}