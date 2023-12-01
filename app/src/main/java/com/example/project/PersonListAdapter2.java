package com.example.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

class PersonListAdapter2 extends ArrayAdapter<Userinfo> {

        private static final String TAG = "PersonListAdapter";

        private final Activity context;
        private int mResource;
        private int lastPosition = -1;


    public PersonListAdapter2(Activity context, int resource, ArrayList<Userinfo> objects) {
        super(context, resource, objects);
        this.context =  context;
        mResource = resource;
    }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getUserName();
        String age = getItem(position).getUserAge();
        String bloodgp = getItem(position).getBloodgp();

        //Create the person object with the information
        Userinfo urs = new Userinfo();


        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvage = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvbg = (TextView) convertView.findViewById(R.id.textView2);


        tvname.setText(name);
        tvage.setText(age);
        tvbg.setText(bloodgp);


        return convertView;
    }


    }
