package com.example.javaappversion8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

//to show all wallpapers when cardview is clicked
public class inside_single_view_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    wallpaperAdapter adapter;
    ArrayList<String> dataList ;    //all images url
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_single_view);
        intialize();

        dataList = new ArrayList<>();
        dataList = getIntent().getStringArrayListExtra("dataList");


        adapter = new wallpaperAdapter(inside_single_view_activity.this , dataList); //adapter is intialized
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() ,2));
        recyclerView.setAdapter(adapter);




    }

    private void intialize() {

        recyclerView = findViewById(R.id.recycleView);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent)); // Set to transparent
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


    }
}