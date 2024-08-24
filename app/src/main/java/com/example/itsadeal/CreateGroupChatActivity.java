package com.example.itsadeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class CreateGroupChatActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private EditText groupNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_chat);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        groupNameInput = findViewById(R.id.group_name_input);

        Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(v -> createGroupChat());
    }

    private void createGroupChat() {
        String groupName = groupNameInput.getText().toString();
        if (groupName.isEmpty()) {
            return;
        }

        Map<String, Object> groupChatData = new HashMap<>();
        groupChatData.put("name", groupName);
        groupChatData.put("created_at", System.currentTimeMillis());

        db.collection("group_chats").add(groupChatData)
                .addOnSuccessListener(documentReference -> {
                    String groupChatId = documentReference.getId();
                    saveGroupChatId(groupChatId);
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }

    private void saveGroupChatId(String groupChatId) {
        Map<String, Object> userGroupData = new HashMap<>();
        userGroupData.put("group_chat_id", groupChatId);

        String userId = auth.getCurrentUser().getUid();
        db.collection("users").document(userId).collection("group_chats")
                .document(groupChatId)
                .set(userGroupData)
                .addOnSuccessListener(aVoid -> {
                    // Successfully saved
                    startGroupChatActivity(groupChatId);
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }

    private void startGroupChatActivity(String groupChatId) {
        Intent intent = new Intent(this, GroupChatActivity.class);
        intent.putExtra("GROUP_CHAT_ID", groupChatId);
        startActivity(intent);
    }
}
