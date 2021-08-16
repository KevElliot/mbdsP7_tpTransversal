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

public class ListMatchBetAdapter  extends ArrayAdapter<MatchParis> {

    public static final String SHARED_BET = "shared_bets";
    SharedPreferences sharedPreferences_bet = getContext().getSharedPreferences(SHARED_BET, MODE_PRIVATE);

    public ListMatchBetAdapter(Context context, List<MatchParis> matchArrayList){
        super(context, R.layout.list_bet, matchArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MatchParis match = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_bet,parent,false);
        }

        /* ImageView imageView = convertView.findViewById(R.id.profile_pic);*/
        TextView stadeDomicile = convertView.findViewById(R.id.betStadeDomicile);
        TextView equipe1 = convertView.findViewById(R.id.betEquipe1);
        TextView equipe2 = convertView.findViewById(R.id.betEquipe2);
        TextView prono = convertView.findViewById(R.id.betProno);
        TextView gain = convertView.findViewById(R.id.betGain);
        TextView cote = convertView.findViewById(R.id.betCote);
        EditText mise = convertView.findViewById(R.id.betChangeMise);
        Button btnMajBet = convertView.findViewById(R.id.betMiser);


        equipe1.setText(match.getEquipe1().getNom());
        equipe2.setText(match.getEquipe2().getNom());
        prono.setText(match.getProno());
        gain.setText(String.valueOf(match.getGain()));
        cote.setText(String.valueOf(match.getCote()));
        mise.setText(String.valueOf(match.getMise()), TextView.BufferType.EDITABLE);

        btnMajBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return convertView;
    }

}
