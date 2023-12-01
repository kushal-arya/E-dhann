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

import java.util.ArrayList;
import java.util.List;

public class oldnewsfeed extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<oldUser> peopleList;
    ListView mListView;


    Oldageadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldnewsfeed);
        mListView = findViewById(R.id.recycle2);
        peopleList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("oldage");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // val = dataSnapshot.getKey();
                //Toast.makeText(getApplicationContext(), "" + val, Toast.LENGTH_SHORT).show();
                peopleList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    oldUser old = data.getValue(oldUser.class);

                    peopleList.add(old);


                }
                adapter = new Oldageadapter(oldnewsfeed.this, R.layout.custom_oldage_details, peopleList);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


