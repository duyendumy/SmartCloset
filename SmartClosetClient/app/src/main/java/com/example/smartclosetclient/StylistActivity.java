package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StylistActivity extends AppCompatActivity {

    private ImageView topImage, bottomImage;
    private TextView topItem, bottomItem;
    private Button suggestBtn;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        topImage = findViewById(R.id.topImage);
        bottomImage = findViewById(R.id.bottomImage);
        topItem = findViewById(R.id.topItem);
        bottomItem = findViewById(R.id.bottomItem);
        suggestBtn = findViewById(R.id.suggestBtn);
        items = new ArrayList<Item>();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);}
        suggestOutfit();
        suggestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.isEmpty()){
                    Toast.makeText(StylistActivity.this,"Please add items first",Toast.LENGTH_SHORT).show();
                }else
                { randomItem(items); }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void suggestOutfit(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);
        itemApi.getAllItems(closetId)
                .enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        items = response.body();
                        if(!items.isEmpty()){
                        randomItem(items);}
                        else{
                            Toast.makeText(StylistActivity.this,"Please add items first",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Toast.makeText(StylistActivity.this,"Failed to load items!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void randomItem(List<Item> itemList){
        List<Item> itemTop = new ArrayList<Item>();
        List<Item> itemBottom = new ArrayList<Item>();
        for(int i = 0; i < itemList.size();i++){
            if(itemList.get(i).getCategory().getId() == (long)4){
                itemTop.add(itemList.get(i));
            }
            else if (itemList.get(i).getCategory().getId() == (long)3) {
                itemBottom.add(itemList.get(i));
            }
        }
        int rndTop = new Random().nextInt(itemTop.size());
        int rndBottom = new Random().nextInt(itemBottom.size());
        Glide.with(StylistActivity.this).load(itemTop.get(rndTop).getImagePath()).into(topImage);
        Glide.with(StylistActivity.this).load(itemBottom.get(rndBottom).getImagePath()).into(bottomImage);
        topItem.setText(itemTop.get(rndTop).getSubCategory().getName());
        bottomItem.setText(itemBottom.get(rndBottom).getSubCategory().getName());

    }

}