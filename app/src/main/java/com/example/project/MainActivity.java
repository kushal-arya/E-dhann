package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.auth.User;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9003;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    ImageView iv1, iv2;
    TextView tv;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DatabaseReference myRef;
    String uid,role;
    private FirebaseFirestore mDb;
    private boolean mLocationPermissionGranted = false;
     private FusedLocationProviderClient mFusedLocationClient;

     private  UserLocation mUserLocation;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);


         tv = findViewById(R.id.textView);
         iv1 = findViewById(R.id.imageView);
         iv2 = findViewById(R.id.imageView2);
         mDb = FirebaseFirestore.getInstance();

         mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
         firebaseAuth = FirebaseAuth.getInstance();

         progressDialog = new ProgressDialog(this);

      first();
      //   progressDialog.show();
     }
    public void first() {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user =firebaseAuth.getCurrentUser();

                if(user != null)
                {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                        progressDialog.setTitle("Logging");
                        progressDialog.show();
                        //  progressDialog.show();
                        startActivity(new Intent(MainActivity.this, Userpage.class));
                        finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Verify your email", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                    }
                }
                else
                {
                Intent it = new Intent(MainActivity.this, userlogin.class);
                startActivity(it);
            }
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user1 =firebaseAuth.getCurrentUser();

                if(user1 != null)
                {
                    uid = firebaseAuth.getCurrentUser().getUid();
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
                                Toast.makeText(MainActivity.this, "logout from User", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (role.equals("Child Orphanage")) {
                                Intent it = new Intent(MainActivity.this, norphan.class);
                                startActivity(it);
                            } else if (role.equals("Ngo")) {
                                Intent it = new Intent(MainActivity.this, ngo.class);
                                startActivity(it);
                            } else if (role.equals("Old age home")) {
                                Intent it = new Intent(MainActivity.this, noldage.class);
                                startActivity(it);
                            } else if (role.equals("Blood Bank")) {
                                Intent it = new Intent(MainActivity.this, bloodneed.class);
                                startActivity(it);
                            } else if (role.equals("Organ donate organisation")) {
                                Intent it = new Intent(MainActivity.this, Organneed.class);
                                startActivity(it);
                            } else {
                                Toast.makeText(getApplicationContext(), "Choose login type", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                else
                {

                Intent it = new Intent(MainActivity.this,ngologin.class);
                startActivity(it);
            }
            }
        });

    }

}


