package com.example.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MoneyAdapter extends ArrayAdapter<Ngoinfo> {
    // private static final String TAG = "PersonListAdapter";

    private final Activity context;
    private int mResource;
//    private int lastPosition = -1;


    public MoneyAdapter(Activity context, int resource, ArrayList<Ngoinfo> objects) {
        super(context, resource, objects);
        this.context = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getUserName();
        String money = getItem(position).getMoney();
        String phonenumber = getItem(position).getUserPhonenumber();

        //Create the person object with the information


        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvmon = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvbg = (TextView) convertView.findViewById(R.id.textView3);


        tvname.setText(name);
        tvmon.setText(money);
        tvbg.setText(phonenumber);


        return convertView;

    }
}
