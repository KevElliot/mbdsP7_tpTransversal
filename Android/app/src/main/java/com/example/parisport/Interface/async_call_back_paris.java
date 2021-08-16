package com.example.parisport.Interface;

import com.example.parisport.Modele.HistoriquePari;
import com.example.parisport.Modele.Paris;

import java.util.ArrayList;

public interface async_call_back_paris {
    void getListesParisParClient(ArrayList<HistoriquePari> parisArrayList);
}
