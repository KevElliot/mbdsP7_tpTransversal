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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.parisport.Fragments.BottomDialog;
import com.example.parisport.Fragments.HomeFragment;
import com.example.parisport.Modele.DetailsParis;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.R;
import com.example.parisport.Service.Service;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ListMatchAdapter extends ArrayAdapter<MatchFoot> {

    private List<MatchFoot> tmpListeFoot =  new ArrayList<>();
    private List<DetailsParis> resultatPari =  new ArrayList<>();
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

    public ListMatchAdapter(Context context, ArrayList<MatchFoot> matchFootArrayList){
        super(context, R.layout.list_item, matchFootArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MatchFoot matchFoot = getItem(position);

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
                MatchFoot foot = new MatchFoot(matchFoot.getId(), matchFoot.getEquipe1(),matchFoot.getEquipe2(), matchFoot.getCotev1(), matchFoot.getCotev2(), matchFoot.getCotex(), matchFoot.getDateMatch(), matchFoot.getLieuMatch(), matchFoot.getResultat());
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
                betEquipe1.setText(matchFoot.getEquipe1().getNom());
                betEquipe2.setText(matchFoot.getEquipe2().getNom());

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
                        DetailsParis tmpDetails = new DetailsParis(foot,prono);
                        // Add match to details
                        resultatPari.add(tmpDetails);
                        // Add match foot list
                        tmpListeFoot.add(foot);
                        // Add detail to details


                        // appel service
                        Service.ValidationPari(userBet, jetonsUser, resultatPari, sharedPreferences, getContext());

                        // add Match foot to DetailsMatch
                        Toast.makeText(getContext(), "Confirmation Pari", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
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
                MatchFoot foot = new MatchFoot(matchFoot.getId(), matchFoot.getEquipe1(),matchFoot.getEquipe2(), matchFoot.getCotev1(), matchFoot.getCotev2(), matchFoot.getCotex(), matchFoot.getDateMatch(), matchFoot.getLieuMatch(), matchFoot.getResultat());
                tmpListeFoot.add(foot);
                Log.i("TAILLE ++ : ","_"+tmpListeFoot.size());
                //Service.getAllBet(foot);
                Toast.makeText(getContext(), "User name"+sharedPreferences.getString("name",""),Toast.LENGTH_SHORT).show();
            }
        });

        cotex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchFoot foot = new MatchFoot(matchFoot.getId(), matchFoot.getEquipe1(),matchFoot.getEquipe2(), matchFoot.getCotev1(), matchFoot.getCotev2(), matchFoot.getCotex(), matchFoot.getDateMatch(), matchFoot.getLieuMatch(), matchFoot.getResultat());
                tmpListeFoot.add(foot);
                Log.i("TAILLE ++ : ","_"+tmpListeFoot.size());
                //Service.getAllBet(foot);
                Toast.makeText(getContext(), "User name"+sharedPreferences.getString("name",""),Toast.LENGTH_SHORT).show();
            }
        });

        stadeDomicile.setText(matchFoot.getLieuMatch());
        equipe1.setText(matchFoot.getEquipe1().getNom());
        equipe2.setText(matchFoot.getEquipe2().getNom());
        // controller getter date "NULL"
        // dateMatch.setText((CharSequence) matchFoot.getDateMatch());
        cotev1.setText(String.valueOf(matchFoot.getCotev1()));
        cotev2.setText(String.valueOf(matchFoot.getCotev2()));
        cotex.setText(String.valueOf(matchFoot.getCotex()));

        return convertView;
    }

}
