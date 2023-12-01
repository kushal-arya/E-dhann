package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class Oldageadapter  extends ArrayAdapter<oldUser> {

private static final String TAG = "PersonListAdapter";

private final Activity context;
private int mResource;
private int lastPosition = -1;


public Oldageadapter(Activity context, int resource, ArrayList<oldUser> objects) {
        super(context, resource, objects);
        this.context =  context;
        mResource = resource;
        }

@NonNull
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        String age = getItem(position).getAge();
        String gender = getItem(position).getGen();
        String med = getItem(position).getMedical();
        String address = getItem(position).getAddress();
        String loc = getItem(position).getLocation();

    //Create the person object with the information


        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.oname);
        TextView tvage = (TextView) convertView.findViewById(R.id.oage);
        TextView tvgen = (TextView) convertView.findViewById(R.id.ogen);
        TextView tvmed = (TextView) convertView.findViewById(R.id.omed);
        TextView tvadd = (TextView) convertView.findViewById(R.id.oadd);
        TextView tvloc = (TextView) convertView.findViewById(R.id.oloc);


        tvname.setText(name);
        tvage.setText(age);
        tvgen.setText(gender);
        tvmed.setText(med);
        tvadd.setText(address);
        tvloc.setText(loc);


        return convertView;
        }


        }