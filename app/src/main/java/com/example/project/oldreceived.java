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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class oldreceived extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1;
    ArrayList<money> peopleList;
    ListView mListView;
    String uid,uname,Nname;
    MA adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldreceived);
        mListView = findViewById(R.id.olddonate);
        peopleList = new ArrayList<money>();
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
        myRef = database.getReference("Money");
       // Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                peopleList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    money urs = data.getValue(money.class);
                    String n = urs.getNname();
                    if (n.equals(Nname)) {
                      //  uname = urs.getUname();
                        peopleList.add(urs);
                    }
                }
                adapter = new MA(oldreceived.this, R.layout.adapter_view_layout, peopleList);
                mListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public class  MA extends ArrayAdapter<money> {
        // private static final String TAG = "PersonListAdapter";

        private final Activity context;
        private int mResource;
//    private int lastPosition = -1;


        public  MA(Activity context, int resource, ArrayList<money> objects) {
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
            String meal  = getItem(position).getAmmount();

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
