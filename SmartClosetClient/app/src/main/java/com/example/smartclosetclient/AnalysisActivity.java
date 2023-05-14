package com.example.smartclosetclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smartclosetclient.helper.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnalysisActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageView menu;
    private LinearLayout home, about, logout,manageCategory, manageUser;;
    private BottomNavigationView bottomNavigationView;

    private CardView outfitCard, stylistCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        drawerLayout = findViewById(R.id.drawerLayout);
        manageCategory = findViewById(R.id.manageCategory);
        manageUser = findViewById(R.id.manageUser);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        outfitCard = findViewById(R.id.outfitCard);
        outfitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalysisActivity.this, OutfitListActivity.class);
                startActivity(intent);
            }
        });
        stylistCard = findViewById(R.id.stylistCard);
        stylistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnalysisActivity.this, StylistActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_analysis);

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
                redirectActivity(AnalysisActivity.this, MainActivity.class);
            }
        });
        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(AnalysisActivity.this, AboutActivity.class);
            }
        });
        this.manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(AnalysisActivity.this, CategoryListActivity.class);
            }
        });
        this.manageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(AnalysisActivity.this, UserListActivity.class);
            }
        });
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnalysisActivity.this, "Logout",Toast.LENGTH_SHORT).show();
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