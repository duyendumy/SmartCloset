package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.CategoryApater;
import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Category;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.retrofit.CategoryApi;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClosetActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView menu;
    private LinearLayout home, about, logout,manageCategory, manageUser;;
    private BottomNavigationView bottomNavigationView;

    private CardView itemCard;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_closet);
        recyclerView = findViewById(R.id.recyclerViewCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadCategory();
        handleBottomNavigation();
        handleMenu();
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
    private void populateListView(List<Category> categoryList){
        CategoryApater categoryApater = new CategoryApater(this,categoryList);
        recyclerView.setAdapter(categoryApater);

    }

    private void loadCategory(){
        RetrofitService retrofitService = new RetrofitService();
        CategoryApi categoryApi = retrofitService.getRetrofit().create(CategoryApi.class);
        categoryApi.getAllCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(ClosetActivity.this,"Failed to load categories!",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void handleBottomNavigation(){
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_calendar:
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_analysis:
                    startActivity(new Intent(getApplicationContext(), AnalysisActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_closet:
                    return true;
            }
            return false;
        });


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
                redirectActivity(ClosetActivity.this, MainActivity.class);
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(ClosetActivity.this, AboutActivity.class);
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(ClosetActivity.this, CategoryListActivity.class);
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(ClosetActivity.this, UserListActivity.class);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClosetActivity.this, "Logout",Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

    }

    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}