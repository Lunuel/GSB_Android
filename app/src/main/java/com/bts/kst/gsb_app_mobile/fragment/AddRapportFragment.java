package com.bts.kst.gsb_app_mobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bts.kst.gsb_app_mobile.MainActivity;
import com.bts.kst.gsb_app_mobile.R;
import com.bts.kst.gsb_app_mobile.ShowRapports;
import com.bts.kst.gsb_app_mobile.adapter.PraticienAdapter;
import com.bts.kst.gsb_app_mobile.entity.Praticien;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddRapportFragment extends Fragment  {

    String bilan,dateVisite,dateRapport,matricule;
    RapportVisite rapportVisite;

    List<Praticien> lesPraticiens;

    Spinner spinner;

    Praticien selectedPraticien;


    public AddRapportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param praticiens Parameter 1.
     * @return A new instance of fragment AddRapportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRapportFragment newInstance(List<Praticien> praticiens) {
        ArrayList<Praticien> lesPraticiens = new ArrayList<>(praticiens.size());
        lesPraticiens.addAll(praticiens);
        AddRapportFragment fragment = new AddRapportFragment();
        Bundle args = new Bundle();
        args.putSerializable("praticiens",lesPraticiens);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            lesPraticiens = (ArrayList<Praticien>) getArguments().getSerializable("praticiens");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_add_rapport, container, false);

        try{
            spinner = (Spinner) v.findViewById(R.id.spinner);
            ArrayAdapter<Praticien> adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item, lesPraticiens) ;
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedPraticien  = (Praticien) adapterView.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch(Exception e) {
            Log.e("SPINNER Adapter Error", e.toString());
        }


        //Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(this.AddRapportFragment);

        final ImageView imgAddRapport = v.findViewById(R.id.imgAddRapport);
        final EditText ET_bilan = v.findViewById(R.id.ET_bilan);
        final DatePicker DP_dateVisite = (DatePicker) v.findViewById(R.id.DP_dateVisite);


        final Button B_addRapport = v.findViewById(R.id.B_addRapport);
        imgAddRapport.setImageResource(R.drawable.addrapport_white);


        // Set a click listener for button widget
        B_addRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    rapportVisite = new RapportVisite();
                    bilan = ET_bilan.getText().toString();
                    dateVisite = DP_dateVisite.getDayOfMonth()+ "/" + (DP_dateVisite.getMonth() + 1) + "/" + DP_dateVisite.getYear();

                    String dateRapport = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());;
                    if (bilan.isEmpty()){
                        Toast.makeText(getContext(), "Le bilan est vide", Toast.LENGTH_LONG).show();
                    }
                    else {
                        rapportVisite.setBilan(bilan);
                        rapportVisite.setDateRapport(dateRapport);
                        rapportVisite.setDateVisite(dateVisite);
                        rapportVisite.setPraticien(selectedPraticien);
                        rapportVisite.setVisiteur(((ShowRapports)getActivity()).visiteur);
                        ShowRapports.PostData("http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/add/rapport/",rapportVisite,getContext());
                        Toast.makeText(getContext(), "Ajout d'un rapport", Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }

            }
        });

        return v;
    }

    public Praticien getSelectedPraticien(View v) {
        Praticien praticien = (Praticien) spinner.getSelectedItem();
        return praticien;

    }



}
