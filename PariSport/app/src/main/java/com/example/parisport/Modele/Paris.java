package com.example.parisport.Modele;

import java.util.Date;

public class Paris {

    private int id;
    private String idClient;
    private String code;
    private String gain;
    private Date dateParis;
    private float coteGlobal;
    private Double mise;
    private int nbMatch;
    private int nbGain;
    private int nbPerdu;

    public Paris() {
    }

    public Paris(int id, String idClient, String code, String gain, Date dateParis, float coteGlobal, Double mise, int nbMatch, int nbGain, int nbPerdu) {
        this.id = id;
        this.idClient = idClient;
        this.code = code;
        this.gain = gain;
        this.dateParis = dateParis;
        this.coteGlobal = coteGlobal;
        this.mise = mise;
        this.nbMatch = nbMatch;
        this.nbGain = nbGain;
        this.nbPerdu = nbPerdu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public Date getDateParis() {
        return dateParis;
    }

    public void setDateParis(Date dateParis) {
        this.dateParis = dateParis;
    }

    public float getCoteGlobal() {
        return coteGlobal;
    }

    public void setCoteGlobal(float coteGlobal) {
        this.coteGlobal = coteGlobal;
    }

    public Double getMise() {
        return mise;
    }

    public void setMise(Double mise) {
        this.mise = mise;
    }

    public int getNbMatch() {
        return nbMatch;
    }

    public void setNbMatch(int nbMatch) {
        this.nbMatch = nbMatch;
    }

    public int getNbGain() {
        return nbGain;
    }

    public void setNbGain(int nbGain) {
        this.nbGain = nbGain;
    }

    public int getNbPerdu() {
        return nbPerdu;
    }

    public void setNbPerdu(int nbPerdu) {
        this.nbPerdu = nbPerdu;
    }
}
