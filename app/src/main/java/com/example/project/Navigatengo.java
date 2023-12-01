package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Navigatengo extends AppCompatActivity {
ImageView reg,ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigatengo);
         ver = findViewById(R.id.verify);
         reg = findViewById(R.id.regngo);
    ver.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(Navigatengo.this,adminverify.class);
            startActivity(it);
        }
    });
    reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(Navigatengo.this,Admin.class);
            startActivity(it);

        }
    });
    }
}