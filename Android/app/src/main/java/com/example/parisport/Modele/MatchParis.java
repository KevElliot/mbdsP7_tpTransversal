package com.example.parisport.Modele;

public class MatchParis {
    private int id;
    private Equipe equipe1;
    private Equipe equipe2;
    private String prono;
    private Float mise;
    private Float cote;
    private Float gain;

    public MatchParis() {
    }

    public MatchParis(int id, Equipe equipe1, Equipe equipe2, String prono, Float mise, Float cote, Float gain) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.prono = prono;
        this.mise = mise;
        this.cote = cote;
        this.gain = gain;
    }

    public MatchParis(int id, Equipe equipe1, Equipe equipe2, String prono, Float mise) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.prono = prono;
        this.mise = mise;
    }

    public Float getCote() {
        return cote;
    }

    public void setCote(Float cote) {
        this.cote = cote;
    }

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
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

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }

    public Float getMise() {
        return mise;
    }

    public void setMise(Float mise) {
        this.mise = mise;
    }
}
