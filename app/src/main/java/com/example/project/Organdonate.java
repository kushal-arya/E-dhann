package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Organdonate extends AppCompatActivity {
    TextView All;
    Spinner sp,sp1,sp2;
    Button submit;
    EditText desp;
    String text,text1,text2,prev;
    ArrayAdapter<String> ar;
    ArrayList<String> al;
    ArrayAdapter<String> ar1;
    ArrayList<String> al1;
    ArrayAdapter<String> ar2;
    ArrayList<String> al2;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Userinfo urs = new Userinfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organdonate);
        All = findViewById(R.id.Heading);
        sp = findViewById(R.id.spinner2);
        sp1 = findViewById(R.id.spinner3);
        sp2 = findViewById(R.id.spinner4);
        desp = findViewById(R.id.editTextdes);
        submit = findViewById(R.id.buttonsub);
        c = findViewById(R.id.checkBox);
        c1 = findViewById(R.id.checkBox1);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);
        c7 = findViewById(R.id.checkBox7);
        c8 = findViewById(R.id.checkBox8);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
       BloodType();

        BodySize();

        donatetype();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                text = sp.getSelectedItem().toString();
                text1 = sp1.getSelectedItem().toString();
                text2 = sp2.getSelectedItem().toString();
                prev = desp.getText().toString();

                if ((text.equals("select-")) && (text1.equals("select-")) && (text2.equals("select-"))&&(prev.equals(""))) {
                    Toast.makeText(getApplicationContext(), "Enter all the feilds", Toast.LENGTH_SHORT).show();
                } else {

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                urs = data.getValue(Userinfo.class);
                                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                if ((urs.getUserEmail()).equalsIgnoreCase(email)) {
                                    urs.setBloodgp(text);
                                    urs.setBodysize(text1);
                                    urs.setPrevdes(prev);
                                    urs.setOdonatetype(text2);
                                    onCheckBoxChecked();

                                    myRef.child(data.getKey()).setValue(urs);
                                    break;

                                }
                            }

                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    private void BloodType() {
        al = new ArrayList<String>();
        al.add("select-");
        al.add("O+");
        al.add("O-");
        al.add("A+");
        al.add("A-");
        al.add("B+");
        al.add("B-");
        al.add("AB+");
        al.add("AB-");
        ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, al);
        sp.setAdapter(ar);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) sp.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

        private void donatetype () {
            al2 = new ArrayList<String>();
            al2.add("select-");
            al2.add("(DBD)Donation after Brain Stem Death");
            al2.add("Donation after Circulatory Death: ");
            al2.add("Living Organ Donation: ");
            ar2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, al2);
            sp2.setAdapter(ar2);
            sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String data = (String) sp2.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        private void BodySize () {
            al1 = new ArrayList<String>();
            al1.add("select-");
            for (int i = 20; i <= 225; i++) {
                al1.add(i + " CM");
            }
            ar1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, al1);
            sp1.setAdapter(ar1);
            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String data = (String) sp1.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }


    public void onCheckBoxChecked() {

                if (c.isChecked()) {
                    String organ = "Brain";
                    urs.setBrain(organ);


                }
                if (c1.isChecked()) {
                    String organ = "Eye";
                    urs.setEye(organ);

                }
                if (c2.isChecked()) {
                    String organ = "heart";
                    urs.setHeart(organ);

                }
                if (c3.isChecked()) {
                    String organ = "Kidney";
                    urs.setKidney(organ);


                }
                if (c4.isChecked()) {
                    String organ = "Lunges";
                    urs.setLungs(organ);


                }
                if (c5.isChecked()) {
                    String organ = "Liver";
                    urs.setLiver(organ);


                }
                if (c6.isChecked()) {
                    String organ = "Small Intestine";
                    urs.setSmallint(organ);

                }
                if (c7.isChecked()) {
                    String organ = "Pancreas";
                    urs.setPancs(organ);

                }
                if (c8.isChecked()) {
                    String organ = "Large Intestine";
                    urs.setLargeintestine(organ);

                }


    }
}
