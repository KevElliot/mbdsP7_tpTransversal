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
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.example.parisport.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // getListMatch SERVICE
        Service.getAllMatchFoot();

        // getListMatch from database
        ArrayList<MatchFoot> matchFootArrayList = new ArrayList<>();

        float[] ct1 = {1,2,2,3,5,6,7,8,9};
        float[] ct2 = {4,5,7,7,6,2,7,2,1};
        float[] ctx = {7,8,6,8,9,3,5,7,8};
        String[] lieuM = {"A","B","B","B","B","B","B","B","B"};
        String[] resultat = {"OK","NOT OK","NOT OK","NOT OK","NOT OK","NOT OK","NOT OK","NOT OK","NOT OK"};

        Equipe tmp1 = new Equipe(1, "Italy",10);
        Equipe tmp2 = new Equipe(2,"France",8);

        Date date = new Date(System.currentTimeMillis());

        for(int i = 0; i<9;i++){
            MatchFoot tmp = new MatchFoot(i, tmp1, tmp2, ct1[i], ct2[i], ctx[i], date, lieuM[i], resultat[i]);
            matchFootArrayList.add(tmp);
        }


        ListMatchAdapter listMatchAdapter = new ListMatchAdapter(getActivity(), matchFootArrayList);

        binding.listHome.setAdapter(listMatchAdapter);
        binding.listHome.setClickable(true);
        binding.listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("ONCLICK LISTENER", String.valueOf((position)));

                Intent i = new Intent(getActivity(), MatchActivity.class);
                i.putExtra("lieuMatch",matchFootArrayList.get(position).getLieuMatch());
                i.putExtra("dateMatch",matchFootArrayList.get(position).getDateMatch());
                i.putExtra("equipe1",matchFootArrayList.get(position).getEquipe1().getNom());
                i.putExtra("equipe2",matchFootArrayList.get(position).getEquipe2().getNom());
                i.putExtra("cotev1",matchFootArrayList.get(position).getCotev1());
                i.putExtra("cotev2",matchFootArrayList.get(position).getCotev2());
                i.putExtra("cotex",matchFootArrayList.get(position).getCotex());
                i.putExtra("resulat",matchFootArrayList.get(position).getResultat());
                startActivity(i);
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void sendData(MatchFoot matchFoot){

    }


}