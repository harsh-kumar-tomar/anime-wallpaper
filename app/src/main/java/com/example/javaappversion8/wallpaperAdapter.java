package com.example.javaappversion8;


import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
//Adapter to show all the wallpapers when a cardview is clicked
public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.viewHolder> {

    Context context;
    ArrayList<String> dataList = new ArrayList<>();

    public wallpaperAdapter(Context context, ArrayList<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public wallpaperAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_wallpaper, parent , false);
        viewHolder viewHolder = new viewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull wallpaperAdapter.viewHolder holder, int position) {

        Glide.with(context)
                .load(dataList.get(holder.getAdapterPosition()))
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() { //to show full screen wallpaper
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , fullscreenwallpaper.class);
                intent.putExtra("imageurl" , dataList.get(holder.getAdapterPosition()));
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


}
