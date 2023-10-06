package com.example.javaappversion8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//to show all wallpapers when cardview is clicked
public class inside_single_view_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    wallpaperAdapter adapter;
    TextView title ;
    private ImageView backButton ;
    ArrayList<String> dataList ;    //all images url
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_single_view);
        intialize();

        Log.e("click success", "inside single view activity ");


        dataList = new ArrayList<>();
        title.setText(getIntent().getStringExtra("title"));
        dataList = getIntent().getStringArrayListExtra("dataList");

        Log.e("click success", "inside single view activity wallapaper data fetch successfully "+dataList.size());

        adapter = new wallpaperAdapter(inside_single_view_activity.this , dataList); //adapter is intialized
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext() ,2));
        recyclerView.setAdapter(adapter);
        Log.e("click success", "inside single view activity after recyler view ");

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);


                finish();
            }
        });




    }

    private void intialize() {

        recyclerView = findViewById(R.id.recycleView);
        Window window = getWindow();
        int statusBarColor = android.graphics.Color.parseColor("#242C5A");
        window.setStatusBarColor(statusBarColor);

        title = findViewById(R.id.title);
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(android.R.color.transparent)); // Set to transparent
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


    }
}