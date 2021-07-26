package com.example.parisport.Service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.parisport.Activity.HomeActivity;
import com.example.parisport.Activity.SignInActivity;
import com.example.parisport.Interface.async_call_back;
import com.example.parisport.Interface.async_call_back_login;
import com.example.parisport.Modele.DetailsParis;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.Modele.Paris;
import com.example.parisport.Modele.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.internal.http.StatusLine;

import static android.content.ContentValues.TAG;

public class Service {

    public static String urlMatchAPI = "https://tpt-server-grails.herokuapp.com/matchApi/matchs";
    public static String urlPlacerParisAPI = "https://tpt-server-grails.herokuapp.com/parisApi/paris";
    public static String urlListesParisParClientAPI = "https://tpt-server-grails.herokuapp.com/parisApi/listeParis?idclient=abcd";
    public static String urlListesEquipesAPI = "https://tpt-server-grails.herokuapp.com/equipeApi/equipes";

    public static String urlLoginQR = "https://parilocalnode.herokuapp.com/pariBack/auth/loginQr";
    public static String urlLogin = "https://parilocalnode.herokuapp.com/pariBack/auth/login";
    public static String urlregister = "https://parilocalnode.herokuapp.com/pariBack/auth/register";

    public static void getAllMatchFoot(final async_call_back myCallBack){
        AndroidNetworking.get(urlMatchAPI)
                .build()
                .getAsObjectList(MatchFoot.class, new ParsedRequestListener<List<MatchFoot>>() {
                    @Override
                    public void onResponse(List<MatchFoot> matchFoots) {
                        // do anything with response
                        List<MatchFoot> ListeMatchFoot = new ArrayList<>();
                        for (MatchFoot matchFoot : matchFoots) {
                            matchFoots.removeAll(Collections.singleton(null));
                            ListeMatchFoot.add(matchFoot);
                            myCallBack.getAllMatchFoot((ArrayList<MatchFoot>) ListeMatchFoot);

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

    // login QR
    public static void loginQR(String x, SharedPreferences sharedPreferences, Context context){
        String[] separated = x.split(";");
        User tmp = new User();
        String xname = separated[0].trim();
        String xpassword = separated[1].trim();
        tmp.setEmail(xname.trim());
        tmp.setPassword(xpassword.trim());
        AndroidNetworking.post(urlLoginQR)
                .addBodyParameter(tmp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Gson gson = new Gson();
                        Log.i("LOGIN", String.valueOf(response));
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        try{
                            User tmpUser = gson.fromJson(String.valueOf(response), User.class);
                            editor.putString("_id",tmpUser.getId());
                            editor.putString("name",tmpUser.getName());
                            editor.putFloat("jetons",tmpUser.getJetons());
                            editor.putString("role",tmpUser.getRole());
                            editor.apply();
                            Toast.makeText(context, "Login Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

    public static void loginPost(String x, String y, SharedPreferences sharedPreferences, Context context){
        User tmp = new User();
        tmp.setEmail(x.trim());
        tmp.setPassword(y.trim());
        AndroidNetworking.post(urlLogin)
                .addBodyParameter(tmp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Gson gson = new Gson();
                        Log.i("LOGIN", String.valueOf(response));
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                            try{
                                User tmpUser = gson.fromJson(String.valueOf(response), User.class);
                                editor.putString("_id",tmpUser.getId());
                                editor.putString("name",tmpUser.getName());
                                editor.putFloat("jetons",tmpUser.getJetons());
                                editor.putString("role",tmpUser.getRole());
                                editor.apply();
                                Toast.makeText(context, "Login Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public static DetailsParis[] zavatra (List<DetailsParis> tmp){
        DetailsParis[] detailsParises = new DetailsParis[tmp.size()];
        for(int i = 0; i<tmp.size(); i++){
            detailsParises[i] = tmp.get(i);
            Log.i("DETAILS PARI -- : ",detailsParises.toString());
        }
        return detailsParises;
    }

    public static void ValidationPari(float solde, float jetonsUser, List<DetailsParis> detailsParisList, SharedPreferences sharedPreferences, Context context){
        try{
            // verification solde client avant validation
            if(solde > jetonsUser){
                Toast.makeText(context, "Jetons insuffisant", Toast.LENGTH_SHORT).show();
                // open activity to manapy anle jetons
            }else {
                DetailsParis[] tmpDetailsParis = zavatra(detailsParisList);
                String id = sharedPreferences.getString("_id","");
                Paris paris = new Paris(id, tmpDetailsParis, solde);

                Gson gson = new Gson();
                String json = gson.toJson(paris);
                // {"idclient":"abcd","detailsparis":[{"match":{"id":1},"prono":"V1"},{"match":{"id":21},"prono":"V1"}],"mise":1000.0}
                Log.i("JSON PARI !!! ",json);
            }
            // if solde tsy ampy
            // manokatra activity makany amin'ny demande jetons
            // if solde ampy
            // creation pari
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getAllBet(MatchFoot Foot){
        List<MatchFoot> footList = new ArrayList<>();
        footList.add(Foot);
        Log.d(TAG, "Taille liste bet : " + footList.size());
        for (MatchFoot matchFoot : footList) {
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

    // logout
    public static void logout(SharedPreferences sharedPreferences, Context context){
        Log.i("LOGOUT", "LOG-_-OUT");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.i("Shared", ""+sharedPreferences.getString("name",null));
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

}
