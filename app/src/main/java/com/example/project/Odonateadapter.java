package com.example.project;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Odonateadapter extends ArrayAdapter<Userinfo> {

private static final String TAG = "PersonListAdapter";

private final Activity context;
private int mResource;
private int lastPosition = -1;

//SharedPreferences  sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);



    public Odonateadapter(Activity context, int resource, ArrayList<Userinfo> objects) {
        super(context, resource, objects);
        this.context =  context;
        mResource = resource;
        }

@NonNull
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
    String name = getItem(position).getUserName();
        String age = "Donated 500";
        String bloodgp = getItem(position).getBloodgp();

        //Create the person object with the information



        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvage = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvbg = (TextView) convertView.findViewById(R.id.textView3);


        tvname.setText(name);
        tvage.setText(age);
        tvbg.setText(bloodgp);


        return convertView;
        }


        }
