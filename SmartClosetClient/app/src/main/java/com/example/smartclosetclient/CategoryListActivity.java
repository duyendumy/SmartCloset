package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.adapter.ManagedCategoryApdater;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView menu;
    private LinearLayout home, about, logout, manageCategory, manageUser;
    private DrawerLayout drawerLayout;
    private FloatingActionButton fabBtn;
    private BottomNavigationView bottomNavigationView;
    private TextView nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);
        drawerLayout = findViewById(R.id.drawerLayout);
        nodata = findViewById(R.id.nodata);
        recyclerView = findViewById(R.id.category_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabBtn = findViewById(R.id.fab);
        loadCategories();

        handleMenu();
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryListActivity.this, AddCategoryActivity.class);
                startActivity(intent);
            }
        });
        visibleManagement();

    }
    public void visibleManagement(){
        LinearLayout manageUser = findViewById(R.id.manageUser);
        LinearLayout manageCategory = findViewById(R.id.manageCategory);
        String roleName = SharedPrefManager.getInstance(this).getRole();
        if(roleName.contains("ROLE_MANAGER")){
            manageUser.setVisibility(View.VISIBLE);
            manageCategory.setVisibility(View.VISIBLE);
        }
    }

    public void handleMenu(){
        this.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });

        this.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CategoryListActivity.this, MainActivity.class);
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CategoryListActivity.this, AboutActivity.class);
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CategoryListActivity.this, UserListActivity.class);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CategoryListActivity.this, "Logout",Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    private void loadCategories(){
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        categoryApi.getAllCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if(response.body().isEmpty()){
                            nodata.setVisibility(View.VISIBLE);}
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(CategoryListActivity.this,"Failed to load category!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void populateListView(List<Category> categoryList){
        ManagedCategoryApdater managedCategoryApdater = new ManagedCategoryApdater(this,categoryList);
        recyclerView.setAdapter(managedCategoryApdater);

    }
}