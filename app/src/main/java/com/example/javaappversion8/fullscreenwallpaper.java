package com.example.javaappversion8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;

import java.io.IOException;

public class fullscreenwallpaper extends AppCompatActivity {

    ImageView imageView;
    ImageView backButton ;
    Button setwallpaper;
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreenwallpaper);
        initialize();

        imageView = findViewById(R.id.fullscreenwallpaper);
        setwallpaper = findViewById(R.id.setwallpaper);

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("imageurl"))
                .into(imageView);

        setwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fullscreenwallpaper.this, "Success", Toast.LENGTH_SHORT).show();
                setBackground();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                finish();
            }
        });

//        toolbar = findViewById(R.id.toolbar5);




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