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
public class UserInformation extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    private TextView Name,Phone,Email;
    private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
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
                Name.setText("Hello " + value.getString("FirstName") + " " +value.getString("LastName")+" !");
                Phone.setText("Your Number is '" + value.getString("Phone")+"'");
                Email.setText("And your Email '" + value.getString("Email")+"'");
            }
        });
    }
}