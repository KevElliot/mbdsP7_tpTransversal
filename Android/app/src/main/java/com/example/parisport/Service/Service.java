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
import com.example.parisport.Interface.async_call_back;
import com.example.parisport.Interface.async_call_back_login;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.Modele.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public static void loginQR(){
    }

    // login
    // Log.i("Verification TRUE","Email : "+email+"user.getEmail : "+user.getEmail()+"Password : "+password+"user.getPassword : "+user.getPassword());

    /*
    public static void login(final async_call_back_login myCallBack){
        AndroidNetworking.post(urlLogin)
                .build()
                .getAsObject(User.class, new ParsedRequestListener<User>() {
                    @Override
                    public void onResponse(User user) {
                        // do anything with response
                        Log.d("CHECK", "Verification !!!");
                        if (user != null) {
                            String x = null;
                            String y = null;
                            if(x.equals(user.getEmail()) && y.equals(user.getPassword())){
                                Log.i("Verification TRUE","Email : "+x+"user.getEmail : "+user.getEmail()+"Password : "+y+"user.getPassword : "+user.getPassword());
                                myCallBack.login(x, y,user);
                            }
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }

     */

    public static void loginPost(String x, String y, SharedPreferences sharedPreferences, Context context){
        User tmp = new User();
        tmp.setEmail(x);
        tmp.setPassword(y);
        AndroidNetworking.post(urlLogin)
                .addBodyParameter(tmp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Gson gson = new Gson();
                        Log.i("LOGIN", String.valueOf(response));
                        if(response){
                            try{
                                User tmpUser = gson.fromJson(String.valueOf(response), User.class);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("_id",tmpUser.getId());
                                editor.putString("name",tmpUser.getName());
                                editor.putFloat("jetons",tmpUser.getJetons());
                                editor.putString("role",tmpUser.getRole());
                                editor.commit();
                                Toast.makeText(context, "Login Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, HomeActivity.class);
                                context.startActivity(intent);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText(context,"Credentials are not valid",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    // logout
    public static void logout(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
