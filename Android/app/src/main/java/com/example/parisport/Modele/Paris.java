package com.example.parisport.Modele;

import java.util.Date;

public class Paris {

    private String id;
    private DetailsParis[] detailsParises;
    private float mise;
    private float gain;

    public Paris() {
    }

    public Paris(String id, DetailsParis[] detailsParises, float mise) {
        this.id = id;
        this.detailsParises = detailsParises;
        this.mise = mise;
    }

    public Paris(String id, DetailsParis[] detailsParises, float mise, float gain) {
        this.id = id;
        this.detailsParises = detailsParises;
        this.mise = mise;
        this.gain = gain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DetailsParis[] getDetailsParises() {
        return detailsParises;
    }

    public void setDetailsParises(DetailsParis[] detailsParises) {
        this.detailsParises = detailsParises;
    }

    public float getMise() {
        return mise;
    }

    public void setMise(float mise) {
        this.mise = mise;
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
