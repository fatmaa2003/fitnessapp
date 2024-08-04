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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Back extends AppCompatActivity {
    private static final String API_KEY = "UEUOPQUY+s/Fq82davzKJQ==Aj0alGwQMWKQD2KB";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ProgressBar progressbar=findViewById(R.id.idPBLoading);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getInstance().create(ApiService.class);

        List<Exercise> combinedExercises = new ArrayList<>();
        ExerciseAdapter adapter = new ExerciseAdapter(combinedExercises);
        recyclerView.setAdapter(adapter);

        Call<List<Exercise>> lowerBackCall = apiService.getExercises(API_KEY, "lower_back");
        Call<List<Exercise>> middleBackCall = apiService.getExercises(API_KEY, "middle_back");

        lowerBackCall.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {

                progressbar.setVisibility(View.VISIBLE);

                if (response.isSuccessful() && response.body() != null) {
                    combinedExercises.addAll(response.body());
                    adapter.notifyDataSetChanged(); // Notify the adapter to update the UI

                    progressbar.setVisibility(View.GONE);
                } else {
                    Log.e("BackActivity", "Request not successful for lower_back");
                    progressbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.e("BackActivity", "Request failed for lower_back", t);
            }
        });

        middleBackCall.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    combinedExercises.addAll(response.body());
                    adapter.notifyDataSetChanged(); // Notify the adapter to update the UI
                } else {
                    Log.e("BackActivity", "Request not successful for middle_back");
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.e("BackActivity", "Request failed for middle_back", t);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.navigationhome) {
                    startActivity(new Intent(Back.this, MainActivity.class));
                    return true;
                } else if (itemId == R.id.navigationback) {
                    Toast.makeText(Back.this, "Back Selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.navigationchest) {
                    startActivity(new Intent(Back.this, Chest.class));
                    return true;
                } else if (itemId == R.id.navigationbiceps) {
                    startActivity(new Intent(Back.this, Biceps.class));
                    return true;
                }

                return false;
            }
        });
    }
}
