package com.example.javaappversion8;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;

//this Adapter is used to show all the wallpapers related to that title

public class wallpaperAdapter extends RecyclerView.Adapter<wallpaperAdapter.viewHolder> {

    Context context;
    ArrayList<String> dataList = new ArrayList<>();         //this is where all the wallpapers url will be stored

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
                .placeholder(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache on both disk and memory
                .into(holder.imageView);

//        if (isImageDownloaded(context, getFileNameFromUrl(dataList.get(holder.getAdapterPosition())))) {
//            // If downloaded, load it from internal storage
//            loadImageFromStorage(context, holder.imageView, getFileNameFromUrl(dataList.get(holder.getAdapterPosition())));
//        } else {
//            // If not downloaded, download it and save it to internal storage
//            downloadAndSaveImage(context, dataList.get(holder.getAdapterPosition()), getFileNameFromUrl(dataList.get(holder.getAdapterPosition())), holder.imageView);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() { //to show full screen wallpaper
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , fullscreenwallpaper.class);
                intent.putExtra("imageurl" , dataList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
//                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left , android.R.anim.slide_out_right);



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

    // Helper method to get the file name from a URL
    private String getFileNameFromUrl(String url) {
        // Extract the file name from the URL (e.g., "image.jpg")
        return url.substring(url.lastIndexOf('/') + 1);
    }

    // Helper method to check if an image is already downloaded
    private boolean isImageDownloaded(Context context, String fileName) {
        File file = new File(context.getFilesDir() + "/YourAnimeWallpaper", fileName);
        return file.exists();
    }

    // Helper method to load an image from internal storage
    private void loadImageFromStorage(Context context, ImageView imageView, String fileName) {
        File file = new File(context.getFilesDir() + "/YourAnimeWallpaper", fileName);
        Glide.with(context)
                .load(Uri.fromFile(file))
                .placeholder(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE) // Don't cache this as it's already in internal storage
                .into(imageView);
    }

    // Helper method to download and save an image
    private void downloadAndSaveImage(Context context, String imageUrl, String fileName, ImageView imageView) {
        // Download the image from imageUrl and save it to internal storage
        // You can use HttpURLConnection or any other library for downloading
        // After downloading, save the image to the "/YourAnimeWallpaper" directory

        // Update imageView with the downloaded image
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


}
