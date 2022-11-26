package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserInfo extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private TextView Name,Phone,Email;
    private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Name = findViewById(R.id.NameOfuser);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.EmailOfuser);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UID = firebaseAuth.getCurrentUser().getUid();
        documentReference = firebaseFirestore.collection("users").document(UID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Name.setText("Name Of the user : " + value.getString("FirstName") + " " +value.getString("LastName"));
                Phone.setText("The Phone Number : " + value.getString("Phone"));
                Email.setText("The Email : " + value.getString("Email"));
            }
        });
    }
}