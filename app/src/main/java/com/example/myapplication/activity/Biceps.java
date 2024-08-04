package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Exercise;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ExerciseAdapter;
import com.example.myapplication.data.ApiClient;
import com.example.myapplication.data.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Biceps extends AppCompatActivity {
    private static final String API_KEY = "UEUOPQUY+s/Fq82davzKJQ==Aj0alGwQMWKQD2KB";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biceps);
        ProgressBar progressbar=findViewById(R.id.idPBLoading);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getInstance().create(ApiService.class);
        apiService.getExercises(API_KEY, "biceps").enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {

                progressbar.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Exercise> exercises = response.body();
                    ExerciseAdapter adapter = new ExerciseAdapter(exercises);
                    recyclerView.setAdapter(adapter);
                    progressbar.setVisibility(View.GONE);
                } else {
                    Log.e("BicepsActivity", "Request not successful");
                    progressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.e("BicepsActivity", "Request failed", t);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.navigationhome) {
                    startActivity(new Intent(Biceps.this, MainActivity.class));
                    return true;
                } else if (itemId == R.id.navigationbiceps) {
                    Toast.makeText(Biceps.this, "Biceps Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.navigationback) {
                    startActivity(new Intent(Biceps.this, Back.class));
                    return true;
                } else if (itemId == R.id.navigationchest) {
                    startActivity(new Intent(Biceps.this, Chest.class));
                    return true;
                }

                return false;
            }
        });

    }
}
