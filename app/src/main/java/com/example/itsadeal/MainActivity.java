package com.example.itsadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String product_name="abcd";
    public static final String time_range="wxyz";
    public static final String university_roll_no="mnop";
    EditText product,time,roll;
    Spinner spinnerProduct;
    Button test_try;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerProduct = findViewById(R.id.spinner_product);
        time=findViewById(R.id.time);
        roll=findViewById(R.id.roll);
        test_try=findViewById(R.id.test_try);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProduct.setAdapter(adapter);
        test_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Product = spinnerProduct.getSelectedItem().toString();
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