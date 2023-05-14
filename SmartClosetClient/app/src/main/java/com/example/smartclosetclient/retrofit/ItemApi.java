package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.SubCategory;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ItemApi {

    @GET("/item/get-all/{idCloset}")
    Call<List<Item>> getAllItems(@Path("idCloset") Long idCloset);

    @POST("/item/save")
    Call<Item> save(@Body ItemDto item);

    @GET("/item/delete/{id}")
    Call<Void> deleteItemById(@Path("id") Long id);

    @PUT("/item/update/{id}")
    Call<ItemDto> updateItem(@Path("id") Long id, @Body ItemDto updatedItem);

    @GET("/get-item-by-category/{idCloset}/{idCategory}")
    Call<List<Item>> getItemsOfCategory(@Path("idCloset") Long idCloset, @Path("idCategory") Long idCategory);

    @GET("/item/get-count/{idCloset}")
    Call<Long> getCountItem(@Path("idCloset") Long idCloset);

}
