package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fooddonate extends AppCompatActivity {
    String uname, Nname;
    Button donate;
    FirebaseDatabase database;
    //  String n;
    DatabaseReference myRef, myRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddonate);
        donate = findViewById(R.id.buttonDF);
        Intent i = getIntent();
        Nname = i.getStringExtra("name2");
        database = FirebaseDatabase.getInstance();
        username();
    }
    private void username() {
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final String uid = firebaseAuth.getUid();
        myRef = database.getReference("Users").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                final Userinfo urs = snapshot.getValue(Userinfo.class);
                uname = urs.getUserName();

                //Toast.makeText(Moneydonate.this, "" + uname + "  " + Nname, Toast.LENGTH_LONG).show();
                donate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef1 = database.getReference("Food");
                        Food c = new Food();
                        c.setUname(uname);
                        c.setNname(Nname);
                        myRef1.child(uid).setValue(c);

                    }

                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}