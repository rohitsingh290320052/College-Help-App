package com.example.itsadeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_page extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText emailET;
    EditText passEt;
    EditText confirmpassET;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        firebaseAuth=FirebaseAuth.getInstance();
        emailET=findViewById(R.id.emailEt);
        passEt=findViewById(R.id.passET);
        confirmpassET=findViewById(R.id.confirmPassEt);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailET.getText().toString();
                String passward=passEt.getText().toString();
                String confirmpass=confirmpassET.getText().toString();
                if(email.isEmpty() || passward.isEmpty() || confirmpass.isEmpty()){
                    Toast.makeText(Signup_page.this, "empty fields are not allowed", Toast.LENGTH_SHORT).show();
                }
                if (passward.equals(confirmpass)) {

                    firebaseAuth.createUserWithEmailAndPassword(email,passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Signup_page.this, Signin_page.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Signup_page.this, "Unknown error occured, please try agian later", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    }
                else {
                    Toast.makeText(Signup_page.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
                }

        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup_page.this,Signin_page.class);
                startActivity(intent);
            }
        });
    }
}