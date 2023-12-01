package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DonateNavigate extends AppCompatActivity {
    ImageView food,shirt,coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_navigate);
        food = findViewById(R.id.food);
        shirt = findViewById(R.id.cloth);
        coin = findViewById(R.id.money);
        coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DonateNavigate.this, oldreceived.class);
                startActivity(it);

            }
        });
        shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DonateNavigate.this, clothRecived.class);
                startActivity(it);

            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DonateNavigate.this, Foodrecived.class);
                startActivity(it);

            }
        });

    }
}