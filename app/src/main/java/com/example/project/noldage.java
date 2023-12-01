package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class noldage extends AppCompatActivity {
    ImageView news, donate;
    Button request;
    FirebaseDatabase database;
    DatabaseReference myRef, myRef1, myRef2;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noldage);
        news = findViewById(R.id.news2);
        firebaseAuth = FirebaseAuth.getInstance();
        donate = findViewById(R.id.don2);
        request = findViewById(R.id.Req2);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(noldage.this, oldnewsfeed.class);
                startActivity(it);

            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData();
                Intent it = new Intent(noldage.this, odonationrequest.class);
                startActivity(it);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(noldage.this,DonateNavigate.class);
                startActivity(it);

            }
        });
    }

    private void RequestData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ngo");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Ngoinfo urs = data.getValue(Ngoinfo.class);

                    urs.setFood("");
                    urs.setCloth("");
                    urs.setMoney("");
                    myRef.child(data.getKey()).setValue(urs);
                    break;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(noldage.this,MainActivity.class));
    }
    public  void  profile(){
        startActivity(new Intent(noldage.this,Ngoprofile.class));
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
