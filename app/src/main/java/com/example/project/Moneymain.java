package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Moneymain extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Ngoinfo> peopleList;
    ListView mListView;
    MoneyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneymain);
        mListView = findViewById(R.id.mon1);
        peopleList = new ArrayList<Ngoinfo>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ngo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // val = dataSnapshot.getKey();
                //Toast.makeText(getApplicationContext(), "" + val, Toast.LENGTH_SHORT).show();
                peopleList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Ngoinfo urs = data.getValue(Ngoinfo.class);
                   if (!(urs.getMoney()).equals("")) {
                        peopleList.add(urs);
                    }
                }
                adapter = new MoneyAdapter(Moneymain.this, R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Moneymain.this, Moneydonate.class);
                Ngoinfo urs = adapter.getItem(position);
                intent.putExtra("name", urs.getUserName());

                startActivity(intent);

            }
        });
}
}