package com.example.parisport.Modele;

import java.util.ArrayList;
import java.util.Date;

public class MatchFoot {

    private int id;
    private Equipe equipe1;
    private Equipe equipe2;
    private float cotev1;
    private float cotev2;
    private float cotex;
    private Date dateMatch;
    private String lieuMatch;
    private String resultat;

    public MatchFoot() {
    }

    public MatchFoot(int id, Equipe equipe1, Equipe equipe2, float cotev1, float cotev2, float cotex, Date dateMatch, String lieuMatch, String resultat) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.cotev1 = cotev1;
        this.cotev2 = cotev2;
        this.cotex = cotex;
        this.dateMatch = dateMatch;
        this.lieuMatch = lieuMatch;
        this.resultat = resultat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public float getCotev1() {
        return cotev1;
    }

    public void setCotev1(float cotev1) {
        this.cotev1 = cotev1;
    }

    public float getCotev2() {
        return cotev2;
    }

    public void setCotev2(float cotev2) {
        this.cotev2 = cotev2;
    }

    public float getCotex() {
        return cotex;
    }

    public void setCotex(float cotex) {
        this.cotex = cotex;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getLieuMatch() {
        return lieuMatch;
    }

    public void setLieuMatch(String lieuMatch) {
        this.lieuMatch = lieuMatch;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
