package com.example.parisport.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.parisport.Activity.MatchActivity;
import com.example.parisport.Adapter.ListMatchAdapter;
import com.example.parisport.Interface.async_call_back;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.example.parisport.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Service.getAllMatchFoot(new async_call_back() {
                            @Override
                            public void getAllMatchFoot(ArrayList<MatchFoot> matchFootArrayList) {
                                ListMatchAdapter listMatchAdapter = new ListMatchAdapter(getActivity(), matchFootArrayList);

                                int i = listMatchAdapter.getCount();



                                binding.listHome.setAdapter(listMatchAdapter);
                                binding.listHome.setClickable(true);
                                binding.listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Log.i("ONCLICK LISTENER", String.valueOf((position)));

                                        Intent i = new Intent(getActivity(), MatchActivity.class);

                                        i.putExtra("lieuMatch", matchFootArrayList.get(position).getLieuMatch());
                                        i.putExtra("dateMatch", matchFootArrayList.get(position).getDateMatch());
                                        i.putExtra("equipe1", matchFootArrayList.get(position).getEquipe1().getNom());
                                        i.putExtra("equipe2", matchFootArrayList.get(position).getEquipe2().getNom());
                                        i.putExtra("cotev1", matchFootArrayList.get(position).getCotev1());
                                        i.putExtra("cotev2", matchFootArrayList.get(position).getCotev2());
                                        i.putExtra("cotex", matchFootArrayList.get(position).getCotex());
                                        i.putExtra("resultat", matchFootArrayList.get(position).getResultat());

                                        startActivity(i);
                                    }
                                });
                            }
                        });
                        try {
                            Thread.sleep(100);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

         */

        // get listes matchs from API
        Service.getAllMatchFoot(new async_call_back() {
            @Override
            public void getAllMatchFoot(ArrayList<MatchFoot> matchFootArrayList) {
                ListMatchAdapter listMatchAdapter = new ListMatchAdapter(getActivity(), matchFootArrayList);

                binding.listHome.setAdapter(listMatchAdapter);
                binding.listHome.setClickable(true);
                binding.listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("ONCLICK LISTENER", String.valueOf((position)));

                        Intent i = new Intent(getActivity(), MatchActivity.class);

                        i.putExtra("id", matchFootArrayList.get(position).getId());
                        i.putExtra("lieuMatch", matchFootArrayList.get(position).getLieuMatch());
                        i.putExtra("dateMatch", matchFootArrayList.get(position).getDateMatch());
                        i.putExtra("equipe1", matchFootArrayList.get(position).getEquipe1().getNom());
                        i.putExtra("equipe2", matchFootArrayList.get(position).getEquipe2().getNom());
                        i.putExtra("cotev1", matchFootArrayList.get(position).getCotev1());
                        i.putExtra("cotev2", matchFootArrayList.get(position).getCotev2());
                        i.putExtra("cotex", matchFootArrayList.get(position).getCotex());
                        i.putExtra("resultat", matchFootArrayList.get(position).getResultat());

                        startActivity(i);
                    }
                });
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