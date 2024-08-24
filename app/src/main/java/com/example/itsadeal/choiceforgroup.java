package com.example.itsadeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class choiceforgroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceforgroup);

        Button createGroupButton = findViewById(R.id.create_group_button);
        Button joinGroupButton = findViewById(R.id.join_group_button);

        createGroupButton.setOnClickListener(v -> {
            Intent intent = new Intent(choiceforgroup.this, CreateGroupChatActivity.class);
            startActivity(intent);
        });

        joinGroupButton.setOnClickListener(v -> {
            Intent intent = new Intent(choiceforgroup.this, JoinGroupChatActivity.class);
            startActivity(intent);
        });
    }
}
