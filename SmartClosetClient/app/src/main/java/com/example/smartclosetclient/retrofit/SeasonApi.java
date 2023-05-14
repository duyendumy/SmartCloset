package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Season;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeasonApi {
    @GET("/season/get-all")
    Call<List<Season>> getAllSeasons();
}
