package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.adapter.OutfitAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Outfit;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.OutfitApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutfitListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabBtn;
    private TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_list);
        recyclerView = findViewById(R.id.outfit_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        nodata = findViewById(R.id.nodata);
        fabBtn = findViewById(R.id.fab);
        fabBtn.setOnClickListener(view -> {
            Intent intent = new Intent(OutfitListActivity.this, AddOutfitActivity.class);
            startActivity(intent);
        });
        loadOutfits();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // finish();
            Intent intent = new Intent(OutfitListActivity.this, AnalysisActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadOutfits(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        RetrofitService retrofitService = new RetrofitService();
        OutfitApi outfitApi = retrofitService.getRetrofit().create(OutfitApi.class);
        outfitApi.getAllOutfits(closetId)
                .enqueue(new Callback<List<Outfit>>() {
                    @Override
                    public void onResponse(Call<List<Outfit>> call, Response<List<Outfit>> response) {
                        if(response.body().isEmpty()){
                            nodata.setVisibility(View.VISIBLE);
                        }
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Outfit>> call, Throwable t) {
                        Toast.makeText(OutfitListActivity.this,"Failed to load users!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void populateListView(List<Outfit> outfitList){
        OutfitAdapter outfitAdapter = new OutfitAdapter(this,outfitList);
        recyclerView.setAdapter(outfitAdapter);

    }
}