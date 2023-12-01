package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userlogin extends AppCompatActivity {
    Button login;
    EditText mail, pwd;
    TextView tv, tv1,tv2;
    private FirebaseAuth fbAuth;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String code,uid;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        login = findViewById(R.id.loginButtonngo);
        mail = findViewById(R.id.edittexteno);
        pwd = findViewById(R.id.edittextnpw);
        tv = findViewById(R.id.textView1);
        tv1 = findViewById(R.id.signupngo);
        tv2=findViewById(R.id.forgotPasswordngo);
        String a="Users";
        fbAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();

           login.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   final String email = mail.getText().toString();
                   final String password = pwd.getText().toString();
                   if ((email.equals("admin@admin.com")) && (password.equals("admin123"))) {
                       Intent it1 = new Intent(userlogin.this, Navigatengo.class);
                       startActivity(it1);
                   } else
                   {
                       fbAuth.signInWithEmailAndPassword(email, password)
                               .addOnCompleteListener(userlogin.this, new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {


                                       if (task.isSuccessful()) {
                                           if (fbAuth.getCurrentUser().isEmailVerified()) {
                                               uid = fbAuth.getCurrentUser().getUid();

                                               myRef = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                                               myRef.addValueEventListener(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                       final Userinfo urs = snapshot.getValue(Userinfo.class);
                                                       try {
                                                           code = urs.getCode();
                                                       } catch (NullPointerException e) {

                                                       }


                                                       if (code == null) {
                                                           Toast.makeText(userlogin.this, "Try again", Toast.LENGTH_SHORT).show();
                                                           return;
                                                       }
                                                       if (code.equals("user")) {
                                                           Intent it = new Intent(userlogin.this, Userpage.class);
                                                           it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                           startActivity(it);
                                                       }


                                                   }

                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError error) {

                                                   }

                                               });


                                               //  else{
                                               // Intent it = new Intent(userlogin.this, Userpage.class);
                                               // startActivity(it);

                                               // Toast.makeText(userlogin.this, "login failed", Toast.LENGTH_SHORT).show();
                                               //}


                                           } else {
                                               Toast.makeText(getApplicationContext(), "please verify your email", Toast.LENGTH_SHORT).show();

                                           }

                                       }
                                       else {
                                           Toast.makeText(userlogin.this, task.getException().getMessage()+"", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });

                   }
               }
           });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(userlogin.this, userregistration.class);
                startActivity(it);
            }
        });

    }
    public void gotouserpageActivity(){
    Intent it1 = new Intent(userlogin.this,Userpage.class);
    startActivity(it1);
    }
}