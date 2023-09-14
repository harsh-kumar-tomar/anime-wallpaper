package com.example.javaappversion8;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class animeAdapter extends RecyclerView.Adapter<animeAdapter.ViewHolder> {
    Context context ;
    ArrayList<structAnime> animeArrayList ; //all different anime cardview details  name , drawable , code
    DatabaseReference databaseReference ;
    ArrayList<String> insideImagesList ;

    String Code;


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



        runAnimation(holder.itemView , position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //selecting one card view

                Code = animeArrayList.get(holder.getAdapterPosition()).Code;
                databaseReference = FirebaseDatabase.getInstance().getReference().child(Code);
                insideImagesList = new ArrayList<>();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {  //fetching the url from internet

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String data = dataSnapshot.getValue().toString();
                            insideImagesList.add(data);         //putting all url of the wallpaper related to that anime

                        }                           //all image information are inside insideImageslist

                        Intent intent = new Intent(context , inside_single_view_activity.class);    //open images List
                        intent.putExtra("dataList",insideImagesList);
                        context.startActivity(intent);

                    }
                    public void onCancelled( DatabaseError error) {

                        Toast.makeText(context , "error"+error.getMessage() , Toast.LENGTH_SHORT);


                    }
                });

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
}
