package com.bts.kst.gsb_app_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bts.kst.gsb_app_mobile.R;

import com.bts.kst.gsb_app_mobile.entity.RapportVisite;

import java.util.List;




public class RapportsVisiteAdapter extends ArrayAdapter<RapportVisite> {

    private List<RapportVisite> listRapportsVisite;

    public RapportsVisiteAdapter(Context context, List<RapportVisite> lesRapports) {
        super(context, -1, lesRapports);
        this.listRapportsVisite = lesRapports;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vItem = null;
        if (convertView != null) {
            vItem = convertView;
        } else {
            vItem = layoutInflater.inflate(R.layout.item_rapport, parent, false);
        }

        RapportVisite rapportVisite = this.listRapportsVisite.get(position);

        ImageView image = vItem.findViewById(R.id.checkListImg);
        image.setImageResource(R.drawable.listrapport_white);
        TextView num = (TextView) vItem.findViewById(R.id.idTv_NumRapport);
        num.setText(rapportVisite.getId());
        TextView dateRapport = (TextView) vItem.findViewById(R.id.idTv_DateRapport);
        dateRapport.setText(rapportVisite.getDateRapport());
        TextView dateVisite = (TextView) vItem.findViewById(R.id.idTv_DateVisite);
        dateVisite.setText(rapportVisite.getDateVisite());
        TextView TV_praticien = (TextView) vItem.findViewById(R.id.Tv_nomprenom);
        TV_praticien.setText(rapportVisite.getPraticien().getPraPrenom() + " " + rapportVisite.getPraticien().getPraNom());

        return vItem;
    };
}
