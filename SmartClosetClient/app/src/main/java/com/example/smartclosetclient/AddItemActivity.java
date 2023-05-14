package com.example.smartclosetclient;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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


import com.example.smartclosetclient.dto.ItemDto;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Season;
import com.example.smartclosetclient.model.SubCategory;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SeasonApi;
import com.example.smartclosetclient.retrofit.SubCategoryApi;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddItemActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    Spinner spinnerSubCategory;
    Spinner spinnerSeason;

    Long selectedCategory;

    String imageURL;

    Uri uri;

    Long selectedSubcategory;
    Long selectedSeason;
    EditText addPrice;
    EditText addBrand;
    EditText addColor;
    EditText addDescription;

    ImageView addImage;

    Button saveButton,cancelButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        this.spinnerCategory = findViewById(R.id.addCategory);
        this.spinnerSubCategory =  findViewById(R.id.addSubCategory);
        this.spinnerSeason = findViewById(R.id.addSeason);
        this.saveButton = findViewById(R.id.saveButton);
        this.cancelButton = findViewById(R.id.cancelButton);
        this.addImage = findViewById(R.id.addImage);
        this.addPrice = findViewById(R.id.addPrice);
        this.addBrand = findViewById(R.id.addBrand);
        this.addColor = findViewById(R.id.addColor);
        this.addDescription = findViewById(R.id.addDescription);
        loadCategory();
        loadSeason();
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            addImage.setImageURI(uri);
                        } else {
                            Toast.makeText(AddItemActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveItem();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void uploadImageToStorage(){
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Android Tutorials").child(currentDate)
                .setValue(imageURL).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddItemActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddItemActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadItem(){
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);

        if (selectedCategory == null) {
            ((TextView) spinnerCategory.getSelectedView()).setError("None Category selected");
            return;
        }

        float price = Float.valueOf(addPrice.getText().toString());
        String brand = String.valueOf(addBrand.getText());
        String color = String.valueOf(addColor.getText());
        String description = String.valueOf(addDescription.getText());


        Long closetId = SharedPrefManager.getInstance(this).getClosetId();

        ItemDto item = new ItemDto();
        item.setImagePath(imageURL);
        item.setCategoryId(selectedCategory);
        item.setSubCategoryId(selectedSubcategory);
        item.setSeasonId(selectedSeason);
        item.setColor(color);
        item.setDescription(description);
        item.setPrice(price);
        item.setBrandName(brand);
        item.setDisplay(1);
        item.setClosetId(closetId);


        itemApi.save(item).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Toast.makeText(AddItemActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddItemActivity.this, ItemActivity.class);
                intent.putExtra("idCategory", String.valueOf(selectedCategory));
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void saveItem(){
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
        if(addBrand.getText().toString().trim().equalsIgnoreCase("")){
            addBrand.setError("Please enter brand");
            return;
        }
        if(addColor.getText().toString().trim().equalsIgnoreCase("")){
            addColor.setError("Please enter color");
            return;
        }
        if(addPrice.getText().toString().trim().equalsIgnoreCase("")){
            addPrice.setError("Please enter price");
            return;
        }
        if(addDescription.getText().toString().trim().equalsIgnoreCase("")){
            addDescription.setError("Please enter description");
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadImageToStorage();
                dialog.dismiss();
                uploadItem();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
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
                        Toast.makeText(AddItemActivity.this,"Failed to load seasons!",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddItemActivity.this,"Failed to load categories!",Toast.LENGTH_SHORT).show();
                    }
                });

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
                        Toast.makeText(AddItemActivity.this,"Failed to load subcategories!",Toast.LENGTH_SHORT).show();
                    }
                });

    }


}