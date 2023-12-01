package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Organneed extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Userinfo> peopleList;
    ListView mListView;
    PersonListAdapter2 adapter;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organneed);
        mListView = findViewById(R.id.listdonate);
        firebaseAuth = FirebaseAuth.getInstance();
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
                    if (!(urs.getBodysize()).equals("")) {
                        peopleList.add(urs);
                    }
                }
                adapter = new PersonListAdapter2(Organneed.this, R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Organneed.this, Organneedinfo.class);
                Userinfo urs = adapter.getItem(position);
                intent.putExtra("name", urs.getUserName());
                intent.putExtra("age", urs.getUserAge());
                intent.putExtra("phno", urs.getUserPhonenumber());
                intent.putExtra("bg", urs.getBloodgp());
                intent.putExtra("size", urs.getBodysize());
                intent.putExtra("type", urs.getOdonatetype());
                intent.putExtra("prev", urs.getPrevdes());
                intent.putExtra("br", urs.getBrain());
                intent.putExtra("eye", urs.getEye());
                intent.putExtra("heart", urs.getHeart());
                intent.putExtra("kid", urs.getKidney());
                intent.putExtra("lun", urs.getLungs());
                intent.putExtra("liv", urs.getLiver());
                intent.putExtra("small", urs.getSmallint());
                intent.putExtra("pan", urs.getPancs());
                intent.putExtra("lag", urs.getLargeintestine());

                startActivity(intent);
                finish();
            }
        });

    }
    public void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Organneed.this,MainActivity.class));
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
                // profile();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
