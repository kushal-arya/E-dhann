package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class OraganMain extends AppCompatActivity {
    ImageButton iv1, iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oragan_main);
        iv1 = findViewById(R.id.imageButton4);
        iv2 = findViewById(R.id.imageButton6);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(OraganMain.this, Organdonate.class);
                startActivity(it);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(OraganMain.this,Organneed.class);
                startActivity(it);
            }
       });
    }
}
