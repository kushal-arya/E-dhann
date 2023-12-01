package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ngoprofile extends AppCompatActivity {
    TextView a, b, c, d;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoprofile);
        a = findViewById(R.id.textViewN1);
        b = findViewById(R.id.textViewN2);
        c = findViewById(R.id.textViewN3);
        d = findViewById(R.id.textViewN4);
        database = FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final String uid = firebaseAuth.getUid();
        myRef = database.getReference("Ngo").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    final Ngoinfo urs = snapshot.getValue(Ngoinfo.class);
                    a.setText(urs.getUserName());
                    b.setText(urs.getUserEmail());
                    c.setText(urs.getUserPhonenumber());
                    d.setText(urs.getUserAddres());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}