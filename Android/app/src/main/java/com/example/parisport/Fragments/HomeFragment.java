package com.example.parisport.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.parisport.Activity.MatchActivity;
import com.example.parisport.Adapter.ListMatchAdapter;
import com.example.parisport.Interface.async_call_back;
import com.example.parisport.Modele.Match;
import com.example.parisport.Service.Service;
import com.example.parisport.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // get listes matchs from API
        Service.getAllMatchFoot(new async_call_back() {
            @Override
            public void getAllMatchFoot(ArrayList<Match> matchArrayList) {
                try {
                    ListMatchAdapter listMatchAdapter = new ListMatchAdapter(getActivity(), matchArrayList);

                    Log.i("TAILLE MATCH ARRAY LIST" , ""+matchArrayList.size());

                    binding.listHome.setAdapter(listMatchAdapter);
                    binding.listHome.setClickable(true);
                    binding.listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Log.i("ONCLICK LISTENER", String.valueOf((position)));

                            Intent i = new Intent(getActivity(), MatchActivity.class);

                            i.putExtra("id", matchArrayList.get(position).getId());
                            i.putExtra("lieuMatch", matchArrayList.get(position).getLieuMatch());
                            i.putExtra("dateMatch", matchArrayList.get(position).getDateMatch());
                            i.putExtra("equipe1", matchArrayList.get(position).getEquipe1().getNom());
                            i.putExtra("equipe2", matchArrayList.get(position).getEquipe2().getNom());
                            i.putExtra("cotev1", matchArrayList.get(position).getCotev1());
                            i.putExtra("cotev2", matchArrayList.get(position).getCotev2());
                            i.putExtra("cotex", matchArrayList.get(position).getCotex());
                            i.putExtra("resultat", matchArrayList.get(position).getResultat());

                            startActivity(i);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}