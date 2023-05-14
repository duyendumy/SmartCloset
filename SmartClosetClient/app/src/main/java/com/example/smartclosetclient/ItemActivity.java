package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private FloatingActionButton fabBtn;
    private TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_item);

        recyclerView = findViewById(R.id.item_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabBtn = findViewById(R.id.fab);
        nodata = findViewById(R.id.nodata);
        loadItems();
        addItem();
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
            Intent intent = new Intent(ItemActivity.this, ClosetActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem(){
        fabBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ItemActivity.this, AddItemActivity.class);
            startActivity(intent);
        });
    }

    private void loadItems(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        Intent intent = this.getIntent();
        Long categoryId = Long.valueOf(intent.getStringExtra("idCategory"));
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);
        itemApi.getItemsOfCategory(closetId,categoryId)
                .enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        if(response.body().isEmpty()){
                            nodata.setVisibility(View.VISIBLE);
                        }
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Toast.makeText(ItemActivity.this,"Failed to load users!",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Item> itemList){
        ItemAdapter itemAdapter = new ItemAdapter(this,itemList);
        recyclerView.setAdapter(itemAdapter);

    }
}