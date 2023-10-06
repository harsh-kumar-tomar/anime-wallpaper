package com.example.javaappversion8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DataLoadedCallback {
    private ArrayList<structAnime> arrAnime = new ArrayList<>();
    private HashMap<String, ArrayList<String>> mapOfLists = new HashMap<>();

    private RecyclerView recyclerView ;
    private ProgressBar progressBar;
    private animeAdapter adapter;
    private SearchView searchView ;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);


        intialize();

        progressBar.setVisibility(View.VISIBLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);






        dataSet();

        if (isNetworkConnected(getApplicationContext()))
        {
            Log.e("tag", "inside  internet");

            A b = new A(this); // Pass the callback
            Thread t = new Thread(b);
            t.start();
        }else
        {

            try {
                Log.e("tag", "inside no internet");
                mapOfLists = loadHashMap(getApplicationContext() , "filename");
                progressBar.setVisibility(View.INVISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            } catch (Exception e)
            {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }





            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));  //setting layout
             adapter = new animeAdapter(MainActivity.this, arrAnime, mapOfLists);       //setting up recycler view
            recyclerView.setAdapter(adapter);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter(newText);
                    return true;
                }
            });





    }

    public static void saveHashMap(Context context, HashMap<String, ArrayList<String>> hashMap, String filename) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load a HashMap from internal storage
    public static HashMap<String, ArrayList<String>> loadHashMap(Context context, String filename) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            hashMap = (HashMap<String, ArrayList<String>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hashMap;
    }


    public void onDataLoaded() {
        // Data is loaded, update the adapter or perform any actions here


//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
                adapter.notifyDataSetChanged();
                if (count == arrAnime.size())
                {
                    progressBar.setVisibility(View.INVISIBLE);

                }

//            }
//        });


    }
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
            }
        }
        return false;
    }



     private void dataSet() {

        arrAnime.add(new structAnime(R.drawable.onepunchman , "One punch man" , "onepunchman"));
        arrAnime.add(new structAnime(R.drawable.teenromanticcomedy , "My teeen romantic comedy" , "teenromanticcomedy"));
        arrAnime.add(new structAnime(R.drawable.rentagirlfriend , "Rent a girlfriend" , "rentagirlfriend"));
        arrAnime.add(new structAnime(R.drawable.spyxfamily , "Spy family" , "spyfamily"));
        arrAnime.add(new structAnime(R.drawable.demonslayer , "Demon slayer" , "demonslayer"));
        arrAnime.add(new structAnime(R.drawable.konosuba , "Konosuba" , "konosuba"));
        arrAnime.add(new structAnime(R.drawable.classroomofelite1 , "Classroom of elite" , "classroomofelite"));
        arrAnime.add(new structAnime(R.drawable.darlinginthefranxx , "Darling in the Franxx" , "darlinginthefranxx"));
        arrAnime.add(new structAnime(R.drawable.journeyofelaina , "Journey of elaina" , "journeyofelaina"));
        arrAnime.add(new structAnime(R.drawable.deathnote , "Death Note" , "deathnote"));
        arrAnime.add(new structAnime(R.drawable.yourname , "Your name" , "yourname"));
        arrAnime.add(new structAnime(R.drawable.onepiece , "One piece" , "onepiece"));

    }


    private void intialize() {
        Window window = getWindow();
        int statusBarColor = android.graphics.Color.parseColor("#242C5A");
        window.setStatusBarColor(statusBarColor);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recycleView);
    }

    public class A implements Runnable {

        private DataLoadedCallback callback;

        public A(DataLoadedCallback callback) {
            this.callback = callback;
        }

        public void run() {


            String Code;
            DatabaseReference databaseReference;
            Log.d("inside run", "start of for function ");


            for (int i = 0; i < arrAnime.size(); i++) {

                Log.d("inside run", "getting code in for function ");

                Code = arrAnime.get(i).Code;
                databaseReference = FirebaseDatabase.getInstance().getReference().child(Code);
                ArrayList<String> insideImagesList;
                insideImagesList = new ArrayList<>();



                String anothercode = Code;
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {  //fetching the url from internet

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String data = dataSnapshot.getValue().toString();
                            insideImagesList.add(data);         //putting all url of the wallpaper related to that anime
                            Log.d("inside run", "putting data into snapshot function ");


                        }                           //all image information are inside insideImageslist

                        mapOfLists.put(anothercode, insideImagesList);
                        adapter.notifyDataSetChanged();


//                        callback.onDataLoaded();
                        count++;

                        if (count == arrAnime.size())
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            saveHashMap(getApplicationContext() , mapOfLists , "filename");


                        }



                    }

                    public void onCancelled(DatabaseError error) {

                        Toast.makeText(getApplicationContext(), "error" + error.getMessage(), Toast.LENGTH_SHORT);


                    }
                });


            }

            Log.d("inside run", "end of for function ");
//            Toast.makeText(MainActivity.this, "Data fetched Successfully", Toast.LENGTH_SHORT).show();


        }

    }
}

