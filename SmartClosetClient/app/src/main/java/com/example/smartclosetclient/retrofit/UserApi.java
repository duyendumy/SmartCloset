package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.UserDto;
import com.example.smartclosetclient.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("/user/get-all")
    Call<List<User>> getAllUsers();

    @POST("/user/save")
    Call<User> save(@Body UserDto user);

    @POST("/user/get-user")
    Call<User> getUser(@Body UserDto user);
}
