package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Userpage extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    ListView list;
    String[] web = {
            " \n"+"\t\t\t\t\t\t\t\t\t\t Child Orphanage",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Donate Money",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Donate Clothes",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Donate  Blood",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Donate  Food",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Donate Organs",
            " \n"+"\t\t\t\t\t\t\t\t\t\t Old Age Home"
    };
    Integer[] imageId = {
            R.drawable.baby,
            R.drawable.money,
            R.drawable.shirt,
            R.drawable.water,
            R.drawable.hamburger,
            R.drawable.heart,
            R.drawable.oldage

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        firebaseAuth = FirebaseAuth.getInstance();
        com.example.project.CustomList adapter = new
                com.example.project.CustomList(Userpage.this, web, imageId);
        list = (ListView) findViewById(R.id.listview1);
        list.setAdapter((ListAdapter) adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Userpage.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                if(position == 0){
                    Intent it= new Intent(Userpage.this,Orphan.class);
                    startActivity(it);

                }
                if(position == 1){
                    Intent it= new Intent(Userpage.this,Moneymain.class);
                    startActivity(it);

                }
                if(position == 2){
                    Intent it= new Intent(Userpage.this,Clothmain.class);
                    startActivity(it);

                }
                if(position == 3){
                    Intent it= new Intent(Userpage.this,blood.class);
                    startActivity(it);

                }
                if(position == 4){
                    Intent it= new Intent(Userpage.this,Foodmain.class);
                    startActivity(it);

                }
                if(position == 5){
                    Intent it= new Intent(Userpage.this,OraganMain.class);
                    startActivity(it);

                }
                if(position == 6){
                    Intent it= new Intent(Userpage.this,oldmain.class);
                    startActivity(it);

                }
            }
        });
    }
    public void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Userpage.this,MainActivity.class));
    }
    public  void  profile(){
        startActivity(new Intent(Userpage.this,Profilepage.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logoutMenu:
                Logout();
                break;
            case R.id.info:
                profile();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}