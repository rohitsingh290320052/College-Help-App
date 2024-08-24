package com.example.itsadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class contact_activity extends AppCompatActivity {
ImageView email_us, call_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        email_us=findViewById(R.id.emailus);
        call_us=findViewById(R.id.call_us);

        call_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idial=new Intent(Intent.ACTION_DIAL);
                idial.setData(Uri.parse("tel: 1111111111"));
                startActivity(idial);
            }
        });

        email_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imail=new Intent(Intent.ACTION_SEND);
                imail.setType("message/rfc822");
                imail.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com","xua@gmail.com"});
                imail.putExtra(Intent.EXTRA_SUBJECT, "I have some issues");
                imail.putExtra(Intent.EXTRA_TEXT, "Please help me");
                startActivity(Intent.createChooser(imail,"email through"));

            }
        });
    }
}