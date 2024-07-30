package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Exercise;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ExerciseAdapter;
import com.example.myapplication.data.ApiClient;
import com.example.myapplication.data.ApiService;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getInstance().create(ApiService.class);
        apiService.getExercises(API_KEY, "back").enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Exercise> exercises = response.body();
                    ExerciseAdapter adapter = new ExerciseAdapter(exercises);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("BackActivity", "Request not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.e("BackActivity", "Request failed", t);
            }
        });
    }
}
