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
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartclosetclient.dto.CategoryDto;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCategoryActivity extends AppCompatActivity {
    EditText updatedCategory;
    ImageView updatedCategoryImage;
    Uri uri;
    StorageReference storageReference;

    Button updateBtn, cancelBtn;

    String oldImageURL;
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        updatedCategory = findViewById(R.id.updatedCategory);
        updatedCategoryImage = findViewById(R.id.updatedCategoryImage);
        updateBtn= findViewById(R.id.updateButton);
        cancelBtn= findViewById(R.id.cancelButton);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updatedCategoryImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UpdateCategoryActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Intent intent = getIntent();
        updatedCategory.setText(intent.getStringExtra("nameCategory"));
        Glide.with(UpdateCategoryActivity.this).load(intent.getStringExtra("imagePath")).into(updatedCategoryImage);
        oldImageURL = intent.getStringExtra("imagePath");
        updatedCategoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageURL == null){
                    updateCategory();
                }else{
                    saveCategory();}
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void saveCategory(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateCategoryActivity.this);
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
                updateCategory();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
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
                            Toast.makeText(UpdateCategoryActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateCategoryActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void updateCategory(){
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        Intent intent = getIntent();
        Long categoryId = Long.valueOf(intent.getStringExtra("idCategory"));
        CategoryDto updatedCategoryDto = new CategoryDto();
        updatedCategoryDto.setName(updatedCategory.getText().toString().trim());
        updatedCategoryDto.setDisplay(1);
        if(imageURL != null){
        updatedCategoryDto.setImagePath(imageURL);}
        else{
            updatedCategoryDto.setImagePath(oldImageURL);}
        categoryApi.updateCategory(categoryId,updatedCategoryDto).enqueue(new Callback<CategoryDto>() {
            @Override
            public void onResponse(Call<CategoryDto> call, Response<CategoryDto> response) {
                Toast.makeText(UpdateCategoryActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateCategoryActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<CategoryDto> call, Throwable t) {
                Toast.makeText(UpdateCategoryActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}