package com.example.parisport.Modele;

public class DetailsParis {

    private String id;
    private MatchFoot matchFoot;
    private String prono;

    public DetailsParis() {
    }

    public DetailsParis(MatchFoot matchFoot, String prono) {
        this.matchFoot = matchFoot;
        this.prono = prono;
    }

    public DetailsParis(String id, MatchFoot matchFoot, String prono) {
        this.id = id;
        this.matchFoot = matchFoot;
        this.prono = prono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MatchFoot getMatchFoot() {
        return matchFoot;
    }

    public void setMatchFoot(MatchFoot matchFoot) {
        this.matchFoot = matchFoot;
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }
}
