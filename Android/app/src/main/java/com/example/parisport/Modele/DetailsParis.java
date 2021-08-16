package com.example.parisport.Modele;

public class DetailsParis {

    private String id;
    private Match match;
    private String prono;

    public DetailsParis() {
    }

    public DetailsParis(Match match, String prono) {
        this.match = match;
        this.prono = prono;
    }

    public DetailsParis(String id, Match match, String prono) {
        this.id = id;
        this.match = match;
        this.prono = prono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Match getMatchFoot() {
        return match;
    }

    public void setMatchFoot(Match match) {
        this.match = match;
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }
}
