package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adminverify extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private List<uploadinfo1> mUploads;

    Adminadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminverify);
        mRecyclerView = findViewById(R.id.recycle3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    uploadinfo1 urs = data.getValue(uploadinfo1.class);

                    mUploads.add(urs);


                }
                 adapter = new Adminadapter(adminverify.this, mUploads);
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public static class Adminadapter extends RecyclerView.Adapter<Adminadapter.ImageViewHolder> {
        private Context mContext;
        private List<uploadinfo1> mUploads;


        public Adminadapter(Context context, List<uploadinfo1> uploads) {
            mContext = context;
            mUploads = uploads;
        }
        @Override
        public Adminadapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.custom_orphan_details, parent, false);
            return new Adminadapter.ImageViewHolder(v);
        }


        @Override
        public void onBindViewHolder(Adminadapter.ImageViewHolder holder, int position) {
            uploadinfo1 uploadCurrent = mUploads.get(position);
            holder.textViewName.setText(uploadCurrent.getImageName());
            holder.textViewloc.setText("");
            Picasso.with(mContext)
                    .load( uploadCurrent.getImageURL()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);
            // Picasso.with(mContext).load(mUploads.get(position).getImageURL()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mUploads.size();
        }

        public static class ImageViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewName;
            public TextView textViewloc;
            public ImageView imageView;
            public ImageViewHolder(View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.chname);
                textViewloc = itemView.findViewById(R.id.chloc);
                imageView = itemView.findViewById(R.id.imageViewc);
            }
        }
    }


}