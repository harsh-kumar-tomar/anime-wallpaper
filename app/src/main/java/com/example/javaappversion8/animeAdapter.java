package com.example.javaappversion8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class animeAdapter extends RecyclerView.Adapter<animeAdapter.ViewHolder> {
    Context context ;
    ArrayList<structAnime> animeArrayList ;

    animeAdapter(Context context , ArrayList<structAnime> animeArrayList)
    {
        this.context = context ;
        this.animeArrayList = animeArrayList ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.single_view_element , parent , false);
        ViewHolder viewHolder = new ViewHolder(v) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imgBackground.setImageResource(animeArrayList.get(position).img);
        holder.txtName.setText(animeArrayList.get(position).name);

    }

    @Override
    public int getItemCount() {
        return animeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName ;
        ImageView imgBackground ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.animeName);
            imgBackground = itemView.findViewById(R.id.animeBackground);

        }
    }
}
