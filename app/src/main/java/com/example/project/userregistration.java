package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userregistration<FirebaseStorage> extends AppCompatActivity {
    TextView tv,tv1;
    EditText et,et1,et2,mail,pwd;
    Button reg;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth fbAuth;
    String Phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);
        tv=findViewById(R.id.registrationTitle);
        tv1=findViewById(R.id.goBackToLogin);
        et=findViewById(R.id.registrationUserName2);
        et1=findViewById(R.id.registrationUserPhnNumber2);
        et2=findViewById(R.id.registrationUserAge);
        mail=findViewById(R.id.registrationEmail2);
        pwd=findViewById(R.id.registrationPassword2);
        reg=findViewById(R.id.registrationButton2);
        fbAuth= FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mail.getText().toString();
                String password = pwd.getText().toString();
                 Phonenumber = et1.getText().toString().trim();
                if (Phonenumber.isEmpty()||Phonenumber.length()<10){
                    et1.setError("Enter valid number");
                    et1.requestFocus();
                    return;
                }
                fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(userregistration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser user = fbAuth.getCurrentUser();
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "Account created ,please check your Email for verification ", Toast.LENGTH_SHORT).show();
                                                        sendData(fbAuth.getUid());
                                                        Intent intent = new Intent(userregistration.this, VerifyPhoneActivity.class);
                                                        intent.putExtra("mobile", Phonenumber);
                                                        startActivity(intent);
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(userregistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(userregistration.this,userlogin.class);
                startActivity(it);
            }
        });
    }

    private void sendData(String uid ) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        String name = et.getText().toString();
        String age = et2.getText().toString();
        String email = mail.getText().toString();
        String mobile_no = et1.getText().toString();
        addToShared(name);
        Userinfo urs = new Userinfo();
        urs.setUserName(name);
        urs.setUserEmail(email);
        urs.setUserAge(age);
        urs.setUserPhonenumber(mobile_no);
        urs.setBloodgp("");
        urs.setGender("");
        urs.setBodysize("");
        urs.setPrevdes("");
        urs.setCode("user");
        myRef.child(uid).setValue(urs);
        Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();
    }

    private void addToShared(String name) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("name",name);
        ed.apply();
    }
}
