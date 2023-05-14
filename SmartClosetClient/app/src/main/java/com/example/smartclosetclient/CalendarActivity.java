package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.ItemAdapter;
import com.example.smartclosetclient.adapter.SchedulerAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.Item;
import com.example.smartclosetclient.model.Scheduler;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.ItemApi;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.SchedulerApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageView menu;
    private LinearLayout home, about, logout, manageCategory, manageUser;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private TextView nodata;

    private FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        recyclerView = findViewById(R.id.recyclerViewScheduler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout = findViewById(R.id.drawerLayout);
        nodata = findViewById(R.id.nodata);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_calendar);
        fabBtn = findViewById(R.id.fab);
        handleBottomNavigation();
        handleMenu();
        loadScheduler();
        addScheduler();
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
    private void addScheduler(){
        fabBtn.setOnClickListener(view -> {
            Intent intent = new Intent(CalendarActivity.this, AddSchedulerActivity.class);
            startActivity(intent);
        });
    }
    private void loadScheduler(){
        Long closetId = SharedPrefManager.getInstance(this).getClosetId();
        RetrofitService retrofitService = new RetrofitService();
        SchedulerApi schedulerApi = retrofitService.getRetrofit().create(SchedulerApi.class);
        schedulerApi.getAllSchedulers(closetId)
                .enqueue(new Callback<List<Scheduler>>() {
                    @Override
                    public void onResponse(Call<List<Scheduler>> call, Response<List<Scheduler>> response) {
                        if(response.body().isEmpty()){
                            nodata.setVisibility(View.VISIBLE);
                        }
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Scheduler>> call, Throwable t) {
                        Toast.makeText(CalendarActivity.this,"Failed to load schedulers!",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Scheduler> schedulerList){
          SchedulerAdapter schedulerAdapter = new SchedulerAdapter(this,schedulerList);
          recyclerView.setAdapter(schedulerAdapter);

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
                redirectActivity(CalendarActivity.this, MainActivity.class);
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CalendarActivity.this, AboutActivity.class);
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CalendarActivity.this, CategoryListActivity.class);
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(CalendarActivity.this, UserListActivity.class);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "Logout",Toast.LENGTH_SHORT).show();
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