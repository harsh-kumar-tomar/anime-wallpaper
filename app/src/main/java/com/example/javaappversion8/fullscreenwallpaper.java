package com.example.javaappversion8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class fullscreenwallpaper extends AppCompatActivity {

    ImageView imageView;
    Button setwallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreenwallpaper);
//        initialize();

        imageView = findViewById(R.id.fullscreenwallpaper);
        setwallpaper = findViewById(R.id.setwallpaper);

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("imageurl"))
                .into(imageView);

        setwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground();
            }
        });




    }

    private void initialize() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(android.R.color.transparent)); // Set to transparent
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void setBackground() {

        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {

            Toast.makeText(getApplicationContext() , "error" , Toast.LENGTH_SHORT);
            throw new RuntimeException(e);
        }
    }
}