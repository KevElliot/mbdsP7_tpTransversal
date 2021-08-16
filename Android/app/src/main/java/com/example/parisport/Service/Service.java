package com.example.parisport.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.parisport.Activity.HomeActivity;
import com.example.parisport.Activity.SignInActivity;
import com.example.parisport.Interface.async_call_back;
import com.example.parisport.Interface.async_call_back_paris;
import com.example.parisport.Modele.DemandeJetons;
import com.example.parisport.Modele.DetailsParis;
import com.example.parisport.Modele.Equipe;
import com.example.parisport.Modele.HistoriquePari;
import com.example.parisport.Modele.Match;
import com.example.parisport.Modele.MatchParis;
import com.example.parisport.Modele.Paris;
import com.example.parisport.Modele.ToJson;
import com.example.parisport.Modele.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


public class Service {

    @SuppressLint("SdCardPath")
    public static String data = "/data/data/com.example.parisport";

    public static String FILE_NAME = "user_historique";

    // session bet final
    public static final String SHARED_BET_FINAL = "shared_bets_final";

    public static String urlMatchAPI = "https://tpt-server-grails.herokuapp.com/matchApi/matchs";
    public static String urlPlacerParisAPI = "https://tpt-server-grails.herokuapp.com/parisApi/paris";
    public static String urlListesParisParClientAPI = "https://tpt-server-grails.herokuapp.com/parisApi/listeParis?idclient={userid}";
    public static String urlListesEquipesAPI = "https://tpt-server-grails.herokuapp.com/equipeApi/equipes";

    public static String urlDemandeJetonsAPI = "https://tpt-server-grails.herokuapp.com/equipeApi/equipes";

    public static String urlUser = "https://parilocalnode.herokuapp.com/pariBack/user/60dac9aa7028070b58bfcd14";
    public static String urlUserModification = "https://parilocalnode.herokuapp.com/pariBack/user";

    public static String urlLoginQR = "https://parilocalnode.herokuapp.com/pariBack/auth/loginQr";
    public static String urlLogin = "https://parilocalnode.herokuapp.com/pariBack/auth/login";
    public static String urlregister = "https://parilocalnode.herokuapp.com/pariBack/auth/register";


    public static void getUser(String user, SharedPreferences sharedPreferences){
        AndroidNetworking.get("https://parilocalnode.herokuapp.com/pariBack/user/{userId}")
                .addPathParameter("userId", user)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(User.class, new ParsedRequestListener<User>() {
                    @Override
                    public void onResponse(User user) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        try{
                            editor.putString("_id",user.getId());
                            editor.putString("email",user.getEmail());
                            editor.putString("name",user.getName());
                            editor.putString("password",user.getPassword());
                            editor.putFloat("jetons",user.getJetons());
                            editor.apply();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + error.getResponse());
                        }
                    }
                });
    }

    public static void demandeJetons(int demande, SharedPreferences sharedPreferences, Context context){
        DemandeJetons tmp = new DemandeJetons();
        String user = sharedPreferences.getString("_id", "");
        tmp.setIduser(user);
        tmp.setJetonsdemande(demande);
        tmp.setStatut(false);
        AndroidNetworking.post(urlDemandeJetonsAPI)
                .addBodyParameter(tmp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        /*
                        Gson gson = new Gson();
                        Log.i("DEMANDE JETONS", String.valueOf(response));
                         */
                        try{
                            Toast.makeText(context, "Demande jetons Successful",Toast.LENGTH_SHORT).show();
                            /*
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                             */
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + error.getResponse());
                        }
                    }
                });
    }


    public static void register(String name, String password, String email, String jetons, Context context){
        int registerFloat = Integer.parseInt(jetons);
        Log.i("USER INFO : ", "name"+name+"password"+password+"email"+email+"jetons"+jetons);
        String role = "user";
        User tmp = new User(name.trim(), password.trim(), email.trim(), role, registerFloat);
        AndroidNetworking.post(urlregister)
                .addBodyParameter(tmp)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.i("REGISTER", String.valueOf(response));
                        try{
                            Toast.makeText(context, "Register Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, SignInActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + error.getResponse());
                        }
                    }
                });
    }

    public static List<HistoriquePari> getHistoriqueParisToJson(){
        List<HistoriquePari> resultat = new ArrayList<>();
        Log.d(TAG, "HistoriquePari TONGA !!! ");
        AndroidNetworking.get(urlListesParisParClientAPI)
                .addPathParameter("userid", "abcd")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String json = gson.toJson(response);


                        Log.w(TAG, "storeDataToLocalFile before write data : "+json);
                        Log.w(TAG, "storeDataToLocalFile before write data : "+new GsonBuilder().setPrettyPrinting().create().toJson(response));
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + error.getResponse());
                        }
                    }
                });

        return resultat;
    }

/*
*
    public static List<HistoriquePari> getHistoriqueParisToJson(){
        List<HistoriquePari> resultat = new ArrayList<>();
        Log.d(TAG, "HistoriquePari TONGA !!! ");
        AndroidNetworking.get("https://tpt-server-grails.herokuapp.com/parisApi/listeParis?idclient=abcd")
                .build()
                .getAsObjectList(HistoriquePari.class, new ParsedRequestListener<List<HistoriquePari>>() {
                    @Override
                    public void onResponse(List<HistoriquePari> parisList) {
                        // do anything with response
                        Log.d(TAG, "HistoriquePari size : " + parisList.size());
                        for (HistoriquePari paris : parisList) {

                            parisList.removeAll(Collections.singleton(null));
                            resultat.add(paris);

                            Log.d(TAG, "getId : " + paris.getId());
                            Log.d(TAG, "getNbperdu : " + paris.getNbperdu());
                            Log.d(TAG, "getNbmatch : " + paris.getNbmatch());
                            Log.d(TAG, "getCode : " + paris.getCode());
                            Log.d(TAG, "getDateparis : " + paris.getDateparis());
                            Log.d(TAG, "getCoteglobal : " + paris.getCoteglobal());
                            Log.d(TAG, "getIdclient : " + paris.getIdclient());
                            Log.d(TAG, "getGainpossible : " + paris.getGainpossible());
                            Log.d(TAG, "getGain : " + paris.getGain());
                            Log.d(TAG, "getNbgain : " + paris.getNbgain());
                            Log.d(TAG, "getVersion : " + paris.getVersion());
                            Log.d(TAG, "getMise : " + paris.getMise());
                        }

                        Gson gson = new Gson();
                        String json = gson.toJson(resultat);
                        storeDataToLocalFile(json, "historique");

                    }
                    @Override
                    public void onError(ANError anError) {
                        if (anError.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d(TAG, "onError errorCode : " + anError.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + anError.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + anError.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + anError.getResponse());
                        }
                    }
                });

        return resultat;
    }


* */

    public static void storeDataToLocalFile(String toStore, String filename){
        Log.d(TAG, "storeDataToLocalFile");
        File file = new File(data,filename);
        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Log.d(TAG, "storeDataToLocalFile before write data : "+toStore);
            /*
            bufferedWriter.write(toStore);
            Log.d(TAG, "storeDataToLocalFile after write data to file !!! ");
            bufferedWriter.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readDataToLocalFile(String filename){
        String resultat = "";
        File file = new File(data,filename);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            // This responce will have Json Format String
            resultat = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resultat;
    }

    public static void getListesParisParClient(final async_call_back_paris myCallBack){
        AndroidNetworking.get(urlListesParisParClientAPI)
                .addPathParameter("userid", "abcd")
                .build()
                .getAsObjectList(HistoriquePari.class, new ParsedRequestListener<List<HistoriquePari>>() {
                    @Override
                    public void onResponse(List<HistoriquePari> parisList) {
                        // do anything with response
                        List<HistoriquePari> ListParis = new ArrayList<>();
                        for (HistoriquePari paris : parisList) {
                            parisList.removeAll(Collections.singleton(null));
                            ListParis.add(paris);
                            myCallBack.getListesParisParClient((ArrayList<HistoriquePari>) ListParis);
                        }

                        Gson gson = new Gson();
                        String json = gson.toJson(ListParis);
                        storeDataToLocalFile(json, "historique");
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.i("HISTORIQUE ERROR !!!! ",anError.toString());
                    }
                });
    }

    public static Float calculGain(Float cote, Float mise){
        float total = 0.0f;

        try {
            total = cote * mise;
        }catch (ArithmeticException e)
        {
            e.printStackTrace();
            System.out.println("ArithmeticException are handled");
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("NumberFormatException are handled");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Exception are handled");
        }

        return total;
    }

    public static void getAllMatchFootToDataLocalPhone(final async_call_back myCallBack){
        Log.i("getAllMatchFoot : ", "TONGA GET ALL MATCH FOOT");
        AndroidNetworking.get(urlMatchAPI)
                .build()
                .getAsObjectList(Match.class, new ParsedRequestListener<List<Match>>() {
                    @Override
                    public void onResponse(List<Match> matches) {
                        Log.i("getAllMatchFoot : ", "TONGA GET ALL MATCH FOOT - - Taille "+matches.size());
                        // do anything with response
                        List<Match> listeMatch = new ArrayList<>();
                        for (Match match : matches) {
                            matches.removeAll(Collections.singleton(null));
                            listeMatch.add(match);
                            myCallBack.getAllMatchFoot((ArrayList<Match>) listeMatch);

                            Log.d(TAG, "Lieu a domicile : " + match.getLieuMatch());
                            Equipe tmpEquipe1 = match.getEquipe1();
                            Equipe tmpEquipe2 = match.getEquipe2();
                            Log.d(TAG, "Equipe 1 : " + tmpEquipe1.getNom());
                            Log.d(TAG, "Equipe 2 : " + tmpEquipe2.getNom());
                            Log.d(TAG, "cotev1 : " + match.getCotev1());
                            Log.d(TAG, "cotev2 : " + match.getCotev2());
                            Log.d(TAG, "cotex : " + match.getCotex());
                            Log.d(TAG, "date : " + match.getDateMatch());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }

    public static void getAllMatchFoot(final async_call_back myCallBack){
        Log.i("getAllMatchFoot : ", "TONGA GET ALL MATCH FOOT");
        AndroidNetworking.get(urlMatchAPI)
                .build()
                .getAsObjectList(Match.class, new ParsedRequestListener<List<Match>>() {
                    @Override
                    public void onResponse(List<Match> matches) {
                        Log.i("getAllMatchFoot : ", "TONGA GET ALL MATCH FOOT - - Taille "+matches.size());
                        // do anything with response
                        List<Match> listeMatch = new ArrayList<>();
                        for (Match match : matches) {
                            matches.removeAll(Collections.singleton(null));
                            listeMatch.add(match);
                            myCallBack.getAllMatchFoot((ArrayList<Match>) listeMatch);

                            Log.d(TAG, "Lieu a domicile : " + match.getLieuMatch());
                            Equipe tmpEquipe1 = match.getEquipe1();
                            Equipe tmpEquipe2 = match.getEquipe2();
                            Log.d(TAG, "Equipe 1 : " + tmpEquipe1.getNom());
                            Log.d(TAG, "Equipe 2 : " + tmpEquipe2.getNom());
                            Log.d(TAG, "cotev1 : " + match.getCotev1());
                            Log.d(TAG, "cotev2 : " + match.getCotev2());
                            Log.d(TAG, "cotex : " + match.getCotex());
                            Log.d(TAG, "date : " + match.getDateMatch());
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
            Log.i("DETAILS PARI -- : ",detailsParises[i].getMatchFoot().toString());
        }
        return detailsParises;
    }

    public static boolean checkSoldeCLient(SharedPreferences sharedPreferences, Float userBet){
        boolean check = false;
        // get user jetons session
        Float userJetons = sharedPreferences.getFloat("jetons",0.0f);
        try{
            if (userBet > userJetons){
                return false;
            }else {
                // verification bet and jetons
                userJetons = userJetons - userBet;
                // check rest
                check = true;
            }
        }catch (ArithmeticException e)
        {
            e.printStackTrace();
            System.out.println("ArithmeticException are handled");
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("NumberFormatException are handled");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Exception are handled");
        }
        return check;
    }

    public static Float calculeMiseTotal(List<MatchParis> matchParisList){
        float total = 0.0f;
        try {
            for (MatchParis tmp : matchParisList) {
                total = total + tmp.getMise();
            }
        }catch (ArithmeticException e)
        {
            e.printStackTrace();
            System.out.println("ArithmeticException are handled");
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("NumberFormatException are handled");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Exception are handled");
        }

        return total;
    }

    public static void ValidationPari(float solde, float jetonsUser, List<DetailsParis> detailsParisList, SharedPreferences sharedPreferences, SharedPreferences sharedPreferencesFinal,List<MatchParis> matchParisList,Context context){
        try{
            // verification solde client avant validation
            if(solde > jetonsUser){
                Toast.makeText(context, "Jetons insuffisant", Toast.LENGTH_SHORT).show();
                // open activity to manapy anle jetons
            }
            // update Mise
            float soldeMAJ = calculeMiseTotal(matchParisList);
            if(soldeMAJ > jetonsUser){
                Toast.makeText(context, "Jetons insuffisant", Toast.LENGTH_SHORT).show();
                // open activity to manapy anle jetons
            }
            else {
                DetailsParis[] tmpDetailsParis = zavatra(detailsParisList);
                String id = sharedPreferences.getString("_id","");

                // change JSON
                ToJson tmpTOJSON = new ToJson(id, tmpDetailsParis, soldeMAJ);

                Gson gson = new Gson();
                String zavtra = gson.toJson(tmpTOJSON);

                SharedPreferences.Editor editor = sharedPreferencesFinal.edit();
                Log.i("ValidationPari _____ : ", zavtra);
                editor.putString("setfinal",zavtra);
                editor.apply();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getAllBet(Match Foot){
        List<Match> footList = new ArrayList<>();
        footList.add(Foot);
        Log.d(TAG, "Taille liste bet : " + footList.size());
        for (Match match : footList) {
            Log.d(TAG, "Lieu a domicile : " + match.getLieuMatch());
            Equipe tmpEquipe1 = match.getEquipe1();
            Equipe tmpEquipe2 = match.getEquipe2();
            Log.d(TAG, "Equipe 1 : " + tmpEquipe1.getNom());
            Log.d(TAG, "Equipe 2 : " + tmpEquipe2.getNom());
            Log.d(TAG, "cotev1 : " + match.getCotev1());
            Log.d(TAG, "cotev2 : " + match.getCotev2());
            Log.d(TAG, "cotex : " + match.getCotex());
        }
    }

    public static List<MatchParis> readListBet(Context context){
        List<MatchParis> results = new ArrayList<>();;
        try{
            String SHARED_PREFS = "shared_bets";
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("set", "");
            Log.i("READLISTBET : ", json);
            if(json.isEmpty()){
                Log.d(TAG, "readListBet JSON EMPTY !!! ");
                Toast.makeText(context ,"There is something error",Toast.LENGTH_LONG).show();
            }
            else{
                Type type = new TypeToken<List<MatchParis>>(){}.getType();
                results = gson.fromJson(json, type);
                for (MatchParis tmp : results){
                    Log.d(TAG, "MatchParis : "+tmp.getEquipe1().getNom()+"_"+tmp.getEquipe1().getNom()+"_"+tmp.getProno()+"_"+tmp.getMise()+"_"+tmp.getId());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return results;
    }

    public static void validationFinalBet(Context context){
        JSONObject jsonObject = new JSONObject();
        String result = "";
        try{
            // get valeur bet session
            SharedPreferences sharedPreferences_betfinal = context.getSharedPreferences(SHARED_BET_FINAL, MODE_PRIVATE);
            String tmp = sharedPreferences_betfinal.getString("setfinal","");
            Log.i("validationFinalBet : ", ""+tmp);

            Gson gson = new Gson();
            ToJson betfinal = gson.fromJson(tmp, ToJson.class);
            Log.i("validation class : ", ""+betfinal);

            result = gson.toJson(betfinal);
            jsonObject = new JSONObject(result);

            // suppression session sharedPreferences
            storeListBetClear(context);
            Toast.makeText(context ,"Validation final bet",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        AndroidNetworking.post(urlPlacerParisAPI)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Log.i("validation URL BET ", String.valueOf(response));
                    }
                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorDetail : " + error.getResponse());
                        }
                    }
                });
    }

    public static void storeListBet(SharedPreferences sharedPreferences, List<MatchParis> matchParisList){
        try{
            Gson gson = new Gson();
            String json = gson.toJson(matchParisList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.i("storeListBet _____ : ", json);
            editor.putString("set",json);
            editor.apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void storeListBetClear(Context context){
        String SHARED_PREFS = "shared_bets";
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Log.i("CLEAR", "SHARED_BET");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.i("SHARED_BET", ""+sharedPreferences.getString("set",null));
        editor.clear();
        editor.apply();
    }

    // logout
    public static void logout(SharedPreferences sharedPreferences, Context context){
        Log.i("LOGOUT", "LOG-_-OUT");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.i("Shared", ""+sharedPreferences.getString("name",null));
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
