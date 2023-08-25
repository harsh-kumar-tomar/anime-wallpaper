package com.example.javaappversion8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<structAnime> arrAnime = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView = findViewById(R.id.recycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;


        structAnime temp = new structAnime(R.drawable.anime1 , "My hero academia");
        arrAnime.add(temp);
        arrAnime.add(new structAnime(R.drawable.anime2 , "Death note"));
        arrAnime.add(new structAnime(R.drawable.anime3 , "Pokemon"));
        arrAnime.add(new structAnime(R.drawable.anime4 , "AOT"));
        arrAnime.add(new structAnime(R.drawable.anime5 , "Spy family"));
        arrAnime.add(new structAnime(R.drawable.anime6 , "Demon slayer"));
        arrAnime.add(new structAnime(R.drawable.anime7, "ASTRA"));

        animeAdapter adapter = new animeAdapter(this , arrAnime);

        recyclerView.setAdapter(adapter);

    }
}