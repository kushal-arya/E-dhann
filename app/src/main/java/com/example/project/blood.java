package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class blood extends AppCompatActivity {
    ImageView donate,need;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);
        donate=findViewById(R.id.imageView1);
        need=findViewById(R.id.imageView3);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(blood.this,blooddonate.class);
                startActivity(it);
            }
        });
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 =new Intent(blood.this,bloodneed.class);
                startActivity(it1);
            }
        });
    }
}
