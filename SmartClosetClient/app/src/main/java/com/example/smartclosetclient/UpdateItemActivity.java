package com.example.smartclosetclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Season;
import com.example.smartclosetclient.model.SubCategory;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SeasonApi;
import com.example.smartclosetclient.retrofit.SubCategoryApi;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateItemActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    Spinner spinnerSubCategory;
    Spinner spinnerSeason;

    String imagePath;
    ImageView itemImage;


    Uri uri;
    Long selectedSubcategory;
    Long selectedSeason;
    EditText addPrice;
    EditText addBrand;
    EditText addColor;
    EditText addDescription;

    ImageView addImage;

    Button updateButton,cancelButton;



    Long selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        this.spinnerCategory = findViewById(R.id.addCategory);
        this.spinnerSubCategory =  findViewById(R.id.addSubCategory);
        this.spinnerSeason = findViewById(R.id.addSeason);
        this.updateButton = findViewById(R.id.updateButton);
        this.cancelButton = findViewById(R.id.cancelButton);
        this.addImage = findViewById(R.id.addImage);
        this.addPrice = findViewById(R.id.addPrice);
        this.addBrand = findViewById(R.id.addBrand);
        this.addColor = findViewById(R.id.addColor);
        this.addDescription = findViewById(R.id.addDescription);
        this.itemImage = findViewById(R.id.addImage);
        Intent intent = this.getIntent();
        imagePath = intent.getStringExtra("imagePath");
         Glide.with(this).load(imagePath).into(itemImage);

        loadCategory();
        loadSeason();
          addPrice.setText(intent.getStringExtra("price"));
          addBrand.setText(intent.getStringExtra("brand"));
          addColor.setText(intent.getStringExtra("color"));
          addDescription.setText(intent.getStringExtra("description"));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateItem();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void updateItem(){
        if (selectedCategory == null) {
            ((TextView) spinnerCategory.getSelectedView()).setError("None Category selected");
            return;
        }
        if (selectedSubcategory== null) {
            ((TextView) spinnerSubCategory.getSelectedView()).setError("None Sub Category selected");
            return;
        }
        if (selectedSeason== null) {
            ((TextView) spinnerSeason.getSelectedView()).setError("None Season selected");
            return;
        }
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);

        if (selectedCategory == null) {
            ((TextView) spinnerCategory.getSelectedView()).setError("None Category selected");
            return;
        }
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        Intent intent = this.getIntent();
        float price = Float.valueOf(addPrice.getText().toString());
        Long idItem = Long.valueOf(intent.getStringExtra("idItem"));
        String brand = String.valueOf(addBrand.getText());
        String color = String.valueOf(addColor.getText());
        String description = String.valueOf(addDescription.getText());

        ItemDto item = new ItemDto();
        item.setImagePath(imagePath);
        item.setCategoryId(selectedCategory);
        item.setSubCategoryId(selectedSubcategory);
        item.setSeasonId(selectedSeason);
        item.setColor(color);
        item.setDescription(description);
        item.setPrice(price);
        item.setBrandName(brand);
        item.setDisplay(1);
        item.setClosetId(closetId);


        itemApi.updateItem(idItem,item).enqueue(new Callback<ItemDto>() {
            @Override
            public void onResponse(Call<ItemDto> call, Response<ItemDto> response) {
                Toast.makeText(UpdateItemActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateItemActivity.this, ItemActivity.class);
                intent.putExtra("idCategory",response.body().getCategoryId().toString());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ItemDto> call, Throwable t) {
                Toast.makeText(UpdateItemActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void loadCategory(){
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        categoryApi.getAllCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        populateCategoryListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(UpdateItemActivity.this,"Failed to load categories!",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void populateCategoryListView(List<Category> categoryList){
        List<String> categoryNameList= new ArrayList<String>();
        categoryNameList.add("Select Category");
        for(int i = 0; i < categoryList.size(); i++) {
            categoryNameList.add(categoryList.get(i).getName());
        }
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categoryNameList
        ){
            @Override
            public boolean isEnabled(int position){
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }
            @Override
            public View getDropDownView(
                    int position, View convertView,
                    @NonNull ViewGroup parent) {

                // Get the item view
                View view = super.getDropDownView(
                        position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    textView.setTextColor(Color.GRAY);
                }
                else { textView.setTextColor(Color.BLACK); }
                return view;
            }
        };


        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );


        // Spinner on item selected listener
        this.spinnerCategory.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view,
                            int position, long id) {

                        // Get the spinner selected item text
                        String selectedItemText = (String) parent
                                .getItemAtPosition(position);

                        // If user change the default selection
                        // First item is disable and
                        // it is used for hint
                        if(position > 0){
                            // Notify the selected item text
                            selectedCategory = categoryList.get(position - 1).getId();
                            loadSubCategory(selectedCategory);
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Selected : "
                                            + selectedItemText,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {
                    }
                });


        // Finally, data bind the spinner object with adapter
        this.spinnerCategory.setAdapter(spinnerArrayAdapter);

    }
    private void populateSubCategoryListView(List<SubCategory> subCategoryList){
        List<String> subCategoryNameList= new ArrayList<String>();
        subCategoryNameList.add("Select Sub Category");
        for(int i = 0; i < subCategoryList.size(); i++) {
            subCategoryNameList.add(subCategoryList.get(i).getName());
        }

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                subCategoryNameList
        ){
            @Override
            public boolean isEnabled(int position){
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }
            @Override
            public View getDropDownView(
                    int position, View convertView,
                    @NonNull ViewGroup parent) {

                // Get the item view
                View view = super.getDropDownView(
                        position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    textView.setTextColor(Color.GRAY);
                }
                else { textView.setTextColor(Color.BLACK); }
                return view;
            }
        };


        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );


        // Spinner on item selected listener
        this.spinnerSubCategory.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view,
                            int position, long id) {

                        // Get the spinner selected item text
                        String selectedItemText = (String) parent
                                .getItemAtPosition(position);

                        // If user change the default selection
                        // First item is disable and
                        // it is used for hint
                        if(position > 0){
                            selectedSubcategory = subCategoryList.get(position - 1).getId();
                            // Notify the selected item text
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Selected : "
                                            + selectedItemText,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {
                    }
                });


        // Finally, data bind the spinner object with adapter
        this.spinnerSubCategory.setAdapter(spinnerArrayAdapter);

    }
    void loadSubCategory(Long selectedCategory){
        RetrofitService retrofitService = new RetrofitService();
        SubCategoryApi subCategoryApi = retrofitService.getRetrofit().create(SubCategoryApi.class);
        subCategoryApi.getSubCategoriesOfCategory(selectedCategory)
                .enqueue(new Callback<List<SubCategory>>() {
                    @Override
                    public void onResponse(Call<List<SubCategory>> call, Response<List<SubCategory>> response) {
                        populateSubCategoryListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<SubCategory>> call, Throwable t) {
                        Toast.makeText(UpdateItemActivity.this,"Failed to load subcategories!",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void populateSeasonListView(List<Season> seasonList){
        List<String> seasonNameList= new ArrayList<String>();
        seasonNameList.add("Select Season");
        for(int i = 0; i < seasonList.size(); i++) {
            seasonNameList.add(seasonList.get(i).getName());
        }
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                seasonNameList
        ){
            @Override
            public boolean isEnabled(int position){
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }
            @Override
            public View getDropDownView(
                    int position, View convertView,
                    @NonNull ViewGroup parent) {

                // Get the item view
                View view = super.getDropDownView(
                        position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    textView.setTextColor(Color.GRAY);
                }
                else { textView.setTextColor(Color.BLACK); }
                return view;
            }
        };


        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );


        // Spinner on item selected listener
        this.spinnerSeason.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view,
                            int position, long id) {

                        // Get the spinner selected item text
                        String selectedItemText = (String) parent
                                .getItemAtPosition(position);

                        // If user change the default selection
                        // First item is disable and
                        // it is used for hint
                        if(position > 0){
                            // Notify the selected item text
                            selectedSeason = seasonList.get(position - 1).getId();
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Selected : "
                                            + selectedItemText,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent) {
                    }
                });


        // Finally, data bind the spinner object with adapter
        this.spinnerSeason.setAdapter(spinnerArrayAdapter);


    }
    void loadSeason(){
        RetrofitService retrofitService = new RetrofitService();
        SeasonApi seasonApi = retrofitService.getRetrofit().create(SeasonApi.class);
        seasonApi.getAllSeasons()
                .enqueue(new Callback<List<Season>>() {
                    @Override
                    public void onResponse(Call<List<Season>> call, Response<List<Season>> response) {
                        populateSeasonListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Season>> call, Throwable t) {
                        Toast.makeText(UpdateItemActivity.this,"Failed to load seasons!",Toast.LENGTH_SHORT).show();
                    }
                });

    }

}