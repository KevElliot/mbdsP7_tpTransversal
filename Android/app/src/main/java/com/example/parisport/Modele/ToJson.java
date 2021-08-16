package com.example.parisport.Modele;

public class ToJson {
    private String idclient;
    private DetailsParis[] detailsparis;
    private float mise;

    public ToJson(String idclient, DetailsParis[] detailsparis, float mise) {
        this.idclient = idclient;
        this.detailsparis = detailsparis;
        this.mise = mise;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public DetailsParis[] getDetailsparis() {
        return detailsparis;
    }

    public void setDetailsparis(DetailsParis[] detailsparis) {
        this.detailsparis = detailsparis;
    }

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }
}
