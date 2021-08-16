package com.example.parisport.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.parisport.Activity.LoginActivity;
import com.example.parisport.Activity.MatchActivity;
import com.example.parisport.Activity.SignUpActivity;
import com.example.parisport.Adapter.ListMatchBetAdapter;
import com.example.parisport.Modele.MatchParis;
import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.example.parisport.databinding.FragmentBetBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class BetFragment extends Fragment {

    private FragmentBetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        List<MatchParis> matchArrayList = Service.readListBet(getContext());

        Log.i("FragmentBetBinding : ", ""+matchArrayList.size());

        ListMatchBetAdapter listMatchBetAdapter = new ListMatchBetAdapter(getActivity(), matchArrayList);

        if(listMatchBetAdapter.isEmpty()){
            Toast.makeText(getContext() ,"There is something error",Toast.LENGTH_LONG).show();
        }
        else {
            binding.listBet.setAdapter(listMatchBetAdapter);
            binding.listBet.setClickable(true);
            binding.listBet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.i("ONCLICK LISTENER", String.valueOf((position)));

                    Intent i = new Intent(getActivity(), MatchActivity.class);

                    i.putExtra("id", matchArrayList.get(position).getId());
                    i.putExtra("equipe1", matchArrayList.get(position).getEquipe1().getNom());
                    i.putExtra("equipe2", matchArrayList.get(position).getEquipe2().getNom());
                    i.putExtra("prono", matchArrayList.get(position).getProno());
                    i.putExtra("mise", matchArrayList.get(position).getMise());
                    i.putExtra("cote", matchArrayList.get(position).getCote());
                    i.putExtra("gain", matchArrayList.get(position).getGain());

                    startActivity(i);
                }
            });

            binding.btnValiderBet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Service.validationFinalBet(getContext());
                }
            });
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}