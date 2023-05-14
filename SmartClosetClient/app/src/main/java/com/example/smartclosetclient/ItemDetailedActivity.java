package com.example.smartclosetclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.UserApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDetailedActivity extends AppCompatActivity {

    TextView itemId, itemCategory,itemSubCategory, itemBrand, itemSeason, itemPrice, itemColor;
    ImageView itemImage;

    FloatingActionButton updateBtn;
    FloatingActionButton deleteBtn;
    Intent intent;
    String description;
    String idCategory;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detailed);
        itemId = findViewById(R.id.itemId);
        itemCategory = findViewById(R.id.itemCategory);
        itemSubCategory = findViewById(R.id.itemSubCategory);
        itemBrand  = findViewById(R.id.itemBrand);
        itemSeason = findViewById(R.id.itemSeason);
        itemPrice = findViewById(R.id.itemPrice);
        itemColor = findViewById(R.id.itemColor);
        itemImage = findViewById(R.id.detailImage);
        updateBtn = findViewById(R.id.fabUpdate);
        deleteBtn= findViewById(R.id.fabDelete);
        intent = this.getIntent();
        idCategory = intent.getStringExtra("idCategory");
        getItemDetail();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemDetailedActivity.this);
                builder.setMessage("Do you really want to delete this item?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RetrofitService retrofitService = new RetrofitService();
                        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);
                        itemApi.deleteItemById(Long.parseLong(intent.getStringExtra("idItem")))
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(ItemDetailedActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ItemDetailedActivity.this, ItemActivity.class);
                                        intent.putExtra("idCategory",idCategory);
                                        startActivity(new Intent(getApplicationContext(), ItemActivity.class));
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(ItemDetailedActivity.this,"Delete failed!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mDiaglog = builder.create();
                mDiaglog.show();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailedActivity.this, UpdateItemActivity.class);
                intent.putExtra("idItem",itemId.getText().toString());
                intent.putExtra("category",itemCategory.getText().toString());
                intent.putExtra("idCategory",idCategory);
                intent.putExtra("subCategory",itemSubCategory.getText().toString());
                intent.putExtra("brand",itemBrand.getText().toString());
                intent.putExtra("season",itemSeason.getText().toString());
                intent.putExtra("price",itemPrice.getText().toString());
                intent.putExtra("color",itemColor.getText().toString());
                intent.putExtra("description",description);
                intent.putExtra("imagePath", imagePath);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(ItemDetailedActivity.this, ItemActivity.class);
            intent.putExtra("idCategory", idCategory);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    private void getItemDetail(){
        if (intent != null) {
            String idItem = intent.getStringExtra("idItem");
            String category =  intent.getStringExtra("category");
            String subCategory =  intent.getStringExtra("subCategory");
            //idCategory = intent.getStringExtra("idCategory");
            String brand =  intent.getStringExtra("brand");
            String season =  intent.getStringExtra("season");
            String price =  intent.getStringExtra("price");
            String color =  intent.getStringExtra("color");
            imagePath = intent.getStringExtra("imagePath");
            description = intent.getStringExtra("description");
            itemId.setText(idItem);
            itemCategory.setText(category);
            itemSubCategory.setText(subCategory);
            itemBrand.setText(brand);
            itemSeason.setText(season);
            itemPrice.setText(price);
            itemColor.setText(color);
            Glide.with(this).load(imagePath).into(itemImage);
        }
    }

}