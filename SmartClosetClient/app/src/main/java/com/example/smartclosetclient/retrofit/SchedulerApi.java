package com.example.smartclosetclient.retrofit;

import com.example.smartclosetclient.dto.CategoryDto;
import com.example.smartclosetclient.dto.SchedulerDto;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Scheduler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SchedulerApi {
    @GET("/scheduler/get-all/{idCloset}")
    Call<List<Scheduler>> getAllSchedulers(@Path("idCloset") Long idCloset);

    @POST("/scheduler/save")
    Call<Scheduler> save(@Body SchedulerDto schedulerDto);

    @GET("/scheduler/get-count/{idCloset}")
    Call<Long> getCountScheduler(@Path("idCloset") Long idCloset);


}
