package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Onewsfeed extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private List<uploadinfo> mUploads;


    Orphanadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onewsfeed);
        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orphan");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // val = dataSnapshot.getKey();
                //Toast.makeText(getApplicationContext(), "" + val, Toast.LENGTH_SHORT).show();
                mUploads.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    uploadinfo urs = data.getValue(uploadinfo.class);

                        mUploads.add(urs);



                }
                adapter = new Orphanadapter(Onewsfeed.this,mUploads);
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
