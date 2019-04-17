package com.bts.kst.gsb_app_mobile.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bts.kst.gsb_app_mobile.R;
import com.bts.kst.gsb_app_mobile.ShowRapports;
import com.bts.kst.gsb_app_mobile.adapter.RapportsVisiteAdapter;
import com.bts.kst.gsb_app_mobile.entity.Praticien;
import com.bts.kst.gsb_app_mobile.entity.RapportVisite;
import com.bts.kst.gsb_app_mobile.entity.Visiteur;

import java.io.Serializable;
import java.util.List;

/**
 * Fragment with all rappports
 * create an instance of this fragment.
 */
public class RapportsFragment extends Fragment {

    private static final String LISTRAPPORTS = "listRapportsVisite";

    int nbRapports;
    List<RapportVisite> listRapportsVisite;


    public RapportsFragment() {
        // Required empty public constructor
    }

    /**
     * New instance with liste des rapports de visite
     *
     * @param  listRapportsVisite .
     * @return A new instance of fragment RapportsFragment.
     */
    public static RapportsFragment newInstance(List<RapportVisite> listRapportsVisite) {
        RapportsFragment fragment = new RapportsFragment();
        Bundle args = new Bundle();
        args.putSerializable(LISTRAPPORTS, (Serializable) listRapportsVisite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listRapportsVisite = (List<RapportVisite>) getArguments().getSerializable(LISTRAPPORTS);
            nbRapports = listRapportsVisite.size();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rapports, container, false);

        ListView LV_Rv = v.findViewById(R.id.LV_Rv);
        TextView TV_nbRv = v.findViewById(R.id.TV_nbRv);

        try{

            if (listRapportsVisite.size() == 0){
                TV_nbRv.setText("Aucun rapport disponible");
            }else {
                TV_nbRv.setText("Nombre de Rapports : " + nbRapports);
                RapportsVisiteAdapter RvAdapter = new RapportsVisiteAdapter(getContext(), listRapportsVisite);
                LV_Rv.setAdapter(RvAdapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /**
         * Selected rapport de visite
         */
        LV_Rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RapportVisite rapportVisite = listRapportsVisite.get(position);
                ((ShowRapports)getActivity()).fragment_rapport = RapportFragment.newInstance(rapportVisite);
                ((ShowRapports)getActivity()).fm.beginTransaction().add(R.id.main_container, ((ShowRapports)getActivity()).fragment_rapport, "3").commit();
                ((ShowRapports)getActivity()).fm.beginTransaction().hide(((ShowRapports)getActivity()).active).show(((ShowRapports)getActivity()).fragment_rapport).commit();
                ((ShowRapports)getActivity()).active = ((ShowRapports)getActivity()).fragment_rapport;

            }

        });

        return v;
    }

}
