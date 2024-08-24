package com.example.itsadeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class JoinGroupChatActivity extends AppCompatActivity {

    private static final String TAG = "JoinGroupChatActivity";
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private EditText groupNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group_chat);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        groupNameInput = findViewById(R.id.group_name_input);

        Button joinButton = findViewById(R.id.join_button);
        joinButton.setOnClickListener(v -> joinGroupChat());
    }

    private void joinGroupChat() {
        String enteredGroupName = groupNameInput.getText().toString().trim();

        if (enteredGroupName.isEmpty()) {
            Log.d(TAG, "Group Name is empty.");
            return;
        }

        Log.d(TAG, "Attempting to join group chat with name: " + enteredGroupName);

        // Query for the group chat by name
        db.collection("group_chats")
                .whereEqualTo("name", enteredGroupName)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // Assume there's only one group chat with the given name
                        String groupChatId = queryDocumentSnapshots.getDocuments().get(0).getId();
                        Log.d(TAG, "Group chat found. ID: " + groupChatId);

                        // Add user to the group chat
                        String userId = auth.getCurrentUser().getUid();
                        if (userId != null) {
                            Map<String, Object> userGroupData = new HashMap<>();
                            userGroupData.put("group_chat_id", groupChatId);

                            db.collection("users").document(userId).collection("group_chats")
                                    .document(groupChatId)
                                    .set(userGroupData)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d(TAG, "User successfully added to group chat.");
                                        startGroupChatActivity(groupChatId);
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e(TAG, "Error adding user to group chat", e);
                                    });
                        } else {
                            Log.e(TAG, "User not authenticated.");
                        }
                    } else {
                        Log.d(TAG, "No group chat found with the provided name.");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error searching for group chat", e);
                });
    }

    private void startGroupChatActivity(String groupChatId) {
        Intent intent = new Intent(this, GroupChatActivity.class);
        intent.putExtra("GROUP_CHAT_ID", groupChatId);
        startActivity(intent);
    }
}
