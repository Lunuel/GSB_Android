package com.bts.kst.gsb_app_mobile.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bts.kst.gsb_app_mobile.MainActivity;
import com.bts.kst.gsb_app_mobile.R;
import com.bts.kst.gsb_app_mobile.ShowRapports;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {

    Button buttonLogout;

    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_logout, container, false);

        try{
            ImageView imgLogout = v.findViewById(R.id.imgLogout);
            imgLogout.setImageResource(R.drawable.logout_white);
            buttonLogout = v.findViewById(R.id.B_logout);
        }catch(Exception e){
            Log.e("Error Logout",e.toString());
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });


        return v;
    }

    public void logout(View vue) {

    }

}
