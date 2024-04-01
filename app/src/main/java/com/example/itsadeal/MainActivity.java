package com.example.itsadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String product_name="abcd";
    public static final String time_range="wxyz";
    public static final String university_roll_no="mnop";
    EditText product,time,roll;
    Button test_try;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product=findViewById(R.id.product);
        time=findViewById(R.id.time);
        roll=findViewById(R.id.roll);
        test_try=findViewById(R.id.test_try);
        test_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Product= product.getText().toString();
                String Time=time.getText().toString();
                String Roll=roll.getText().toString();
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(product_name,Product);
                intent.putExtra(time_range,Time);
                intent.putExtra(university_roll_no,Roll);
                startActivity(intent);
            }
        });


    }
}