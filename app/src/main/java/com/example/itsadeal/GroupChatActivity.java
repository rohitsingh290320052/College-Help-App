package com.example.itsadeal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupChatActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private String groupChatId;
    private TextView groupNameTextView;
    private RecyclerView recyclerViewMessages;
    private EditText messageInput;
    private Button sendButton;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        db = FirebaseFirestore.getInstance();
        groupNameTextView = findViewById(R.id.group_name);
        recyclerViewMessages = findViewById(R.id.recycler_view_messages);
        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);

        groupChatId = getIntent().getStringExtra("GROUP_CHAT_ID");

        setupRecyclerView();
        loadGroupChatInfo();
        loadMessages();

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void setupRecyclerView() {
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter();
        recyclerViewMessages.setAdapter(adapter);
    }

    private void loadGroupChatInfo() {
        db.collection("group_chats").document(groupChatId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String groupName = documentSnapshot.getString("name");
                        groupNameTextView.setText(groupName);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }

    private void loadMessages() {
        db.collection("group_chats").document(groupChatId).collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        // Handle the error
                        return;
                    }
                    if (querySnapshot != null) {
                        List<Message> messages = querySnapshot.toObjects(Message.class);
                        adapter.setMessages(messages);
                    }
                });
    }

    private void sendMessage() {
        String messageText = messageInput.getText().toString();
        if (messageText.isEmpty()) {
            return;
        }

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("text", messageText);
            messageData.put("sender_id", currentUser.getUid());  // Ensure this is set correctly
            messageData.put("timestamp", System.currentTimeMillis());

            db.collection("group_chats").document(groupChatId).collection("messages")
                    .add(messageData)
                    .addOnSuccessListener(documentReference -> messageInput.setText(""))
                    .addOnFailureListener(e -> {
                        // Handle the error
                        Log.e("GroupChatActivity", "Error sending message", e);
                    });
        } else {
            // Handle case where user is not authenticated
            Log.w("GroupChatActivity", "User not authenticated");
        }
    }

}
