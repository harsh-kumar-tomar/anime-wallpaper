package com.example.javaappversion8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<structAnime> arrAnime = new ArrayList<>();
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       intialize();

        arrAnime.add(new structAnime(R.drawable.anime2 , "One punch man" , "onepunchman"));
        arrAnime.add(new structAnime(R.drawable.anime3 , "My teeen romantic comedy" , "teenromanticcomedy"));
        arrAnime.add(new structAnime(R.drawable.anime2 , "Rent a girlfriend" , "rentagirlfriend"));
        arrAnime.add(new structAnime(R.drawable.anime3 , "Spy family" , "spyfamily"));


        //recyler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        animeAdapter adapter = new animeAdapter(this , arrAnime );       //adapter

        recyclerView.setAdapter(adapter);



    }

    private void intialize() {
        Window window = getWindow();
        int statusBarColor = android.graphics.Color.parseColor("#242C5A");
        window.setStatusBarColor(statusBarColor);



        recyclerView = findViewById(R.id.recycleView);
    }





}