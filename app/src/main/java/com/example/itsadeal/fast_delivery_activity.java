package com.example.itsadeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fast_delivery_activity extends AppCompatActivity {

    private EditText nameEditText, amountEditText, deliveryTimeEditText;
    private Button saveButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_delivery);

        // Initialize EditTexts and Button
        nameEditText = findViewById(R.id.name);
        amountEditText = findViewById(R.id.ammount_company);
        deliveryTimeEditText = findViewById(R.id.delivety_time);
        saveButton = findViewById(R.id.save);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Set OnClickListener for Save Button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFirebase();
            }
        });
    }

    // Method to save data to Firebase Database
    private void saveDataToFirebase() {
        // Get text from EditText fields
        String name = nameEditText.getText().toString().trim();
        String amount = amountEditText.getText().toString().trim();
        String deliveryTime = deliveryTimeEditText.getText().toString().trim();

        // Check if any field is empty
        if (name.isEmpty() || amount.isEmpty() || deliveryTime.isEmpty()) {
            Toast.makeText(fast_delivery_activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create an instance of Order class (a POJO) with the data
        Order order = new Order(name, amount, deliveryTime);

        // Push data to Firebase under "orders" node
        databaseReference.child("orders").push().setValue(order);

        // Optionally, clear EditText fields after saving
        nameEditText.setText("");
        amountEditText.setText("");
        deliveryTimeEditText.setText("");

        // Inform the user that data has been saved
        Toast.makeText(fast_delivery_activity.this, "Data saved to Firebase", Toast.LENGTH_SHORT).show();
    }
}