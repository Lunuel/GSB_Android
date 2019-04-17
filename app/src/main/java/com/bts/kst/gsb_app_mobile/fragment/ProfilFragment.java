package com.bts.kst.gsb_app_mobile.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bts.kst.gsb_app_mobile.R;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {

    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ADRESSE = "adresse";
    private static final String CP = "cp";
    private static final String VILLE = "ville";

    String nom,prenom,login,password,adresse,cp,ville = "";

    Visiteur visiteur;


    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(Visiteur visiteur) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(NOM, visiteur.getNom());
        args.putString(PRENOM, visiteur.getPrenom());
        args.putString(LOGIN, visiteur.getLogin());
        args.putString(PASSWORD, visiteur.getPass());
        args.putString(ADRESSE, visiteur.getAdresse());
        args.putString(CP, visiteur.getCp());
        args.putString(VILLE, visiteur.getVille());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nom = getArguments().getString(NOM);
            prenom = getArguments().getString(PRENOM);
            login = getArguments().getString(LOGIN);
            password = getArguments().getString(PASSWORD);
            adresse = getArguments().getString(ADRESSE);
            cp = getArguments().getString(CP);
            ville = getArguments().getString(VILLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.fragment_profil, container, false);
        TextView nom = v.findViewById(R.id.TV_nom);
        nom.setText(this.nom);

        TextView TV_prenom = v.findViewById(R.id.TV_prenom);
        TV_prenom.setText(this.prenom);

        TextView TV_login = v.findViewById(R.id.TV_login);
        TV_login.setText(this.login);

        TextView TV_password = v.findViewById(R.id.TV_pass);
        TV_password.setText(this.password);

        TextView TV_adresse = v.findViewById(R.id.TV_adresse);
        TV_adresse.setText(this.adresse);

        TextView TV_cp = v.findViewById(R.id.TV_cp);
        TV_cp.setText(this.cp);

        TextView TV_ville = v.findViewById(R.id.TV_ville);
        TV_ville.setText(this.ville);

        ImageView image = v.findViewById(R.id.imgProfil);
        image.setImageResource(R.drawable.user_white);
        return v;


    }

}
