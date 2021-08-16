package com.example.parisport.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.parisport.Modele.DetailsParis;
import com.example.parisport.Modele.Match;
import com.example.parisport.Modele.MatchParis;
import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ListMatchAdapter extends ArrayAdapter<Match> {

    private List<Match> tmpListeFoot =  new ArrayList<>();
    private List<DetailsParis> resultatPari =  new ArrayList<>();
    private List<MatchParis> tmpListeMatchFootAvantValidation = new ArrayList<>();
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    // session bet user
    public static final String SHARED_BET = "shared_bets";
    SharedPreferences sharedPreferences_bet = getContext().getSharedPreferences(SHARED_BET, MODE_PRIVATE);
    // session bet final
    public static final String SHARED_BET_FINAL = "shared_bets_final";
    SharedPreferences sharedPreferences_betfinal = getContext().getSharedPreferences(SHARED_BET_FINAL, MODE_PRIVATE);


    public ListMatchAdapter(Context context, ArrayList<Match> matchArrayList){
        super(context, R.layout.list_item, matchArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.i("LISTE ADAPTER VIEW : ","MAMETRAKA ANLE LISTES");

        Match match = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        /* ImageView imageView = convertView.findViewById(R.id.profile_pic);*/
        TextView stadeDomicile = convertView.findViewById(R.id.stadeDomicile);
        TextView equipe1 = convertView.findViewById(R.id.equipe1);
        TextView equipe2 = convertView.findViewById(R.id.equipe2);
        TextView dateMatch = convertView.findViewById(R.id.dateMatch);
        Button cotev1 = convertView.findViewById(R.id.equipe1Win);
        Button cotev2 = convertView.findViewById(R.id.equipe2Win);
        Button cotex = convertView.findViewById(R.id.equipeDraw);


        cotev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Match Foot
                Match foot = new Match(match.getId(), match.getEquipe1(), match.getEquipe2(), match.getCotev1(), match.getCotev2(), match.getCotex(), match.getDateMatch(), match.getLieuMatch(), match.getResultat());
                // Match Foot "ID"
                Match tmpFoot = new Match(match.getId());

                Log.i("TAILLE ++ : ","_"+tmpListeFoot.size());

                // open modal
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.Theme_Design_BottomSheetDialog);
                View bottomSheetView = LayoutInflater.from(getContext().getApplicationContext())
                        .inflate(R.layout.botton_modal, parent.findViewById(R.id.modalBottomSheetContainer));

                // Modal view
                EditText betMatch = bottomSheetView.findViewById(R.id.betMoney);
                TextView betEquipe1 = bottomSheetView.findViewById(R.id.betNomEquipe1);
                TextView betEquipe2 = bottomSheetView.findViewById(R.id.betNomEquipe2);

                // Change Name
                betEquipe1.setText(match.getEquipe1().getNom());
                betEquipe2.setText(match.getEquipe2().getNom());

                // button validation BET
                bottomSheetView.findViewById(R.id.btnBet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // verification solde user
                        float jetonsUser = sharedPreferences.getFloat("jetons",0.0f);
                        // bet user
                        String s = betMatch.getText().toString();
                        float userBet = Float.parseFloat(s);
                        // prono
                        String prono = "V1";
                        //Details pari
                        DetailsParis[] detailsParises = new DetailsParis[0];
                        // Create match details
                        DetailsParis tmpDetails = new DetailsParis(tmpFoot,prono);
                        // condition userBet
                        if(userBet > jetonsUser){
                            // redirection get jetons
                            Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // cote V1
                            float v = match.getCotev1();
                            // calcul GAIN
                            float gainUser = Service.calculGain(v, userBet);
                            // Match Foot Paris avant validation
                            MatchParis tmpMatchParis = new MatchParis(match.getId(), match.getEquipe1(), match.getEquipe2(), prono, userBet, v, gainUser);
                            // Add matchparis to tmpListeMatchFootAvantValidation
                            tmpListeMatchFootAvantValidation.add(tmpMatchParis);
                            // check solde and jetons user for validation ADD to tableau
                            Float verificationBet =  Service.calculeMiseTotal(tmpListeMatchFootAvantValidation);
                            if(verificationBet > jetonsUser){
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet start : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                // Calculate index of last element
                                int index = tmpListeMatchFootAvantValidation.size() - 1;
                                // Delete last element by passing index
                                tmpListeMatchFootAvantValidation.remove(index);
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet end condition : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // Add match to details
                                resultatPari.add(tmpDetails);
                                // Add match foot list
                                tmpListeFoot.add(foot);
                                // Add detail to details

                                // tojson liste BET
                                Service.storeListBet(sharedPreferences_bet, tmpListeMatchFootAvantValidation);

                                // appel service
                                Service.ValidationPari(userBet, jetonsUser, resultatPari, sharedPreferences, sharedPreferences_betfinal, tmpListeMatchFootAvantValidation, getContext());

                                // add Match foot to DetailsMatch
                                Toast.makeText(getContext(), "Confirmation Pari", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                            }
                        }
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                // open modal end
            }
        });

        cotev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Match Foot
                Match foot = new Match(match.getId(), match.getEquipe1(), match.getEquipe2(), match.getCotev1(), match.getCotev2(), match.getCotex(), match.getDateMatch(), match.getLieuMatch(), match.getResultat());
                // Match Foot "ID"
                Match tmpFoot = new Match(match.getId());

                Log.i("TAILLE ++ : ","_"+tmpListeFoot.size());

                // open modal
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.Theme_Design_BottomSheetDialog);
                View bottomSheetView = LayoutInflater.from(getContext().getApplicationContext())
                        .inflate(R.layout.botton_modal, parent.findViewById(R.id.modalBottomSheetContainer));

                // Modal view
                EditText betMatch = bottomSheetView.findViewById(R.id.betMoney);
                TextView betEquipe1 = bottomSheetView.findViewById(R.id.betNomEquipe1);
                TextView betEquipe2 = bottomSheetView.findViewById(R.id.betNomEquipe2);

                // Change Name
                betEquipe1.setText(match.getEquipe1().getNom());
                betEquipe2.setText(match.getEquipe2().getNom());

                // button validation BET
                bottomSheetView.findViewById(R.id.btnBet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // verification solde user
                        float jetonsUser = sharedPreferences.getFloat("jetons",0.0f);
                        // bet user
                        String s = betMatch.getText().toString();
                        float userBet = Float.parseFloat(s);
                        // prono
                        String prono = "V2";
                        //Details pari
                        DetailsParis[] detailsParises = new DetailsParis[0];
                        // Create match details
                        DetailsParis tmpDetails = new DetailsParis(tmpFoot,prono);
                        // condition userBet
                        if(userBet > jetonsUser){
                            // redirection get jetons
                            Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // cote V1
                            float v = match.getCotev1();
                            // calcul GAIN
                            float gainUser = Service.calculGain(v, userBet);
                            // Match Foot Paris avant validation
                            MatchParis tmpMatchParis = new MatchParis(match.getId(), match.getEquipe1(), match.getEquipe2(), prono, userBet, v, gainUser);
                            // Add matchparis to tmpListeMatchFootAvantValidation
                            tmpListeMatchFootAvantValidation.add(tmpMatchParis);
                            // check solde and jetons user for validation ADD to tableau
                            Float verificationBet =  Service.calculeMiseTotal(tmpListeMatchFootAvantValidation);
                            if(verificationBet > jetonsUser){
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet start : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                // Calculate index of last element
                                int index = tmpListeMatchFootAvantValidation.size() - 1;
                                // Delete last element by passing index
                                tmpListeMatchFootAvantValidation.remove(index);
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet end condition : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // Add match to details
                                resultatPari.add(tmpDetails);
                                // Add match foot list
                                tmpListeFoot.add(foot);
                                // Add detail to details

                                // tojson liste BET
                                Service.storeListBet(sharedPreferences_bet, tmpListeMatchFootAvantValidation);

                                // appel service
                                Service.ValidationPari(userBet, jetonsUser, resultatPari, sharedPreferences, sharedPreferences_betfinal,tmpListeMatchFootAvantValidation, getContext());

                                // add Match foot to DetailsMatch
                                Toast.makeText(getContext(), "Confirmation Pari", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                            }
                        }
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                // open modal end
            }
        });

        cotex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Match Foot
                Match foot = new Match(match.getId(), match.getEquipe1(), match.getEquipe2(), match.getCotev1(), match.getCotev2(), match.getCotex(), match.getDateMatch(), match.getLieuMatch(), match.getResultat());
                // Match Foot "ID"
                Match tmpFoot = new Match(match.getId());

                Log.i("TAILLE ++ : ","_"+tmpListeFoot.size());

                // open modal
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.Theme_Design_BottomSheetDialog);
                View bottomSheetView = LayoutInflater.from(getContext().getApplicationContext())
                        .inflate(R.layout.botton_modal, parent.findViewById(R.id.modalBottomSheetContainer));

                // Modal view
                EditText betMatch = bottomSheetView.findViewById(R.id.betMoney);
                TextView betEquipe1 = bottomSheetView.findViewById(R.id.betNomEquipe1);
                TextView betEquipe2 = bottomSheetView.findViewById(R.id.betNomEquipe2);

                // Change Name
                betEquipe1.setText(match.getEquipe1().getNom());
                betEquipe2.setText(match.getEquipe2().getNom());

                // button validation BET
                bottomSheetView.findViewById(R.id.btnBet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // verification solde user
                        float jetonsUser = sharedPreferences.getFloat("jetons",0.0f);
                        // bet user
                        String s = betMatch.getText().toString();
                        float userBet = Float.parseFloat(s);
                        // prono
                        String prono = "VX";
                        //Details pari
                        DetailsParis[] detailsParises = new DetailsParis[0];
                        // Create match details
                        DetailsParis tmpDetails = new DetailsParis(tmpFoot,prono);
                        // condition userBet
                        if(userBet > jetonsUser){
                            // redirection get jetons
                            Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // cote V1
                            float v = match.getCotev1();
                            // calcul GAIN
                            float gainUser = Service.calculGain(v, userBet);
                            // Match Foot Paris avant validation
                            MatchParis tmpMatchParis = new MatchParis(match.getId(), match.getEquipe1(), match.getEquipe2(), prono, userBet, v, gainUser);
                            // Add matchparis to tmpListeMatchFootAvantValidation
                            tmpListeMatchFootAvantValidation.add(tmpMatchParis);
                            // check solde and jetons user for validation ADD to tableau
                            Float verificationBet =  Service.calculeMiseTotal(tmpListeMatchFootAvantValidation);
                            if(verificationBet > jetonsUser){
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet start : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                // Calculate index of last element
                                int index = tmpListeMatchFootAvantValidation.size() - 1;
                                // Delete last element by passing index
                                tmpListeMatchFootAvantValidation.remove(index);
                                Log.i("Affichage click : ", "Jetons user : "+jetonsUser+"__ verificationBet end condition : "+verificationBet+"__Taille : "+tmpListeMatchFootAvantValidation.size());
                                Toast.makeText(getContext(), "Jetons insuffisant affichage", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // Add match to details
                                resultatPari.add(tmpDetails);
                                // Add match foot list
                                tmpListeFoot.add(foot);
                                // Add detail to details

                                // tojson liste BET
                                Service.storeListBet(sharedPreferences_bet, tmpListeMatchFootAvantValidation);

                                // appel service
                                Service.ValidationPari(userBet, jetonsUser, resultatPari, sharedPreferences, sharedPreferences_betfinal,tmpListeMatchFootAvantValidation, getContext());

                                // add Match foot to DetailsMatch
                                Toast.makeText(getContext(), "Confirmation Pari", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                            }
                        }
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                // open modal end
            }
        });

        Log.i("LISTEADAPTER MATCH : ", "zavatra "+match.getLieuMatch()+" zavatra "+match.getEquipe1().getNom()+"zavatra "+match.getEquipe2().getNom()+"zavatra "+match.getDateMatch());

        stadeDomicile.setText(match.getLieuMatch());
        equipe1.setText(match.getEquipe1().getNom());
        equipe2.setText(match.getEquipe2().getNom());
        dateMatch.setText((CharSequence) match.getDateMatch());
        cotev1.setText(String.valueOf(match.getCotev1()));
        cotev2.setText(String.valueOf(match.getCotev2()));
        cotex.setText(String.valueOf(match.getCotex()));

        return convertView;
    }

}
