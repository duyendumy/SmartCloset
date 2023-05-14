package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.dto.OutfitDto;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OutfitApi {



    @POST("/outfit/save")
    Call<Outfit> save(@Body OutfitDto OutfitDto);

    @GET("/outfit/get-all/{idCloset}")
    Call<List<Outfit>> getAllOutfits(@Path("idCloset") Long idCloset);

    @GET("/outfit/get-count/{idCloset}")
    Call<Long> getCountOutfit(@Path("idCloset") Long idCloset);


}
