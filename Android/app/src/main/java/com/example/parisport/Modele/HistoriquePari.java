package com.example.parisport.Modele;

import java.util.Date;

public class HistoriquePari {
    private int id;
    private int nbperdu;
    private Details[] detailsparis;
    private int nbmatch;
    private String code;
    private Date dateparis;
    private Float coteglobal;
    private String idclient;
    private Float gainpossible;
    private Float gain;
    private int nbgain;
    private int version;
    private Float mise;

    public HistoriquePari() {
    }

    public HistoriquePari(int id, int nbperdu, Details[] detailsparis, int nbmatch, String code, Date dateparis, Float coteglobal, String idclient, Float gainpossible, Float gain, int nbgain, int version, Float mise) {
        this.id = id;
        this.nbperdu = nbperdu;
        this.detailsparis = detailsparis;
        this.nbmatch = nbmatch;
        this.code = code;
        this.dateparis = dateparis;
        this.coteglobal = coteglobal;
        this.idclient = idclient;
        this.gainpossible = gainpossible;
        this.gain = gain;
        this.nbgain = nbgain;
        this.version = version;
        this.mise = mise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbperdu() {
        return nbperdu;
    }

    public void setNbperdu(int nbperdu) {
        this.nbperdu = nbperdu;
    }

    public Details[] getDetailsparis() {
        return detailsparis;
    }

    public void setDetailsparis(Details[] detailsparis) {
        this.detailsparis = detailsparis;
    }

    public int getNbmatch() {
        return nbmatch;
    }

    public void setNbmatch(int nbmatch) {
        this.nbmatch = nbmatch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateparis() {
        return dateparis;
    }

    public void setDateparis(Date dateparis) {
        this.dateparis = dateparis;
    }

    public Float getCoteglobal() {
        return coteglobal;
    }

    public void setCoteglobal(Float coteglobal) {
        this.coteglobal = coteglobal;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public Float getGainpossible() {
        return gainpossible;
    }

    public void setGainpossible(Float gainpossible) {
        this.gainpossible = gainpossible;
    }

    public Float getGain() {
        return gain;
    }

    public void setGain(Float gain) {
        this.gain = gain;
    }

    public int getNbgain() {
        return nbgain;
    }

    public void setNbgain(int nbgain) {
        this.nbgain = nbgain;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Float getMise() {
        return mise;
    }

    public void setMise(Float mise) {
        this.mise = mise;
    }
}
