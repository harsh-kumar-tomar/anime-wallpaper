package com.example.javaappversion8;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


//this adapter is used to display all the Anime titles with thier images


public class animeAdapter extends RecyclerView.Adapter<animeAdapter.ViewHolder> {
    private Context context ;
    private ArrayList<structAnime> animeArrayList ; //all different anime cardview details  name , drawable , code will be stored here
    private DatabaseReference databaseReference ;   //this database will be used to fetch all the wallpapers related to that anime title
    private ArrayList<String> insideImagesList ;    //this is where all the url of images will be stored
    private String Code;                            //this will be used to access data related to different titles
    private HashMap<String, ArrayList<String>> mapOfLists ;

    private ArrayList<structAnime> tempArrayList ;


    animeAdapter(Context context ,ArrayList<structAnime>animeArrayList , HashMap<String, ArrayList<String>> mapOfLists)
    {

            this.context = context;
            this.animeArrayList = animeArrayList;
            this.mapOfLists = mapOfLists;

            tempArrayList = new ArrayList<>();
            tempArrayList.addAll(animeArrayList);

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


        runAnimation(holder.itemView , position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //selecting one card view

                try {

                    insideImagesList = mapOfLists.get(animeArrayList.get(holder.getAdapterPosition()).Code);

                    Intent intent = new Intent(context, inside_single_view_activity.class);    //open images List
                    intent.putExtra("dataList", insideImagesList);
                    intent.putExtra("title",animeArrayList.get(holder.getAdapterPosition()).name);
                    context.startActivity(intent);
                }catch (Exception e)
                {
                    Log.e("click success", "inside click catch part"+e.getMessage());


                }


            }
        });


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

    public void runAnimation(View view , int position)
    {
        Animation animation = AnimationUtils.loadAnimation(context , android.R.anim.slide_in_left);
        view.startAnimation(animation);

    }

    public void getFilter(String s) {

        animeArrayList.clear();

        for (int i = 0; i < tempArrayList.size(); i++) {

            if (tempArrayList.get(i).name.toLowerCase().contains(s.toLowerCase())) {
                animeArrayList.add(tempArrayList.get(i)); // Add matching items to the filtered list
            }

        }

        notifyDataSetChanged();

    }

//
}
