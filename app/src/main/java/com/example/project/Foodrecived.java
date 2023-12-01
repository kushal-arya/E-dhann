package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Foodrecived extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1;
    ArrayList<Food> peopleList;
    ListView mListView;
    String uid,uname,Nname;
    FD adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodrecived);
        mListView = findViewById(R.id.fldonate);
        peopleList = new ArrayList<Food>();
        database = FirebaseDatabase.getInstance();
        Ngoname();
    }
    public void Ngoname(){
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
        myRef1 = database.getReference("Ngo").child(uid);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final Ngoinfo urs = snapshot.getValue(Ngoinfo.class);
                Nname = urs.getUserName();
                CheckandAdd();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void CheckandAdd() {
        myRef = database.getReference("Food");
        // Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                peopleList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Food f = data.getValue(Food.class);
                    String n = f.getNname();
                    if (n.equals(Nname)) {
                        uname = f.getUname();
                        peopleList.add(f);
                    }
                }
                adapter = new FD(Foodrecived.this,R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public class  FD extends ArrayAdapter<Food> {
        // private static final String TAG = "PersonListAdapter";

        private final Activity context;
        private int mResource;
//    private int lastPosition = -1;


        public  FD(Activity context, int resource, ArrayList<Food> objects) {
            super(context, resource, objects);
            this.context = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //get the persons information
           /* myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final Userinfo urs = snapshot.getValue(Userinfo.class);
                    name = urs.getUserName();
                    Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            String name = getItem(position).getUname();
            String Donate = "Donated :";
            String meal = "food";

            //Create the person object with the information


            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(mResource, parent, false);

            TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
            TextView tvmon = (TextView) convertView.findViewById(R.id.textView2);
            TextView tvbg = (TextView) convertView.findViewById(R.id.textView3);


            tvname.setText(name);
            tvmon.setText(Donate);
            tvbg.setText(meal);


            return convertView;

        }
    }
}