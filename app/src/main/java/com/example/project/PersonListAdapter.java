package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Dictionary;

public class PersonListAdapter extends ArrayAdapter<Userinfo> {

    private static final String TAG = "PersonListAdapter";

    private final Activity context;
    private int mResource;
    private int lastPosition = -1;


    public PersonListAdapter(Activity context, int resource, ArrayList<Userinfo> objects) {
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