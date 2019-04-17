package com.bts.kst.gsb_app_mobile.entity;

import java.io.Serializable;

public class Praticien implements Serializable {
    private int praNum;
    private String praNom;
    private String praPrenom;
    private String praAdresse;
    private String praCp;
    private String praVille;
    private float praCoefNotoriete;
    private String praTypeLibelle;

    public float getPraCoefNotoriete() {
        return praCoefNotoriete;
    }

    @Override
    public String toString() {
        return this.praPrenom + " " + this.praNom ;
    }

    public int getPraNum() {
        return praNum;
    }

    public String getPraAdresse() {
        return praAdresse;
    }

    public String getPraCp() {
        return praCp;
    }

    public String getPraNom() {
        return praNom;
    }

    public String getPraPrenom() {
        return praPrenom;
    }

    public String getPraTypeLibelle() {
        return praTypeLibelle;
    }

    public String getPraVille() {
        return praVille;
    }

    public void setPraAdresse(String praAdresse) {
        this.praAdresse = praAdresse;
    }

    public void setPraCoefNotoriete(float praCoefNotoriete) {
        this.praCoefNotoriete = praCoefNotoriete;
    }

    public void setPraCp(String praCp) {
        this.praCp = praCp;
    }

    public void setPraNom(String praNom) {
        this.praNom = praNom;
    }

    public void setPraNum(int praNum) {
        this.praNum = praNum;
    }

    public void setPraPrenom(String praPrenom) {
        this.praPrenom = praPrenom;
    }

    public void setPraTypeCode(String praTypeCode) {
        this.praTypeLibelle = praTypeCode;
    }

    public void setPraVille(String praVille) {
        this.praVille = praVille;
    }

    public Praticien(int praNum, String praNom, String praPrenom, String praAdresse, String praCp,
                     String praVille, float praCoefNotoriete, String praTypeCode, String praVisiteur) {
        this.praNum = praNum;
        this.praNom = praNom;
        this.praPrenom = praPrenom;
        this.praAdresse = praAdresse;
        this.praCp = praCp;
        this.praVille = praVille;
        this.praCoefNotoriete = praCoefNotoriete;
        this.praTypeLibelle = praTypeLibelle;
    }

    public Praticien() {
        super();
    }

}
