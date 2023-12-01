package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Organneedinfo extends AppCompatActivity {
    String product3;
    TextView a, b, c, d,e,f;
    ImageView call;
    ArrayList<String> al;
    ListView lv;
    ArrayAdapter<String> ad;

    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organneedinfo);
        a = findViewById(R.id.textViewaa);
        b = findViewById(R.id.textViewbb);
        c = findViewById(R.id.textViewcc);
        d = findViewById(R.id.textViewdd);
        e = findViewById(R.id.textViewee);
        f = findViewById(R.id.textViewff);
        call = findViewById(R.id.callbg2);
        lv = findViewById(R.id.olist);
        al =new ArrayList<String>();
       // al=null;
        Intent i = getIntent();
        // getting attached intent data
        String product1 = i.getStringExtra("name");
        String product2 = i.getStringExtra("age");
        product3 = i.getStringExtra("phno");
        String product4 = i.getStringExtra("bg");
        String organs = i.getStringExtra("br");
        String product5 = i.getStringExtra("size");
        String product6 = i.getStringExtra("type");
        String product7 = i.getStringExtra("prev");
        String organs1 = i.getStringExtra("eye");
        String organs2 = i.getStringExtra("heart");
        String organs3 = i.getStringExtra("kid");
        String organs4 = i.getStringExtra("lun");
        String organs5 = i.getStringExtra("liv");
        String organs6 = i.getStringExtra("small");
        String organs7 = i.getStringExtra("pan");
        String organs8 = i.getStringExtra("lag");
        a.setText(product1);
        b.setText(product2);
        c.setText(product4);
        d.setText(product5);
        e.setText(product6);
        f.setText(product7);
        String[] organslist={organs,organs1,organs2,organs3,organs4,organs5,organs6,organs7,organs8};

        try {
            for(String organ:organslist){
                if(organ!=null){
                    al.add(organ);
                }
            }
                    Toast.makeText(getApplicationContext(), "" + al, Toast.LENGTH_SHORT).show();

                ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
                lv.setAdapter(ad);

                        //ad.notifyDataSetChanged();

        }
        catch (NullPointerException ignored){

        }


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

    }
    private void makePhoneCall() {
        String number = product3;
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Organneedinfo.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Organneedinfo.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Organneedinfo.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
