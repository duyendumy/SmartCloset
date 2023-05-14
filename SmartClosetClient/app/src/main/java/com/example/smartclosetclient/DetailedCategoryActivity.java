package com.example.smartclosetclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.SubCategory;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SubCategoryApi;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedCategoryActivity extends AppCompatActivity {
    FloatingActionButton fabAddSubCategory, fabDeleteCategory, fabUpdateCategory;
    ExtendedFloatingActionButton menu_fab;
    TextView addSubCategoryText, deleteCategoryText, updateCategoryText;
    Boolean isAllFabsVisible;

    TextView idCategory, nameCategory;

    Spinner spSubCategory;
    ImageView categoryImage;
    String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_category);
        fabAddSubCategory = findViewById(R.id.fabAddSubCategory);
        fabDeleteCategory = findViewById(R.id.fabDeleteCategory);
        fabUpdateCategory = findViewById(R.id.fabUpdateCategory);
        menu_fab = findViewById(R.id.menu_fab);
        addSubCategoryText = findViewById(R.id.add_subcategory_text);
        deleteCategoryText = findViewById(R.id.delete_subcategory_text);
        updateCategoryText  = findViewById(R.id.update_category_text);

        idCategory = findViewById(R.id.idCategory);
        nameCategory = findViewById(R.id.nameCategory);
        spSubCategory = findViewById(R.id.spSubCategory);
        categoryImage = findViewById(R.id.detailImage);

        fabAddSubCategory.setVisibility(View.GONE);
        fabDeleteCategory.setVisibility(View.GONE);
        fabUpdateCategory.setVisibility(View.GONE);
        addSubCategoryText.setVisibility(View.GONE);
        deleteCategoryText.setVisibility(View.GONE);
        updateCategoryText.setVisibility(View.GONE);
        isAllFabsVisible = false;
        menu_fab.shrink();
        menu_fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {
                            fabAddSubCategory.show();
                            fabDeleteCategory.show();
                            fabUpdateCategory.show();
                            addSubCategoryText.setVisibility(View.VISIBLE);
                            deleteCategoryText.setVisibility(View.VISIBLE);
                            updateCategoryText.setVisibility(View.VISIBLE);
                            menu_fab.extend();
                            isAllFabsVisible = true;
                        } else {

                            fabAddSubCategory.hide();
                            fabDeleteCategory.hide();
                            fabUpdateCategory.hide();
                            addSubCategoryText.setVisibility(View.GONE);
                            deleteCategoryText.setVisibility(View.GONE);
                            updateCategoryText.setVisibility(View.GONE);
                            menu_fab.shrink();
                            isAllFabsVisible = false;
                        }
                    }
                }
        );
        fabAddSubCategory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(DetailedCategoryActivity.this, "Add Sub Category", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DetailedCategoryActivity.this, AddSubCategoryActivity.class);
                        startActivity(intent);
                    }
                });
        fabDeleteCategory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailedCategoryActivity.this);
                        builder.setMessage("Do you really want to delete this category?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RetrofitService retrofitService = new RetrofitService();
                                CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
                                categoryApi.deleteCategory(Long.valueOf(idCategory.getText().toString()))
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                Toast.makeText(DetailedCategoryActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), CategoryListActivity.class));
                                                finish();
                                            }
                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Toast.makeText(DetailedCategoryActivity.this,"Delete failed!",Toast.LENGTH_SHORT).show();
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
        fabUpdateCategory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(DetailedCategoryActivity.this, "Update Category", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DetailedCategoryActivity.this, UpdateCategoryActivity.class);
                        String categoryId = idCategory.getText().toString();
                        String categoryName = nameCategory.getText().toString();
                        String categoryImagePath  = imagePath;
                        intent.putExtra("idCategory",categoryId);
                        intent.putExtra("nameCategory",categoryName);
                        intent.putExtra("imagePath",categoryImagePath);
                        startActivity(intent);
                    }
                });
        loadSubCategories();
        getCategoryDetail();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);}

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void populateSpinnerView(List<SubCategory> subCategoryList){
        List<String> nameSubCategoryList= new ArrayList<String>();
        for(int i = 0; i < subCategoryList.size(); i++) {
            nameSubCategoryList.add(subCategoryList.get(i).getName());
        }
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                nameSubCategoryList
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSubCategory.setAdapter(spinnerArrayAdapter);


    }
    private void loadSubCategories(){
        Intent intent = this.getIntent();
        Long categoryId = Long.valueOf(intent.getStringExtra("idCategory"));
        RetrofitService retrofitService = new RetrofitService();
        SubCategoryApi subCategoryApi = retrofitService.getRetrofit().create(SubCategoryApi.class);
        subCategoryApi.getSubCategoriesOfCategory(categoryId)
                .enqueue(new Callback<List<SubCategory>>() {
                    @Override
                    public void onResponse(Call<List<SubCategory>> call, Response<List<SubCategory>> response) {
                        populateSpinnerView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<SubCategory>> call, Throwable t) {
                        Toast.makeText(DetailedCategoryActivity.this,"Failed to load subcategory!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getCategoryDetail(){
        Intent intent = this.getIntent();
        if (intent != null) {
            String stringIdCategory = intent.getStringExtra("idCategory");
            String stringNameCategory =  intent.getStringExtra("categoryName");
            imagePath = intent.getStringExtra("categoryImage");
            idCategory.setText(stringIdCategory);
            nameCategory.setText(stringNameCategory);
            Glide.with(this).load(imagePath).into(categoryImage);
        }
    }
}