package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class bloodneed extends AppCompatActivity  {


    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Userinfo> peopleList;
    ListView mListView;
    PersonListAdapter adapter;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodneed);
        firebaseAuth = FirebaseAuth.getInstance();
        mListView = findViewById(R.id.listview3);
        peopleList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // val = dataSnapshot.getKey();
                //Toast.makeText(getApplicationContext(), "" + val, Toast.LENGTH_SHORT).show();
                peopleList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Userinfo urs = data.getValue(Userinfo.class);
                    if (!(urs.getBloodgp()).equals("")) {
                        peopleList.add(urs);
                    }
                }
                 adapter = new PersonListAdapter(bloodneed.this, R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(bloodneed.this, bloodneedinfo.class);
                Userinfo urs = adapter.getItem(position);
                intent.putExtra("name", urs.getUserName());
                intent.putExtra("age", urs.getUserAge());
                intent.putExtra("phno", urs.getUserPhonenumber());
                intent.putExtra("bg", urs.getBloodgp());
                intent.putExtra("gender", urs.getGender());
                startActivity(intent);
                finish();
            }
        });
    }

    public void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(bloodneed.this,MainActivity.class));
    }
    public  void  profile(){
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final String uid = firebaseAuth.getUid();
        myRef = database.getReference("Users").child(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Userinfo urs = data.getValue(Userinfo.class);
                    if (urs.getCode().equals("user")) {
                        startActivity(new Intent(bloodneed.this,Profilepage.class));

                    }
                    else{
                        startActivity(new Intent(bloodneed.this,Ngoprofile.class));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logoutMenu:
                Logout();
                break;
            case R.id.info:
                 profile();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


}
