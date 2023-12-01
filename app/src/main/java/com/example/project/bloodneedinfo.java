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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class bloodneedinfo extends AppCompatActivity {
    String product3;
    TextView a, b, c, d;
    ImageView call;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodneedinfo);
        a = findViewById(R.id.textViewu1);
        b = findViewById(R.id.textViewu2);
        c = findViewById(R.id.textViewN3);
        d = findViewById(R.id.textViewN4);

        call = findViewById(R.id.callbg2);
        Intent i = getIntent();
        // getting attached intent data
        String product1 = i.getStringExtra("name");
        String product2 = i.getStringExtra("age");
         product3 = i.getStringExtra("phno");
        String product4 = i.getStringExtra("bg");
        String product5 = i.getStringExtra("gender");

        a.setText(product1);
        b.setText(product2);
        // ph.setText(product3);
        c.setText(product4);
        d.setText(product5);
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
                if (ContextCompat.checkSelfPermission(bloodneedinfo.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(bloodneedinfo.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                } else {
                    String dial = "tel:" + number;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            } else {
                Toast.makeText(bloodneedinfo.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
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

