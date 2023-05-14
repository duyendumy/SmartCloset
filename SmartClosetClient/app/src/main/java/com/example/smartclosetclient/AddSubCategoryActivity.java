package com.example.smartclosetclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.dto.SubCategoryDto;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.SubCategory;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SubCategoryApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSubCategoryActivity extends AppCompatActivity {

    EditText addNameSubCategory;
    Spinner spinnerCategory;
    Button savebtn,cancelbtn;
    Long selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category);
        this.spinnerCategory = findViewById(R.id.addCategory);
        this.addNameSubCategory = findViewById(R.id.addNameSubCategory);
        this.savebtn =findViewById(R.id.saveButton);
        this.cancelbtn =findViewById(R.id.cancelButton);
        loadCategory();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubcategory();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private  void saveSubcategory(){
        RetrofitService retrofitService = new RetrofitService();
        SubCategoryApi subCategoryApi = retrofitService.getRetrofit().create(SubCategoryApi.class);

        if (selectedCategory == null) {
            ((TextView) spinnerCategory.getSelectedView()).setError("None Category selected");
            return;
        }


        String name = String.valueOf(addNameSubCategory.getText());
        SubCategoryDto subCategory = new SubCategoryDto();
        subCategory.setName(name);
        subCategory.setCategoryId(selectedCategory);


        subCategoryApi.save(subCategory).enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                Toast.makeText(AddSubCategoryActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddSubCategoryActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {
                Toast.makeText(AddSubCategoryActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadCategory(){
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
                        Toast.makeText(AddSubCategoryActivity.this,"Failed to load categories!",Toast.LENGTH_SHORT).show();
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
}