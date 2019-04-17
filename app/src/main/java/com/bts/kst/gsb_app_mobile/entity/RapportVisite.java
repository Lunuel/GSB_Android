package com.bts.kst.gsb_app_mobile.entity;

import android.text.method.PasswordTransformationMethod;

import java.io.Serializable;
import java.util.Date;

public class RapportVisite implements Serializable {

    private String id_Rapport;
    private Visiteur visiteur;
    private Praticien praticien;
    private String bilan;
    private String dateVisite;
    private String dateRapport;

    public RapportVisite(String id,Visiteur visiteur,Praticien praticien,String bilan,String dateVisite, String dateRapport){
        this.id_Rapport = id;
        this.visiteur = visiteur;
        this.praticien = praticien;
        this.bilan = bilan;
        this.dateVisite = dateVisite;
        this.dateRapport = dateRapport;
    }
    public RapportVisite(String id,String bilan,String dateVisite, String dateRapport,Praticien praticien){
        this.id_Rapport = id;
        this.bilan = bilan;
        this.dateVisite = dateVisite;
        this.dateRapport = dateRapport;
        this.praticien = praticien;
    }

    public RapportVisite(String id,String bilan){
        this.id_Rapport = id;
        this.bilan = bilan;
    }

    public RapportVisite(){

    }

    public String getId() {
        return id_Rapport;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public String getBilan() {
        return bilan;
    }

    public String getDateRapport() {
        return dateRapport;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public void setId(String id) {
        this.id_Rapport = id;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setDateRapport(String dateRapport) {this.dateRapport = dateRapport;}

    /*public String toString() {
        return getClass()+" : [Id_Rapport : "+getId()+" , Visiteur : "+getVisiteur().getId()+", Praticien : "+getPraticien().getId()+", Bilan : "+getBilan()+"]";
    }*/


}
