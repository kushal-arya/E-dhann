package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Orphanadapter extends RecyclerView.Adapter<Orphanadapter.ImageViewHolder> {
    private Context mContext;
    private List<uploadinfo> mUploads;


    public Orphanadapter(Context context, List<uploadinfo> uploads) {
        mContext = context;
        mUploads = uploads;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_orphan_details, parent, false);
        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        uploadinfo uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getImageName());
        holder.textViewloc.setText(uploadCurrent.getLoc());
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


