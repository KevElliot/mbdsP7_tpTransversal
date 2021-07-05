package com.example.parisport.Service;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Service {

    public static String urlMatchAPI = "https://tpt-server-grails.herokuapp.com/matchApi/matchs";
    public static String urlPlacerParisAPI = "https://tpt-server-grails.herokuapp.com/parisApi/paris";
    public static String urlListesParisParClientAPI = "https://tpt-server-grails.herokuapp.com/parisApi/listeParis?idclient=abcd";
    public static String urlListesEquipesAPI = "https://tpt-server-grails.herokuapp.com/equipeApi/equipes";

    public static void getAllMatchFoot(){

        AndroidNetworking.get(urlMatchAPI)
                .build()
                .getAsObjectList(MatchFoot.class, new ParsedRequestListener<List<MatchFoot>>() {
                    @Override
                    public void onResponse(List<MatchFoot> matchFoots) {
                        // do anything with response
                        for (MatchFoot matchFoot : matchFoots) {
                            Log.d(TAG, "Lieu a domicile : " + matchFoot.getLieuMatch());
                            Equipe tmpEquipe1 = matchFoot.getEquipe1();
                            Equipe tmpEquipe2 = matchFoot.getEquipe2();
                            Log.d(TAG, "Equipe 1 : " + tmpEquipe1.getNom());
                            Log.d(TAG, "Equipe 2 : " + tmpEquipe2.getNom());
                            Log.d(TAG, "cotev1 : " + matchFoot.getCotev1());
                            Log.d(TAG, "cotev2 : " + matchFoot.getCotev2());
                            Log.d(TAG, "cotex : " + matchFoot.getCotex());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }

}
