package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartclosetclient.adapter.UserAdapter;
import com.example.smartclosetclient.helper.SharedPrefManager;
import com.example.smartclosetclient.model.User;
import com.example.smartclosetclient.retrofit.RetrofitService;
import com.example.smartclosetclient.retrofit.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView menu;
    private LinearLayout home, about, logout,manageCategory, manageUser;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_list);
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);
        recyclerView = findViewById(R.id.userList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadUsers();
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
                redirectActivity(UserListActivity.this, MainActivity.class);
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(UserListActivity.this, AboutActivity.class);;
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(UserListActivity.this, CategoryListActivity.class);
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserListActivity.this, "Logout",Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void loadUsers(){
        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
        userApi.getAllUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(UserListActivity.this,"Failed to load users!",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<User> userList){
        UserAdapter userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

    }
}