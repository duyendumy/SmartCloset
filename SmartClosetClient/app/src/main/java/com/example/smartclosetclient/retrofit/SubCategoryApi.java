package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.CategoryDto;
import com.example.smartclosetclient.dto.SubCategoryDto;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.SubCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubCategoryApi {
    @GET("/subcategory/get-all")
    Call<List<SubCategory>> getAllSubCategories();

    @GET("/subcategory/{idCategory}")
    Call<List<SubCategory>> getSubCategoriesOfCategory(@Path("idCategory") Long idCategory);


    @POST("/subcategory/save")
    Call<SubCategory> save(@Body SubCategoryDto subCategory);
}
