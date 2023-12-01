package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    EditText et,et1,address,mail,pwd,about;
    Button reg;
    Spinner sp2;
    ArrayAdapter<String> ar2;
    ArrayList<String> al2;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth fbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        et=findViewById(R.id.registrationUserName2);
        sp2 = findViewById(R.id.spinner5);
        et1=findViewById(R.id.registrationUserPhnNumber2);
        address=findViewById(R.id.addrestext2);
        mail=findViewById(R.id.registrationEmail2);
        pwd=findViewById(R.id.registrationPassword2);
        reg=findViewById(R.id.registrationButton2);
        about=findViewById(R.id.ngoinfo);
        fbAuth= FirebaseAuth.getInstance();
        ngotypeselect();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String ngotype = sp2.getSelectedItem().toString();
                String email = mail.getText().toString();
                String password = pwd.getText().toString();
                fbAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Admin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "created ", Toast.LENGTH_SHORT).show();
                                    sendData(fbAuth.getUid());

                                } else {
                                    Toast.makeText(getApplicationContext(), "failed ", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

            }
        });

    }
    private void sendData(String uid) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ngo");
        String name = et.getText().toString().trim();
        String loc = address.getText().toString().trim();
        String email = mail.getText().toString().trim();
        String mobile_no = et1.getText().toString().trim();
        String ngotype = sp2.getSelectedItem().toString().trim();
        String info = about.getText().toString().trim();
        Ngoinfo ngo = new Ngoinfo();
        ngo.setUserName(name);
        ngo.setUserEmail(email);
        ngo.setUserAddres(loc);
        ngo.setUserPhonenumber(mobile_no);
        ngo.setNgotype(ngotype);
        ngo.setMoney("");
        ngo.setCloth("");
        ngo.setFood("");
        ngo.setComanyinfo(info);
        myRef.child(uid).setValue(ngo);
        Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();

    }
    private void ngotypeselect () {
        al2 = new ArrayList<String>();
        al2.add("select-");
        al2.add("Child Orphanage");
        al2.add("NGO");
        al2.add("Old age home");
        al2.add("Blood Bank");
        al2.add("Organ donate organisation");

        ar2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al2);
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
}
