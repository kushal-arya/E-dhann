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

public class Profilepage extends AppCompatActivity {
    TextView a, b, c, d;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
        a = findViewById(R.id.textViewu1);
        b = findViewById(R.id.textViewu2);
        c = findViewById(R.id.textViewN3);
        d = findViewById(R.id.textViewN4);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot data : snapshot.getChildren()) {
                        Userinfo urs = data.getValue(Userinfo.class);
                        a.setText(urs.getUserName());
                        b.setText(urs.getUserAge());
                        c.setText(urs.getUserEmail());
                        d.setText(urs.getUserPhonenumber());
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}