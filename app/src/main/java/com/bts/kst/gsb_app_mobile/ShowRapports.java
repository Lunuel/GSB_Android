package com.bts.kst.gsb_app_mobile;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bts.kst.gsb_app_mobile.adapter.RapportsVisiteAdapter;
import com.bts.kst.gsb_app_mobile.entity.Praticien;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;
import com.bts.kst.gsb_app_mobile.fragment.AddRapportFragment;
import com.bts.kst.gsb_app_mobile.fragment.LogoutFragment;
import com.bts.kst.gsb_app_mobile.fragment.ProfilFragment;
import com.bts.kst.gsb_app_mobile.fragment.RapportFragment;
import com.bts.kst.gsb_app_mobile.fragment.RapportsFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRapports extends AppCompatActivity {

    SharedPreferences session_user;
    TextView TV_navLibelle;
    List<RapportVisite> listRapportsVisite;
    List<Praticien> listPraticiens;
    public Visiteur visiteur;
    public RapportVisite rapportVisite;
    public Praticien praticien;
    String matricule;

    public Fragment fragment_profil = new ProfilFragment();
    public Fragment fragment_rapports = new RapportsFragment();
    public Fragment fragment_rapport = new RapportFragment();
    public Fragment fragment_addrapport = new AddRapportFragment();
    public Fragment fragment_logout = new LogoutFragment();
    public FragmentManager fm = getSupportFragmentManager();
    public Fragment active;

    public int initData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rapports);

        // Session init values
        session_user = getSharedPreferences("user", MODE_PRIVATE);

        // init lists
        this.listRapportsVisite = new ArrayList<RapportVisite>();
        this.listPraticiens = new ArrayList<Praticien>();

        // set matricule from before activity
        matricule = session_user.getString("matricule", null);

        initData = 0;

        //Init user informations
        fetchDataArray("http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/" + matricule, "visiteur");

        //Init rapports de visite user
        fetchDataArray("http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/" + matricule + "/Rapports/", "rapports_visites");

        //Init praticiens du visiteur
        fetchDataArray("http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/" + matricule + "/Praticiens/", "praticiens");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm.beginTransaction().add(R.id.main_container, fragment_logout, "5").hide(fragment_logout).commit();

        // set active fragment
        active =  fragment_rapports;

    }

    /**
    * Select item from bottom navigation
    */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            TV_navLibelle = findViewById(R.id.TV_navLibelle); //TextView Navigation Libelle
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    TV_navLibelle.setText("Profil");
                    fm.beginTransaction().hide(active).show(fragment_profil).commit();
                    active = fragment_profil;
                    return true;
                case R.id.navigation_rapports:
                    TV_navLibelle.setText("Rapports de visites");
                    fm.beginTransaction().hide(active).show(fragment_rapports).commit();
                    active = fragment_rapports;
                    return true;
                case R.id.navigation_addrapport:
                    TV_navLibelle.setText("Ajouter un rapport");
                    fm.beginTransaction().hide(active).show(fragment_addrapport).commit();
                    active = fragment_addrapport;
                    return true;
                case R.id.navigation_logout:
                    TV_navLibelle.setText("Se déconnecter");
                    fm.beginTransaction().hide(active).show(fragment_logout).commit();
                    active = fragment_logout;
                    return true;
            }
            return false;
        }
    };

    /**
     * Fetch data from local web service on symfony
     * received : array
     */
    public void fetchDataArray(String url, String type) {

        RequestQueue requestQueue = null;
        requestQueue = Volley.newRequestQueue(ShowRapports.this);
        final String typeObject = type;
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.e("Response",response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                JSONObject object = (JSONObject) response.get(i);

                                String jsonResponse = object.toString();
                                switch (typeObject) {
                                    case "visiteur":
                                        visiteur = gson.fromJson(jsonResponse, Visiteur.class);
                                        fragment_profil = ProfilFragment.newInstance(visiteur);
                                        fm.beginTransaction().add(R.id.main_container, fragment_profil, "2").hide(fragment_profil).commit();
                                        break;
                                    case "rapports_visites":
                                        rapportVisite = gson.fromJson(jsonResponse, RapportVisite.class);
                                        listRapportsVisite.add(rapportVisite);
                                        break;
                                    case "praticiens":
                                        praticien = gson.fromJson(jsonResponse, Praticien.class);
                                        listPraticiens.add(praticien);
                                        break;
                                }

                            }
                            if (typeObject.equals("rapports_visites")) {
                                fragment_rapports = RapportsFragment.newInstance(listRapportsVisite);
                                fm.beginTransaction().add(R.id.main_container, fragment_rapports, "1").hide(fragment_rapports).commit();
                                active = fragment_rapports;
                                fm.beginTransaction().show(active).commit();
                            }
                            if (typeObject.equals("praticiens")) {
                                fragment_addrapport = AddRapportFragment.newInstance(listPraticiens);
                                fm.beginTransaction().add(R.id.main_container, fragment_addrapport, "4").hide(fragment_addrapport).commit();
                                active = fragment_rapports;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        initData++;
                        Log.e("Response",error.toString());
                        Toast.makeText(ShowRapports.this, "Chargement", Toast.LENGTH_LONG).show();
                        ShowRapports.this.finish();
                        if (initData < 3) {
                            startActivity(ShowRapports.this.getIntent());
                        }
                        else {
                            startActivity(MainActivity.getInstance().getIntent());
                        }

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


    /**
     * Fetch data from local web service on symfony
     * received : object
     */
    public void fetchDataObject(String url, String type) {

        RequestQueue requestQueue = null;
        requestQueue = Volley.newRequestQueue(ShowRapports.this);
        final String typeObject = type;
        // Initialize a new JsonArrayRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                Log.i("Response",response.toString());
                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                switch (typeObject) {
                                    case "visiteur":
                                        //String nom = response.getString("vis_nom");
                                        //String prenom = response.getString("vis_prenom");
                                        visiteur = gson.fromJson(response.toString(), Visiteur.class);
                                        //fragment_profil = ProfilFragment.newInstance(visiteur);
                                        //fm.beginTransaction().add(R.id.main_container, fragment_profil, "1").hide(fragment_profil).commit();
                                        break;
                                    case "rapport_visite":
                                        rapportVisite = gson.fromJson(response.toString(), RapportVisite.class);
                                        break;
                                }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("onResponse",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response",error.toString());
                        Toast.makeText(ShowRapports.this, "Error Response ", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }


    /**
     * Post data to local web service on symfony
     * received : array
     */
    public static void PostData(String url, final RapportVisite rapportVisite, Context context) {
        GsonBuilder fabrique = new GsonBuilder();
        final Gson gson = fabrique.create();
        try {
            StringRequest requete = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("ResponseError", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametres = new HashMap<String, String>();
                    String jsonValues = gson.toJson(rapportVisite);
                    Log.i("json",gson.toJson(rapportVisite));
                    parametres.put("rapport", gson.toJson(rapportVisite));
                    return parametres;
                }
            };
            RequestQueue fileReq = Volley.newRequestQueue(context);
            fileReq.add(requete);
        } catch (Exception e) {
            Log.e("PostResponse", e.getMessage());
        }
    }

}


