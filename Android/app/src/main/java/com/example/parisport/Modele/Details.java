package com.example.parisport.Modele;

public class Details {
    private int id;
    private ParisDetails paris;
    private Match match;
    private String prono;
    private String gain;
    private String version;
    private Float cote;

    public Details(int id, ParisDetails paris, Match match, String prono, String gain, String version, Float cote) {
        this.id = id;
        this.paris = paris;
        this.match = match;
        this.prono = prono;
        this.gain = gain;
        this.version = version;
        this.cote = cote;
    }

    public Details() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParisDetails getParis() {
        return paris;
    }

    public void setParis(ParisDetails paris) {
        this.paris = paris;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Float getCote() {
        return cote;
    }

    public void setCote(Float cote) {
        this.cote = cote;
    }
}
