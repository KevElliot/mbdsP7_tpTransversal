package com.example.parisport.Modele;

public class DemandeJetons {
    private int id;
    private String iduser;
    private int jetonsdemande;
    private boolean statut;

    public DemandeJetons() {
    }

    public DemandeJetons(String iduser, int jetonsdemande, boolean statut) {
        this.iduser = iduser;
        this.jetonsdemande = jetonsdemande;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public int getJetonsdemande() {
        return jetonsdemande;
    }

    public void setJetonsdemande(int jetonsdemande) {
        this.jetonsdemande = jetonsdemande;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
}
