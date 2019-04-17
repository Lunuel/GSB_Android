package com.bts.kst.gsb_app_mobile.entity;

public class Visiteur {
    private String id;
    private String nom;
    private String prenom;
    private String login;
    private String pass;
    private String adresse;
    private String cp;
    private String ville;

    public Visiteur(String id,String nom,String prenom,String login,String pass,String adresse,String cp,String ville){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.pass = pass;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
    }

    public Visiteur(String id,String nom,String prenom,String login,String pass){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return getClass()+" : [Matricule : "+getId()+" , Nom : "+getNom()+", Prénom : "+getPrenom()+", Login : "+getLogin()+"]";
    }


}


