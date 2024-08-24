package com.example.itsadeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomSQLiteQuery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin_page extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText emailET;
    EditText passET;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);
        firebaseAuth=FirebaseAuth.getInstance();
        emailET=findViewById(R.id.emailEt);
        passET=findViewById(R.id.passET);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailET.getText().toString();
                String passward=passET.getText().toString();
                if(email.isEmpty() || passward.isEmpty()){
                    Toast.makeText(Signin_page.this, "please check your email and passward again", Toast.LENGTH_SHORT).show();
                }
                    firebaseAuth.signInWithEmailAndPassword(email,passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(Signin_page.this,first_page.class);
                                intent.putExtra("user_email", email);
                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(Signin_page.this,"An issue occured, Please try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin_page.this,Signup_page.class);
                startActivity(intent);
            }
        });

    }
}