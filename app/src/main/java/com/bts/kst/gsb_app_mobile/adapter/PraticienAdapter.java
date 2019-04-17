package com.bts.kst.gsb_app_mobile.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.bts.kst.gsb_app_mobile.entity.Praticien;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;

import java.util.List;

public class PraticienAdapter extends ArrayAdapter<Praticien> {

    private List<Praticien> listPraticiens;

    public PraticienAdapter(Context context, List<Praticien> lesPraticiens) {
        super(context, -1, lesPraticiens);
        this.listPraticiens = lesPraticiens;
    }

}