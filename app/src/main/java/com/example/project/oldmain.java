package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class oldmain extends AppCompatActivity {
    RadioGroup radigrp;
    TextView name,age,gender,add,med,loc;
    Button submit;
    EditText ntext,agetext,addtext,medtext,loctext;
    FirebaseDatabase database;
    DatabaseReference myRef;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldmain);
        name = findViewById(R.id.textView2);
        age = findViewById(R.id.textView3);
        radigrp=findViewById(R.id.radioGroup);
        gender = findViewById(R.id.textView4);
        add = findViewById(R.id.textView5);
        med = findViewById(R.id.textView6);
        loc = findViewById(R.id.textView7);
        submit = findViewById(R.id.button);
        ntext = findViewById(R.id.editText);
        agetext = findViewById(R.id.editText2);
        addtext = findViewById(R.id.editText3);
        medtext = findViewById(R.id.editText4);
        loctext = findViewById(R.id.editText5);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("oldage");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namee=ntext.getText().toString();
                String agee=agetext.getText().toString();
                String addresss=addtext.getText().toString();
                String medical=medtext.getText().toString();
                String location=loctext.getText().toString();
               String val =
                        ((RadioButton) findViewById(radigrp.getCheckedRadioButtonId()))
                                .getText().toString();


                oldUser usr=new oldUser();
                usr.setName(namee);
                usr.setAge(agee);
                usr.setAddress(addresss);
                usr.setMedical(medical);
                usr.setLocation(location);
                usr.setGen(val);
                myRef.child(namee).setValue(usr);

                Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void checkButton(View v) {
        int radioId = radigrp.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

}
