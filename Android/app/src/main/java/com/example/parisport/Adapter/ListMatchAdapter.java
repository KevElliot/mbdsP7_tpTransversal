package com.example.parisport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.parisport.Fragments.HomeFragment;
import com.example.parisport.Modele.MatchFoot;
import com.example.parisport.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListMatchAdapter extends ArrayAdapter<MatchFoot> {

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

        /*
        imageView.setImageResource(user.imageId);
        userName.setText(user.name);
        lastMsg.setText(user.lastMessage);
        time.setText(user.lastMsgTime);
         */


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
