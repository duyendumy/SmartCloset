package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.CategoryDto;
import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryApi {
    @GET("/category/get-all")
    Call<List<Category>> getAllCategories();

    @POST("/category/save")
    Call<Category> save(@Body CategoryDto category);

    @GET("/category/delete/{id}")
    Call<Void> deleteCategory(@Path("id") Long id);

    @PUT("/category/update/{id}")
    Call<CategoryDto> updateCategory(@Path("id") Long id, @Body CategoryDto updatedCategory);

}
