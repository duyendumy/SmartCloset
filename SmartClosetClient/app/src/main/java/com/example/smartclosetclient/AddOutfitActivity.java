package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.dto.OutfitDto;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.OutfitApi;
import com.example.smartclosetclient.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOutfitActivity extends AppCompatActivity {


    private Button btnSaveOutfit;
    private CardView cardTop;
    private CardView cardBottom;
    private  List<Item> bottomData;
    private  List<Item> topData;

    Item selectedTop;
    Item selectedBottom;
    private Boolean isTop;

    ImageView bottomImage;
    ImageView topImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_outfit);
        btnSaveOutfit = findViewById(R.id.btnSaveOutfit);
        cardTop = findViewById(R.id.recCardTop);
        cardBottom = findViewById(R.id.recCardBottom);
        bottomImage = findViewById(R.id.bottomImage);
        topImage = findViewById(R.id.topImage);
        bottomData = new ArrayList<Item>();
        topData = new ArrayList<Item>();
        List<Long> categoryIdTopList = new ArrayList<Long>(Arrays.asList(4L));
        List<Long> categoryIdBottomList = new ArrayList<Long>(Arrays.asList(3L));
        cardTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTop = true;
                clickOpenBottomFragment(categoryIdTopList);
            }
        });
        cardBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTop = false;
                clickOpenBottomFragment(categoryIdBottomList);
            }
        });
        saveOutfit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AddOutfitActivity.this, OutfitListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveOutfit(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        btnSaveOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTop == null){
                    Toast.makeText(AddOutfitActivity.this, "Please select top!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(selectedBottom == null){
                    Toast.makeText(AddOutfitActivity.this, "Please select bottom!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RetrofitService retrofitService = new RetrofitService();
                OutfitApi outfitApi = retrofitService.getRetrofit().create(OutfitApi.class);
                OutfitDto outfit = new OutfitDto();
                List<Long> itemIdList = new ArrayList<Long>();
                itemIdList.add(selectedBottom.getId());
                itemIdList.add(selectedTop.getId());
                outfit.setClosetId(closetId);
                outfit.setDisplay(1);
                outfit.setItemIdList(itemIdList);
                outfitApi.save(outfit).enqueue(new Callback<Outfit>() {
                    @Override
                    public void onResponse(Call<Outfit> call, Response<Outfit> response) {
                        Toast.makeText(AddOutfitActivity.this, "Save Outfit successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddOutfitActivity.this, OutfitListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Outfit> call, Throwable t) {
                        Toast.makeText(AddOutfitActivity.this, "Save Outfit failed!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

    }
    private void clickOpenBottomFragment( List<Long> categoryId){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);
        for(int i = 0; i < categoryId.size(); i++) {
            itemApi.getItemsOfCategory(closetId, categoryId.get(i))
                    .enqueue(new Callback<List<Item>>() {
                        @Override
                        public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                            if(isTop == true){
                                topData = response.body();}
                            else{
                                bottomData =  response.body();}
                        }

                        @Override
                        public void onFailure(Call<List<Item>> call, Throwable t) {
                            Toast.makeText(AddOutfitActivity.this, "Failed to load items!", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        if(isTop == true && !topData.isEmpty()){
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(topData, new IClickListener() {
            @Override
            public void clickItems(Item item) {
                selectedTop = item;
                Toast.makeText(AddOutfitActivity.this, item.getId().toString(),Toast.LENGTH_SHORT).show();
                Glide.with(AddOutfitActivity.this).load(item.getImagePath()).into(topImage);
            }
        });
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());}
        else if(isTop == false && !bottomData.isEmpty()){
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(bottomData, new IClickListener() {
                @Override
                public void clickItems(Item item) {
                    selectedBottom = item;
                    Toast.makeText(AddOutfitActivity.this, item.getId().toString(),Toast.LENGTH_SHORT).show();
                    Glide.with(AddOutfitActivity.this).load(item.getImagePath()).into(bottomImage);
                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        }


    }



}