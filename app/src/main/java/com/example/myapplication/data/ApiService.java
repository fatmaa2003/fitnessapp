package com.example.myapplication.data;

import com.example.myapplication.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("exercises")
    Call<List<Exercise>> getExercises(
            @Header("X-Api-Key") String apiKey,
            @Query("muscle") String muscle
    );
}

