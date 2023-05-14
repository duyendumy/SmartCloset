package com.example.smartclosetclient;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.OutfitApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SchedulerApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   DrawerLayout drawerLayout;
   ImageView menu;
   LinearLayout home, about, logout, manageCategory, manageUser;
   BottomNavigationView bottomNavigationView;

   TextView titleUsername, profileName, profileEmail, itemNumber, outfitsNo, notificationNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);

        titleUsername= findViewById(R.id.titleUsername);
        titleUsername.setText(SharedPrefManager.getInstance(this).getUsername());

        profileName= findViewById(R.id.profileName);
        profileName.setText(SharedPrefManager.getInstance(this).getUsername());

        profileEmail= findViewById(R.id.profileEmail);
        profileEmail.setText(SharedPrefManager.getInstance(this).getEmail());

        itemNumber= findViewById(R.id.itemNumber);
        outfitsNo= findViewById(R.id.outfitsNo);
        notificationNo= findViewById(R.id.notificationNo);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        handleBottomNavigation();
        handleMenu();
        loadProfile();
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

    public void loadProfile(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        RetrofitService retrofitService = new RetrofitService();
        ItemApi itemApi = retrofitService.getRetrofit().create(ItemApi.class);
        itemApi.getCountItem(closetId)
                .enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        itemNumber.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Failed to load count items!",Toast.LENGTH_SHORT).show();
                    }
                });
        OutfitApi outfitApi = retrofitService.getRetrofit().create(OutfitApi.class);
        outfitApi.getCountOutfit(closetId)
                .enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        outfitsNo.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Failed to load count outfits!",Toast.LENGTH_SHORT).show();
                    }
                });
        SchedulerApi schedulerApi = retrofitService.getRetrofit().create(SchedulerApi.class);
        schedulerApi.getCountScheduler(closetId)
                .enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        notificationNo.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Failed to load count scheduler!",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void handleBottomNavigation(){
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
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
                    startActivity(new Intent(getApplicationContext(), ClosetActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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
                recreate();
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               redirectActivity(MainActivity.this, AboutActivity.class);
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(MainActivity.this, CategoryListActivity.class);
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(MainActivity.this, UserListActivity.class);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Logout",Toast.LENGTH_SHORT).show();
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