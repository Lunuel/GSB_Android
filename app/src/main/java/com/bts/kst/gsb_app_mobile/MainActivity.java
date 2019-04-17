package com.bts.kst.gsb_app_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.bts.kst.gsb_app_mobile.adapter.RapportsVisiteAdapter;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText input_login;
    EditText input_pass;
    Button button_connexion;
    SharedPreferences session_user;

    Visiteur visiteur_auth;
    String login;
    String pass;

    private static MainActivity sInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_login = findViewById(R.id.login);
        input_pass = findViewById(R.id.pass);
        button_connexion = findViewById(R.id.button_connexion);
        session_user = getSharedPreferences("user",MODE_PRIVATE);

        sInstance = this;

        // Set a click listener for button widget
        button_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(sInstance);

                login = input_login.getText().toString();
                pass = input_pass.getText().toString();
                String url = "http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/" + login + "/" + pass;
                //url = "http://10.0.2.2:8000/GIT/GITLAB/S3_GSB_Services/web/app_dev.php/Visiteur/lvillachane/azerty";

                Log.i("LogPass","Visiteur :"+ login + " | " + pass);

                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    for(int i=0;i<response.length();i++){
                                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                        JSONObject object = (JSONObject) response.get(i);

                                        String jsonResponse = object.toString();
                                        visiteur_auth = gson.fromJson(jsonResponse, Visiteur.class);


                                    }
                                    Bundle post_var = new Bundle();
                                    post_var.putString("nbRv","15");

                                    Toast.makeText(MainActivity.this, "Connexion réussie : " + visiteur_auth.getNom() + " " + visiteur_auth.getPrenom(), Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor =  session_user.edit();
                                    editor.putString("matricule",visiteur_auth.getId());

                                    editor.commit();
                                    Intent next_activity = new Intent(MainActivity.this, ShowRapports.class);
                                    next_activity.putExtras(post_var);
                                    startActivity(next_activity);

                                }catch (JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "Connexion échouée", Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Toast.makeText(MainActivity.this, "Connexion échouée", Toast.LENGTH_LONG).show();
                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }
        });

    }

    public synchronized static MainActivity getInstance() {
        return sInstance;
    }






}
