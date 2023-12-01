package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Clothmain extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Ngoinfo> peopleList;
    ListView mListView;
    clothAdapter adapter;
    FirebaseAuth firebaseAuth;
    String cl,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothmain);
        mListView = findViewById(R.id.cl1);
        peopleList = new ArrayList<Ngoinfo>();
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
         uid = firebaseAuth.getUid();

        myRef = database.getReference("Ngo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // val = dataSnapshot.getKey();
                Toast.makeText(getApplicationContext(), "ETETEEETE" , Toast.LENGTH_SHORT).show();
                peopleList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Ngoinfo urs = data.getValue(Ngoinfo.class);
                    cl = urs.getCloth();

                    Toast.makeText(Clothmain.this, ""+cl, Toast.LENGTH_SHORT).show();
                    if (!(urs.getCloth()).equals("")) {
                        peopleList.add(urs);
                    }
                }
                adapter = new clothAdapter(Clothmain.this, R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Clothmain.this, Clothdonate.class);
                Ngoinfo urs = adapter.getItem(position);
                intent.putExtra("name1", urs.getUserName());

                startActivity(intent);

            }
        });

    }
    public class  clothAdapter extends ArrayAdapter<Ngoinfo> {
        // private static final String TAG = "PersonListAdapter";

        private final Activity context;
        private int mResource;
//    private int lastPosition = -1;


        public  clothAdapter(Activity context, int resource, ArrayList<Ngoinfo> objects) {
            super(context, resource, objects);
            this.context = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //get the persons information
            String name = getItem(position).getUserName();
            String cloth = getItem(position).getCloth();
            String phonenumber = getItem(position).getUserPhonenumber();

            //Create the person object with the information


            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);

            TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
            TextView tvmon = (TextView) convertView.findViewById(R.id.textView2);
            TextView tvbg = (TextView) convertView.findViewById(R.id.textView3);


            tvname.setText(name);
            tvmon.setText(cloth);
            tvbg.setText(phonenumber);


            return convertView;

        }
    }


}