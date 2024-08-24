package com.example.itsadeal;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BlankFragment extends Fragment {
    private TextView emailTextView;
    private ImageView rentItemImageView;
    private ImageView fastDeliveryImageView;
    private ImageView comingSoonImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        emailTextView = view.findViewById(R.id.user_email);

        // Get the email from the arguments
        Bundle arguments = getArguments();
        if (arguments != null) {
            String userEmail = arguments.getString("user_email");
            if (userEmail != null && userEmail.contains("@gmail.com")) {
                String emailPrefix = userEmail.split("@")[0];
                emailTextView.setText(emailPrefix);
            } else {
                emailTextView.setText(userEmail);
            }
        }
        rentItemImageView = view.findViewById(R.id.rentitem_f1);
        fastDeliveryImageView = view.findViewById(R.id.fastdelivery_f1);
        comingSoonImageView = view.findViewById(R.id.comingsoon_f1);

        rentItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for fastDeliveryImageView
        fastDeliveryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), fast_delivery_activity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for comingSoonImageView


        return view;
    }
}
