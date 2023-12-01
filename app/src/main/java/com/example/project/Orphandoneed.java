package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Orphandoneed extends AppCompatActivity {
    CheckBox cbfood,cbclothing,cbmoney;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1,myRef2;
    Ngoinfo urs = new Ngoinfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orphandoneed);
        cbfood =findViewById(R.id.cbfood);
        cbclothing =findViewById(R.id.cbclothing);
        cbmoney =findViewById(R.id.cbmoney);
        submit = findViewById(R.id.cbsubmit);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ngo");

        cbfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbfood.isChecked()){
                    cbfood.setTextColor(getResources().getColor(R.color.colorcheckbox2));

                }
                else
                    cbfood.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });
        cbclothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbclothing.isChecked()){
                    cbclothing.setTextColor(getResources().getColor(R.color.colorAccent));

                }
                else
                    cbclothing.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });
        cbmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbmoney.isChecked()){
                    cbmoney.setTextColor(getResources().getColor(R.color.colorcheckbox1));
                   }
                else
                    cbmoney.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                          urs = data.getValue(Ngoinfo.class);

                                checkbox();

                                myRef.child(data.getKey()).setValue(urs);
                                break;


                        }

                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
    public void checkbox() {

        if (cbfood.isChecked()) {

            String food = "need";
            urs.setFood(food);

        }
        if (cbclothing.isChecked()) {

            String cloth = "need";
            urs.setCloth(cloth);

        }
        if (cbmoney.isChecked()) {

            String m = "need";
            urs.setMoney(m);

        }
    }
}
