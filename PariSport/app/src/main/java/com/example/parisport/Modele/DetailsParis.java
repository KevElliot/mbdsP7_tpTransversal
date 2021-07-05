package com.example.parisport.Modele;

public class DetailsParis {

    private MatchFoot matchFoot;
    private Paris paris;
    private String prono;
    private String gain;
    private float cote;

    public DetailsParis() {
    }

    public DetailsParis(MatchFoot matchFoot, Paris paris, String prono, String gain, float cote) {
        this.matchFoot = matchFoot;
        this.paris = paris;
        this.prono = prono;
        this.gain = gain;
        this.cote = cote;
    }

    public MatchFoot getMatchFoot() {
        return matchFoot;
    }

    public void setMatchFoot(MatchFoot matchFoot) {
        this.matchFoot = matchFoot;
    }

    public Paris getParis() {
        return paris;
    }

    public void setParis(Paris paris) {
        this.paris = paris;
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public float getCote() {
        return cote;
    }

    public void setCote(float cote) {
        this.cote = cote;
    }
}
