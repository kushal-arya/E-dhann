package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class blooddonate extends AppCompatActivity implements OnMapReadyCallback {
    Spinner sp;
    ArrayAdapter<String> ar;
    ArrayList<String> al;
    Button submit;
    String val, text;
    RadioGroup rg;
    RadioButton m, f, o;
    private MapView mapView;
    private GoogleMap gmap;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blooddonate);
        sp = findViewById(R.id.spinner);
        submit = findViewById(R.id.button4);
        rg = findViewById(R.id.Radiogroup);
        m = findViewById(R.id.radioButton);
        f = findViewById(R.id.radioButton2);
        o = findViewById(R.id.radioButton3);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);


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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val =
                        ((RadioButton) findViewById(rg.getCheckedRadioButtonId()))
                                .getText().toString();

                text = sp.getSelectedItem().toString();


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Userinfo urs = data.getValue(Userinfo.class);
                            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            if ((urs.getUserEmail()).equalsIgnoreCase(email)) {
                                urs.setBloodgp(text);
                                urs.setGender(val);
                                myRef.child(data.getKey()).setValue(urs);
                                break;

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(), "Thank you for your valuable donation", Toast.LENGTH_SHORT).show();
            }


        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(17.310655, 78.524595);
        googleMap.addMarker(new MarkerOptions().position(ny).title("marker"));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

}


