package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ngologin extends AppCompatActivity {
    Button login;
    EditText mail, pwd;
    Spinner sp2;
    ArrayAdapter<String> ar2;
    ArrayList<String> al2;
    TextView tv, signup,forgotpwd;
    String role,uid;
    DatabaseReference myRef;
    private FirebaseAuth fbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngologin);
        login = findViewById(R.id.loginButtonngo);
        mail = findViewById(R.id.edittexteno);
        pwd = findViewById(R.id.edittextnpw);
        tv = findViewById(R.id.textView1);
        signup = findViewById(R.id.signupngo);
        forgotpwd=findViewById(R.id.forgotPasswordngo);
        fbAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mail.getText().toString();
                final String password = pwd.getText().toString();
                fbAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ngologin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if ((task.isSuccessful())) {
                                    uid = fbAuth.getCurrentUser().getUid();

                                    myRef = FirebaseDatabase.getInstance().getReference("Ngo").child(uid);
                                    myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                             Ngoinfo urs = snapshot.getValue(Ngoinfo.class);
                                            try {
                                                role = urs.getNgotype();
                                            }
                                            catch (NullPointerException e) {
                                            }

                                                if (role == null) {
                                                    Toast.makeText(ngologin.this, "Try again", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                                 if (role.equals("Child Orphanage")) {
                                                    Intent it = new Intent(ngologin.this, norphan.class);
                                                     it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(it);
                                                } else if (role.equals("Ngo")) {
                                                    Intent it = new Intent(ngologin.this, ngo.class);
                                                     it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(it);
                                                } else if (role.equals("Old age home")) {
                                                    Intent it = new Intent(ngologin.this, noldage.class);
                                                     it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(it);
                                                } else if (role.equals("Blood Bank")) {
                                                    Intent it = new Intent(ngologin.this, bloodneed.class);
                                                     it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(it);
                                                } else if (role.equals("Organ donate organisation")) {
                                                    Intent it = new Intent(ngologin.this, Organneed.class);
                                                     it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(it);
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Choose login type", Toast.LENGTH_SHORT).show();
                                                }
                                            }


                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    //switch (role) {



                                    }

                                else {
                                    Toast.makeText(getApplicationContext(), "failed ", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(ngologin.this,Ngodocument.class);
                startActivity(it);
            }
        });
    }

}

