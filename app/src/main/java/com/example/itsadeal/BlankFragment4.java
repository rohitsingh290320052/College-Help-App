package com.example.itsadeal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class BlankFragment4 extends Fragment {



    public BlankFragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank4,container,false);
        ImageView lab= (ImageView) view.findViewById(R.id.lab);
        ImageView study= (ImageView) view.findViewById(R.id.study);
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView contact_us= (ImageView) view.findViewById(R.id.contact_us);
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), contact_activity.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String googleDriveLink = "https://drive.google.com/drive/folders/0B6guZ4n5yz_LekExUGpOQTFJLVE?resourcekey=0-mBVEEssRKc4LFyyH2m6xcg";
                Intent intent = new Intent(getActivity(),WebView_Activity.class);
                intent.putExtra("url", googleDriveLink);
                startActivity(intent);
            }
        });
        ImageView delivery= (ImageView) view.findViewById(R.id.delivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), fast_delivery_activity.class);
                startActivity(intent);
            }
        });
        ImageView hackathonclick= (ImageView) view.findViewById(R.id.hackathonclick);
        hackathonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),choiceforgroup.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}