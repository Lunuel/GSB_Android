package com.bts.kst.gsb_app_mobile.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bts.kst.gsb_app_mobile.R;
import com.bts.kst.gsb_app_mobile.entity.Praticien;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RapportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RapportFragment extends Fragment {

    private static final String NUM = "num";
    private static final String BILAN = "bilan";
    private static final String DATEVISITE = "dateVisite";
    private static final String DATERAPPORT = "dateRapport";

    RapportVisite rapportVisite;


    public RapportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param rapportVisite Parameter 1.

     * @return A new instance of fragment RapportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RapportFragment newInstance(RapportVisite rapportVisite) {
        RapportFragment fragment = new RapportFragment();
        Bundle args = new Bundle();
        args.putSerializable("rapportVisite", rapportVisite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.rapportVisite = (RapportVisite) getArguments().getSerializable("rapportVisite");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_rapport, container, false);

        TextView TV_num = v.findViewById(R.id.TV_idRapport);
        TextView TV_bilan = v.findViewById(R.id.TV_bilan);
        TextView TV_dateVisite = v.findViewById(R.id.TV_dateVisite);
        TextView TV_dateRapport = v.findViewById(R.id.TV_dateRapport);
        ImageView imgR = v.findViewById(R.id.imgRapport);
        ImageView imgP = v.findViewById(R.id.imgPraticien);
        TextView TV_idPraticien = v.findViewById(R.id.TV_idPraticien);
        TextView TV_nom = v.findViewById(R.id.TV_nom);
        TextView TV_prenom = v.findViewById(R.id.TV_prenom);
        TextView TV_adresse = v.findViewById(R.id.TV_addr);
        TextView TV_ville = v.findViewById(R.id.TV_ville);
        TextView TV_notoriete = v.findViewById(R.id.TV_notoriete);
        TextView TV_type = v.findViewById(R.id.TV_type);


        try {
            TV_num.setText("Rapport n°" + this.rapportVisite.getId());
            TV_bilan.setText(this.rapportVisite.getBilan());
            TV_dateVisite.setText(this.rapportVisite.getDateVisite());
            TV_dateRapport.setText(this.rapportVisite.getDateRapport());
            imgR.setImageResource(R.drawable.addrapport_white);
            imgP.setImageResource(R.drawable.user_white);
            TV_idPraticien.setText("Praticien n°" + this.rapportVisite.getPraticien().getPraNum());
            TV_nom.setText(this.rapportVisite.getPraticien().getPraNom());
            TV_prenom.setText(this.rapportVisite.getPraticien().getPraPrenom());
            TV_adresse.setText(this.rapportVisite.getPraticien().getPraAdresse());
            TV_ville.setText(this.rapportVisite.getPraticien().getPraVille() + " | " + this.rapportVisite.getPraticien().getPraCp());
            TV_notoriete.setText(Float.toString(this.rapportVisite.getPraticien().getPraCoefNotoriete()));
            TV_type.setText(this.rapportVisite.getPraticien().getPraTypeLibelle());
        }catch (Exception e){
            e.printStackTrace();
        }



        return v;
    }


}
